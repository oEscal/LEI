import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ex7 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String args[]){

        String expression = input.nextLine();
        String[] expression_content = expression.split(" ");

        TreeNode tree_head = new TreeNode();
        if(!tree_head.expressionTree(Arrays.asList(expression_content))){
            System.err.println("Error! Wrong input!");
            System.exit(1);
        }

        System.out.println("Infix: " + tree_head.getInfix());
        System.out.println("Result: " + tree_head.eval());
    }



}
