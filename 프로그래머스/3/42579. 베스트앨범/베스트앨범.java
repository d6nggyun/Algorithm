import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 재생 횟수 정렬 : keyList
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String g = genres[i];
            int p = plays[i];
            map.put(g, map.getOrDefault(g, 0) + p);
        }
        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList, (a, b) -> map.get(b) - map.get(a));
        
        // 재생 횟수가 많은 장르부터 스타트
        List<Integer> answerList = new ArrayList<>();
        for (String g : keyList) {
            // plays의 index와 재생 횟수를 map에 저장 : genMap
            Map<Integer, Integer> genMap = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(g)) {
                    genMap.put(i, plays[i]);
                }
            }
            
            // 재생 횟수를 기준으로 genMap을 정렬
            // 만약 같을 경우, 고유 번호(index)가 낮은 노래를 먼저 수록
            List<Integer> genList = new ArrayList<>(genMap.keySet());
            Collections.sort(genList, (a, b) -> {
                int va = genMap.get(a), vb = genMap.get(b);
                if (va != vb) return vb - va;
                return a.compareTo(b);
            });
            
            // 최대 2개까지 수록
            for (int i = 0; i < genList.size(); i++) {
                if (i >= 2) break;
                answerList.add(genList.get(i));
            }
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}