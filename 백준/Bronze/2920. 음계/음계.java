import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[8];
        
        boolean ascending = true;
        boolean descending = true;
        
        for (int i =0; i<8; i++)
            numbers[i] = sc.nextInt();
        
        for (int i=0; i<numbers.length - 1; i++){
            if (numbers[i+1] != numbers[i] + 1){
                ascending = false;
                break;
            }
        }
        if (ascending) 
            System.out.println("ascending");
        
        for (int i=0; i<numbers.length - 1; i++){
            if (numbers[i+1] != numbers[i] - 1){
                descending = false;
                break;
            }
        }
        if (descending) 
            System.out.println("descending");
        
        if (!ascending && !descending)
            System.out.println("mixed");
        
        sc.close();
    }
}