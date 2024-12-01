import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Comparator;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        sc.nextLine();
        HashSet<String> wordsSet = new HashSet<>();

        for (int i =0; i<number; i++){
            wordsSet.add(sc.nextLine());
        }

        // Set을 ArrayList로 변환
        ArrayList<String> words = new ArrayList<>(wordsSet);

        // comparingInt(String::length)은 문자열 길이 기준 정렬
        // thenComparing은 두 번째 정렬 기준
        // naturalOrder은 사전순 정렬
        words.sort(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()));

        for (int i=0; i<words.size(); i++){
            System.out.println(words.get(i));
        }

        sc.close();
    }
}