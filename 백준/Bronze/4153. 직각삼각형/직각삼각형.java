import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Comparator;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a, b, c;
        int max = 0;

        while (true){
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();

            if (a == 0 && b == 0 && c == 0) {
                break;
            }

            max = Math.max(a, Math.max(b,c));

            if (max == a){
                if ((max * max) == (b*b) + (c*c))
                    System.out.println("right");
                else System.out.println("wrong");
            }
            else if (max == b){
                if ((max * max) == (a*a) + (c*c))
                    System.out.println("right");
                else System.out.println("wrong");
            }
            else {
                if ((max * max) == (a*a) + (b*b))
                    System.out.println("right");
                else System.out.println("wrong");
            }
        }
        sc.close();
    }
}