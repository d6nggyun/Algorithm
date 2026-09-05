import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            String addStr = ("" + str1.charAt(i) + str1.charAt(i+1)).toLowerCase();
            // 문자열만 남겼을 때, 길이가 2일 경우 list에 추가
            if (addStr.replaceAll("[^a-z]", "").length() == 2) {
                list1.add(addStr);
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            String addStr = ("" + str2.charAt(i) + str2.charAt(i+1)).toLowerCase();
            if (addStr.replaceAll("[^a-z]", "").length() == 2) {
                list2.add(addStr);
            }
        }
        
        return (int) (j(list1, list2) * 65536);
    }
    
    double j(List<String> a, List<String> b) {
        // 둘 다 공집합이면 1.0 반환
        if (a.size() == 0 && b.size() == 0) return 1;
        
        // 교집합
        double inter = 0;
        
        // 합집합
        double union = 0;
        
        while (!a.isEmpty()) {
            String s = a.get(0);
            
            // 둘 다 존재하지 확인 후
            // 둘 다 있다면 교집합 값 증가 후, 각각 삭제
            if (b.contains(s)) {
                inter++;
                b.remove(s);
            }
            
            union++;
            a.remove(s);
        }
        
        // 나머지 b 개수는 union에 추가
        union += b.size();
        
        return inter/union;
    }
}