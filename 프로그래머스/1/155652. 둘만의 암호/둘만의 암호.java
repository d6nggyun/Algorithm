import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        String answer = "";
        List<Character> stringList = new ArrayList<>();
        List<Character> skipList = new ArrayList<>();
        int stringAscii = 0;
        
        for (int i = 0; i < skip.length(); i++) {
            skipList.add(skip.charAt(i));
        }
        
        for (int i = 0; i < s.length(); i++) {
            stringAscii = (int) s.charAt(i);
            for (int j = 0; j < index; j++) {
                stringAscii += 1;
                if (stringAscii > 122) stringAscii = stringAscii - 26;
                while (skipList.contains((char) stringAscii)) {
                    stringAscii += 1;
                    if (stringAscii > 122) stringAscii = stringAscii - 26;
                }
            }
            answer = answer + (char) stringAscii;
        }
        
        return answer;
    }
}