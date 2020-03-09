
public class Goals {
	private String category;
	
	public Goals(String c){
		category = c;
	}
	
	public int getGoalsIndex() {
		int index = 0;

		switch (category) {
		case "education":
			index = 0;
			break;
		case "groceries":
			index = 1;
			break;
		case "health":
			index = 2;
			break;
		case "insurance":
			index = 3;
			break;
		case "restaurants & dining":
			index = 4;
			break;
		case "shopping & entertainment":
			index = 5;
			break;
		case "transportation":
			index = 6;
			break;
		case "travel":
			index = 7;
			break;
		case "utilities":
			index = 8;
			break;
		case "other":
			index = 9;
			break;
		}
		return index;

	}
	
}
