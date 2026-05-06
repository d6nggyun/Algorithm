import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> q = new ArrayDeque<>();
        
        // 큐에 우선순위 index 순서대로 넣음
        for (int i = 0; i < priorities.length; i++) {
            q.offer(i);
        }
        // [0, 1, 2, 3, 4, 5]
        // [2, 3, 4, 5, 0, 1]
        // [3, 4, 5, 0, 1]
        int turn = 1;
        while (!q.isEmpty()) {
            // 큐의 상단 index의 값이 최대인지 확인
            int idx = q.poll();
            if (priorities[idx] == -1) {
                q.poll();
                continue;
            }
            int maxIdx = findMax(priorities, idx);
                
            // 최대라면 해당 값 제거
            if (idx == maxIdx) {
                priorities[idx] = -1;
            } else { // 최대가 아니라면 다시 큐에 적재
                q.offer(idx);
                continue;
            }

            if (idx == location) return turn;
            
            turn++;
        }
        
        return answer;
    }
    
    private Integer findMax(int[] priorities, int idx) {
        int cur = priorities[idx];
        int maxIdx = idx;
        
        for (int i = 0; i < priorities.length; i++) {
            if (priorities[i] == -1) continue;
            if (priorities[i] > cur) {
                cur = priorities[i];
                maxIdx = i;
            }
        }
        
        return maxIdx;
    }
}