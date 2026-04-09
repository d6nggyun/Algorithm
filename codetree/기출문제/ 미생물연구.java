import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int q = Integer.parseInt(input[1]);
        int[][] board = new int[n][n];

        for (int i = 0; i < q; i++) {
            input = br.readLine().split(" ");
            int lbr = Integer.parseInt(input[0]);
            int lbc = Integer.parseInt(input[1]);
            int rtr = Integer.parseInt(input[2]);
            int rtc = Integer.parseInt(input[3]);

            // 미생물 투입
            inputCell(i+1, board, n, lbr, lbc, rtr, rtc);

            // 투입 후, 잘린 미생물 존재 시, 삭제
            deleteCellIfCut(board, n);

            // 배양 용기 이동
            moveCell(board, n);
            
            // 실험 결과 기록
            int record = recordCellResult(board, n);

            bw.write("" + record);
            bw.newLine();
            bw.flush();
        }
    }
    
    // 그림의 격자에 맞춰 위아래가 반전된 리스트에 값을 세팅
    // 값은 해당 미생물의 번호로 세팅
    private static void inputCell(int i, int[][] board, int n, int lbr, int lbc, int rtr, int rtc) {
        // ex) (2, 2) ~ (5, 6)
        // r = 2 ~ 5
        // c = 2 ~ 4
        for (int r = n - rtc; r < n - lbc; r++) {
            for (int c = lbr; c < rtr; c++) {
                board[r][c] = i;
            }
        }
    }

    // 현재 보드에 남아있는 미생물별로 총 개수를 셈
    // 각 미생물의 칸 하나를 기준으로 BFS/DFS로 연결된 칸 개수를 셈
    // 총 개수 != 연결된 칸 개수면 잘린 미생물
    private static void deleteCellIfCut(int[][] board, int n) {
        Map<Integer, Integer> totalCount = new HashMap<>();

        // 미생물 별 총 개수 count
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = board[i][j];
                if (k != 0) {
                    totalCount.put(k, totalCount.getOrDefault(k, 0) + 1);
                }
            }
        }

        // dfs
        boolean[][] visit = new boolean[n][n];
        Set<Integer> toDelete = new HashSet<>();
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        // 미생물 별 첫 번째 덩어리 크기 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = board[i][j];
                int total = 0;

                if (k == 0 || visit[i][j]) continue;
                if (toDelete.contains(k)) continue;

                // [row, col]
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                visit[i][j] = true;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int r = cur[0]; int c = cur[1];
                    total++;

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        if (visit[nr][nc]) continue;
                        if (board[nr][nc] != k) continue;

                        visit[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }

                if (totalCount.get(k) != total) {
                    toDelete.add(k);
                }
            }
        }

        removeCell(board, n, toDelete);
    }

    private static void removeCell(int[][] board, int n, Set<Integer> toDelete) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (toDelete.contains(board[i][j])) {
                    board[i][j] = 0;
                }
            }
        }
    }

    // 미생물 무리 정보
    static class Group {
        int id;
        int area;
        List<int[]> cells = new ArrayList<>();

        int minCol;
        int maxRow;

        Group(int id) {
            this.id = id;
        }
    }

    // 그룹 추출
    private static List<Group> extractGroups(int[][] board, int n) {
        boolean[][] visit = new boolean[n][n];
        List<Group> groups = new ArrayList<>();

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int id = board[i][j];
                if (id == 0 || visit[i][j]) continue;

                Group g = new Group(id);
                g.minCol = j;
                g.maxRow = i;

                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                visit[i][j] = true;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int r = cur[0];
                    int c = cur[1];

                    g.cells.add(new int[]{r, c});
                    g.area++;

                    g.minCol = Math.min(g.minCol, c);
                    g.maxRow = Math.max(g.maxRow, r);

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        if (visit[nr][nc]) continue;
                        if (board[nr][nc] != id) continue;

                        visit[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }

                groups.add(g);
            }
        }

        return groups;
    }

    // 미생물 무리를 탐색
    // 미생물을 왼쪽과, 아래로 최대한 땡겨서 배치
    private static void moveCell(int[][] board, int n) {
        List<Group> groups = extractGroups(board, n);

        // area 기준 내림차순, id 기준 오름차순
        groups.sort((a,b) -> {
            if (a.area != b.area) return b.area - a.area;
            return a.id - b.id;
        });

        int[][] nextBoard = new int[n][n];

        for (Group g : groups) {
            boolean placed = false;

            // x 좌표 작은 위치 우선
            for (int c = 0; c < n && !placed; c++) {
                // y 좌표 작은 위치 우선
                for (int r = n - 1 ; r >= 0; r--) {
                    if (canPlace(g, r, c, nextBoard, n)) {
                        placeGroup(g, r, c, nextBoard);
                        placed = true;
                        break;
                    }
                }
            }
            
            // 배치 불가 시, 사라짐
        }

        // 배열 복사
        for (int i = 0; i < n; i++) {
            System.arraycopy(nextBoard[i], 0, board[i], 0, n);
        }
    }

    // 배치 가능 여부 판단
    private static boolean canPlace(Group g, int ancR, int ancC, int[][] nextBoard, int n) {
        for (int[] cell : g.cells) {
            int r = cell[0];
            int c = cell[1];

            int nr = ancR + (r - g.maxRow);
            int nc = ancC + (c - g.minCol);

            if (nr < 0 || nr >=n || nc < 0 || nc >= n) return false;
            if (nextBoard[nr][nc] != 0) return false;
        }
        return true;
    }

    // 그룹을 배치
    private static void placeGroup(Group g, int ancR, int ancC, int[][] nextBoard) {
        for (int[] cell : g.cells) {
            int r = cell[0];
            int c = cell[1];

            int nr = ancR + (r - g.maxRow);
            int nc = ancC + (c - g.minCol);

            nextBoard[nr][nc] = g.id;
        }
    }

    // 실험 결과 계산
    private static int recordCellResult(int[][] board, int n) {
        int record = 0;

        // 미생물 별 총 개수 count
        Map<Integer, Integer> totalCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = board[i][j];
                if (k != 0) {
                    totalCount.put(k, totalCount.getOrDefault(k, 0) + 1);
                }
            }
        }

        // dfs로 근접해 있는 cell 짝을 List에 담음
        boolean[][] visit = new boolean[n][n];
        // [minCell, maxCell]
        Set<String> nearCell = new HashSet<>();
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int k = board[i][j];
                int total = 0;

                if (k == 0 || visit[i][j]) continue;

                // [row, col]
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                visit[i][j] = true;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int r = cur[0]; int c = cur[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        if (visit[nr][nc] || board[nr][nc] == 0) continue;
                        if (board[nr][nc] != k) {
                            int minCell = Math.min(k, board[nr][nc]);
                            int maxCell = Math.max(k, board[nr][nc]);

                            nearCell.add(minCell + "," + maxCell);
                            continue;
                        }

                        visit[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        // 짝을 곱해서 더함
        for (String cur : nearCell) {
            int minCellSize = totalCount.get(Integer.parseInt(cur.split(",")[0]));
            int maxCellSize = totalCount.get(Integer.parseInt(cur.split(",")[1]));
            record += minCellSize * maxCellSize;
        }

        return record;
    }
}