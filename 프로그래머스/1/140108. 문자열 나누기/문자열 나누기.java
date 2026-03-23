class Solution {
    public int solution(String s) {
        int answer = 0;
        int first = 1;
        int diff = 0;
        int idx = 0;
        
        for (int i = 1; i <= s.length(); i++) {
            if (first == diff) {
                answer++;
                idx = i;
                first = 1;
                diff = 0;
                continue;
            } else {
                if (i == s.length()) {
                    answer++;
                }
            }
            if (i == s.length()) break;
            if (s.charAt(idx) == s.charAt(i)) first++;
            else diff++;
        }
        
        return answer;
    }
}