import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 값 세팅
        List<List<Integer>> place = new ArrayList<>();
        List<List<Integer>> robot = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int l = Integer.parseInt(line[2]);

        // place 세팅
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");

            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(Integer.parseInt(line[j]));
            }
            place.add(row);
        }

        // robot 세팅
        for (int i = 0; i < k; i++) {
            line = br.readLine().split(" ");
            List<Integer> row = new ArrayList<>();
            row.add(Integer.parseInt(line[0]) - 1);
            row.add(Integer.parseInt(line[1]) - 1);
            robot.add(row);
        }

        // 테스트 케이스(L) 반복
        for (int i = 0; i < l; i++) {
            for (int r = 0; r < k; r++) {
                // 청소기 이동 및 청소(청소기 마다)
                    // bfs를 통해 가장 가까운 오염된 격자를 탐색
                    // 그 중, 행번호가 작고, 열번호가 작은 방향으로 이동
                moveRobot(place, robot, r, n);
            }

            // 청소
            for (int r = 0; r < k; r++) {
                int robotRow = robot.get(r).get(0);
                int robotCol = robot.get(r).get(1);
                
                int left = 0;
                int right = 0;
                int top = 0;
                int bot = 0;

                // 경계 밖이거나 -1일 경우 0으로 세팅
                if (robotCol-1 < 0 || place.get(robotRow).get(robotCol-1) == -1) left = 0;
                else left = place.get(robotRow).get(robotCol-1);
                
                if (robotCol+1 >= n || place.get(robotRow).get(robotCol+1) == -1) right = 0;
                else right = place.get(robotRow).get(robotCol+1);
                
                if (robotRow-1 < 0 || place.get(robotRow-1).get(robotCol) == -1) top = 0;
                else top = place.get(robotRow-1).get(robotCol);
                
                if (robotRow+1 >= n || place.get(robotRow+1).get(robotCol) == -1) bot = 0;
                else bot = place.get(robotRow+1).get(robotCol);

                // 최대 20 청소 가능하므로 넘어갈 경우 20으로 세팅
                if (left > 20) left = 20;
                if (right > 20) right = 20;
                if (top > 20) top = 20;
                if (bot > 20) bot = 20;
                int here = Math.min(place.get(robotRow).get(robotCol), 20);

                // 먼지량이 가장 큰 방향 판단
                int maxClean = 0;
                int where = 0;
                for (int mc = 0; mc < 4; mc++) {
                    // 오른쪽
                    if (mc == 0) {
                        if (maxClean < top + right + bot + here) {
                            maxClean = top + right + bot + here;
                            where = 0;
                        }
                    } else if (mc == 1) { // 아래쪽
                        if (maxClean < right + bot + left + here) {
                            maxClean = right + bot + left + here;
                            where = 1;
                        }
                    } else if (mc == 2) { // 왼쪽
                        if (maxClean < bot + left + top + here) {
                            maxClean = bot + left + top + here;
                            where = 2;
                        }
                    } else if (mc == 3) { // 위쪽
                        if (maxClean < left + top + right + here) {
                            maxClean = left + top + right + here;
                            where = 3;
                        }
                    }
                }

                // 로봇 청소기 자리 청소
                cleanHere(place, robotRow, robotCol);

                // where에 따라서 가장 큰 dust를 확인 후 해당 부위 청소
                if (where == 0) { // 오른쪽
                    // 위, 오른, 아래 청소
                    if (top != 0) cleanTop(place, robotRow, robotCol);
                    if (right != 0) cleanRight(place, robotRow, robotCol);
                    if (bot != 0) cleanBot(place, robotRow, robotCol);
                } else if (where == 1) { // 아래쪽
                    // 오른, 아래, 왼 청소
                    if (right != 0) cleanRight(place, robotRow, robotCol);
                    if (bot != 0) cleanBot(place, robotRow, robotCol);
                    if (left != 0) cleanLeft(place, robotRow, robotCol);
                } else if (where == 2) { // 왼쪽
                    // 아래, 왼, 위 청소
                    if (bot != 0) cleanBot(place, robotRow, robotCol);
                    if (left != 0) cleanLeft(place, robotRow, robotCol);
                    if (top != 0) cleanTop(place, robotRow, robotCol);
                } else { // 위쪽
                    // 왼, 위, 오른 청소
                    if (left != 0) cleanLeft(place, robotRow, robotCol);
                    if (top != 0) cleanTop(place, robotRow, robotCol);
                    if (right != 0) cleanRight(place, robotRow, robotCol);
                }
            }
            
            // 먼지 축적
            for (int j = 0; j < n; j++) {
                for (int m = 0; m < n; m++) {
                    // 격자의 dust가 0보다 크다면 5 추가
                    int dust = place.get(j).get(m);
                    if (dust > 0) place.get(j).set(m, dust + 5);
                }
            }

            // 먼지 확산
            // 임시 리스트에 확산된 후의 값들을 모두 저장한 후, 임시 리스트의 값들을 place에 옮김
            List<List<Integer>> tempPlace = new ArrayList<>();
            // j: 행 , m: 열
            for (int j = 0; j < n; j++) {
                List<Integer> tempRow = new ArrayList<>();
                for (int m = 0; m < n; m++) {
                    int divDust = 0;
                    // 깨끗한 격자라면
                    if (place.get(j).get(m) == 0) {
                        // 상하좌우의 합을 10으로 나눈값을 저장
                        // 조건: 각 경계를 넘지 않음, -1 (사물)이 아님일 경우에만 더함
                        // top
                        if (j-1 >= 0 && place.get(j-1).get(m) != -1) divDust += place.get(j-1).get(m);
                        // bot
                        if (j+1 < n && place.get(j+1).get(m) != -1) divDust += place.get(j+1).get(m);
                        // left
                        if (m-1 >= 0 && place.get(j).get(m-1) != -1) divDust += place.get(j).get(m-1);
                        // right
                        if (m+1 < n && place.get(j).get(m+1) != -1) divDust += place.get(j).get(m+1);

                        tempRow.add(divDust/10);
                    } else { // 오염된 격자나 사물일 경우 그대로 복사
                        tempRow.add(place.get(j).get(m));
                    }
                }
                tempPlace.add(tempRow);
            }
            place = tempPlace;

            int spot = 0;
            int dust = 0;
            // place의 총 먼지 출력
            for (int j = 0; j < n; j++) {
                for (int m = 0; m < n; m++) {
                    spot = place.get(j).get(m);
                    if (spot == -1) continue;
                    dust += spot;
                }
            }
            bw.write(dust + "");
            bw.newLine();
            bw.flush();
        }

        bw.close();
    }

    private static void cleanHere(List<List<Integer>> place, int robotRow, int robotCol) {
        int hereDust = place.get(robotRow).get(robotCol);
        if (hereDust - 20 < 0) hereDust = 0;
        else hereDust -= 20;
        place.get(robotRow).set(robotCol, hereDust);
    }

    private static void cleanTop(List<List<Integer>> place, int robotRow, int robotCol) {
        int topDust = place.get(robotRow - 1).get(robotCol);
        if (topDust - 20 < 0) topDust = 0;
        else topDust -= 20;
        place.get(robotRow - 1).set(robotCol, topDust);
    }

    private static void cleanRight(List<List<Integer>> place, int robotRow, int robotCol) {
        int rightDust = place.get(robotRow).get(robotCol+1);
        if (rightDust - 20 < 0) rightDust = 0;
        else rightDust -= 20;
        place.get(robotRow).set(robotCol+1, rightDust);
    }

    private static void cleanBot(List<List<Integer>> place, int robotRow, int robotCol) {
        int botDust = place.get(robotRow+1).get(robotCol);
        if (botDust - 20 < 0) botDust = 0;
        else botDust -= 20;
        place.get(robotRow+1).set(robotCol, botDust);
    }

    private static void cleanLeft(List<List<Integer>> place, int robotRow, int robotCol) {
        int leftDust = place.get(robotRow).get(robotCol-1);
        if (leftDust - 20 < 0) leftDust = 0;
        else leftDust -= 20;
        place.get(robotRow).set(robotCol-1, leftDust);
    }

    private static void moveRobot(
        List<List<Integer>> place, 
        List<List<Integer>> robot, 
        int idx, int n
        ) {
            int sr = robot.get(idx).get(0);
            int sc = robot.get(idx).get(1);

            // 현재 칸 오염 시 위치 유지
            if (place.get(sr).get(sc) > 0) return;

            // 다른 로봇들 위치
            boolean[][] blockedByRobot = new boolean[n][n];
            for (int i = 0; i < robot.size(); i++) {
                if (i == idx) continue;
                int rr = robot.get(i).get(0);
                int rc = robot.get(i).get(1);
                blockedByRobot[rr][rc] = true;
            }

            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[n][n];

            // 상하좌우
            int[] dr = new int[]{-1, 1, 0, 0};
            int[] dc = new int[]{0, 0, -1, 1};

            q.offer(new int[]{sr, sc});
            visited[sr][sc] = true;

            List<int[]> candidates = new ArrayList<>();
            boolean found = false;

            while (!q.isEmpty() && !found) {
                int size = q.size();
                candidates.clear();

                // BFS 레벨
                for (int bfs = 0; bfs < size; bfs++) {
                    int[] cur = q.poll();
                    int r = cur[0];
                    int c = cur[1];

                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (nr < 0 || nr >= n || nc < 0 || nc >=n) continue;
                        if (visited[nr][nc]) continue;
                        if (blockedByRobot[nr][nc]) continue;
                        if (place.get(nr).get(nc) == -1) continue;

                        visited[nr][nc] = true;

                        if (place.get(nr).get(nc) > 0) {
                            candidates.add(new int[]{nr, nc});
                            found = true;
                        } else {
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            // 이동 가능한 먼지 칸이 있으면 가장 위/왼쪽 칸으로 이동
            if (!candidates.isEmpty()) {
                candidates.sort((a,b) -> {
                    // 행이 다르면 행 기준 정렬
                    if (a[0] != b[0]) return a[0] - b[0];
                    // 행이 같으면 열 기준 정렬
                    else return a[1] - b[1];
                });

                int nr = candidates.get(0)[0];
                int nc = candidates.get(0)[1];

                robot.get(idx).set(0, nr);
                robot.get(idx).set(1, nc);
            }
    }
}