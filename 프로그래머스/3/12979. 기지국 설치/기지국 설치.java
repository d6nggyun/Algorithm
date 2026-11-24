class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int cover = 2*w+1;
        int start = 1;
        
        for (int s : stations) {
            int left = s-w;
            if (start < left) {
                answer += Math.ceil((left-start)/(cover*1.0));
            }
            start = s+w+1;
        }
        
        if (start <= n) {
            answer += Math.ceil((n-start+1)/(cover*1.0));
        }

        return answer;
    }
}