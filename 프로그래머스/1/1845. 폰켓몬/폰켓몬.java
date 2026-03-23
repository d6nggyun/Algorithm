import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        List<Integer> numList = new ArrayList<>();
        
        for (int num : nums) {
            if (numList.size() == nums.length / 2) 
                break;
            if (!numList.contains(num)) {
                answer++;
                numList.add(num);
            }
        }
        
        return answer;
    }
}