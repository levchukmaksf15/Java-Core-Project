package javacorproject.ua.itea;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board {
	public static final int WHITE = 0;
	public static final int BLACK = 0;
	public static final int GRAY = 1;
	public static final int GRAY_WITH_BLACK_CHECKER = 2;
	public static final int GRAY_WITH_WHITE_CHECKER = 3;
	public static final int BOARD_WIDTH = 8;
	public static final int BOARD_LENGTH = 8;

	private List<Checker> listOfCheckers = null;
	private int[][] boardMap = new int[BOARD_LENGTH][BOARD_WIDTH];
	private JPanel boardPanel = null;
	private Cell[][] boardCells = new Cell[BOARD_LENGTH][BOARD_WIDTH];

	public Board(int playerCheckerColor) {

		boardMapFilling();

		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(8, 8));
	}

	private void boardMapFilling() {
		boolean isWhite = true;

		for (int i = 0; i < boardMap.length; i++) {
			for (int j = 0; j < boardMap[i].length; j++) {
				boardCells[i][j] = new Cell(null, new int[] { i, j }, isWhite ? Color.WHITE : Color.GRAY);
				isWhite = !isWhite;
			}
			isWhite = !isWhite;
		}
	}

	public JPanel boardPainting() {
		for (int i = 0; i < boardMap.length; i++) {
			for (int j = 0; j < boardMap.length; j++) {
				boardPanel.add(boardCells[i][j].setButton(new JButton()));
				boardCells[i][j].getButton().setBorderPainted(false);
				boardCells[i][j].getButton()
						.setBackground(boardCells[i][j].getColor() == Color.GRAY ? Color.GRAY : Color.WHITE);
			}
		}
		return boardPanel;
	}

	public int[][] getBoardMap() {
		return boardMap;
	}

	public JPanel getBoardPanel() {
		return boardPanel;
	}

	public Cell[][] getBoardCells() {
		return boardCells;
	}

	public List<Checker> getListOfCheckers() {
		return listOfCheckers;
	}

}
