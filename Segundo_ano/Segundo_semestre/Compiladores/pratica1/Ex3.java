import java.util.*;

public class Ex3 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]){

        String message = input.nextLine();

        ReadWordsToNumbers words_to_numbers_converter = new ReadWordsToNumbers("../../../numbers.txt", message);
        String result  = words_to_numbers_converter.wordsToNumbers();

        System.out.println(result);
    }

}
