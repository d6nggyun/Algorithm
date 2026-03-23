import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 세팅
        String answer = "mm:ss";
        String prevVideo_len = "mm:ss";
        if (aIsBiggerThanB(video_len, "00:10") || video_len.equals("00:10")) {
            prevVideo_len = prevVideo(video_len);
        } else prevVideo_len = "00:00";
        
        // 오프닝 체크
        pos = checkOpening(pos, op_start, op_end);
        
        // 커맨드 입력
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("next")) {
                // 비디오 끝나는 10초 전보다 크다면
                if (pos.equals(video_len) || aIsBiggerThanB(pos, prevVideo_len)) { 
                    pos = video_len;
                } else {
                    // pos에서 10 추가
                    pos = nextVideo(pos);
                }
            } else if (commands[i].equals("prev")) {
                if (pos.equals("00:10") || aIsBiggerThanB(pos, "00:10")) {
                    // pos에서 10 차감
                    pos = prevVideo(pos);
                } else {
                    pos = "00:00";
                }
            } else {
                return "-1";
            }
            pos = conventionAnswer(pos);
            pos = checkOpening(pos, op_start, op_end);
        }
        
        // 오프닝 체크
        pos = checkOpening(pos, op_start, op_end);
        
        // 정답 형식
        answer = conventionAnswer(pos);
        
        return answer;
    }
    
    private Boolean aIsBiggerThanB(String a, String b) {
        String[] partsA = a.split(":");
        String[] partsB = b.split(":");
        Integer partsAFirst = Integer.parseInt(partsA[0]);
        Integer partsASecond = Integer.parseInt(partsA[1]);
        Integer partsBFirst = Integer.parseInt(partsB[0]);
        Integer partsBSecond = Integer.parseInt(partsB[1]);
        if (partsAFirst > partsBFirst) return true;
        else if (partsAFirst < partsBFirst) return false;
        else {
            if (partsASecond > partsBSecond) return true;
            else if (partsASecond < partsBSecond) return false;
            else return false;
        }
    }
    
    private String nextVideo(String pos) {
        String[] parts = pos.split(":");
        Integer partsFirst = Integer.parseInt(parts[0]);
        Integer partsSecond = Integer.parseInt(parts[1]);
        
        partsSecond += 10;
        
        if (partsSecond >= 60) {
            partsFirst += 1;
            partsSecond -= 60;
        }
        
        return partsFirst + ":" + partsSecond;
    }
    
    private String prevVideo(String pos) {
        String[] parts = pos.split(":");
        Integer partsFirst = Integer.parseInt(parts[0]);
        Integer partsSecond = Integer.parseInt(parts[1]);
        
        partsSecond -= 10;
        
        if (partsSecond < 0) {
            partsFirst -= 1;
            partsSecond += 60;
        }
        
        return partsFirst + ":" + partsSecond;
    }
    
    private String conventionAnswer(String answer) {
        String[] parts = answer.split(":");
        if (parts[0].length() != 2) {
                parts[0] = "0" + parts[0];
        }
        if (parts[1].length() != 2) {
                parts[1] = "0" + parts[1];
        }
        return parts[0] + ":" + parts[1];
    }
    
    private String checkOpening(String pos, String op_start, String op_end) {
        if ((aIsBiggerThanB(pos, op_start) || pos.equals(op_start)) && (aIsBiggerThanB(op_end, pos) || pos.equals(op_end))) {
            pos = op_end;
        }
        return pos;
    }
}