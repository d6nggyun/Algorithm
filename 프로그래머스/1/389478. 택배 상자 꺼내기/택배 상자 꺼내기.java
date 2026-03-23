import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        List<List<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            arr.add(new ArrayList<>());
        }
    
        int flag = 0;
        if (w == 1) flag = -1;
        int index = 0;
        int answerArr = 0;
        int answer = 0;

        for (int i = 1; i < n+1; i++) {
            if (flag == 0) {
                if (i % w == 0) {
                    index = w - 1;
                    flag = 1;
                } else {
                    index = i % w - 1;
                }

                arr.get(index).add(i);

            } else if (flag == 1){
                if (i % w == 0) {
                    index = 0;
                    flag = 0;
                } else {
                    index = w - i % w;
                }

                arr.get(index).add(i);
            } else {
                arr.get(0).add(i);
            }
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                if (arr.get(i).get(j) == num) {
                    answerArr = i;
                    break;
                }
            }
        }

        for (int i = 0; i < arr.get(answerArr).size(); i++) {
            if (arr.get(answerArr).get(i) >= num) {
                answer++;
            }
        }

        return answer;
    }
}