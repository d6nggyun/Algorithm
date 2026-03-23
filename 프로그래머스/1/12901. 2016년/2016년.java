class Solution {
    public String solution(int a, int b) {
        int minusDay = 0;
        if (a == 1 && b == 1) {
            return "FRI";
        }
        else {
            if (a == 1) minusDay = b - 1;
            else if (a == 2) minusDay = b + 31 - 1;
            else if (a == 3) minusDay = b + 31 + 29 - 1;
            else if (a == 4) minusDay = b + 31 + 29 + 31 - 1;
            else if (a == 5) minusDay = b + 31 + 29 + 31 + 30 - 1;
            else if (a == 6) minusDay = b + 31 + 29 + 31 + 30 + 31 - 1;
            else if (a == 7) minusDay = b + 31 + 29 + 31 + 30 + 31 + 30 - 1;
            else if (a == 8) minusDay = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 - 1;
            else if (a == 9) minusDay = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 - 1;
            else if (a == 10) minusDay = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 - 1;
            else if (a == 11) minusDay = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 - 1;
            else if (a == 12) minusDay = b + 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 - 1;
        }
        
        minusDay %= 7;
        
        if (minusDay == 1) return "SAT";
        else if (minusDay == 2) return "SUN";
        else if (minusDay == 3) return "MON";
        else if (minusDay == 4) return "TUE";
        else if (minusDay == 5) return "WED";
        else if (minusDay == 6) return "THU";
        else if (minusDay == 0) return "FRI";
        else return "ERROR";
    }
}