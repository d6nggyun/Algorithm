class Solution {
    public int solution(int n) {
        
        // n을 2진수로 변환 했을 때, 1의 갯수를 binaryN에 저장
        String binaryNString = Integer.toBinaryString(n);
        int binaryN = binaryNString.replace("0", "").length();
        
        // n부터 1씩 증가하며 해당 숫자에 대해서 탐색
        // 해당 숫자를 2진수 변환 했을 때, 1의 갯수가 binaryN과 같은지 비교
        // 같다면 return
        int i = n + 1;
        while (true) {
            if (binaryN == Integer.toBinaryString(i).replace("0", "").length()) {
                break;
            }
            i++;
        }
        
        return i;
    }
}