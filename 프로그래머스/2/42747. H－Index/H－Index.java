import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        List<Integer> list = new ArrayList<>();
        
        for (int cit : citations) {
            list.add(cit);
        }
        
        list.sort(Collections.reverseOrder());
        
        boolean found = false;
        for (int h = list.get(0); h >= 0; h--) {
            if (found) break;
            int count = 0;
            
            for (int i = 0; i < list.size(); i++) {
                int cit = list.get(i);
                
                if (cit >= h) {
                    count++;
                } 
                
                if (count == h) {
                    answer = h;
                    found = true;
                    break;
                }
            }
        }
        
        return answer;
    }
}