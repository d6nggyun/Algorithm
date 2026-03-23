import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int correct = 0;
        int incorrect = 0;
        int zero = 0;
        List<Integer> win_nums_List = new ArrayList<>(6);
        
        for (int wn : win_nums) {
            win_nums_List.add(wn);
        }
        
        for (int lot : lottos) {
            if (lot == 0) zero++;
            
            else if (win_nums_List.contains(lot)) {
                correct++;
            }
            else {
                incorrect++;
            }
        }
        
        answer[1] = rank(correct);
        
        while (zero != 0) {
            correct++;
            zero--;
        }
        
        answer[0] = rank(correct);
        
        return answer;
    }
    
    private int rank(int correct) {
        if (correct == 6) return 1;
        else if (correct == 5) return 2;
        else if (correct == 4) return 3;
        else if (correct == 3) return 4;
        else if (correct == 2) return 5;
        else return 6;
    }
}