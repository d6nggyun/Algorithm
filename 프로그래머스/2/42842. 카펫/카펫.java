class Solution {
    public int[] solution(int brown, int yellow) {
        // brown과 yellow를 더해서 sum에 저장
        int sum = brown + yellow;
            
        // sum을 만들 수 있는 곱셈의 조합 중,
        // yellow == (가로 - 2) * (세로 - 2)
        // 위 조건을 만족하는 조합을 찾음
        for (int i = 3; i <= sum/2; i++) {
            if (sum % i == 0) {
                if (yellow == (sum/i - 2) * (i - 2)) {
                    return new int[]{sum / i, i};
                }
            }
        }
        
        return new int[]{-1, -1};
    }
}