import java.io.File;

public class Main {

	public static void main(String[] args) {
		buildFinanceViews();

	}

	private static void buildFinanceViews() {
		// CREATE THE STRINGS FOR THE FILE PATHS TO BE PASSED TO THE FINANCE
		// MODEL
		String path = System.getProperty("user.home") + File.separator + ".finance_app";
		String goalsTxt = path + File.separator + "goals.txt";
		String expensesTxt = path + File.separator + "expenses.txt";
		String colorTxt = path + File.separator + "color.txt";

		// CREATE THE APP VIEWS
		View view = new View();

		// CREATE THE FINANCE MODEL
		FinanceModel model = new FinanceModel(view, expensesTxt, goalsTxt, colorTxt, path);

		// CREATE FOLDER IF FIRST LOAD UP
		boolean hasRan = model.createFolder();

		// DISPLAY STORED EXPENSES AND GOALS IF APP HAS RAN BEFORE
		if (hasRan == true) {
			model.displayStoredGoals();
			model.displayStoredExpenses();
		}

		// CREATE ACTIONLISTERS
		ActionListeners listeners = new ActionListeners(view.getBlue(), view.getBlack(), view.getRed(), view.getGreen(),
				view.getYellow(), view, model, colorTxt);
		
		//CREATE BACKGROUND ON LOAD UP
		listeners.createBackground();

		// CALL COLOR LISTENERS METHOD
		listeners.colorListeners();

		// CALL SUBMIT LISTENERS METHOD
		listeners.submitListeners();
	}

}
