import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        
        List<String> dict = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            dict.add("" + (char) ('A' + i));
        }
        
        for (int i = 0; i < msg.length(); i++) {
            // 현재 문자
            String cur = "" + msg.charAt(i);
            
            // 최대 긴 문자 탐색
            while(i < msg.length()) {
                // 다음 인덱스의 문자를 더한 문장이 사전에 있으면
                // i 증가 및 문자 연결
                i++;
                
                if (i == msg.length()) {
                    answer.add(dict.indexOf(cur) + 1);
                    break;
                }
                
                if (dict.contains(cur+msg.charAt(i))) {
                    cur = cur + msg.charAt(i);
                } else {
                    dict.add(cur+msg.charAt(i));
                    i--;
                    answer.add(dict.indexOf(cur) + 1);
                    break;
                }
            }
        }
        
        int[] ans = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            ans[i] = answer.get(i);
        }
        
        return ans;
    }
}