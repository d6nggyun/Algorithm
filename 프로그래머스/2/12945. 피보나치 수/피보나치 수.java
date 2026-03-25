import java.util.*;

class Solution {
    public int solution(int n) {
        List<Integer> fibo = new ArrayList<>();
        
        // 처음 배열에 0,1,2까지는 1을 저장
        fibo.add(1);
        fibo.add(1);
        fibo.add(1);
        
        // 3부터 n까지 돌며,
        for (int i = 3; i <= n; i++) {
            // 피보나치를 계산
                // 필요한 피보나치 값이 배열에 존재할 경우, 재사용
            // 배열에 피보나치 수를 저장
            fibo.add(fiboFun(i, fibo) % 1234567);   
        }
        
        return fibo.get(n);
    }
    
    // 피보나치 수열 함수
    private int fiboFun(int num, List<Integer> fibo) {
        // 만약 피보나치 값 리스트의 크기가 num보다 크다면 값 재사용
        if (fibo.size() > num) return fibo.get(num);
        
        // 재귀 호출
        return fiboFun(num-1, fibo) + fiboFun(num-2, fibo);
    }
}