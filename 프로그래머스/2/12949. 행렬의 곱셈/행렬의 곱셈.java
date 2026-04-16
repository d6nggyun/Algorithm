class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        for (int j = 0; j < arr2[0].length; j++) {
            for (int i = 0; i < arr1.length; i++) {
                int[] a = arr1[i]; // row
                int[] b = new int[arr2.length]; // col
                
                for (int c = 0; c < arr2.length; c++) {
                    b[c] = arr2[c][j];
                }
                
                answer[i][j] = multipleArr(a, b);
            }
        }
        
        return answer;
    }
    
    private int multipleArr(int[] a, int[] b) {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[i];
        }
        return result;
    }
}