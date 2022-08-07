package javacorproject.ua.itea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dataBaseConnection.ua.itea.CheckersDataBaseOperations;
import frame.ua.itea.Frame;
import frame.ua.itea.MenuItemListener;
import frame.ua.itea.MyMenuBar;

public class Game {

	private static final int WHITE = 0;

	private String name = "Game";

	private Frame frame;
	private Board board;
	private JPanel boardPanel;
	private Cell[][] boardCells;
	private List<Checker> listOfCheckers;

	private boolean isWhiteCheckerMove = true;
	private Checker choosenCheckerForMove;
	private Cell choosenCellForMove;

	private int playerCheckersColor;
	private MyMenuBar menuBar;

	private CheckersDataBaseOperations dbOperations;

	public Game(Frame frame, int playerCheckersColor) {
		dbOperations = new CheckersDataBaseOperations();

		this.frame = frame;
		board = new Board(this.playerCheckersColor = playerCheckersColor);
		boardPanel = board.boardPainting();
		boardCells = board.getBoardCells();

		this.listOfCheckers = playerCheckersColor == 2 ? dbOperations.openGame(boardCells)
				: createDefaulListOfCheckers();
		setCheckersOnTheBoard();

		this.frame.getContentPane().removeAll();
		this.frame.getContentPane().add(boardPanel);
		this.frame.pack();

		menuBar = new MyMenuBar(frame, new MenuItemListener(frame, this));
		frame.setJMenuBar(menuBar.getMenuBar());

		initListeners();
	}

	public List<Checker> getListOfCheckers() {
		return listOfCheckers;
	}

	public void setListOfCheckers(List<Checker> listOfWhiteCheckers) {
		this.listOfCheckers = listOfWhiteCheckers;
	}

	private List<Checker> createDefaulListOfCheckers() {
		List<Checker> list = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i < 3 && boardCells[i][j].getColor() == Color.GRAY) {
					list.add(new Checker(boardCells[i][j], playerCheckersColor == WHITE ? Color.BLACK : Color.WHITE));
				} else if (i > 4 && boardCells[i][j].getColor() == Color.GRAY) {
					list.add(new Checker(boardCells[i][j], playerCheckersColor == WHITE ? Color.WHITE : Color.BLACK));
				}
			}
		}

		return list;
	}

	private void setCheckersOnTheBoard() {
		for (int i = 0; i < listOfCheckers.size(); i++) {
			for (int j = 0; j < boardCells.length; j++) {
				for (int k = 0; k < boardCells[j].length; k++) {
					if (Arrays.equals(listOfCheckers.get(i).getCell().getCoordinates(), new int[] { j, k })) {
						if (listOfCheckers.get(i).getColor() == Color.WHITE)
							System.out.println("One in");
						boardCells[j][k].getButton()
								.setIcon(listOfCheckers.get(i).getColor() == Color.WHITE
										? new ImageIcon("Images/WhiteChecker.png")
										: new ImageIcon("Images/BlackChecker.png"));
					}
				}
			}
		}
	}

	private void initListeners() {
		if (boardCells != null) {
			for (int i = 0; i < boardCells.length; i++) {
				for (int j = 0; j < boardCells[i].length; j++) {
					Cell cell = boardCells[i][j];

					boardCells[i][j].getButton().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (choosenCheckerForMove == null) {
								for (int k = 0; k < listOfCheckers.size(); k++) {
									if (listOfCheckers.get(k).getCell() == cell
											&& (isWhiteCheckerMove && listOfCheckers.get(k).getColor() == Color.WHITE
													|| !isWhiteCheckerMove
															&& listOfCheckers.get(k).getColor() == Color.BLACK)) {
										choosenCheckerForMove = listOfCheckers.get(k);
										cell.getButton().setBackground(Color.RED);
										break;
									}
								}

							} else if (choosenCellForMove == null) {
								choosenCellForMove = cell;
								choosenCheckerForMove.getCell().getButton().setBackground(Color.GRAY);

								if (move(choosenCheckerForMove, choosenCellForMove)) {
									isWhiteCheckerMove = !isWhiteCheckerMove;
								}

								choosenCheckerForMove = null;
								choosenCellForMove = null;
							}
						}
					});
				}
			}
		}

	}

	private boolean move(Checker checker, Cell cell) {
		if (!checkIfTheCellIsFreeToMoveOnIt(cell)) {
			return false;
		}

		if ((int) Math.abs(checker.getCell().getCoordinates()[0] - cell.getCoordinates()[0]) == 2
				&& (int) Math.abs(checker.getCell().getCoordinates()[1] - cell.getCoordinates()[1]) == 2) {
			if (!checkIfThereIsCheckerOppositeColorOfCheckerBetweenTheCellAndTheChecker(cell, checker)) {
				return false;
			}
			cell.getButton().setIcon(checker.getCell().getButton().getIcon());
			checker.getCell().getButton().setIcon(null);
			checker.setCell(cell);
			return true;
		}

		if (checker.getColor() == (playerCheckersColor == 1 ? Color.WHITE : Color.BLACK)) {
			if (checker.getCell().getCoordinates()[0] - cell.getCoordinates()[0] == -1
					&& (int) Math.abs(checker.getCell().getCoordinates()[1] - cell.getCoordinates()[1]) == 1) {
				cell.getButton().setIcon(checker.getCell().getButton().getIcon());
				checker.getCell().getButton().setIcon(null);
				checker.setCell(cell);
			} else {
				return false;
			}
		} else if (checker.getColor() == (playerCheckersColor == 1 ? Color.BLACK : Color.WHITE)) {
			if (checker.getCell().getCoordinates()[0] - cell.getCoordinates()[0] == 1
					&& (int) Math.abs(checker.getCell().getCoordinates()[1] - cell.getCoordinates()[1]) == 1) {
				cell.getButton().setIcon(checker.getCell().getButton().getIcon());
				checker.getCell().getButton().setIcon(null);
				checker.setCell(cell);
			} else {
				return false;
			}
		}

		return true;
	}

	private boolean checkIfThereIsCheckerOppositeColorOfCheckerBetweenTheCellAndTheChecker(Cell cell, Checker checker) {

		int[] coordinatesOfTheCellBetweenTheCellToMoveOnItAndTheChecker = new int[] {
				(int) Math.abs((cell.getCoordinates()[0] + checker.getCell().getCoordinates()[0]) / 2),
				(int) Math.abs((cell.getCoordinates()[1] + checker.getCell().getCoordinates()[1]) / 2) };

		for (int i = 0; i < listOfCheckers.size(); i++) {
			if (Arrays.equals(coordinatesOfTheCellBetweenTheCellToMoveOnItAndTheChecker,
					listOfCheckers.get(i).getCell().getCoordinates())
					&& listOfCheckers.get(i).getColor() != checker.getColor()) {
				listOfCheckers.get(i).getCell().getButton().setIcon(null);
				listOfCheckers.remove(i);
				return true;
			}
		}
		return false;
	}

	private boolean checkIfTheCellIsFreeToMoveOnIt(Cell cell) {

		for (int i = 0; i < listOfCheckers.size(); i++) {
			if (Arrays.equals(listOfCheckers.get(i).getCell().getCoordinates(), cell.getCoordinates())) {
				return false;
			}
		}

		return true;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public JPanel getBoardPanel() {
		return boardPanel;
	}

	public void setBoardPanel(JPanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
