import java.util.*;

public class MyCalculatorListener extends CalculatorBaseListener{

	private HashMap<String, Integer> map_number_to_name = new HashMap<>();

	public void enterStat(CalculatorParser.StatContext ctx){
		
		int number = Integer.parseInt(ctx.number.getText());
		String number_name = ctx.name.getText();

		map_number_to_name.put(number_name, number);
	}

	public HashMap<String, Integer> getMap(){
		return map_number_to_name;
	}

}