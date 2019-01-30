package tests;

import java.util.Arrays;

public class GenerateRandomStrings {
    /************************************************************
     *  Function that generates a random word with a dimension  *
     *  between "min_length"->"max_length"                      *
     *  Arguments:                                              *
     *      int min_length-> Minimum size of the word           *
     *      int max_length-> Maximum size of the word           *
     ************************************************************/
    public static String generate(int min_length, int max_length) {
        int length = (int) (Math.random() * (max_length - min_length)) + min_length;
        String result = "";
        int value_random;

        int[] interval_chars = new int[122 - 97];
        Arrays.setAll(interval_chars, i -> i + 97);
        for (int num = 0; num < length; num++) {
            value_random = (Math.abs((int) System.nanoTime())) % 58;
            if (value_random >= 48 && value_random <= 57) {
                result = result.concat(Character.toString((char) value_random));
                continue;
            }
            result = result.concat(Character.toString((char) (interval_chars[(int) (Math.random() * interval_chars.length)] - (Math.random() > 0.5 ? 32 : 0))));
        }
        return result;
    }
}
