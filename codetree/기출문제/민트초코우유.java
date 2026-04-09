import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int t = Integer.parseInt(input[1]);

        String[][] food = new String[n][n];
        int[][] trust = new int[n][n];

        // 초기값 세팅
        // T: 민트, C: 초코, M: 우유
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < line.length(); j++) {
                food[i][j] = "" + line.charAt(j);
            }
        }
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < input.length; j++) {
                trust[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < t; i++) {
            // 아침 시간
            morning(trust, n);

            // 점심 시간
            List<Group> groups = launch(food, trust, n);

            // 저녁 시간
            List<Integer> totalTrust = dinner(food, trust, n, groups);

            // 출력
            for (int tt = 0; tt < totalTrust.size(); tt++) {
                bw.write("" + totalTrust.get(tt));

                if (tt != totalTrust.size() - 1) {
                    bw.write(" ");
                }
            }
            bw.newLine();
            bw.flush();
        }
    }

    private static void test(String[][] food, int[][] trust, int n) {
        System.out.println("food: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(food[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println("trust: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(trust[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }

    // 모든 학생 신앙심 1 추가
    private static void morning(int[][] trust, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trust[i][j] += 1;
            }
        }
    }

    static class Group {
        String food;
        // 그룹에 속한 학생들의 좌표 [row, col, trust]
        List<int[]> stu;
        // 대표자 [row, col, trust]
        int[] star;
        // 총 인원 수
        int total;

        public Group(String food) {
            this.food = food;
            this.stu = new ArrayList<>();
            this.star = new int[3];
            this.total = 0;
        }
    }

    // bfs를 통해 그룹을 만듬
    // 대표자의 trust를 (total - 1) 만큼 증가
    // 나머지의 trust를 -1 만큼 감소
    private static List<Group> launch(String[][] food, int[][] trust, int n) {
        boolean[][] visit = new boolean[n][n];
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        Queue<int[]> q = new ArrayDeque<>();
        List<Group> groups = new ArrayList<>();

        // bfs로 그룹 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) continue;

                visit[i][j] = true;
                q.offer(new int[]{i, j});

                Group g = new Group(food[i][j]);
                g.stu.add(new int[]{i, j, trust[i][j]});
                g.total = 1;

                while (!q.isEmpty()) {
                    int cur[] = q.poll();
                    
                    for (int d = 0; d < 4; d++) {
                        int nr = cur[0] + dr[d];
                        int nc = cur[1] + dc[d];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        if (visit[nr][nc]) continue;
                        if (!food[nr][nc].equals(food[cur[0]][cur[1]])) continue;

                        visit[nr][nc] = true;
                        q.offer(new int[]{nr, nc});

                        g.stu.add(new int[]{nr, nc, trust[nr][nc]});
                        g.total++;
                    }
                }

                groups.add(g);
            }
        }

        // 신앙심이 가장 큰
        // row가 가장 작은
        // col이 가장 작은
        for (int i = 0; i < groups.size(); i++) {
            Group g = groups.get(i);

            g.stu.sort((a,b) -> {
                if (a[2] != b[2]) return b[2] - a[2];
                else if (a[0] != b[0]) return a[0] - b[0];
                else return a[1] - b[1];
            });

            // 대표자 세팅 후, 대표자는 stu에서 삭제
            System.arraycopy(g.stu.get(0), 0, g.star, 0, 3);
            g.stu.remove(0);
        }

        // 각 그룹의 대표자의 trust를 (total - 1) 만큼 증가
        // 나머지의 trust를 -1 만큼 감소
        for (int i = 0; i < groups.size(); i++) {
            Group g = groups.get(i);

            // 대표자
            int[] starStu = g.star;
            int starStuRow = starStu[0];
            int starStuCol = starStu[1];
            trust[starStuRow][starStuCol] += g.total - 1;
            g.star[2] = trust[starStuRow][starStuCol];

            // 나머지
            for (int j = 0; j < g.stu.size(); j++) {
                int[] curStu = g.stu.get(j);
                int curStuRow = curStu[0];
                int curStuCol = curStu[1];
                trust[curStuRow][curStuCol] -= 1;
                g.stu.get(j)[2] = trust[curStuRow][curStuCol];
            }
        }

        return groups;
    }

    
    private static List<Integer> dinner(String[][] food, int[][] trust, int n, List<Group> groups) {
        // 전파는 그룹 중 음식이 단일 -> 이중 -> 삼중 순으로 진행
        // 같은 그룹 내에서는, 대표자 신앙심이 높은 -> 대표자 행 번호가 작은 -> 대표자 열 번호가 작은 순으로 진행
        groups.sort((a,b) -> {
            if (a.food.length() != b.food.length()) return a.food.length() - b.food.length();
            else {
                if (a.star[2] != b.star[2]) return b.star[2] - a.star[2];
                else if (a.star[0] != b.star[0]) return a.star[0] - b.star[0];
                else return a.star[1] - b.star[1];
            }
        });

        // 전파 당한 여부
        boolean[][] attack = new boolean[n][n];

        // 전파자 순서대로 진행
        for (int i = 0; i < groups.size(); i++) {
            Group g = groups.get(i);
            // 전파자
            int[] star = g.star;
            int row = star[0];
            int col = star[1];

            if (attack[row][col]) continue;

            // 전파자는 간절함(x)을 획득 : x = 신앙심 - 1
            // 신앙심 % 4 = 결과에 따라 진행 (0,1,2,3: 상,하,좌,우)
            int x = trust[row][col] - 1;
            int where = trust[row][col] % 4;
            trust[row][col] = 1;

            int[] dr = new int[]{-1, 1, 0, 0};
            int[] dc = new int[]{0, 0, -1, 1};

            // 전파
            for (int j = 0; j < n; j++) {
                row += dr[where];
                col += dc[where];

                boolean isAttack = false;

                // 전파 방향으로 한 칸씩 이동하며 전파, 격자 밖 or 간절함 0이면 전파 종료
                // 전파 대상과 음식이 같은 경우 continue
                if (row < 0 || row >= n || col < 0 || col >= n) break;
                if (x == 0) break;
                if (g.food.equals(food[row][col])) continue;

                // 다른 경우, 전파 진행
                // 전파 대상 신앙심이 y일때, x > y -> 강한 전파, x <= y -> 약한 전파
                int y = trust[row][col];
                // 강한 전파 : 전파 대상은 음식이 전파자와 같아짐
                    // 전파자는 간절함(x)가 y+1만큼 깎임
                    // 전파 대상의 신앙심 1 증가
                if (x > y) {
                    food[row][col] = g.food;
                    x -= (y + 1);
                    trust[row][col]++;
                    isAttack = true;
                }
                // 약한 전파 : 기존의 관심 음식에 전파자의 관심 기존 음식을 모두 합친 음식을 신봉 
                    //+ 전파자는 간절함이 0이 됨, 전파 대상의 신앙심은 x만큼 증가
                else {
                    Set<String> set = new HashSet<>();
                    // 전파 대상 기본 음식을 set에 넣음
                    for (int f = 0; f < food[row][col].length(); f++) {
                        set.add("" + food[row][col].charAt(f));
                    }
                    // 전파자의 기본 음식을 set에 넣음
                    for (int f = 0; f < g.food.length(); f++) {
                        set.add("" + g.food.charAt(f));
                    }

                    // 음식을 정렬하여 투입
                    food[row][col] = sortFood(set);

                    trust[row][col] += x;
                    x = 0;
                    
                    isAttack = true;
                }

                // 전파를 당한 학생은 당일에 전파하지 않음
                if (isAttack) {
                    attack[row][col] = true;
                }
            }
        }

        // 민트초코우유, 민트초코, 민트우유, 초코우유, 우유, 초코, 민트 순서대로 신앙심 총합 출력
        return showTrust(food, trust, n);
    }

    // T C M
    // 3: 민트초코우유 (TCM)
    // 2: 민트초코(TC), 민트우유(TM), 초코우유(CM)
    // 1: 우유, 초코, 민트 (M, C, T)
    private static String sortFood(Set<String> set) {
        if (set.size() == 3) return "TCM";
        else if (set.size() == 1) {
            if (set.contains("T")) return "T";
            else if (set.contains("C")) return "C";
            else return "M";
        }
        else {
            if (set.contains("T") && set.contains("C")) return "TC";
            else if (set.contains("T") && set.contains("M")) return "TM";
            else return "CM";
        }
    }

    // 총 신앙심 출력
    private static List<Integer> showTrust(String[][] food, int[][] trust, int n) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> totalTrust = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                map.put(food[r][c], map.getOrDefault(food[r][c], 0) + trust[r][c]);
            }
        }

        totalTrust.add(map.getOrDefault("TCM", 0));
        totalTrust.add(map.getOrDefault("TC", 0));
        totalTrust.add(map.getOrDefault("TM", 0));
        totalTrust.add(map.getOrDefault("CM", 0));
        totalTrust.add(map.getOrDefault("M", 0));
        totalTrust.add(map.getOrDefault("C", 0));
        totalTrust.add(map.getOrDefault("T", 0));

        return totalTrust;
    }
}