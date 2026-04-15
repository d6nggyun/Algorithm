import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String[] clo : clothes) {
            String kind = clo[1];
            
            map.put(kind, map.getOrDefault(kind, 0) + 1);
        }
        
        int answer = 1;
        for (Integer value : map.values()) {
            answer = answer * (value+1);
        }
        answer--;

        return answer;
    }
}