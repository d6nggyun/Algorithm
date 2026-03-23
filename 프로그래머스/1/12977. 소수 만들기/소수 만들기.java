class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        for (int i = 0; i < nums.length - 2; i++) {
            int num = nums[i];
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int m = j + 1; m < nums.length; m++) {
                    if (isPrimeNum(nums[i] + nums[j] + nums[m])) answer++;
                }
            }
        }

        return answer;
    }
    
    private boolean isPrimeNum(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}