class Solution {
    int solution(int[][] land) {
        int n = land.length;
        
        for (int row = 1; row < n; row++) {
            for (int col = 0; col < 4; col++) {
                int best = 0;
                for (int prev = 0; prev < 4; prev++) {
                    if (prev != col) {
                        best = Math.max(best, land[row-1][prev]);
                    }
                }
                land[row][col] += best;
             }
        }
        
        int answer = 0;
        for (int col = 0; col < 4; col++) {
            answer = Math.max(answer, land[n-1][col]);
        }

        return answer;
    }
}