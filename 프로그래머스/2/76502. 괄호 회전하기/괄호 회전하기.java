import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i, s.length()) + s.substring(0, i);
            
            if (!isComplete(temp)) {
                continue;
            }
            answer++;
        }
        
        return answer;
    }
    
    private boolean isComplete(String temp) {
        Stack<String> stack = new Stack<>();
        
        for (int i = 0; i < temp.length(); i++) {
            String ts = "" + temp.charAt(i);
            
            if (i == temp.length() - 1 
                && (ts.equals("(") || ts.equals("{") || ts.equals("["))) {
                return false;
            }
            
            if (ts.equals("(") || ts.equals("{") || ts.equals("[")) { 
                stack.push(ts);
                continue;
            }
            else {
                if (stack.isEmpty()) return false;
                
                String s = stack.pop();
                
                if (s.equals("{") && ts.equals("}")) continue;
                else if (s.equals("[") && ts.equals("]")) continue;
                else if (s.equals("(") && ts.equals(")")) continue;
                else return false;
            }
        }
        
        return stack.isEmpty();
    }
}