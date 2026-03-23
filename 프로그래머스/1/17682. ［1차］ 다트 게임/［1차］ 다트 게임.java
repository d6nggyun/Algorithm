class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int currentPoint = 0;
        String currentPointString = "";
        int prevPrevPoint = 0;
        int prevPoint = 0;
        int answerPlan = 0;
        boolean flag = false;
        String nextCC = "";
        
        for (int i = 0; i < dartResult.length(); i++) {
            char cc = dartResult.charAt(i);
            if (i+1 < dartResult.length()) nextCC = "" + dartResult.charAt(i+1);
            else nextCC = "";
            
            if (!Character.isDigit(cc)) {
                if (cc == '#') continue;
                
                if (cc == '*') {
                    answer = answer + prevPrevPoint + prevPoint;
                    prevPrevPoint = prevPrevPoint * 2;
                    prevPoint = prevPoint * 2;
                    currentPointString = "";
                }
                else {
                    currentPoint = Integer.parseInt(currentPointString);
                    prevPrevPoint = prevPoint;
                    if (nextCC.equals("#")) {
                        answerPlan = handleChar(currentPoint, cc) * (-1);
                        answer += answerPlan;
                        prevPoint = answerPlan;
                    } else {
                        answerPlan = handleChar(currentPoint, cc);
                        answer += answerPlan;
                        prevPoint = answerPlan;
                    }
                    currentPointString = "";
                }
            } else {
                currentPointString = currentPointString + cc;
            }
        }
        
        return answer;
    }
    
    private int handleChar(int point, char c) {
        if (c == 'S') return point;
        else if (c == 'D') return point * point;
        else if (c == 'T') return point * point * point;
        return -99999;
    }
}