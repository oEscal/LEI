import java.util.Scanner;

public class Ex1 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        double num1 = 0,
                num2 = 0,
                result = 0;
        Character operator;

        String imputed_string;
        String[] imputed_strings_separated;

        System.out.print("New operation: ");
        imputed_string = input.nextLine();

        imputed_strings_separated = imputed_string.split(" ");

        // verify if the user imputed just 3 elements
        if(imputed_strings_separated.length != 3){
            System.err.println("Error!\n" +
                                "Input must be <number> <operator> <number>");
            System.exit(1);
        }

        // verify if operator has just one character
        if(imputed_strings_separated[1].length() > 1){
            System.err.println("Error!\n" +
                    "Operator must have just one character");
            System.exit(1);
        }
        operator = imputed_strings_separated[1].charAt(0);

        try{
            num1 = Double.parseDouble(imputed_strings_separated[0]);
            num2 = Double.parseDouble(imputed_strings_separated[2]);
        }catch (java.lang.NumberFormatException e){
            System.err.println("Error!\n" +
                                "Input must be <number> <operator> <number>");
            System.exit(1);
        }


        // calc
        switch (operator){
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '/': result = num1 / num2; break;
            case '*': result = num1 * num2; break;
            default: System.err.println("Error!\n" +
                                        "Operator unknown!");
                    System.exit(1);
        }

        System.out.println("Result: " + result);

    }

}
