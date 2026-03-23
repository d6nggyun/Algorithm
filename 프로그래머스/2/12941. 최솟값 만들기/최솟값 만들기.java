import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        
        for (int a : A) aList.add(a);
        for (int b : B) bList.add(b);
        
        Collections.sort(aList);
        bList.sort(Collections.reverseOrder());
        
        for (int i = 0; i < aList.size(); i++) {
            answer += aList.get(i) * bList.get(i);
        }

        return answer;
    }
}