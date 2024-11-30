import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int number = sc.nextInt();
        sc.nextLine(); // 줄바꿈 문자 처리
        int[] point = new int[number];
        int bonus = 0;
        String ox = "";
        
        for (int i = 0; i<number; i++){
            ox = sc.nextLine();
            bonus = 0;
            
            for (int m = 0; m < ox.length(); m++){
                if (ox.charAt(m) == 'O'){
                    if (bonus > 0){
                        point[i] += bonus;
                    }
                    point[i] += 1;
                    bonus += 1;
                }
                else if (ox.charAt(m) == 'X'){
                    bonus = 0;
                }
                else
                    break;
            }
        }
        
        for (int i=0; i<number;i++)
            System.out.println(point[i]);
        
        sc.close();
    }
}