import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        List<Integer> ListSection = new ArrayList<>();
        int lastSection = 0;
        
        for (int sec : section) {
            ListSection.add(sec);
        }
        
        for (int sec : section) {
            if (!ListSection.contains(Integer.valueOf(sec))) {
                continue;
            }
            else {
                answer++;
                if ((sec + m - 1) > n) lastSection = n;
                else lastSection = sec + m - 1;
                for (int i = sec; i <= lastSection; i++) {
                    if (ListSection.contains(Integer.valueOf(i))) {
                        ListSection.remove(Integer.valueOf(i));
                    }
                }
            }
        }
        
        return answer;
    }
}