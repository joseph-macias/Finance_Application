import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class FinanceModel {
	private View view;
	private Goals goals;
	private Months months;
	private String path;
	private String expensesTxt;
	private String goalsTxt;
	private double[] goalsList;
	private double[] originalGoalsList;
	private double[] monthlySpending;
	private File expensesFile;
	private File goalsFile;
	private boolean hasRan;
	private String allGoals;
	private Double total;

	private static DecimalFormat df = new DecimalFormat("#.00");

	public FinanceModel(View v, String e, String g, String p) {
		view = v;
		expensesTxt = e;
		goalsTxt = g;
		path = p;
		hasRan = false;
		allGoals = "";
		total = 0.0;
		goalsList = new double[10];
		originalGoalsList = new double[10];
		monthlySpending = new double[12];
	}

	public void createFolder() {
		// CREATE DIRECTORY
		File customDir = new File(path);
		try {
			// CREATE FILES
			expensesFile = new File(expensesTxt);
			goalsFile = new File(goalsTxt);
			// CREATE FOLDER IF IT DOES NOT ALREADY EXIST
			if (customDir.mkdirs()) {
				// CREATE FILES IN THE FOLDER
				expensesFile.createNewFile();
				goalsFile.createNewFile();
				hasRan = false;
			} else {
				hasRan = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void displayStoredExpenses() {
		Scanner scan = null;
		if (hasRan == true) {
			try {
				scan = new Scanner(expensesFile);
				while (scan.hasNext()) {
					// GET THE STORED EXPENSE
					String output = scan.nextLine();
					// GET THE CATEGORY
					String category = getCategoryForExpense(output);
					// GET THE MONTH
					getMonth(output);
					// calculateMargins(month);
					// GET THE INDEX THE EXPENSE BELONGS TO
					getIndexForExpense(category, output);
					displayExpenses(output);
				}
				displayMonthlyMargins();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (scan != null) {
					scan.close();
				}
			}
		}
	}

	public void displayStoredGoals() {
		Scanner scan = null;
		if (hasRan == true) {
			try {
				scan = new Scanner(goalsFile);
				while (scan.hasNext()) {
					// GET THE STORED GOAL
					String output = scan.nextLine();

					// FIND WHAT INDEX IT BELONGS TO
					String category = getCategory(output);
					int index = getIndex(category, output, true);
					// DISPLAY MARGINS
					displayGoalMargins(index);

					// ADD TO STRING OF GOALS
					allGoals += output + "\n";
					// DISPLAY THE GOAL
					displayGoals(output);
					// ADD TO THE TOTAL
					total += totalGoals(output);
				}
				// displayResults("Total Goals Budget: $" + df.format(total));

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (scan != null) {
					scan.close();
				}
			}
		}
	}

	public void storeExpenses() throws IOException {
		PrintWriter writer = null;
		try {
			// CREATE FILEWRITER
			writer = new PrintWriter(new FileWriter(expensesTxt, true));
			// STORE INPUT IN A STRING
			String expense = view.getMoney().getText();
			String category = view.getCategories().getItemAt(view.getCategories().getSelectedIndex());
			String description = view.getDescriptionTxt().getText();
			String input = expense + "\t" + category + "\t" + description;

			// GET CURRENT DATE AND ADD IT TO INPUT
			input += "\t" + getCurrentDate();
			// ADD INPUT TO EXPENSES TEXT FILE
			writer.println(input);
			// ADD INPUT TO THE SCROLL PANE
			displayExpenses(input);

			// UPDATE MARGINS
			getIndexForExpense(category.toLowerCase(), input);

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void storeGoals() throws IOException {
		PrintWriter writer = null;
		try {
			// CREATE FILEWRITER
			writer = new PrintWriter(new FileWriter(goalsTxt, true));
			// STORE INPUT IN A STRING
			String category = view.getCategories1().getItemAt(view.getCategories1().getSelectedIndex());
			String amount = view.getGoalAmount().getText();
			String input = category + "\t" + amount;
			// CHECK IF THERE IS ALREADY A GOAL FOR THIS CATEGORY STORED
			if (allGoals.contains(category)) {
				updateGoals(input, category);
			} else {
				// WRITE NEW GOAL TO THE TEXT FILE
				writer.println(input);
				// ADD TO THE TOTAL
				total += Double.parseDouble(amount);
				// ADD NEW GOAL TO allGoals
				allGoals += input + "\n";
				// ADD INPUT TO THE SCROLL PANE
				displayGoals(input);
				// displayResults("Total Goals Budget: $" + df.format(total));
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void updateGoals(String input, String category) throws IOException {
		// CREATE BUFFERED READER TO SEARCH FOR GOAL
		BufferedReader br = null;
		PrintWriter writer = null;
		// ARRAYLIST TO HOLD THE OLD CONTENT OF THE GOALS FILE
		ArrayList<String> content = new ArrayList<String>();
		// RESET THE TOTAL
		total = 0.0;
		try {
			br = new BufferedReader(new FileReader(goalsFile));
			// CREATE STRING TO HOLD CURRENT LINE
			String line = "";

			// SEARCH THROUGH FILE
			while ((line = br.readLine()) != null) {
				// CHECK IF LINE CONTAINS THE CATEGORY AND ADD THE NEW GOAL
				if (line.contains(category)) {
					content.add(input);
					// UPDATE THE ARRAY OF GOALS
					String cat = getCategory(input);
					int index = getIndex(cat, input, false);
					updateGoalMargins(cat, index, input);
					// UPDATE MARGINS
					displayGoalMargins(index);
					total += totalGoals(input);
				}
				// WRITE THE REMAINING LINES
				else {
					content.add(line);
					total += totalGoals(line);

				}
			}
			// CREATE BUFFERED WRITER TO OVERWRITE FILE
			writer = new PrintWriter(new FileWriter(goalsFile, false));
			// RESET THE GOALS TEXT IN THE SCROLL PANE
			resetGoals();
			// WRITE THE CONTENT TO THE GOALS FILE AND DISPLAY TO SCROLL PANE
			displayGoals("Goals:");
			for (int i = 0; i < content.size(); i++) {
				writer.println(content.get(i));
				displayGoals(content.get(i));
			}
			// WRITE THE TOTAL GOALS TO THE END OF THE TEXT FILE
			// displayResults("Total Goals Budget: $" + df.format(total));
			// CLOSE WRITER AND READER
			writer.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String[] splitString(String str) {
		return str.split("\t");
	}

	public double totalGoals(String str) {
		// SPLIT DATA BASED ON SPACE
		String[] split = splitString(str);
		// RETURN THE LAST INDEX OF THE ARRAY
		return Double.parseDouble(split[split.length - 1]);
	}

	public String getCategory(String str) {
		// RETURN THE LAST INDEX OF THE ARRAY
		return splitString(str)[0].trim().toLowerCase();
	}

	public String getCategoryForExpense(String str) {
		// RETURN THE LAST INDEX OF THE ARRAY
		return splitString(str)[1].trim().toLowerCase();
	}

	public double getExpense(String str) {
		return Double.parseDouble(splitString(str)[0]);
	}

	public void getIndexForExpense(String category, String output) {
		int index = goalsIndex(category);
		// GET THE AMOUNT SPENT FOR THE EXPENSE
		double expense = getExpense(output);

		// SUBTRACT THE EXPENSE FROM THE GOAL FOR THE CORRESPONDING INDEX
		goalsList[index] -= expense;

		// DISPLAY NEW MARGINS
		displayGoalMargins(index);

	}

	public int getIndex(String category, String output, boolean change) {
		int index = goalsIndex(category);
		if (change == true) {
			// ADD GOAL AMOUNT TO THE CORRECT INDEX
			goalsList[index] = totalGoals(output);
			// ADD TO ORIGINAL LIST IF FIRST ENTRY
			originalGoalsList[index] = totalGoals(output);
		}

		return index;
	}

	public int goalsIndex(String category) {
		// GET THE CATEGORY INDEX
		goals = new Goals(category);
		return goals.getGoalsIndex();

	}

	public void displayGoalMargins(int index) {
		// GET THE ARRAYLIST OF JLABELS
		ArrayList<JLabel> labels = view.getLabels();

		// GET MARGIN
		double margin = goalsList[index];

		// SET JLABEL TO THE CORRECT MARGIN
		labels.get(index).setText("$" + df.format(margin));

		// CHANGE COLOR OF MARGIN
		Color DARK_GREEN = new Color(0, 153, 0);
		Color DARK_RED = new Color(204, 0, 0);
		// POSITIVE
		if (margin > 0) {
			labels.get(index).setForeground(DARK_GREEN);
		}
		// NEGATIVE
		else if (margin < 0) {
			labels.get(index).setForeground(DARK_RED);
		}
		// 0
		else {
			labels.get(index).setForeground(Color.DARK_GRAY);
		}

	}

	public void updateGoalMargins(String category, int index, String input) {
		// GET THE NEW GOAL
		double newGoal = totalGoals(input);
		// CALCULATE THE DIFFERENCE BETWEEN BOTH GOALS
		double difference = newGoal - originalGoalsList[index];

		// ADD THAT DIFFERENCE TO THE ARRAY
		goalsList[index] += difference;

		// CHANGE THE ORIGINAL GOALS LIST
		originalGoalsList[index] = newGoal;

	}

	// GET THE MONTH AS A STRING NAME
	public void getMonth(String str) {
		// SPLIT EXPENSES BASED ON TABS
		String[] split1 = splitString(str);
		// SPLIT DATE BASED ON /
		String[] dateSplit = split1[split1.length - 1].split("/");
		// GET THE MONTH AS AN INTGER
		int month = Integer.parseInt(dateSplit[0]);
		// GET THE EXEPNSE VALUE
		double expense = Double.parseDouble(split1[0]);
		// ADD EXPENSE TO PROPER MONTHLY SPENDING INDEX
		monthlySpending[month - 1] += expense;
		// SET THE TEXT FOR THE CORRECT MONTH

		// GET THE CURRENT MONTH
		// int currentMonth = splitCurrentDate();

	}

	public int splitCurrentDate() {
		// GET CURRENT DATE
		String date = getCurrentDate();
		// SPLIT THE DATE BASED ON /
		String[] dateSplit = date.split("/");
		// RETURN THE MONTH
		return Integer.parseInt(dateSplit[0]);

	}

	public String getCurrentDate() {
		// GET CURRENT DATE AND ADD IT TO INPUT
		DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		return date.format(now);
	}

	public void displayMonthlyMargins() {
		// GET ARRAYLIST OF JLABELS
		ArrayList<JTextArea> monthsList = view.getMonthTexts();
		// DISPLAY ALL MONTHLY MARGINS
		for (int i = 0; i < monthsList.size(); i++) {
			// GET THE MONTH NAME
			months = new Months(i + 1);
			String name = months.getMonthName() + "\n";

			// SET THE TEXT OF TEXT AREA
			monthsList.get(i).setText(name + "$" + monthlySpending[i]);
		}

	}

	public void displayExpenses(String input) {
		view.setScrollPaneText(input + "\n");
	}

	public void displayGoals(String input) {
		view.setGoalsScrollText(input + "\n");
	}

	public void resetGoals() {
		view.resetGoalsScrollText();
	}

}
