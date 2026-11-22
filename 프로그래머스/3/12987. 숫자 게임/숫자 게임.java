import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        Queue<Integer> que = new ArrayDeque<>();
        
        for (int a : A) {
            que.offer(a);
        }
        
        // 2 2 6 8
        // 1 3 5 7
        
        for (int b : B) {
            if (b > que.peek()) {
                que.poll();
                answer++;
            }
        }
        
        return answer;
    }
}