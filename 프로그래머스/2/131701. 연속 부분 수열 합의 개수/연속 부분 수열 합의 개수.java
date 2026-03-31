import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int ele = 0;
        int len = elements.length;
        
        // element의 길이만큼 반복
        for (int i = 1; i <= len; i++) {
            // 투포인터로, i만큼 길이의 수열을 뽑아 경우의 수들을 만듦
            // 만든 수를 Set에 add
            for (int a = 0; a < len; a++) {
                ele = 0;
                
                int b = a + i;
                int idx = -1;
                
                for (int c = a; c < b; c++) {
                    if (c >= len) idx = c % len;
                    else idx = c;
                    ele += elements[idx];
                }
                
                set.add(ele);
            }
        }
        
        // set의 size를 return
        return set.size();
    }
}