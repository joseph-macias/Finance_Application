import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

	public static void main(String[] args) {
		buildFinanceViews();

	}

	private static void buildFinanceViews() {
		// CREATE THE APP VIEWS
		View view = new View();

		// CREATE THE STRINGS FOR THE FILE PATHS TO BE PASSED TO THE FINANCE
		// MODEL
		String path = System.getProperty("user.home") + "\\.finance_app";
		String goalsTxt = path + "\\goals.txt";
		String expensesTxt = path + "\\expenses.txt";
		
		//CREATE GOALS CLASS
		Goals goals = new Goals("");

		// CREATE THE FINANCE MODEL
		FinanceModel model = new FinanceModel(view,expensesTxt, goalsTxt, path);
		

		// CREATE FOLDER IF FIRST LOAD UP
		model.createFolder();
		
		//DISPLAY STORED EXPENSES AND GOALS
		model.displayStoredGoals();
		model.displayStoredExpenses();
		//model.displayMargins();

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
		
		//CREATE ACTION LISTENER TO SUBMIT GOALS
		view.getGoalsBtn().addActionListener(new ActionListener(){

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
