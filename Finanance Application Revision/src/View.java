import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class View {
	// PANELS
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	// WINDOW AND CONTAINER
	private JFrame window;
	private Container container;
	// SUBMIT EXPENSES PANEL
	private JFormattedTextField money;
	private NumberFormat format;
	private JLabel dollarSign;
	private JLabel dollarSign1;
	private JLabel categoryLbl;
	private JComboBox<String> categories;
	private JLabel descriptionLbl;
	private JTextField descriptionTxt;
	private JButton expensesBtn;
	private JLabel expensesLbl;
	private JLabel blankLbl;
	// SUBMIT GOALS PANEL
	private JFormattedTextField goalAmount;
	private JComboBox<String> categories1;
	private JButton goalsBtn;
	private JLabel goalsLbl;
	// EXPENSES SCROLL PANE
	private JScrollPane scrollPane;
	private JTextArea scrollPaneText;
	// GOALS SCROLL PANE
	private JScrollPane goalsScrollPane;
	private JTextArea goalsScrollText;
	// FONTS
	private Font lblFont;
	private Font goalsFont;
	private Font amountFont;
	private Font currencyFont;
	private Font currencyFont2;
	// MARGINS
	private JLabel margins;
	private JLabel blank2;
	private JLabel education;
	private JLabel groceries;
	private JLabel health;
	private JLabel shop;
	private JLabel transportation;
	private JLabel dining;
	private JLabel travel;
	private JLabel insurance;
	private JLabel utilities;
	private JLabel other;
	private JLabel educationResult;
	private JLabel groceriesResult;
	private JLabel healthResult;
	private JLabel shopResult;
	private JLabel transportationResult;
	private JLabel diningResult;
	private JLabel travelResult;
	private JLabel insuranceResult;
	private JLabel utilitiesResult;
	private JLabel otherResult;
	private ArrayList<JLabel> labels;
	// MONTHLY SPENDING
	private JTextArea jan;
	private JTextArea feb;
	private JTextArea mar;
	private JTextArea apr;
	private JTextArea may;
	private JTextArea jun;
	private JTextArea jul;
	private JTextArea aug;
	private JTextArea sep;
	private JTextArea oct;
	private JTextArea nov;
	private JTextArea dec;
	private ArrayList<JTextArea> months;
	// MENU BUTTON
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem blue, red, green, yellow, black;

	public View() {
		// CREATE THE WINDOW
		createWindow();

		// MAKE FRAME FULL SIZE OF THE SCREEN
		makeFrameFullSize(window);

		// CREATE CONTAINER
		createContainer();

		// SET THE FORMATS
		format = NumberFormat.getNumberInstance();
		format.setMinimumFractionDigits(2);

		// SET THE FONTS
		setFonts();

		// CREATE EXPENSE INPUT AREA
		createExpenses();

		// CREATE GOALS INPUT AREA
		createGoals();

		// CREATE SCROLL PANE AND TEXT AREA TO DISPLAY CURRENT GOALS
		createGoalScrollable();

		// CREATE GRIDLAYOUT TO DISPLAY GOAL MARGINS
		createGoalMarginsGrid();

		// CREATE GRIDLAYOUT TO DISPLAY MONTHLY EXPENSES
		createMonthlySpendingGrid();

		// CREATE THE WEST PANEL
		createWestPanel();

		// CREATE EXPENSES SCROLLABLE
		createExpensesScrollable();

		// CREATE MENU BAR
		createMenuBar();
	}

	private void createExpensesScrollable() {
		// CREATE SCROLL PANE AND TEXT AREA TO DISPLAY EXPENSES
		scrollPaneText = new JTextArea();
		scrollPaneText.setEditable(false);
		String title = "Amount\tCategory\tDescription\tDate\n";
		scrollPaneText.setText(title);
		scrollPaneText.setFont(lblFont);
		scrollPaneText.setTabSize(20);
		scrollPane = new JScrollPane(scrollPaneText);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));

		// ADD TO THE CONTAINER
		container.add(scrollPane, BorderLayout.CENTER);
	}

	private void createWestPanel() {
		// CREATE GRIDLAYOUT
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(5, 1));
		// makeLayout();
		panel3.setBorder(new LineBorder(Color.GRAY, 3));
		panel3.setBackground(Color.LIGHT_GRAY);

		// ADD COMPONENTS TO THE GRIDLAYOUT
		panel3.add(expensesLbl);
		panel3.add(blankLbl);
		panel3.add(dollarSign);
		panel3.add(money);
		panel3.add(categoryLbl);
		panel3.add(categories);
		panel3.add(descriptionLbl);
		panel3.add(descriptionTxt);
		panel3.add(expensesBtn);

		// ADD GRID LAYOUT PANEL TO THE EXPENSES PANEL
		panel2.add(panel3);
		panel2.add(panel4);

		// ADD PANEL TO THE CONTAINER
		container.add(panel2, BorderLayout.WEST);
	}

	private void createGoalScrollable() {
		goalsScrollText = new JTextArea(25, 25);
		goalsScrollText.setEditable(false);
		goalsScrollText.setText("Goals:\n");
		goalsScrollText.setFont(lblFont);
		goalsScrollText.setTabSize(15);
		goalsScrollPane = new JScrollPane(goalsScrollText);
		goalsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		goalsScrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));
		container.add(goalsScrollPane, BorderLayout.EAST);
	}

	private void createGoals() {
		// CREATE GOALS TEXT
		goalsLbl = new JLabel("Set Goals:     ");
		goalsLbl.setFont(goalsFont);

		// CREATE LABEL AND DROP DOWN FOR CATEGORY
		categories1 = createCategories();
		categories1.setFont(lblFont);

		// CREATE GOAL AMOUNT
		goalAmount = new JFormattedTextField(format);
		goalAmount.setValue(new Double(0));
		goalAmount.setColumns(10);
		goalAmount.setFont(currencyFont);

		// CREATE SUBMIT BUTTONS
		goalsBtn = new JButton("SUBMIT");
		goalsBtn.setFont(lblFont);

		// CREATE GOALS PANEL
		panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.WHITE, 5));
		panel1.setLayout(new FlowLayout());
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.add(goalsLbl);
		panel1.add(dollarSign1);
		panel1.add(goalAmount);
		panel1.add(categories1);
		panel1.add(goalsBtn);

		// ADD PANEL TO THE CONTAINER
		container.add(panel1, BorderLayout.NORTH);
	}

	private void createExpenses() {
		// CREATE LABEL AND TEXTFIELD TO INSERT EXPENSE
		money = new JFormattedTextField(format);
		money.setValue(new Double(0));
		money.setFont(currencyFont2);
		money.setHorizontalAlignment(SwingConstants.CENTER);
		dollarSign = new JLabel("Amount:");
		dollarSign1 = new JLabel("Amount:");
		dollarSign.setFont(lblFont);
		dollarSign.setHorizontalAlignment(SwingConstants.CENTER);
		dollarSign1.setFont(lblFont);

		// CREATE EXPENSES LABEL AND BLANK LABEL
		expensesLbl = new JLabel("Enter Expenses:");
		expensesLbl.setFont(amountFont);
		blankLbl = new JLabel("");

		// CREATE LABEL AND DROP DOWN FOR CATEGORY
		categories = createCategories();
		categories.setFont(lblFont);
		categoryLbl = new JLabel("Category:");
		categoryLbl.setFont(lblFont);
		categoryLbl.setHorizontalAlignment(SwingConstants.CENTER);

		// CREATE LABEL AND TEXTFIELD FOR DESCRIPTION
		descriptionTxt = new JTextField();
		descriptionTxt.setEditable(true);
		descriptionTxt.setFont(lblFont);
		descriptionLbl = new JLabel("Description:");
		descriptionLbl.setFont(lblFont);
		descriptionLbl.setHorizontalAlignment(SwingConstants.CENTER);

		// CREATE SUBMIT BUTTON
		expensesBtn = new JButton("SUBMIT");
		expensesBtn.setFont(lblFont);

		// CREATE EXPENSES PANEL
		panel2 = new JPanel();
		panel2.setBorder(new LineBorder(Color.WHITE, 5));
		panel2.setLayout(new GridLayout(2, 1));
		panel2.setBackground(Color.LIGHT_GRAY);
	}

	private void createMonthlySpendingGrid() {
		// CREATE GRIDLAYOUT
		panel5 = new JPanel();
		panel5.setLayout(new GridLayout(1, 12));
		panel5.setBorder(new LineBorder(Color.GRAY, 3));
		panel5.setBackground(Color.LIGHT_GRAY);

		// CREATE TEXT AREAS
		jan = new JTextArea();
		jan.setText("January\n");
		jan.setBackground(Color.LIGHT_GRAY);
		jan.setFont(lblFont);
		feb = new JTextArea();
		feb.setText("February\n");
		feb.setBackground(Color.LIGHT_GRAY);
		feb.setFont(lblFont);
		mar = new JTextArea();
		mar.setText("March\n");
		mar.setBackground(Color.LIGHT_GRAY);
		mar.setFont(lblFont);
		apr = new JTextArea();
		apr.setText("April\n");
		apr.setBackground(Color.LIGHT_GRAY);
		apr.setFont(lblFont);
		may = new JTextArea();
		may.setText("May\n");
		may.setBackground(Color.LIGHT_GRAY);
		may.setFont(lblFont);
		jun = new JTextArea();
		jun.setText("June\n");
		jun.setBackground(Color.LIGHT_GRAY);
		jun.setFont(lblFont);
		jul = new JTextArea();
		jul.setText("July\n");
		jul.setBackground(Color.LIGHT_GRAY);
		jul.setFont(lblFont);
		aug = new JTextArea();
		aug.setText("August\n");
		aug.setBackground(Color.LIGHT_GRAY);
		aug.setFont(lblFont);
		sep = new JTextArea();
		sep.setText("September\n");
		sep.setBackground(Color.LIGHT_GRAY);
		sep.setFont(lblFont);
		oct = new JTextArea();
		oct.setText("October\n");
		oct.setBackground(Color.LIGHT_GRAY);
		oct.setFont(lblFont);
		nov = new JTextArea();
		nov.setText("November\n");
		nov.setBackground(Color.LIGHT_GRAY);
		nov.setFont(lblFont);
		dec = new JTextArea();
		dec.setText("December\n");
		dec.setBackground(Color.LIGHT_GRAY);
		dec.setFont(lblFont);

		// CREATE ARRAYLIST OF JTEXTAREAS
		months = new ArrayList<JTextArea>();
		months.add(jan);
		months.add(feb);
		months.add(mar);
		months.add(apr);
		months.add(may);
		months.add(jun);
		months.add(jul);
		months.add(aug);
		months.add(sep);
		months.add(oct);
		months.add(nov);
		months.add(dec);

		// ADD TO PANEL
		for (int i = 0; i < months.size(); i++) {
			panel5.add(months.get(i));
		}
		panel5.setBorder(new LineBorder(Color.WHITE, 5));

		// ADD PANEL TO CONTAINER
		container.add(panel5, BorderLayout.SOUTH);
	}

	private void createGoalMarginsGrid() {
		// CREATE THE PANEL
		panel4 = new JPanel(new GridLayout(11, 2));
		// INTITALIZE JLABELS
		margins = new JLabel("Margins:");
		margins.setFont(amountFont);
		blank2 = new JLabel("");
		education = new JLabel("Education");
		education.setFont(lblFont);
		groceries = new JLabel("Groceries");
		groceries.setFont(lblFont);
		health = new JLabel("Health");
		health.setFont(lblFont);
		shop = new JLabel("Shopping & Entertainment");
		shop.setFont(lblFont);
		transportation = new JLabel("Transportation");
		transportation.setFont(lblFont);
		dining = new JLabel("Restaurants & Dining");
		dining.setFont(lblFont);
		travel = new JLabel("Travel");
		travel.setFont(lblFont);
		insurance = new JLabel("Insurance");
		insurance.setFont(lblFont);
		utilities = new JLabel("Utilities");
		utilities.setFont(lblFont);
		other = new JLabel("Other");
		other.setFont(lblFont);
		educationResult = new JLabel("");
		educationResult.setFont(lblFont);
		groceriesResult = new JLabel("");
		groceriesResult.setFont(lblFont);
		healthResult = new JLabel("");
		healthResult.setFont(lblFont);
		shopResult = new JLabel("");
		shopResult.setFont(lblFont);
		transportationResult = new JLabel("");
		transportationResult.setFont(lblFont);
		diningResult = new JLabel("");
		diningResult.setFont(lblFont);
		travelResult = new JLabel("");
		travelResult.setFont(lblFont);
		insuranceResult = new JLabel("");
		insuranceResult.setFont(lblFont);
		utilitiesResult = new JLabel("");
		utilitiesResult.setFont(lblFont);
		otherResult = new JLabel("");
		otherResult.setFont(lblFont);

		// POPULATE PANEL
		panel4.add(margins);
		panel4.add(blank2);
		panel4.add(education);
		panel4.add(educationResult);
		panel4.add(groceries);
		panel4.add(groceriesResult);
		panel4.add(health);
		panel4.add(healthResult);
		panel4.add(shop);
		panel4.add(shopResult);
		panel4.add(transportation);
		panel4.add(transportationResult);
		panel4.add(dining);
		panel4.add(diningResult);
		panel4.add(travel);
		panel4.add(travelResult);
		panel4.add(insurance);
		panel4.add(insuranceResult);
		panel4.add(utilities);
		panel4.add(utilitiesResult);
		panel4.add(other);
		panel4.add(otherResult);

		// ADD LABELS TO THE ARRAYLIST
		labels = new ArrayList<JLabel>();
		labels.add(educationResult);
		labels.add(groceriesResult);
		labels.add(healthResult);
		labels.add(insuranceResult);
		labels.add(diningResult);
		labels.add(shopResult);
		labels.add(transportationResult);
		labels.add(travelResult);
		labels.add(utilitiesResult);
		labels.add(otherResult);

		// SET BORDER
		panel4.setBorder(new LineBorder(Color.GRAY, 3));
	}

	private void createMenuBar() {
		// CREATE MENU BAR
		menuBar = new JMenuBar();
		menu = new JMenu("Settings");

		// CREATE MENU ITEMS FOR THE COLORS
		blue = new JMenuItem("Blue");
		red = new JMenuItem("Red");
		green = new JMenuItem("Green");
		yellow = new JMenuItem("Yellow");
		black = new JMenuItem("Black");

		// ADD MENU ITEMS TO MENU
		menu.add(blue);
		menu.add(red);
		menu.add(green);
		menu.add(yellow);
		menu.add(black);

		// ADD MENU TO MENU BAR
		menuBar.add(menu);

		// ADD TO THE FRAME
		window.setJMenuBar(menuBar);
	}

	private void createContainer() {
		container = window.getContentPane();
		container.setLayout(new BorderLayout(8, 6));
		container.setBackground(Color.blue);
		window.getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
	}

	private void createWindow() {
		window = new JFrame("Track Your Spending");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JComboBox<String> createCategories() {
		String arr[] = { "Education", "Groceries", "Health", "Insurance", "Restaurants & Dining",
				"Shopping & Entertainment", "Transportation", "Travel", "Utilities", "Other" };
		JComboBox<String> box = new JComboBox<String>(arr);
		return box;
	}

	private void makeFrameFullSize(JFrame aFrame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		aFrame.setSize(screenSize.width, screenSize.height);
	}

	private void setFonts() {
		lblFont = new Font("Verdana", Font.BOLD, 14);
		goalsFont = new Font("Copperplate Gothic Bold", Font.BOLD, 30);
		amountFont = new Font("Copperplate Gothic Bold", Font.BOLD, 24);
		currencyFont = new Font("Consolas", Font.PLAIN, 14);
		currencyFont2 = new Font("Consolas", Font.PLAIN, 26);

	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public JTextField getMoney() {
		return money;
	}

	public void setMoney(JFormattedTextField money) {
		this.money = money;
	}

	public JComboBox<String> getCategories() {
		return categories;
	}

	public void setCategories(JComboBox<String> categories) {
		this.categories = categories;
	}

	public JComboBox<String> getCategories1() {
		return categories1;
	}

	public void setCategories1(JComboBox<String> categories1) {
		this.categories1 = categories1;
	}

	public JTextField getDescriptionTxt() {
		return descriptionTxt;
	}

	public void setDescriptionTxt(JTextField descriptionTxt) {
		this.descriptionTxt = descriptionTxt;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public JTextField getGoalAmount() {
		return goalAmount;
	}

	public void setGoalAmount(JFormattedTextField goalAmount) {
		this.goalAmount = goalAmount;
	}

	public JButton getGoalsBtn() {
		return goalsBtn;
	}

	public void setGoalsBtn(JButton goalsBtn) {
		this.goalsBtn = goalsBtn;
	}

	public JButton getExpensesBtn() {
		return expensesBtn;
	}

	public void setExpensesBtn(JButton expensesBtn) {
		this.expensesBtn = expensesBtn;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTextArea getScrollPaneText() {
		return scrollPaneText;
	}

	public void setScrollPaneText(String string) {
		scrollPaneText.append(string);
	}

	public JTextArea getGoalsScrollText() {
		return goalsScrollText;
	}

	public void setGoalsScrollText(String string) {
		this.goalsScrollText.append(string);
	}

	public void resetGoalsScrollText() {
		goalsScrollText.setText("");
	}

	public NumberFormat getFormat() {
		return format;
	}

	public void setFormat(NumberFormat format) {
		this.format = format;
	}

	public ArrayList<JLabel> getLabels() {
		return labels;
	}

	public void setLabels(ArrayList<JLabel> labels) {
		this.labels = labels;
	}

	public ArrayList<JTextArea> getMonthTexts() {
		return months;
	}

	public void setMonths(ArrayList<JTextArea> months) {
		this.months = months;
	}

	public JMenuItem getBlue() {
		return blue;
	}

	public void setBlue(JMenuItem blue) {
		this.blue = blue;
	}

	public JMenuItem getRed() {
		return red;
	}

	public void setRed(JMenuItem red) {
		this.red = red;
	}

	public JMenuItem getGreen() {
		return green;
	}

	public void setGreen(JMenuItem green) {
		this.green = green;
	}

	public JMenuItem getYellow() {
		return yellow;
	}

	public void setYellow(JMenuItem yellow) {
		this.yellow = yellow;
	}

	public JMenuItem getBlack() {
		return black;
	}

	public void setBlack(JMenuItem black) {
		this.black = black;
	}

}
