import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        // 명령어 선언
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        
        // 캐릭터 위치: (5, 5)
        int row = 5;
        int col = 5;
        
        // 이동 경로 (양 끝 점)
        record Move(int r1, int c1, int r2, int c2) {}
        Set<Move> route = new HashSet<>();
        
        // 모든 명령어를 순회
        for (int i = 0; i < dirs.length(); i++) {
            // 명령어 추출
            Character c = dirs.charAt(i);
            // 방향
            int d = 0;
            
            // 방향 선정
            if (c == 'U') d = 0;
            else if (c == 'D') d = 1;
            else if (c == 'L') d = 2;
            else d = 3;
            
            // 다음 이동할 칸 계산
            int nextRow = row + dr[d];
            int nextCol = col + dc[d];
            
            // 경계선 탐지
            if (nextRow < 0 || nextRow > 10
               || nextCol < 0 || nextCol > 10) {
                continue;
            }
            
            // set에 {기존 row, 기존 col, 다음 row, 다음 col}을 저장
            route.add(new Move(row, col, nextRow, nextCol));
            route.add(new Move(nextRow, nextCol, row, col));
             
            // 이동
            row = nextRow;
            col = nextCol;
        }
        
        answer = route.size()/2;
        
        return answer;
    }
}