package frame.ua.itea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import dataBaseConnection.ua.itea.CheckersDataBaseOperations;
import javacorproject.ua.itea.Checker;
import javacorproject.ua.itea.Game;

public class MenuItemListener implements ActionListener {

	private Frame mainFrame;
	private CheckersDataBaseOperations dbOperations;
	private Game game;

	public MenuItemListener(Frame mainFrame, Game game) {
		this.mainFrame = mainFrame;
		dbOperations = mainFrame.getDbOperations();
		this.game = game;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(Frame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Save":
			dbOperations.deleteTableChecker();
			dbOperations.saveGame((ArrayList<Checker>) game.getListOfCheckers());
			break;
		case "New":
			game = new Game(mainFrame, 0);
			break;
		case "Exit":
			dbOperations.deleteTableChecker();
			dbOperations.saveGame((ArrayList<Checker>) game.getListOfCheckers());
			mainFrame.dispose();
			break;
		case "Open":
			game = new Game(mainFrame, 2);
			break;
		}
	}

}
