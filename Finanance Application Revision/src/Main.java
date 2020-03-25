import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		buildFinanceViews();

	}

	private static void buildFinanceViews() {
		// CREATE THE APP VIEWS
		View view = new View();

		// CREATE THE STRINGS FOR THE FILE PATHS TO BE PASSED TO THE FINANCE
		// MODEL
		String path = System.getProperty("user.home") + File.separator + ".finance_app";
		String goalsTxt = path + File.separator + "goals.txt";
		String expensesTxt = path + File.separator + "expenses.txt";

		// CREATE THE FINANCE MODEL
		FinanceModel model = new FinanceModel(view, expensesTxt, goalsTxt, path);

		// CREATE FOLDER IF FIRST LOAD UP
		boolean hasRan = model.createFolder();

		// DISPLAY STORED EXPENSES AND GOALS IF APP HAS RAN BEFORE
		if (hasRan == true) {
			model.displayStoredGoals();
			model.displayStoredExpenses();
		}

		// CREATE ACTION LISTENER TO SUBMIT EXPENSES
		view.getExpensesBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// WRITE TO THE EXPENSES.TXT
				try {
					model.storeExpenses();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		// CREATE ACTION LISTENER TO SUBMIT GOALS
		view.getGoalsBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// WRITE TO THE GOALS.TXT
				try {
					model.storeGoals();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});
	}

}
