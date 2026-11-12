import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        // 모든 노드를 돌며
        // 이미 네트워크가 있는 (방문한) 노드면 패스
        // 방문하지 않은 노드는 도달 가능한 노드들은 방문으로 표시하고 answer++
        boolean[] network = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (network[i]) continue;
            else answer++;
            
            Queue<int[]> que = new ArrayDeque<>();
            boolean[] visit = new boolean[n];
            
            int[] com = computers[i];
            
            que.offer(com);
            
            while(!que.isEmpty()) {
                com = que.poll();
                
                for (int m = 0; m < n; m++) {
                    if (com[m] == 1) {
                        if (visit[m]) continue;
                        que.offer(computers[m]);
                        network[m] = true;
                        visit[m] = true;
                    }
                }
            }
        }
        
        return answer;
    }
}