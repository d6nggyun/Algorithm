import java.util.*;

class Solution {
    public int[] solution(String s) {
        Stack<Character> stack = new Stack<>();
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        String ss = s.substring(1, s.length() - 1);
        String tempStr = "";
        
        // { 와 } 사이의 값들을 리스트에 넣음
        for (int i = 0; i < ss.length(); i++) {
            Character c = ss.charAt(i);
            
            if (c == '{') {
                stack.push(c);
                
                continue;
            } 
            else if (c == '}') {
                temp.add(Integer.parseInt(tempStr));
                list.add(new ArrayList<>(temp));
                
                stack.pop();
                temp.clear();
                tempStr = "";
                
                continue;
            }
            else if (c == ',') {
                if (tempStr.equals("")) continue;
                temp.add(Integer.parseInt(tempStr));
                tempStr = "";
            }
            else {
                tempStr += c;
            }
        }
        
        // 원소 1개부터 ~ 최대까지 돌며
        // 순차적으로 배열에 덧붙임
        // 이때, 이미 배열에 있는 원소는 제외한 나머지 원소만 덧붙임
        int[] answer = new int[list.size()];
        List<Integer> ansList = new ArrayList<>();
        int size = 1;
        
        while (size <= list.size()) {
            for (int i = 0; i < list.size(); i++) {
                if (size == list.get(i).size()) {
                    for (int j = 0; j < list.get(i).size(); j++) {
                        int n = list.get(i).get(j);
                        
                        if (!ansList.contains(n)) {
                            answer[size-1] = n;
                            ansList.add(n);
                            break;
                        }
                    }
                }
            }
            
            size++;
        }
        
        return answer;
    }
}