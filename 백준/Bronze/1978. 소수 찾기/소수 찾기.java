import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        int[] nums = new int[number];
        boolean flag = true;
        int primeNumber = 0;

        for (int i=0; i<number; i++){
            nums[i] = sc.nextInt();
        }

        for (int i=0; i<number; i++){
            flag = true;
            for (int m=2; m <= nums[i] / 2; m++){
                if (nums[i] % m == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag && nums[i] != 1) {
                primeNumber++;
            }
        }

        System.out.println(primeNumber);

        sc.close();
    }
}