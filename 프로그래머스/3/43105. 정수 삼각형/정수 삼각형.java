class Solution {
    public int solution(int[][] triangle) {
        // 특정 숫자에 대해, 그 전의 값을 돌며 합이 최대가 되는 경우를 구함
        for (int r = 1; r < triangle.length; r++) {
            for (int c = 0; c < triangle[r].length; c++) {
                int temp = Integer.MIN_VALUE;
                
                // 2,1 -> 1,0 / 1,1
                // 1, 1 -> 0, 0
                
                // r-1,c-1 / r-1,c
                if (c - 1 >= 0) {
                    if (triangle[r][c] + triangle[r-1][c-1] > temp) {
                        temp = triangle[r][c] + triangle[r-1][c-1];
                    }
                }
                if (c <= triangle[r-1].length - 1) {
                    if (triangle[r][c] + triangle[r-1][c] > temp) {
                        temp = triangle[r][c] + triangle[r-1][c];
                    }
                }
                
                triangle[r][c] = temp;
            }
        }
        
        int answer = Integer.MIN_VALUE;
        int len = triangle.length;
        for (int i = 0; i < triangle[len-1].length; i++) {
            if (triangle[len-1][i] > answer) {
                answer = triangle[len-1][i];
            }
        }
        
        return answer;
    }
}