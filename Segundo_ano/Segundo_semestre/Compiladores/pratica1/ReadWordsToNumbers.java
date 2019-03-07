import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReadWordsToNumbers {

    private Path file_path;
    private String number_to_convert;
    private String final_string_numbers;
    private int final_number;

    public ReadWordsToNumbers(String file_path, String number_to_convert){
        this.file_path = Paths.get(file_path);
        this.number_to_convert = number_to_convert;
    }

    public String wordsToNumbers(){
        // read file
        List<String> file_lines = new ArrayList<>();
        Map<String, Integer> number_name_to_number = new TreeMap<>();

        try{
            file_lines = Files.readAllLines(file_path);
        }catch (IOException e){
            System.err.println("Error!\n" +
                    "Can't open file");
        }

        file_lines.forEach(item -> {
            String[] splited_items = item.split(" - ");
            String name = splited_items[1];
            int number = Integer.parseInt(splited_items[0]);

            number_name_to_number.put(name, number);
        });

        // parse message's written numbers to digit numbers
        List<String> spited_message = Arrays.asList(number_to_convert.split("[ -]"));
        spited_message.forEach(word -> {
            if(number_name_to_number.get(word) != null)
                Collections.replaceAll(spited_message, word, Integer.toString(number_name_to_number.get(word)));
        });

        StringBuilder final_message_builder = new StringBuilder();
        spited_message.forEach(item -> final_message_builder.append(item + " "));
        final_string_numbers = final_message_builder.toString();

        return final_string_numbers;
    }

    public int wordsToOneNumber(){
        wordsToNumbers();
        final_number = 0;

        List<String> list_from_terminal;
        List<Integer> separated_numbers = new ArrayList<>();


        list_from_terminal = Arrays.asList(final_string_numbers.split(" "));
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

        return final_number;
    }
}
