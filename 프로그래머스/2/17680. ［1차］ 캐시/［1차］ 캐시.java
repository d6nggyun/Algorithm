import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new ArrayList<>();
        
        if (cacheSize == 0) return cities.length * 5;
        
        for (String city : cities) {
            String lowerCity = city.toLowerCase();
            
            // 캐시가 비어있으면 넣고, 5를 더하고 다음으로
            if (cache.isEmpty()) {
                answer += 5;
                cache.add(lowerCity);
                continue;
            }
            // 캐시가 비어있지 않으면
            // 캐시에 해당 문자가 포함되는 지를 확인
            // 포함된다면 1 추가
            // 포함되지 않는다면 5 추가
            else if (cache.contains(lowerCity)) {
                answer += 1;
                cache.remove(lowerCity);
            }
            else {
                answer += 5;
                
                if (cache.size() == cacheSize) {
                    cache.remove(0);
                }
            }
            
            // LRU로 캐시 교체
            cache.add(lowerCity);
        }
        
        return answer;
    }
}