import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        boolean flag = false;
        
        while (!flag) {
            if (checkAnswer(wallet, bill)) {
                flag = true;
            } else {
                halfWallet(bill);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void halfWallet(int[] bill) {
        int maxWallet = Math.max(bill[0], bill[1]);
        
        if (bill[0] == maxWallet) bill[0] = bill[0] / 2;
        else bill[1] = bill[1] / 2;
    }
    
    private boolean checkAnswer(int[] wallet, int[] bill) {
        if ((Math.min(bill[0], bill[1]) <= Math.min(wallet[0], wallet[1]))
            && (Math.max(bill[0], bill[1]) <= Math.max(wallet[0], wallet[1]))
           ) return true;
        else return false;
    }
}