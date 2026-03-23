import java.util.*;

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        for (String bab : babbling) {
            answer += isCanSay(bab);
        }
        
        return answer;
    }
    
    private int isCanSay(String bab) {
        int index = 0;
        int sayNumber = -1;
        
        while (index < bab.length()) {
            if (index+3 <= bab.length() && bab.startsWith("aya", index) && sayNumber != 1) {
                sayNumber = 1;
                index += 3;
            } else if (index+3 <= bab.length() && bab.startsWith("woo", index) && sayNumber != 2) {
                sayNumber = 2;
                index += 3;
            } else if (index+2 <= bab.length() && bab.startsWith("ye", index) && sayNumber != 3) {
                sayNumber = 3;
                index += 2;
            } else if (index+2 <= bab.length() && bab.startsWith("ma", index) && sayNumber != 4) {
                sayNumber = 4;
                index += 2;
            } else return 0;
        }
        return 1;
    }
}