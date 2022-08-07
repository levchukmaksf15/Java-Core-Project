package frame.ua.itea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javacorproject.ua.itea.Game;

public class MainMenu extends JPanel {
	private JPanel buttons = new JPanel(new GridBagLayout());
	private JButton startButton;
	private JButton loadGameButton;
	private JButton exitButton;
	private Frame mainFrame;

	public MainMenu(Frame mainFrame) {
		this.mainFrame = mainFrame;

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;

		add(new JLabel("<html><h1><strong><i>Checkers</i></strong></h1><hr></html>"), gbc);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		buttons.add(startButton = new JButton("Start New Game"), gbc);
		buttons.add(loadGameButton = new JButton("Load Saved Game"), gbc);
		buttons.add(exitButton = new JButton("Exit"), gbc);

		gbc.weighty = 1;
		add(buttons, gbc);
		initListener();
	}

	public void initListener() {
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Game(mainFrame, 0);
			}
		});

		loadGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Game(mainFrame, 2);
			}
		});

		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
	}

}
