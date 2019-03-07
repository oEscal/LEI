import java.util.*;

public class TreeNode {

    private enum Operators {
        sum('+'), dif('-'), mult('*'), div('/');

        char value;
        Operators(char value){
            this.value = value;
        }
    }

    private String value;
    private TreeNode right_tree,
                    left_tree;
    private List<String> prefix_expression;

    public TreeNode(){
        this.value = "";
        this.right_tree = null;
        this.left_tree = null;
    }

    public boolean expressionTree(List<String> prefix){

        this.prefix_expression = new ArrayList<>();
        for (String word : prefix) {
            if (!insertValue(word))
                return false;
            this.prefix_expression.add(word);
        }

        return true;
    }

    private boolean insertValue(String value){

        if(this.value.length() == 0){
            this.value = value;
            return true;
        }

        // initialize left and right nodes
        if(this.left_tree == null){
            this.left_tree = new TreeNode();
            this.right_tree = new TreeNode();
        }

        for(Operators op : Operators.values())
            if(this.value.length() == 1 && this.value.charAt(0) == op.value){
                if (left_tree.insertValue(value))
                    return true;
                if (right_tree.insertValue(value))
                    return true;
            }

        return false;
    }

    public String getInfix() {

        if(value.length() == 0)
            return "";

        String result = "";

        if(left_tree != null)
            result += left_tree.getInfix() + " ";
        result += value;
        if(right_tree != null)
            result += " " + right_tree.getInfix();

        if(value.charAt(0) == Operators.dif.value || value.charAt(0) == Operators.sum.value)
            result = "(" + result + ")";
        return  result;
    }

    public double eval(){
        List<String> current_prefix_expression = new ArrayList<>(this.prefix_expression);
        char op = current_prefix_expression.remove(0).charAt(0);

        return eval(current_prefix_expression, op);
    }

    private double eval(List<String> operation, char op){

        double[] vals = new double[2];

        for(int index = 0; index < vals.length; index++){
            String current_op;
            if(isNumber(current_op = operation.remove(0)))
                vals[index] = Double.parseDouble(current_op);
            else {
                char new_op = current_op.charAt(0);
                vals[index] = eval(operation, new_op);
            }
        }

        return Calculator.calc(vals[0], vals[1], op);
    }

    private boolean isNumber(String word){
        return word.chars().allMatch(Character::isDigit);
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "value='" + value + '\'' +
                ", left_tree=" + left_tree +
                ", right_tree=" + right_tree +
                '}';
    }
}
