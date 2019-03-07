import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Ex4_bashPipeline {

    private static Scanner input = new Scanner(System.in);
    private static Path file_path = Paths.get("../../../numbers.txt");

    public static void main(String args[]){

        // read message from terminal
        String message = input.nextLine();

        List<String> list_from_terminal;
        List<Integer> separated_numbers = new ArrayList<>();
        int final_number = 0;

        list_from_terminal = Arrays.asList(message.split(" "));
        list_from_terminal.forEach(item -> {
            if(item.chars().allMatch(Character :: isDigit))
                separated_numbers.add(Integer.parseInt(item));
        });
        Collections.reverse(list_from_terminal);

        for(int index_number = 0, current_to_sum; index_number < separated_numbers.size(); index_number++){
            current_to_sum = separated_numbers.get(index_number);
            while(index_number + 1 < separated_numbers.size() && separated_numbers.get(index_number + 1) >= 100)
                current_to_sum *= separated_numbers.get(++index_number);

            final_number += current_to_sum;
        }

        System.out.println(final_number);
    }
}
