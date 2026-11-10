import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // fees -> [기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원)]
        int basicTime = fees[0];
        int basicFee = fees[1];
        int perTime = fees[2];
        int perFee = fees[3];
        
        Map<Integer, String> map = new HashMap<>();
        Map<Integer, Integer> mapResult = new HashMap<>();
        
        for (String rec : records) {
            String[] recList = rec.split(" ");
            
            String time = recList[0];
            Integer car = Integer.parseInt(recList[1]);
            String act = recList[2];
            
            // IN일 경우 해당 차량의 시간을 기록
            // OUT일 경우 해당 차량의 입차 시간을 꺼내서 이용 시간 계산
            if (act.equals("IN")) {
                map.put(car, time);
            } else {
                String enterTime = map.get(car);
                map.put(car, "None");
                mapResult.put(car, mapResult.getOrDefault(car, 0) + calc(enterTime, time));
            }
        }
        
        // 차량의 입차 시간이 남아있을 경우 (IN), 23:59를 출차 시간으로 잡고 계산
        for (String rec : records) {
            String[] recList = rec.split(" ");
            Integer car = Integer.parseInt(recList[1]);
            
            if (map.get(car).equals("None")) continue;
            else {
                String enterTime = map.get(car);
                map.put(car, "None");
                mapResult.put(car, mapResult.getOrDefault(car, 0) + calc(enterTime, "23:59"));
            }
        }
        
        // 각 차량 번호 별, 이용 시간을 정리
        // 차량 번호가 작은 자동차부터 정렬해서 return
        List<Integer> keySort = new ArrayList<>();
        for (Integer key : mapResult.keySet()) {
            keySort.add(key);
        }
        Collections.sort(keySort);
        
        int[] answer = new int[keySort.size()];
        
        for (int i = 0; i < keySort.size(); i++) {
            answer[i] = makeResult(mapResult.get(keySort.get(i)), 
                                   basicTime, basicFee, perTime, perFee);
        }
        return answer;
    }
    
    // b가 더 크다고 가정
    int calc(String a, String b) {
        String[] alist = a.split(":");
        String[] blist = b.split(":");
        
        int hour = Integer.parseInt(blist[0]) - Integer.parseInt(alist[0]);
        int minute = Integer.parseInt(blist[1]) - Integer.parseInt(alist[1]);
        
        if (minute < 0) {
            hour--;
            minute += 60;
        }
        
        return hour*60 + minute;
    }
    
    int makeResult(int time, int basicTime, int basicFee, int perTime, int perFee) {
        if (time < basicTime) return basicFee;
        return basicFee + (int) Math.ceil((time-basicTime)*1.0/perTime)*perFee;
    }
}