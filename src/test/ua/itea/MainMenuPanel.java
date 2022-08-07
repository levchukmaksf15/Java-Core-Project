package test.ua.itea;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuPanel {

	private JPanel mainMenuPanel;
	private JButton startGameButton;

	public MainMenuPanel() {
		super();
		mainMenuPanel = new JPanel(new GridLayout(1, 1));
		mainMenuPanel.setVisible(true);
		startGameButton = new JButton("START");
		startGameButton.setLocation(100, 100);
		startGameButton.setSize(50, 100);
		mainMenuPanel.add(startGameButton);

	}

	public JPanel getMainMenuPanel() {
		return mainMenuPanel;
	}

	public void setMainMenuPanel(JPanel mainMenuPanel) {
		this.mainMenuPanel = mainMenuPanel;
	}

	public void initListener() {
		startGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

}
