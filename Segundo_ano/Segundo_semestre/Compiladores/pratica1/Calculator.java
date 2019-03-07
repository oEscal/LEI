public class Calculator {

    public static double calc(double num1, double num2, char operator){
        // calc
        double result = 0;
        switch (operator){
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '/': result = num1 / num2; break;
            case '*': result = num1 * num2; break;
            default: System.err.println("Error!\n" +
                    "Operator unknown!");
                System.exit(1);
        }

        return result;
    }

}
