import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer, Integer> tan = new HashMap<>();
        
        // 숫자 별 출현 횟수 계산
        for (int i = 0; i < tangerine.length; i++) {
            if (tan.containsKey(tangerine[i])) {
                tan.put(tangerine[i], tan.get(tangerine[i]) + 1);
            } else {
                tan.put(tangerine[i], 1);
            }
        }
        
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(tan.entrySet());
        list.sort(Map.Entry.comparingByValue());
        
        while (k > 0) {
            k -= list.get(list.size() - 1).getValue();
            list.remove(list.size() - 1);
            answer++;
        }
        
        return answer;
    }
}