import java.util.HashMap;
import java.util.Scanner;

public class Task1 {


    public static int indexFirst(String s){
        //будем хранить символ - значение, сколько раз встретилас€ тот или иной символ
        HashMap<Character, Integer> map = new HashMap<>();
        char c = 0;

        //заполнение map
        for(int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c , map.get(c)+ 1);
            } else
                map.put(c, 1);
        }

        //поиск первого неповтор€ющегос€ с помощью map
        for(int i = 0; i < s.length(); i++){
            c = s.charAt(i);
            if(map.get(c) == 1) {
                System.out.println("The index of the first non-repeating symbol " + i);
                return i;
            }
        }

        System.out.println(-1 + " OOOPS! All symbols repeating!");
        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter please string");
        String s = sc.nextLine();
        int k = indexFirst(s);
    }
}
