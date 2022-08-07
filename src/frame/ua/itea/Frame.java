package frame.ua.itea;

import javax.swing.JFrame;
import dataBaseConnection.ua.itea.CheckersDataBaseOperations;

public class Frame extends JFrame {

	private CheckersDataBaseOperations dbOperations;
	private MenuItemListener menuItemListener;

	public Frame() {
		super("Checkers");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setResizable(false);
		setSize(880, 671);
		setLocationRelativeTo(null);
		setVisible(true);
		
		getContentPane().add(new MainMenu(this));
		dbOperations = new CheckersDataBaseOperations();

	}

	public CheckersDataBaseOperations getDbOperations() {
		return dbOperations;
	}

	public MenuItemListener getMenuItemListener() {
		return menuItemListener;
	}
}
