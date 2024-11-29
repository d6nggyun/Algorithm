import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int[] numbers = new int[3];
        int numResult = 0;
        int strResult = 0;
        
        for (int i =0; i<3; i++){
            numbers[i] = sc.nextInt();
        }
        
        numResult = numbers[0] + numbers[1] - numbers[2];
        String str = Integer.toString(numbers[0]) + Integer.toString(numbers[1]);
        strResult = Integer.parseInt(str) - numbers[2];
        
        System.out.println(numResult);
        System.out.println(strResult);
        
        sc.close();
    }
}