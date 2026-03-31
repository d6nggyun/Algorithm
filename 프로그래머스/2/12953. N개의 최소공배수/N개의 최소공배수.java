class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        
        // 투포인터로 i와 i+1의 공배수를 구함
        // 해당 공배수와 다음 수의 공배수를 구함 -> 배열 끝까지
        for (int i = 1; i < arr.length; i++) {
            answer = commonMultiple(answer, arr[i]);
        }
        
        return answer;
    }
    
    // a, b의 공배수
    private int commonMultiple(int a, int b) {
        int tempA = a; int tempB = b;
        
        while (a != b) {
            if (a > b) b += tempB;
            else a += tempA;
        }
        
        return a;
    }
}