import java.util.*;

class Solution {
    boolean solution(String s) {
        // 스택을 사용해서 열린 괄호를 만나면 닫힌 괄호를 만날 때 pop 되도록
        // 마지막 문자까지 돌은 후, 스택에 괄호가 아직 남아있을 경우, false를 반환하도록
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            
            if (c == '(') {
                stack.push(c);
            }
            else if (c == ')') {
                // 만약 닫힌 괄호 차례인데, 스택이 비었다면 return false
                if (stack.isEmpty()) return false;
                else stack.pop();
            }
            else {
                System.out.println("Wrong Input");
            }
        }
        
        if (!stack.isEmpty()) return false;
        
        return true;
    }
}