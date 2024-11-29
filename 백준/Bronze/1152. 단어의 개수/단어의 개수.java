import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        String line = scanner.nextLine();
        
        String[] words = line.trim().split("\\s+");
        
        int count = 0;
        if (line.trim().isEmpty()) count = 0;
        else count = words.length;
        
        System.out.println(count);
        
        scanner.close();
    }
}