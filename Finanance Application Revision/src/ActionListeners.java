import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

	public ActionListeners(JMenuItem b, JMenuItem bk, JMenuItem r, JMenuItem g, JMenuItem y, View v, FinanceModel m) {
		blue = b;
		black = bk;
		red = r;
		green = g;
		yellow = y;
		view = v;
		model = m;
	}

	// CREATE ACTION LISTENER FOR MENU ITEMS
	public void colorListeners() {
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.black);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));
			}
		});
		blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.blue);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
			}
		});
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.red);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.red));
			}
		});
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.green);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.green));
			}
		});
		yellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getContainer().setBackground(Color.yellow);
				view.getWindow().getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.yellow));
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
}
