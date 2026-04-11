import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n, m
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // 메두사의 집, 공원
        input = br.readLine().split(" ");
        int sr = Integer.parseInt(input[0]);
        int sc = Integer.parseInt(input[1]);
        int er = Integer.parseInt(input[2]);
        int ec = Integer.parseInt(input[3]);

        // 전사
        // [row, col]
        List<int[]> fighter = new ArrayList<>();
        input = br.readLine().split(" ");
        for (int i = 0; i < m * 2; i+=2) {
            int row = Integer.parseInt(input[i]);
            int col = Integer.parseInt(input[i+1]);
            fighter.add(new int[]{row, col});
        }

        // 도로
        int[][] board = new int[n][n];
        // 도로는 0, 도로가 아닌 곳은 1
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 메두사의 이동 경로, 현재 위치 제거
        List<int[]> route = findShortest(board, n, sr, sc, er, ec);
        if (route.isEmpty()) {
            System.out.println(-1);
            return;
        }
        route.remove(0);

        // 메두사가 공원에 도착할 때 까지
        while ((sr != er) || (sc != ec)) {
            // 메두사의 이동
            int[] next = medusaMove(route, fighter);

            // 도착 여부 판단
            sr = next[0];
            sc = next[1];
            if (sr == er && sc == ec) {
                System.out.println(0);
                break;
            }

            // 돌 여부
            boolean[][] isStone = new boolean[n][n];
            boolean[][] isSee = new boolean[n][n];

            // 메두사의 시선
            int stone = medusaSee(n, fighter, sr, sc, isStone, isSee);

            // 전사들의 이동, 공격
            int[] moveAndAttack = moveFighter(fighter, n, sr, sc, isSee, isStone);

            // 결과 출력
            System.out.println(moveAndAttack[0] + " " + stone + " " + moveAndAttack[1]);
        }
    }
    
    // 맨헤튼 거리
    private static int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // bfs를 이용해 최단 경로를 탐색
    private static List<int[]> findShortest(int[][] board, int n, int sr, int sc, int er, int ec) {
        
        // bfs
        int[][][] parent = new int[n][n][2];
        
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                parent[i][j][0] = -1;
                parent[i][j][1] = -1;
            }
        }

        // [row, col]
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][n];

        visit[sr][sc] = true;
        q.offer(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int cur[] = q.poll();

            if (cur[0] == er && cur[1] == ec) break;
            
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visit[nr][nc]) continue;
                if (board[nr][nc] == 1) continue;

                visit[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                parent[nr][nc][0] = cur[0];
                parent[nr][nc][1] = cur[1];
            }
        }

        List<int[]> route = new ArrayList<>();

        if (!visit[er][ec]) return route;

        int cr = er;
        int cc = ec;

        while (!(cr == -1 && cc == -1)) {
            route.add(new int[]{cr, cc});

            int pr = parent[cr][cc][0];
            int pc = parent[cr][cc][1];

            cr = pr;
            cc = pc;
        }

        Collections.reverse(route);
        return route;
    }
    
    private static int[] medusaMove(List<int[]> route, List<int[]> fighter) {
        // 한 칸 이동
        int[] next = route.get(0);
        route.remove(0);

        // 전사가 있을 경우, 전사를 제거
        for (int i = fighter.size() - 1; i >= 0; i--) {
            int fr = fighter.get(i)[0];
            int fc = fighter.get(i)[1];
            if (next[0] == fr && next[1] == fc){
                fighter.remove(i);
            }
        }

        return next;
    }

    // 특정 칸에 전사가 몇 명 있는지
    private static int countFighter(List<int[]> fighters, int r, int c) {
        int cnt = 0;
        for (int[] f : fighters) {
            if (f[0] == r && f[1] == c) cnt++;
        }
        return cnt;
    }

    // 메두사의 시선
    // 네 방향 중 가장 많은 전사를 실제로 볼 수 있는 방향을 선택
    // 실제 보이는 칸만 isSee에 기록하고, 그 칸의 전사들을 돌로 만든다
    private static int medusaSee(int n, List<int[]> fighters, int mr, int mc, 
                                boolean[][] isStone, boolean[][] isSee) {

        int bestCount = -1;
        boolean[][] bestVisible = null;

        // 상, 하, 좌, 우 순서대로 확인
        // 동일 개수면 먼저 나온 방향이 우선되므로 > 일 때만 갱신
        for (int dir = 0; dir < 4; dir++) {
            boolean[][] visible = buildVisible(n, fighters, mr, mc, dir);

            int cnt = 0;
            for (int[] f : fighters) {
                if (visible[f[0]][f[1]]) cnt++;
            }

            if (cnt > bestCount) {
                bestCount = cnt;
                bestVisible = visible;
            }
        }

        // 선택된 방향의 실제 시야를 isSee에 복사
        for (int i = 0; i < n; i++) {
            System.arraycopy(bestVisible[i], 0, isSee[i], 0, n);
        }
        
        // 실제 시야 안에 있는 전사들은 모두 돌
        int stoneCnt = 0;
        for (int[] f : fighters) {
            if (isSee[f[0]][f[1]]) {
                isStone[f[0]][f[1]] = true;
                stoneCnt++;
            }
        }

        return stoneCnt;
    }
    
    // dir 방향으로 메두사가 실제로 보는 칸들을 계산
    // 1) 기본 부채꼴 범위를 앞에서부터 훑는다
    // 2) 아직 가려지지 않은 칸만 visible 처리
    // 3) 그 칸에 전사가 있으면 그 뒤쪽 그림자 영역을 blocked 처리
    private static boolean[][] buildVisible(int n, List<int[]> fighters, int mr, int mc, int dir) {
        boolean[][] visible = new boolean[n][n];
        boolean[][] blocked = new boolean[n][n];

        if (dir == 0) { // 상
            for (int r = mr - 1; r >= 0; r--) {
                int dist = mr - r;
                int minC = Math.max(0, mc - dist);
                int maxC = Math.min(n - 1, mc + dist);

                for (int c = minC; c <= maxC; c++) {
                    if (blocked[r][c]) continue;

                    visible[r][c] = true;

                    if (countFighter(fighters, r, c) > 0) {
                        markShadow(blocked, n, mr, mc, r, c, dir);
                    }
                }
            }
        } else if (dir == 1) { // 하
            for (int r = mr + 1; r < n; r++) {
                int dist = r - mr;
                int minC = Math.max(0, mc - dist);
                int maxC = Math.min(n - 1, mc + dist);

                for (int c = minC; c <= maxC; c++) {
                    if (blocked[r][c]) continue;

                    visible[r][c] = true;

                    if (countFighter(fighters, r, c) > 0) {
                        markShadow(blocked, n, mr, mc, r, c, dir);
                    }
                }
            }
        } else if (dir == 2) { // 좌
            for (int c = mc - 1; c >= 0; c--) {
                int dist = mc - c;
                int minR = Math.max(0, mr - dist);
                int maxR = Math.min(n - 1, mr + dist);

                for (int r = minR; r <= maxR; r++) {
                    if (blocked[r][c]) continue;

                    visible[r][c] = true;

                    if (countFighter(fighters, r, c) > 0) {
                        markShadow(blocked, n, mr, mc, r, c, dir);
                    }
                }
            }
        } else { // 우
            for (int c = mc + 1; c < n; c++) {
                int dist = c - mc;
                int minR = Math.max(0, mr - dist);
                int maxR = Math.min(n - 1, mr + dist);

                for (int r = minR; r <= maxR; r++) {
                    if (blocked[r][c]) continue;

                    visible[r][c] = true;

                    if (countFighter(fighters, r, c) > 0) {
                        markShadow(blocked, n, mr, mc, r, c, dir);
                    }
                }
            }
        }

        return visible;
    }

    // 특정 전사(fr, fc)가 메두사 시야에서 뒤쪽을 가리는 영역을 blocked 처리
    // 전사의 위치가 메두사 기준 왼쪽/정면/오른쪽(또는 위/정면/아래) 어디냐에 따라
    // 뒤로 갈수록 퍼지는 그림자 영역이 달라진다
    private static void markShadow(boolean[][] blocked, int n, int mr, int mc, int fr, int fc, int dir) {
        if (dir == 0) { // 상
            for (int r = fr - 1; r >= 0; r--) {
                int d = fr - r;

                if (fc < mc) { // 왼쪽 위 쪽에 있는 전사
                    int minC = Math.max(0, fc - d);
                    int maxC = fc;
                    for (int c = minC; c <= maxC; c++) blocked[r][c] = true;
                } else if (fc == mc) { // 정면
                    blocked[r][fc] = true;
                } else { // 오른쪽 위 쪽에 있는 전사
                    int minC = fc;
                    int maxC = Math.min(n - 1, fc + d);
                    for (int c = minC; c <= maxC; c++) blocked[r][c] = true;
                }
            }
        } else if (dir == 1) { // 하
            for (int r = fr + 1; r < n; r++) {
                int d = r - fr;

                if (fc < mc) { // 왼쪽 아래 쪽
                    int minC = Math.max(0, fc - d);
                    int maxC = fc;
                    for (int c = minC; c <= maxC; c++) blocked[r][c] = true;
                } else if (fc == mc) { // 정면
                    blocked[r][fc] = true;
                } else { // 오른쪽 아래 쪽
                    int minC = fc;
                    int maxC = Math.min(n - 1, fc + d);
                    for (int c = minC; c <= maxC; c++) blocked[r][c] = true;
                }
            }
        } else if (dir == 2) { // 좌
            for (int c = fc - 1; c >= 0; c--) {
                int d = fc - c;

                if (fr < mr) { // 왼쪽 위 쪽
                    int minR = Math.max(0, fr - d);
                    int maxR = fr;
                    for (int r = minR; r <= maxR; r++) blocked[r][c] = true;
                } else if (fr == mr) { // 정면
                    blocked[fr][c] = true;
                } else { // 왼쪽 아래 쪽
                    int minR = fr;
                    int maxR = Math.min(n - 1, fr + d);
                    for (int r = minR; r <= maxR; r++) blocked[r][c] = true;
                }
            }
        } else { // 우
            for (int c = fc + 1; c < n; c++) {
                int d = c - fc;

                if (fr < mr) { // 오른쪽 위 쪽
                    int minR = Math.max(0, fr - d);
                    int maxR = fr;
                    for (int r = minR; r <= maxR; r++) blocked[r][c] = true;
                } else if (fr == mr) { // 정면
                    blocked[fr][c] = true;
                } else { // 오른쪽 아래 쪽
                    int minR = fr;
                    int maxR = Math.min(n - 1, fr + d);
                    for (int r = minR; r <= maxR; r++) blocked[r][c] = true;
                }
            }
        }
    }

    // 전사들의 이동
    // 도로 비도로 구분 x
    private static int[] moveFighter(List<int[]> fighters, int n, int sr, int sc, boolean[][] isSee, boolean[][] isStone) {
        int moveSum = 0;
        int attackCnt = 0;

        // 상하좌우
        int[] firstOrder = new int[]{0, 1, 2, 3};
        // 좌우상하
        int[] secondOrder = new int[]{2, 3, 0, 1};

        // 전사들을 돌며
        for (int i = fighters.size() - 1; i >= 0; i--) {
            int[] fighter = fighters.get(i);

            // 돌이 된 전사는 이동 x
            if (isStone[fighter[0]][fighter[1]]) continue;

            // 첫 번째 이동
            // 우선순위: 상하좌우
            // 격자 바깥 or 메두사 시야 : break
            moveSum += moveOneStep(fighter, n, sr, sc, isSee, firstOrder);

            // 두 번째 이동
            // 우선순위: 좌우상하
            // 격자 바깥 or 메두사 시야 : break
            moveSum += moveOneStep(fighter, n, sr, sc, isSee, secondOrder);

            // 메두사와 같은 칸이면 공격 후, 사라짐
            if (fighter[0] == sr && fighter[1] == sc) {
                attackCnt++;
                fighters.remove(i);
            }
        }

        return new int[]{moveSum, attackCnt};
    }

    private static int moveOneStep(int[] fighter, int n, int sr,int sc, boolean[][] isSee, int[] order) {
        int r = fighter[0];
        int c = fighter[1];

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int curDist = dist(r, c, sr, sc);

        for (int d : order) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (isSee[nr][nc]) continue;

            int nextDist = dist(nr, nc, sr, sc);
            if (nextDist < curDist) {
                fighter[0] = nr;
                fighter[1] = nc;
                return 1;
            }
        }

        return 0;
    }
}