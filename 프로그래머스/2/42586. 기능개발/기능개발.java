import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            int remainProgress = 100 - progresses[i];
            int toDay = remainProgress / speeds[i];
            if (remainProgress % speeds[i] != 0) {
                toDay += 1;
            }
            
            q.offer(toDay);
        }
        
        // 5 10 1 1 20 1
        
        int temp = q.poll();
        list.add(1);
        
        int qSize = q.size();
        for (int i = 0; i < qSize; i++) {
            int cur = q.poll();
            int lastIdx = list.size() - 1;
            
            if (cur <= temp) {
                list.set(lastIdx, list.get(lastIdx) + 1);
            } else {
                temp = cur;
                list.add(1);
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}