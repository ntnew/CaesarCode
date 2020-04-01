import javax.management.StringValueExp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class HackerCaesar {
    public static char[] abc = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж',
            'з', 'и','й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э','ю', 'я' };

    public static String crypto(String text, int step){
        String res = "";
        char[] textChar = text.toCharArray();

        for (int i=0; i<textChar.length; i++){
            int index = 0;
            boolean isNOTLetter = Character.isWhitespace(textChar[i]);
            if (!isNOTLetter){
                if (Character.isLowerCase(textChar[i])){
                    while (textChar[i] != abc[index]) index++;
                    index +=step;
                    if (index > 32){
                        index -= 33;
                        if (index > 32){
                            index -= 33;
                        }
                    }
                    textChar[i] = abc[index];
                }
            }
        }
        for (char c:textChar) {
            res += c;
        }
        return res;
    }

    public static int searchKey(String text){
        int position = 0;
        String text2 = text.toLowerCase();
        char[] chars = text2.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<chars.length; i++){
            if (String.valueOf(chars[i]).equals(" ") || String.valueOf(chars[i]).equals("\n")
                    || String.valueOf(chars[i]).equals(".") || String.valueOf(chars[i]).equals(",")){

            }
            else if(!map.containsKey(chars[i])){
                map.put(chars[i], 1);
            }
            else map.put(chars[i],map.get(chars[i])+1);
        }

        Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
        int counter = 0;
        Character caesarKey = null;
        while (iterator.hasNext())
        {
            Map.Entry<Character, Integer> pair = iterator.next();
            if(counter < pair.getValue()){
                counter = pair.getValue();
                caesarKey = pair.getKey();
            }
        }
        for(int i = 0; i<abc.length;i++) {
            if (abc[i] == caesarKey){
                position = i+1;
            }
        }
        return position;
    }

    public static void logic(String text2){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] pos = { 16, 6, 1, 10, 15, 20, 19, 18, 3, 13, 12, 14, 5, 17, 21, 33, 29, 30, 4, 9, 2};
        String answer ="";
        boolean flag = true;
        while(flag){
            for(int i = 0; i< pos.length;i++){
                int keyPosition = 33 - searchKey(text2) + pos[i];
                System.out.println(crypto(text2, keyPosition));
                System.out.print("Расшифровка верная? (да/нет):");
                while (true){
                    try {
                        answer = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (answer.equals("да")) {
                    System.out.println("Спасибо за пользование сервисом!");
                    break;
                    }
                    else if(answer.equals("нет")) {
                        System.out.println("Попробуем другой ключ.");
                        break;
                    }
                    else if(!answer.equals("да") && !answer.equals("нет")) {
                        System.out.println("Неопознаный ответ. Попробуйте ещё раз.");
                        System.out.print("Расшифровка верная? (да/нет):");
                        }
                }
                if (answer.equals("да")) {
                    break;
                }
            }
            break;
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите текст, нажмите Enter:");
        String textBuf = "";
        String text = "";
        while(true){
            try {
                textBuf = reader.readLine() +"\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (textBuf.equals("\n")) {
                break;
            }
            text = text + textBuf;

        }

        String text2 = text.toLowerCase();
        logic(text2);
    }
}
