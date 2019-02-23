import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Ex3 {

    private static Scanner input = new Scanner(System.in);
    private static Path file_path = Paths.get("../../../numbers.txt");

    public static void main(String args[]){

        // read message from terminal
        String message = input.nextLine();

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
        List<String> spited_message = Arrays.asList(message.split("[ -]"));
        spited_message.forEach(word -> {
            if(number_name_to_number.get(word) != null)
                Collections.replaceAll(spited_message, word, Integer.toString(number_name_to_number.get(word)));
        });

        String final_message;
        StringBuilder final_message_builder = new StringBuilder();
        spited_message.forEach(item -> final_message_builder.append(item + " "));
        final_message = final_message_builder.toString();

        System.out.println(final_message);

    }

}
