class Solution {
    public String solution(String s) {
        String stringNum = "";
        int intNum = 0;
        int min = 0;
        int max = 0;
        int tempNum = 0;
        int charCount = 0;
        s = s + " ";
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                stringNum = stringNum + s.charAt(i);
            } else {
                intNum = Integer.parseInt(stringNum.trim());
                stringNum = "";
                charCount++;
                
                if (charCount == 1) tempNum = intNum;
                else if (charCount == 2) {
                    if (tempNum <= intNum) {
                        min = tempNum;
                        max = intNum;
                    }
                    else {
                        min = intNum;
                        max = tempNum;
                    }
                }
                else if (charCount > 2) {
                    if (intNum < min) min = intNum;
                    else if (intNum > max) max = intNum;
                }
            }
        }
        
        String answer = min + " " + max;
        
        return answer;
    }
}