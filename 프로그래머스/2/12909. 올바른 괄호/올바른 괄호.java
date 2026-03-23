import java.util.*;

class Solution {
    boolean solution(String s) {
        List<Character> stack = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.contains('(')) {
                    stack.remove(Character.valueOf('('));
                } else {
                    stack.add(c);
                }
            }
        }
        
        if (stack.isEmpty()) return true;
        else return false;
    }
}