class Solution {
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        dfs(0, 0, numbers, target);
        
        return answer;
    }
    
    private void dfs(int idx, int sum, int[] numbers, int target) {
        // idx와 numbers.length와 같다면
            // if sum이 target과 같다면
                // answer++
            // return
        if (idx == numbers.length) {
            if (sum == target) {
                answer++;
            } 
            return;
        }
        
        // dfs(idx+1, sum + numbers[idx])
        // dfs(idx+1, sum - numbers[idx])
        dfs(idx+1, sum + numbers[idx], numbers, target);
        dfs(idx+1, sum - numbers[idx], numbers, target);
    }
}