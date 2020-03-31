import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;

public class ActionListeners {
	private JMenuItem blue;
	private JMenuItem black;
	private JMenuItem red;
	private JMenuItem green;
	private JMenuItem yellow;
	private View view;
	private FinanceModel model;
	private String colorTxt;

	public ActionListeners(JMenuItem b, JMenuItem bk, JMenuItem r, JMenuItem g, JMenuItem y, View v, FinanceModel m,
			String c) {
		blue = b;
		black = bk;
		red = r;
		green = g;
		yellow = y;
		view = v;
		model = m;
		colorTxt = c;
	}

	// CREATE ACTION LISTENER FOR MENU ITEMS
	public void colorListeners() {
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.black);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));
				// PASS STRING TO STORE NEW COLOR
				storeNewColor("black");
			}
		});
		blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.blue);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
				// PASS STRING TO STORE NEW COLOR
				storeNewColor("blue");
			}
		});
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.red);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.red));
				// PASS STRING TO STORE NEW COLOR
				storeNewColor("red");
			}
		});
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.green);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.green));
				// PASS STRING TO STORE NEW COLOR
				storeNewColor("green");
			}
		});
		yellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.yellow);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.yellow));
				// PASS STRING TO STORE NEW COLOR
				storeNewColor("yellow");
			}
		});
	}

	public void submitListeners() {
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

	public void storeNewColor(String color) {
		PrintWriter writer = null;
		try {
			// CREATE PRINTWRITER
			writer = new PrintWriter(new FileWriter(colorTxt, false));
			writer.println(color);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void createBackground() {
		// CREATE COLOR FILE
		File colorFile = new File(colorTxt);
		Scanner scan = null;
		String color = "";
		try {
			scan = new Scanner(colorFile);
			while (scan.hasNext()) {
				// GET STORED COLOR
				color = scan.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}

		// USE SWITCH TO CHANGE BACKGROUND TO SAVED COLOR
		switch (color) {
		case "black":
			view.getContainer().setBackground(Color.black);
			view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));
			break;
		case "blue":
			view.getContainer().setBackground(Color.blue);
			view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
			break;
		case "red":
			view.getContainer().setBackground(Color.red);
			view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.red));
			break;
		case "green":
			view.getContainer().setBackground(Color.green);
			view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.green));
			break;
		case "yellow":
			view.getContainer().setBackground(Color.yellow);
			view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.yellow));
			break;
		// DEFAULT
		case "":
			view.getContainer().setBackground(Color.blue);
			view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
			break;
		}

	}

}
