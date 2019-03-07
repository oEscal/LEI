import java.util.*;

public class Ex4_v2 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]){

        String message = input.nextLine();

        ReadWordsToNumbers words_to_numbers_converter = new ReadWordsToNumbers("../../../numbers.txt", message);
        int result  = words_to_numbers_converter.wordsToOneNumber();

        System.out.println(result);
    }

}
