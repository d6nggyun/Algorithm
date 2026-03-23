import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        double clear = 0.0;
        double user = 0.0;
        Map<Integer, Double> failure = new LinkedHashMap<>();
        List<Double> sortedValue = new ArrayList<>();
        List<Integer> sortedKey = new ArrayList<>();
        List<Double> existedValue = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            clear = 0.0;
            user = 0.0;
            
            for (int sta : stages) {
                if (sta < i) continue;
                if (sta > i) clear++;
                user++;
            }
            
            if (user == 0) failure.put(i, 0.0);
            else failure.put(i, (user - clear) / user);
        }
        
        for (Map.Entry<Integer, Double> entry : failure.entrySet()) {
            sortedValue.add(entry.getValue());
        }
        
        sortedValue.sort(Collections.reverseOrder());
        
        for (double sv : sortedValue) {
            if (existedValue.contains(sv)) continue;
            for (Map.Entry<Integer, Double> entry : failure.entrySet()) {
                if (Double.compare(entry.getValue(), sv) == 0) {
                    sortedKey.add(entry.getKey());
                }
            }
            existedValue.add(sv);
        }
        
        int[] answer = new int[sortedKey.size()];
        for (int i = 0; i < sortedKey.size(); i++) {
            answer[i] = sortedKey.get(i);
        }
        
        return answer;
    }
}