package frame.ua.itea;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar {

	private JMenuBar menuBar;
	private Frame mainFrame;
//	private JPanel boardPanel;
	private MenuItemListener menuItemListener;

	public MyMenuBar(Frame frame, MenuItemListener listener) {
		this.mainFrame = frame;
		this.menuItemListener = listener;
//		this.boardPanel = mainFrame.getGame().getBoardPanel();

		createMenuBar();
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(Frame mainFrame) {
		this.mainFrame = mainFrame;
	}

//	public JPanel getBoardPanel() {
//		return boardPanel;
//	}
//
//	public void setBoardPanel(JPanel boardPanel) {
//		this.boardPanel = boardPanel;
//	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	private void createMenuBar() {
		menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");

		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		newMenuItem.setActionCommand("New");

		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setActionCommand("Open");

		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setActionCommand("Save");

		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setActionCommand("Exit");

		newMenuItem.addActionListener(menuItemListener);
		openMenuItem.addActionListener(menuItemListener);
		saveMenuItem.addActionListener(menuItemListener);
		exitMenuItem.addActionListener(menuItemListener);

		gameMenu.add(newMenuItem);
		gameMenu.add(openMenuItem);
		gameMenu.add(saveMenuItem);
		gameMenu.add(exitMenuItem);

		menuBar.add(gameMenu);
	}
}
