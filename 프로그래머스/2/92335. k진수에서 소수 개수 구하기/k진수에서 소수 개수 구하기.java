class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        // n을 k 진수로 변경
        // 변경된 문자열에서 조건에 맞는 소수가 몇 개인지
        String str = Integer.toString(n, k);
        String num = "";
        
        // 모든 문자에 대해
        for (int i = 0; i < str.length(); i++) {
            // 0일 경우, 지금까지 저장된 값이 소수라면 answer++
            // 저장된 값 초기화
            if (str.charAt(i) == '0') {
                if (num.isBlank()) continue;
                if (isPrimeNum(Long.parseLong(num))) answer++;
                num = "";
            // 0이 아닐 경우, 문자 추가
            } else {
                num += str.charAt(i);
            }
        }
        
        // 마지막 문자 이후, 남은 문자가 있다면 소수인지 판단 후, answer++
        if (!num.isBlank() && isPrimeNum(Long.parseLong(num))) answer++;
        
        return answer;
    }
    
    boolean isPrimeNum(long num) {
        if (num <= 1) return false;
        for (long i = 2; i*i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}