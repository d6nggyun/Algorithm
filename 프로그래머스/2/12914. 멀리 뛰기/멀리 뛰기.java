import java.util.*;

class Solution {
    public long solution(int n) {
        List<Integer> list = new ArrayList<>();
        
        // 각 칸이 피보나치의 규칙을 따름
        // 1 -> 1가지, 2 -> 2가지, 3 -> 3가지 ...
        // 0 -> 임의의 1가지
        list.add(1); list.add(1); list.add(2);
        for (int i = 3; i <= n; i++) {
            list.add(fibo(i, list) % 1234567);
        }
        
        return list.get(n);
    }
    
    private int fibo(int n, List<Integer> list) {
        if (list.size() > n) return list.get(n);
        else {
            return fibo(n - 2, list) + fibo(n - 1, list);
        }
    }
}