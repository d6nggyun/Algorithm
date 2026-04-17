import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String num : phone_book) {
            map.put(num, 1);
        }
        
        for (String num : phone_book) {
            for (int i = 0; i < num.length(); i++) {
                if (map.containsKey(num.substring(0, i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}