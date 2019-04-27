import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class ReadWordsToNumbers {

    private String number_to_convert;
    private String final_string_numbers;
    private int final_number;

    public String wordsToNumbers(String number_to_convert) throws Exception {
        CharStream input = CharStreams.fromStream(System.in);
        // create a lexer that feeds off of input CharStream:
        CalculatorLexer lexer = new CalculatorLexer(input);
        // create a buffer of tokens pulled from the lexer:
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // create a parser that feeds off the tokens buffer:
        CalculatorParser parser = new CalculatorParser(tokens);

        ParseTree tree = parser.program();
        // print LISP-style tree:
        // System.out.println(tree.toStringTree(parser));
        ParseTreeWalker walker = new ParseTreeWalker();
        MyCalculatorListener listener = new MyCalculatorListener();
        walker.walk(listener, tree);
        HashMap<String, Integer> number_name_to_number =  listener.getMap();

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

    public int wordsToOneNumber(String message) throws Exception{
        wordsToNumbers(message);
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
