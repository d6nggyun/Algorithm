import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String line = sc.nextLine();
        String[] word = line.trim().split(" ");
        
        word[0] = new StringBuilder(word[0]).reverse().toString();
        word[1] = new StringBuilder(word[1]).reverse().toString();
        
        int word1 = Integer.parseInt(word[0]);
        int word2 = Integer.parseInt(word[1]);
        
        if (word1 >= word2) System.out.println(word1);
        else System.out.println(word2);
        
        sc.close();
    }
}