import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        long total = 0;
        for (int w : works) {
            pq.offer(w);
            total += w;
        }
        
        if (total <= n) return 0;
        
        for (int i = 0; i < n; i++) {
            int top = pq.poll();
            pq.offer(top-1);
        }
        
        long answer = 0;
        while (!pq.isEmpty()) {
            long w = pq.poll();
            answer += w*w;
        }
        
        return answer;
    }
}