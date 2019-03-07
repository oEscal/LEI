import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Ex2 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        String imputed_string;
        List<String> imputed_strings_separated;
        Stack<Double> stack = new Stack();

        imputed_string = input.nextLine();

        imputed_strings_separated = Arrays.asList(imputed_string.split(" "));

        imputed_strings_separated.forEach(item -> {
            try{
                Double new_item = Double.parseDouble(item);

                stack.push(new_item);
            }catch (java.lang.NumberFormatException e){
                if(stack.size() < 2){
                    System.err.println("Error!\n" +
                                        "Operation unknown");
                    System.exit(1);
                }

                double num1 = stack.pop(),
                        num2 = stack.pop(),
                        result;
                char operator;

                if(item.length() > 1){
                        System.err.println("Error!\n" +
                                "Operator must have just one character");
                        System.exit(1);
                    }
                    operator = item.charAt(0);

                result = calc(operator, num1, num2);
                stack.push(result);

            }

            System.out.println(stack.toString());
        });


        // result
        if(stack.size() != 1){
            System.err.println("Error!\n" +
                                "Ambiguous result");
            System.exit(1);
        }

        System.out.println(stack.toString());

    }

    private static double calc(char operator, double num1, double num2){
        switch (operator){
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '/': return num1 / num2;
            case '*': return num1 * num2;
            default: System.err.println("Error!\n" +
                    "Operator unknown!");
                System.exit(1);
        }

        return 0;
    }

}
