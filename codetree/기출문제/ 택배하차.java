import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // 보드
        int[][] board = new int[n][n];

        // 상자 존재 여부
        boolean[][] isBox = new boolean[n][n];

        // 택배 투입
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int k = Integer.parseInt(input[0]);
            int h = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            int c = Integer.parseInt(input[3]);
            
            // 제일 하단부의 row
            int bot = h - 1;
            // 왼쪽
            int left = c - 1;
            // 오른쪽
            int right = left + w - 1;

            gravity(n, bot, left, right, h, k, board, isBox);
        }

        boolean isClear = false;
        while (!isClear) {
            // 택배 하차 좌측
            int leftBox = removeLeftBox(board, isBox, n);

            // 택배 하차 우측
            int rightBox = removeRightBox(board, isBox, n);

            if (leftBox != -1) {
                bw.write("" + leftBox);
                bw.newLine();
                bw.flush();
            }
            if (rightBox != -1) {
                bw.write("" + rightBox);
                bw.newLine();
                bw.flush();
            }
            if (leftBox == -1 && rightBox == -1) {
                isClear = true;
            }
        }

        bw.close();
    }

    // 중력
    private static void gravity(int n, int bot, int left, int right, int h, int k,
                                int[][] board, boolean[][] isBox) {
        // 상자의 c와 w, 즉 가로 길이를 기준으로 row를 1씩 증가시키며, n-1까지 or 상자에 닿을 때까지 내림
        for (int r = 1; r <= n; r++) {
            int nextBot = bot + r;
            boolean isBlocked = false;
            // 부딪힌다면, board에 넣고, isBox = true
            for (int m = left; m <= right; m++) {
                if (nextBot >= n || isBox[nextBot][m]) {
                    isBlocked = true;
                    break;
                }
            }
            if (isBlocked) {
                // 상에서 하까지
                for (int row = nextBot - h; row < nextBot; row++) {
                    // 좌에서 우까지
                    for (int col = left; col <= right; col++) {
                        board[row][col] = k;
                        isBox[row][col] = true;
                    }
                }
                break;
            }
        }
    }

    // 택배 하차 좌측
    private static int removeLeftBox(int[][] board, boolean[][] isBox, int n) {
        // 가능한 후보자 박스
        // [k, row, col]
        List<int[]> candidates = new ArrayList<>();
        List<int[]> finalCandidates = new ArrayList<>();
        boolean notCan = false;

        // 후보자 세팅
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 왼쪽부터 탐색하며, 박스 발견 시, 후보자에 추가
                if (isBox[i][j]) {
                    int[] can = new int[]{board[i][j], i, j};

                    boolean isExist = false;
                    for (int[] candidate : candidates) {
                        if (can[0] == candidate[0]) {
                            isExist = true;
                            break;
                        }
                    }

                    if (!isExist) {
                        candidates.add(can);
                    }

                    break;
                }
            }
        }

        int ltr = 0; int ltc = 0; // 좌상단
        int lbr = 0; int lbc = 0; // 좌하단

        // 후보자를 돌며, 좌측으로 부딪히는 택배는 후보자에서 제거
        for (int i = 0; i < candidates.size(); i++) {
            int[] cur = candidates.get(i);

            // 좌상단 세팅
            ltr = cur[1];
            ltc = cur[2];

            // 좌하단 세팅
            for (int j = 1; j <= n; j++) {
                int nextRow = ltr + j;
                if (nextRow >= n || board[nextRow][ltc] != cur[0]) {
                    lbr = nextRow - 1;
                    lbc = ltc;
                    break;
                }
            }

            notCan = false;
            // 좌측으로 한 칸씩 옮겨보며, 뺄 수 있다면 최종 후보자에 추가
            for (int j = 1; j <= n; j++) {
                int col = ltc - j;

                if (notCan) break;  

                // 끝자락 도달했으면 가능하기 때문에, 최종 후보자에 추가
                if (col < 0) {
                    finalCandidates.add(cur);
                    break;
                }

                for (int r = ltr; r <= lbr; r++) {
                    if (r >= n) continue;
                    if (isBox[r][col]) {
                        notCan = true;
                        break;
                    }
                }
            }
        }

        // 가능한 박스가 없다면 -1 반환
        if (finalCandidates.isEmpty()) return -1;

        // k 번호 기준 정렬
        finalCandidates.sort((a,b) -> {
            if (a[0] < b[0]) return -1;
            else if (a[0] > b[0]) return 1;
            else return 0;
        });

        // 후보자 중 번호가 최소인 box를 board, isBox에서 제거 후, 
        // 중력을 적용해 위의 박스를 내리고
        // k 반환
        int[] minBox = finalCandidates.get(0);
        int k = minBox[0]; // k
        int mbr = minBox[1]; // row
        int mbc = minBox[2]; // col

        // 박스 제거
        removeBox(board, isBox, k, n);

        // 빠진 박스의 위에 있는 박스들을 탐색
        List<Integer> upBox = new ArrayList<>();
        findUpBox(mbr, n, k, board, upBox);

        // 위의 박스들의 가로, 세로 탐색 (가장 아래부터)
        for (int i = 0; i< upBox.size(); i++) {
            int ubk = upBox.get(i);
            // [bot, left, right, h]
            int[] location = searchUpBoxLocation(upBox, board, n, ubk);

            int bot = location[0]; int left = location[1];
            int right = location[2]; int h = location[3];

            // 박스 제거
            removeBox(board, isBox, ubk, n);

            // 중력
            gravity(n, bot, left, right, h, ubk, board, isBox);
        }

        return k;
    }

    // 택배 하차 우측
    private static int removeRightBox(int[][] board, boolean[][] isBox, int n) {
        // 가능한 후보자 박스
        // [k, row, col]
        List<int[]> candidates = new ArrayList<>();
        List<int[]> finalCandidates = new ArrayList<>();
        boolean notCan = false;

        // 후보자 세팅
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                // 오른쪽부터 탐색하며, 박스 발견 시, 후보자에 추가
                if (isBox[i][j]) {
                    int[] can = new int[]{board[i][j], i, j};

                    boolean isExist = false;
                    for (int[] candidate : candidates) {
                        if (can[0] == candidate[0]) {
                            isExist = true;
                            break;
                        }
                    }

                    if (!isExist) {
                        candidates.add(can);
                    }

                    break;
                }
            }
        }

        int rtr = 0; int rtc = 0; // 우상단
        int rbr = 0; int rbc = 0; // 우하단

        // 후보자를 돌며, 우측으로 부딪히지 않는 택배는 최종 후보자로 추가
        for (int i = 0; i < candidates.size(); i++) {
            int[] cur = candidates.get(i);

            // 우상단 세팅
            rtr = cur[1];
            rtc = cur[2];

            // 우하단 세팅
            for (int j = 1; j <= n; j++) {
                if (rtr + j >= n || board[rtr + j][rtc] != cur[0]) {
                    rbr = rtr + j - 1;
                    rbc = rtc;
                    break;
                }
            }

            notCan = false;
            // 우측으로 한 칸씩 옮겨보며, 뺄 수 있다면 최종 후보자에 추가
            for (int j = 1; j <= n; j++) {
                int col = rtc + j;

                if (notCan) break;

                // 끝자락 도달했으면 가능하기 때문에, 최종 후보자에 추가
                if (col >= n) {
                    finalCandidates.add(cur);
                    break;
                }

                // 충돌 시, 해당 후보자는 패스
                for (int r = rtr; r <= rbr; r++) {
                    if (r >= n) continue;
                    if (isBox[r][col]) {
                        notCan = true;
                        break;
                    }
                }
            }
        }

        // 가능한 박스가 없다면 -1 반환
        if (finalCandidates.isEmpty()) return -1;

        // k 번호 기준 정렬
        finalCandidates.sort((a,b) -> {
            if (a[0] < b[0]) return -1;
            else if (a[0] > b[0]) return 1;
            else return 0;
        });

        // 후보자 중 번호가 최소인 box를 board, isBox에서 제거 후, 반환
        int[] minBox = finalCandidates.get(0);
        int k = minBox[0];
        int mbr = minBox[1];
        int mbc = minBox[2];

        // 박스 제거
        removeBox(board, isBox, k, n);

        // 빠진 박스의 위에 있는 박스들을 탐색
        List<Integer> upBox = new ArrayList<>();
        findUpBox(mbr, n, k, board, upBox);

        // 위의 박스들의 가로, 세로 탐색
        // bot, left, right, h
        for (int i = 0; i < upBox.size(); i++) {
            int ubk = upBox.get(i);
            // [bot, left, right, h]
            int[] location = searchUpBoxLocation(upBox, board, n, ubk);

            int bot = location[0]; int left = location[1];
            int right = location[2]; int h = location[3];

            // 박스 제거
            removeBox(board, isBox, ubk, n);

            // 중력
            gravity(n, bot, left, right, h, ubk, board, isBox);
        }

        return k;
    }

    private static void removeBox(int[][] board, boolean[][] isBox, int k, int n) {
        boolean found = false;
        int foundCol = -1;
        // 박스 제거
        for (int i = 0; i < n; i++) {
            if (found && board[i][foundCol] != k) break;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == k) {
                    board[i][j] = 0;
                    isBox[i][j] = false;
                    found = true;
                    foundCol = j;
                }
            }
        }
    }

    private static void findUpBox(int mbr, int n, int k, int[][] board, List<Integer> upBox) {
        for (int r = mbr - 1; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                int topK = board[r][c];
                if (topK != k && topK != 0 && !upBox.contains(topK)) {
                    upBox.add(topK);
                }
            }
        }
    }

    private static int[] searchUpBoxLocation(List<Integer> upBox, int[][] board, int n, int ubk) {
        int bot = 0; int left = 0; int right = 0; int h = 0;
        // 빠진 박스의 위에 있는 박스들을 탐색
        boolean found = false;
        boolean foundCol = false;
        boolean foundRow = false;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c <= n; c++) {
                // 이미 찾은 상태로, 오른쪽 경계거나, 번호가 다르다면
                if (found && (c >= n || board[r][c] != ubk)) {
                    right = c - 1;
                    foundCol = true;
                    break;
                }
                // 아직 찾지 못했고, 번호가 같다면
                if (c != n && !found && board[r][c] == ubk) {
                    h = r;
                    left = c;
                    found = true;
                }
            }
            if (foundCol) {
                // 좌상단을 한 칸씩 내려보며
                // bot을 탐색
                for (int row = h + 1; row <= n; row++) {
                    if (row >= n || board[row][left] != ubk) {
                        bot = row - 1;
                        h = bot - h + 1;
                        foundRow = true;
                        break;
                    }
                }
            }
            // bot까지 탐색 되었다면 break
            if (foundRow) break;
        }

        return new int[]{bot, left, right, h};
    }
}