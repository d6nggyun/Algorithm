import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        int[][] info = new int[number][3];
        int room = 0;
        int floor = 0;
        int roomNum = 0;

        for (int i=0; i<number; i++){
            for (int j=0; j<3; j++){
                info[i][j] = sc.nextInt();
            }
        }

        for (int i=0; i<number; i++){
            floor = info[i][2] % info[i][0];
            roomNum = (info[i][2] / info[i][0]) + 1;
            if (floor == 0) {
                floor = info[i][0];
                roomNum -= 1;
            }
            room = floor * 100 + roomNum;
            System.out.println(room);
        }

        sc.close();
    }
}