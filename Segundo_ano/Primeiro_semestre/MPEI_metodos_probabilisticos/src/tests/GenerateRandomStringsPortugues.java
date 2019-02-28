package tests;

import java.util.Map;

public class GenerateRandomStringsPortugues {

    /************************************************************************
     *  Function that generates a random word with a dimension              *
     *  between "min_length"->"max_length" and with a given probability     *
     *  Arguments:                                                          *
     *      int min_length-> Minimum size of the word                       *
     *      int max_length-> Maximum size of the word                       *
     *      Map<Character, Float> probAcumulada-> Map with the accumulative *
     *          probabilities of each letter                                *
     ************************************************************************/

    public static String generate(int min_length, int max_length, Map<Character, Float> probAcumulada) {
        String result = "";
        int length = (int) (Math.random() * (max_length - min_length)) + min_length;


        for (int i = 0; i < length; i++) {
            double random_value = Math.random();
            for (Map.Entry<Character, Float> entry : probAcumulada.entrySet()) {
                if (random_value < probAcumulada.get(entry.getKey())) {
                    result = result.concat(Character.toString(entry.getKey()));
                    break;
                }
            }
        }
        return result;
    }
}