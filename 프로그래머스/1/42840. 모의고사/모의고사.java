import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        List<Integer> answerList = new ArrayList<>();
        int[] point = new int[3];
        int cycle = 0;
        
        for (int i = 1; i <= answers.length; i++) {
            // 1번 정답
            if (i % 5 == 0 && answers[i - 1] == 5) point[0]++;
            else if (answers[i - 1] == i % 5) point[0]++;
            
            // 2번 정답
            if ((answers[i - 1] == 2) && (i % 2 == 1)) point[1]++;
            else {
                cycle = i % 8;
                
                if (answers[i - 1] == 1) {
                    if (cycle == 2) point[1]++;
                }
                else if (answers[i - 1] == 3) {
                    if (cycle == 4) point[1]++;
                }
                else if (answers[i - 1] == 4) {
                    if (cycle == 6) point[1]++;
                }
                else if (answers[i - 1] == 5) {
                    if (cycle == 0) point[1]++;
                }
            }
            
            // 3번 정답
            cycle = i % 10;    
            if (answers[i - 1] == 1) {
                if (cycle == 3 || cycle == 4) point[2]++;
            }
            else if (answers[i - 1] == 2) {
                if (cycle == 5 || cycle == 6) point[2]++;
            }
            else if (answers[i - 1] == 3) {
                if (cycle == 1 || cycle == 2) point[2]++;
            }
            else if (answers[i - 1] == 4) {
                if (cycle == 7 || cycle == 8) point[2]++;
            }
            else if (answers[i - 1] == 5) {
                if (cycle == 9 || cycle == 0) point[2]++;
            }
        }
        
        int maxPoint = Math.max(point[0], Math.max(point[1], point[2]));
        for (int i = 0; i < 3; i++) {
            if (point[i] == maxPoint) {
                answerList.add(i + 1);
            }
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}