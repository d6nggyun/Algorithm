import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String line = sc.nextLine();
        int time = 0;
        
        for (int i=0; i<line.length(); i++){
            char word = line.charAt(i);
            
            if ((word == 'A')||(word == 'B')||(word == 'C')) time += 3;
            if ((word == 'D')||(word == 'E')||(word == 'F')) time += 4;
            if ((word == 'G')||(word == 'H')||(word == 'I')) time += 5;
            if ((word == 'J')||(word == 'K')||(word == 'L')) time += 6;
            if ((word == 'M')||(word == 'N')||(word == 'O')) time += 7;
            if ((word == 'P')||(word == 'Q')||(word == 'R')||(word == 'S')) time += 8;
            if ((word == 'T')||(word == 'U')||(word == 'V')) time += 9;
            if ((word == 'W')||(word == 'X')||(word == 'Y')||(word == 'Z')) time += 10;
        }
        
        System.out.println(time);
        
        sc.close();
    }
}