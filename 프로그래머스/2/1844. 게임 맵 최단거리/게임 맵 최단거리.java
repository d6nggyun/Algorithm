import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        // visited 2차원 배열 생성
        // queue 생성
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        
        // dr, dc = 상하좌우 판단용
        // d = 0 -> (-1, 0)
        // d = 1 -> (1, 0)
        // d = 2 -> (0, -1)
        // d = 3 -> (0, 1)
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        // queue에 현재 위치,거리 (0, 0, 1) 추가
        // visited[0][0] = true
        q.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        // while queue가 비어있지 않다면:
        while (!q.isEmpty()) {
            // 현재 위치 (r, c, dist)를 꺼냄
            // 만약 r, c가 도착점이면 dist를 반환
            int[] cur = q.poll();
            
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            
            if (r == n - 1 && c == m - 1) return dist;
            
            // 상하좌우 4방향 반복:
            for (int d = 0; d < 4; d++){
                // nr = r + dr[d]
                // nc = c + dc[d]
                int nr = r + dr[d];
                int nc = c + dc[d];
        
                // 범위 out -> continue
                // 벽 -> continue
                // 이미 방문 -> continue
                if (nr < 0 || nr >= n || nc < 0 || nc >=m) continue;
                if (maps[nr][nc] == 0) continue;
                if (visited[nr][nc]) continue;
        
                // visited[nr][nc] = true
                // queue에 (nr, nc, dist+1) 추가
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, dist+1});
            }
        }
        
        // return -1;
        return -1;
    }
}