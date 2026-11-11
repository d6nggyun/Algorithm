import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        List<Character> skillList = new ArrayList<>();
        for (int i = 0; i < skill.length(); i++) {
            skillList.add(skill.charAt(i));
        }
        
        // 스킬 트리들을 돌며 검사
        for (String st : skill_trees) {
            // 해당 스킬 트리가 가능한지 판단 -> 가능하면 answer++
            
            int next = 1;
            List<Character> mytree = new ArrayList<>();
            // [판단법]
            // 스킬 트리의 글자를 돌며,
            // 해당 글자가 스킬에 포함되는 지 판단 -> 포함 안되면 다음 글자
            // 포함되면, 이전 스킬이 찍혔는지 판단
            for (int idx = 0; idx < st.length(); idx++) {
                char c = st.charAt(idx);
                if (!skillList.contains(c)) continue;
                
                if (skillList.get(0) == c) {
                    mytree.add(c);
                    continue;
                }
                
                char preskill = skillList.get(skillList.indexOf(c)-1);
                if (mytree.contains(preskill)) {
                    mytree.add(c);
                } else {
                    next = 0;
                    break;
                }
            }
            
            answer += next;
        }
        
        return answer;
    }
}