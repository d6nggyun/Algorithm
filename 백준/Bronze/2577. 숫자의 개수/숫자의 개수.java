import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int[] numbers = new int[3];
        int[] count = new int[10];
        int sum = 1;
        
        for (int i =0; i<3; i++) 
            numbers[i] = sc.nextInt();
        
        for (int i=0; i<3; i++) 
            sum *= numbers[i];
        
        String sumStr = Integer.toString(sum);
        
        for (int i=0; i<10; i++){
            for (int j=0; j<sumStr.length(); j++){
                if (sumStr.charAt(j) - '0' == i)
                    count[i] += 1;
            }
        }
        
        for (int i=0; i<10; i++) 
            System.out.println(count[i]);
        
        sc.close();
    }
}