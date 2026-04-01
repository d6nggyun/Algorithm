import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Stack<String> stack = new Stack<>();
        String before = "";
        String now = "";
        boolean isStopped = false;
        
        int idx = 1;
        stack.push(words[0]);
        
        while (idx < words.length) {
            before = stack.peek();
            now = words[idx];
                
            // 만약 이전 단어의 끝 글자와 현재 단어의 첫 글자가 != 라면 break
            if (before.charAt(before.length()-1) != now.charAt(0)) {
                isStopped = true;
                break;
            }
            // 현재 단어가 stack에 contains 되어 있다면 break
            if (stack.contains(now)) {
                isStopped = true;
                break;
            }
            
            stack.push(now);
            
            idx++;
        }
        
        if (isStopped) return new int[] {idx % n + 1, idx / n + 1};
        else return new int[] {0, 0};
    }
}