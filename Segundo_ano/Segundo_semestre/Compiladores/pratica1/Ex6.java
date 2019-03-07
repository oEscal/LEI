import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Ex6 {

    private static Random random = new Random();

    public static void main(String args[]){

        List<String> file_lines = new ArrayList<>();
        Map<String, List<String>> words_with_translation = new HashMap<>();

        readFile(args[1], file_lines);
        mapWordsToTranslation(file_lines, words_with_translation);

        for(int argc = 2; argc < args.length; argc++){
            file_lines.clear();
            readFile(args[argc], file_lines);

            file_lines.forEach(line -> {
                if(args[0].equals("-t"))
                    System.out.println(translateLine(line, words_with_translation));
                else
                    if(args[0].equals("-d"))
                        System.out.println(defineLine(line, words_with_translation));
                else{
                    System.err.println("Error in arguments!");
                    System.exit(1);
                }
            });
        }
    }

    private static void readFile(String file_name, List<String> file_lines){

        Path file_path = Paths.get(file_name);

        try{
            Files.lines(file_path).forEach(line -> file_lines.add(line));
        }catch (IOException e){
            System.err.println("Error in file opening!");
        }
    }

    private static void mapWordsToTranslation(List<String> file_lines, Map<String, List<String>> words_with_translation){

        file_lines.forEach(line -> {
            List<String> current_words = new ArrayList<>(Arrays.asList(line.split(" ")));
            String word_to_translate = current_words.remove(0);

            if(!words_with_translation.keySet().contains(word_to_translate))
                words_with_translation.put(word_to_translate, new ArrayList<>());
            words_with_translation.get(word_to_translate).addAll(current_words);
        });
    }

    private static String translateLine(String line, Map<String, List<String>> words_with_translation){

        List<String> words_in_line = new ArrayList<>(Arrays.asList(line.split(" ")));
        StringBuilder result = new StringBuilder();

        for(int index = 0; index < words_in_line.size(); index++){

            String word = words_in_line.get(index);
            if(words_with_translation.keySet().contains(word)){
                int random_index = random.nextInt(words_with_translation.get(word).size());
                String random_word_translated = words_with_translation.get(word).get(random_index);

                words_in_line.set(index, random_word_translated);
            }

            result.append(words_in_line.get(index) + " ");
        }

        return result.toString();
    }

    private static String defineLine(String line, Map<String, List<String>> words_with_translation){

        List<String> words_in_line = new ArrayList<>(Arrays.asList(line.split(" ")));
        StringBuilder result = new StringBuilder();

        for(int index = 0; index < words_in_line.size(); index++){

            String word = words_in_line.get(index);
            if(words_with_translation.keySet().contains(word)){

                words_in_line.remove(index);
                Collections.reverse(words_with_translation.get(word));
                for(String word_to_put : words_with_translation.get(word))
                    words_in_line.add(index, word_to_put);
                index--;
                Collections.reverse(words_with_translation.get(word));
            }
        }

        words_in_line.forEach(word -> result.append(word + " "));

        return result.toString();
    }
}
