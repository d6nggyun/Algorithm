import java.util.*;

class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        List<Integer> numList = new ArrayList<>();
        
        for (int num = 1; num <= number; num++) {
            int tempNum = findNum(num);
            if (tempNum > limit) numList.add(power);
            else numList.add(tempNum);
        }
        
        for (int addNum : numList) {
            answer += addNum;
        }
        
        return answer;
    }
    
    // 약수 계산
    private int findNum(int num) {
        if (num == 1) return 1;
        int result = 0;
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) {
                result++;
            }
        }
        return (result + 2);
    }
}