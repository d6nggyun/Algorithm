import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        // 1 2 3
        // 1 3 2
        // 등 dfs로 탐색해보며, result가 더 크다면 change
        
        boolean[] visit = new boolean[dungeons.length];
        return dfs(k, dungeons, 0, visit);
    }
    
    private int dfs(int k, int[][] dungeons, int count, boolean[] visit) {
        int result = count;
        
        for (int i = 0; i < dungeons.length; i++) {
            int[] dun =  dungeons[i];
            int need = dun[0];
            int cost = dun[1];
            
            if (!visit[i] && k >= need) {
                visit[i] = true;
                result = Math.max(result, dfs(k - cost, dungeons, count + 1, visit));
                visit[i] = false;
            }
        }
        
        return result;
    }
}