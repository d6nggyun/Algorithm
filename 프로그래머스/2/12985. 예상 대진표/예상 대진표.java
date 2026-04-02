class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        
        while (true) {
            if (Math.abs(a - b) == 1) {
                if (a % 2 == 1) {
                    if (a / 2 + 1 == b / 2) break;
                } else {
                    if (a / 2 == b / 2 + 1) break;
                }
            }
            
            if (a % 2 == 1) a = a / 2 + 1;
            else a = a / 2;
            
            if (b % 2 == 1) b =  b / 2 + 1;
            else b = b / 2;
            
            answer++;
        }

        return answer;
    }
}