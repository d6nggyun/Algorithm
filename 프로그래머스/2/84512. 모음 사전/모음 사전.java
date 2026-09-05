import java.util.*;

class Solution {
    
    List<String> list = new ArrayList<>();
    
    public int solution(String word) {
        dfs("");
        return list.indexOf(word) + 1;
    }
    
    public void dfs(String word) {
        if (word.length() > 5) return;
        if (!word.isEmpty()) list.add(word);
        for (char c : "AEIOU".toCharArray()) dfs(word + c);
    }
}