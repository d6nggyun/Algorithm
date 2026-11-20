import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1];
        boolean[][] blocked = new boolean[n+1][m+1];
        
        for (int[] pud : puddles) {
            blocked[pud[1]][pud[0]] = true;
        }
        
        dp[1][1] = 1;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (r == 1 && c == 1) continue;
                if (blocked[r][c]) {
                    dp[r][c] = 0;
                    continue;
                }
                dp[r][c] = (dp[r-1][c] + dp[r][c-1]) % 1000000007;
            }
        }
        
        return dp[n][m];
    }
}