import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        
        while (n > 2) {
            // // 홀수면 ans에 1을 더하고 n--
            if (n % 2 == 1) {
                ans++;
                n--;
            } else { // 짝수면 n을 /2
                n /= 2;
            }
        }

        // 2일 경우, 배터리 1칸으로 도달 가능
        return ans + 1;
    }
}