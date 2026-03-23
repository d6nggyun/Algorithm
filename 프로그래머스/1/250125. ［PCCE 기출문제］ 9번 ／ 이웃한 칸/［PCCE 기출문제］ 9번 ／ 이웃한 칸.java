class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        for (int i = 0; i < 4; i++) {
            if (i == 0 && w < board[0].length - 1) answer += checkEast(board, h, w);
            else if (i == 1 && w != 0) answer += checkWest(board, h, w);
            else if (i == 2 && h < board.length - 1) answer += checkSouth(board, h, w);
            else if (i == 3 && h != 0) answer += checkNorth(board, h, w);
            else continue;
        }
        
        return answer;
    }
    
    private int checkEast(String[][] board, int h, int w) {
        if (board[h][w].equals(board[h][w+1])) return 1;
        return 0;
    }
    
    private int checkWest(String[][] board, int h, int w) {
        if (board[h][w].equals(board[h][w-1])) return 1;
        return 0;
    }
    
    private int checkSouth(String[][] board, int h, int w) {
        if (board[h][w].equals(board[h+1][w])) return 1;
        return 0;
    }
    
    private int checkNorth(String[][] board, int h, int w) {
        if (board[h][w].equals(board[h-1][w])) return 1;
        return 0;
    }
}