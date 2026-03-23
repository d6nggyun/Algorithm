class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        int targetKeyCount = 0;
        int kc = 0;
        int idx = 0;
        
        for (String tg : targets) {
            targetKeyCount = 0;
            for (int i = 0; i < tg.length(); i++) {
                kc = keyCount(keymap, tg.charAt(i));
                if (kc == -1) {
                    targetKeyCount = -1;
                    break;
                }
                targetKeyCount += kc;
            }
            answer[idx] = targetKeyCount;
            idx++;
        }
        
        return answer;
    }
    
    private int keyCount(String[] keymap, char c) {
        int count = -1;
        
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                if (key.charAt(i) == c) {
                    if (count == -1 || i < count) count = i + 1;
                    break;
                }
            }
        }
        
        return count;
    }
}