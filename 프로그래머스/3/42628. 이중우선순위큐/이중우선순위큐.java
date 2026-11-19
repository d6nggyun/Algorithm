import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> rpq = new PriorityQueue<>(Collections.reverseOrder());
        List<Integer> record = new ArrayList<>();
        
        for (String op : operations) {
            String[] sp = op.split(" ");
            String act = sp[0];
            int num = Integer.parseInt(sp[1]);
            
            if (act.equals("I")) {
                pq.offer(num);
                rpq.offer(num);
                record.add(num);
            } else {
                if (record.isEmpty()) continue;
                
                if (num == 1) {
                    while (!rpq.isEmpty()) {
                        int pollRPQ = rpq.poll();
                        if (record.contains(pollRPQ)) {
                            record.remove(Integer.valueOf(pollRPQ));
                            break;
                        }
                    }
                } else {
                    while (!pq.isEmpty()) {
                        int pollPQ = pq.poll();
                        if (record.contains(pollPQ)) {
                            record.remove(Integer.valueOf(pollPQ));
                            break;
                        }
                    }
                }
            }
        }
        
        if (record.isEmpty()) return new int[]{0, 0};
        
        Collections.sort(record);
        int size = record.size();
        return new int[]{record.get(size-1), record.get(0)};
    }
}