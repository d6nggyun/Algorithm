import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // idx부터 총 10개를 자른 리스트를 저장
        // want가 해당 리스트에 다 포함되는 지 확인
        // 포함된다면 answer++, 안된다면 다음
        for (int i = 0; i < discount.length; i++) {
            boolean cant = false;
            if (Math.abs(discount.length - i) < 10) break;
            
            List<String> dL = new ArrayList<>();
            
            for (int j = 0; j < 10; j++) {
                dL.add(discount[i + j]);
            }
            
            for (int w = 0; w < want.length; w++) {
                if (!isInList(want[w], number[w], dL)) {
                    cant = true;
                    break;
                }
            }
            
            if (!cant) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isInList(String want, int number, List<String> dL) {
        for (int i = 0; i < number; i++) {
            if (!dL.remove(want)) {
                return false;
            }
        }
        
        return true;
    }
}