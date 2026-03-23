import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int maxApple = 0;
        List<Integer> scoreList = Arrays.stream(score).boxed()
            .collect(Collectors.toList());
        List<Integer> box = new ArrayList<>();
        List<List<Integer>> totalBox = new ArrayList<>();
        
        scoreList.sort(null);
        while (scoreList.size() >= m) {
            for (int i=0; i<m; i++) {
                if (scoreList.size() < 1) continue;
                
                maxApple = scoreList.get(scoreList.size() - 1);
                box.add(maxApple);
                scoreList.remove(scoreList.size() - 1);
            }
            totalBox.add(new ArrayList<>(box));
            box.clear();
        }
        
        return maxPrice(totalBox, m);
    }
    
    // 상자 최대 이익 계산
    private int maxPrice(List<List<Integer>> totalBox, int m) {
        int answer = 0;
        for (List<Integer> box : totalBox) {
            if (box.size() != m) continue;
            
            int min = Integer.MAX_VALUE;
            for (int apple : box) {
                if (apple < min) min = apple;
            }
            
            answer = answer + min * box.size();
        }
        
        return answer;
    }
}