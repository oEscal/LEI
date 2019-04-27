import java.util.*;

public class Ex3 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]) throws Exception{

        String message = "two";
        ReadWordsToNumbers words_to_numbers_converter = new ReadWordsToNumbers();
        String result  = words_to_numbers_converter.wordsToNumbers(message);
        System.out.println(result);
    }

}
