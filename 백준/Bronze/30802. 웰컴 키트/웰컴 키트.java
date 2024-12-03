import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int people = sc.nextInt();
        int[] size = new int[6];
        int[] bundle = new int[2];
        int Tsum = 0;

        for (int i=0; i<6; i++){
            size[i] = sc.nextInt();
        }
        for (int i=0; i<2; i++){
            bundle[i] = sc.nextInt();
        }

        // 티셔츠 주문 수 계산
        for (int i=0; i<6; i++){
            if (size[i] % bundle[0] == 0 || size[i] == 0)
                Tsum += size[i] / bundle[0];
            else
                Tsum += size[i] / bundle[0] + 1;
        }

        System.out.println(Tsum);
        // 펜 주문 수 계산
        System.out.print(people / bundle[1]);
        System.out.print(" ");
        System.out.println(people % bundle[1]);

        sc.close();
    }
}