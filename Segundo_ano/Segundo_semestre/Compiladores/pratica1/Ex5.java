import java.util.*;

public class Ex5 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]){

        String operation;
        Map<String, Double> variables = new TreeMap<>();

        while(true){

            System.out.print("> ");
            operation = input.nextLine();

            List<String> imputed_strings_separated = Arrays.asList(operation.split(" "));
            List<String> second_part_operation;
            String var = imputed_strings_separated.get(0);
            double result;

            if(imputed_strings_separated.contains("quit"))
                break;

            if(imputed_strings_separated.contains("=")){
                second_part_operation =
                        imputed_strings_separated.subList(2, imputed_strings_separated.size());
            }else{
                second_part_operation =
                        imputed_strings_separated.subList(0, imputed_strings_separated.size());
                var = "";
            }

            result = equals(second_part_operation, var, variables);
            variables.put(var, result);

            System.out.printf("< %f\n\n", result);
        }
    }

    private static double equals(List<String> words, String var, Map<String, Double> variables){

        double nums[] = new double[2];
        char operator;

        if(words.size() == 1)
            return getValueFromVariableOrNumber(words.get(0), variables);

        // else
        String[] ops = {words.get(0), words.get(2)};
        operator = words.get(1).charAt(0);
        for(int index = 0; index < ops.length; index++)
            nums[index] = getValueFromVariableOrNumber(ops[index], variables);

        return Calculator.calc(nums[0], nums[1], operator);
    }

    private static double getValueFromVariableOrNumber(String value, Map<String, Double> variables){

        return isNumber(value) ? Double.parseDouble(value) : variables.get(value);
    }

    private static boolean isNumber(String w){

        return w.chars().allMatch(Character :: isDigit);
    }
}
