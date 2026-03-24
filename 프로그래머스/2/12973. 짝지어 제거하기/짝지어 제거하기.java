import java.util.*;

class Solution
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        char c;
        
        // 첫 번째 문자를 stack에 push
        stack.push(s.charAt(0));
        
        // s의 모든 문자열을 돌며, 
        // 제일 top과 같을 경우, pop
        // 다를 경우, push
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            if (stack.isEmpty()) stack.push(c);
            else if (c == stack.peek()) stack.pop();
            else stack.push(c);
        }
        
        if (stack.isEmpty()) return 1;
        else return 0;
    }
}