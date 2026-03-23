class Solution {
    public int solution(int n) {
        int answer = 0;
        int temp = 0;
        int near = 0;
        
        // n의 절반까지, 돌며 해당 숫자부터의 연속되는 케이스를 검색
        for (int i = 1; i <= n/2; i++) {
            temp = i;
            near = i + 1;
            // 연속하는 숫자들을 더하며,
            while (temp < n) {
                temp += near;
                near++;
            }
            
            // 해당 숫자와 일치하면 answer++; Continue;
            // 불일치하면 continue;
            if (temp == n) {
                answer++;
            }
        }
        
        return answer+1;
    }
}