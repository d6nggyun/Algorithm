import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int binaryCount = 0;
        int zeroCount = 0;
        int lengthCount = 0;
        
        // 이진 변환 문자열이 "1"일 경우까지 반복
        while (!s.equals("1")) { 
            
            // s에서 0을 제거 후 남은 길이를 lengthCount에 저장
            lengthCount = s.replace("0", "").length();
            // s에서 1을 제거 후 남은 길이를 zeroCount에 저장
            zeroCount += s.replace("1", "").length();
            
            // 문자를 다 돌았을 경우,
            // lengthCount를 이진 변환하여, s에 저장
            s = binaryTrans(lengthCount);
            
            // 이진 변환 횟수++, 
            binaryCount++;
        }
            
        // answer[0] = 이진 변환 횟수
        answer[0] = binaryCount;
        // answer[1] = 0 카운트
        answer[1] = zeroCount;
        
        return answer;
    }
    
    // 숫자를 이진 변환하여 문자열에 저장
    private String binaryTrans(int lengthCount) {
        String result = "";
        int q = lengthCount;
        List<Integer> rList = new ArrayList<>();
        
        while (q != 1) {
            // 몫을 2로 나누고 나머지를 List에 저장;
            // 나눠진 값을 몫에 저장;
            rList.add(q % 2);
            q = q / 2;
        }
        
        rList.add(1);
        
        // List를 거꾸로 돌면서 문자열로 형변환하고 String에 add;
        for (int i = rList.size() - 1; i >= 0; i--) {
            result = result + rList.get(i);
        }
        
        return result;
    }
}