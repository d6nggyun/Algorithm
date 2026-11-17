import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // begin으로 시작
        
        // 하나의 알파벳만 바꿔서 만들 수 있는 words 속의 단어로 변형
        // (변형 할때마다 cnt++)
        // 이미 봤던(도달한) 단어는 패스
        // target에 도달했을 경우 cnt를 return
        
        // [idx, cnt]
        Queue<int[]> que = new ArrayDeque<>();
        boolean[] visit = new boolean[words.length];
        
        que.offer(new int[]{-1, 0});
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            String curText = (cur[0] == -1) ? begin : words[cur[0]];
            int cnt = cur[1];
            
            if (curText.equals(target)) return cnt;
            
            for (int i = 0; i < words.length; i++) {
                if (visit[i]) continue;
                if (isChange(curText, words[i])) {
                    que.offer(new int[]{i, cur[1]+1});
                    visit[i] = true;
                }
            }
        }
        
        return 0;
    }
    
    boolean isChange(String a, String b) {
        if (a.equals(b)) return false;
        
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
                if (cnt > 1) return false;
            }
        }
        
        return true;
    }
}