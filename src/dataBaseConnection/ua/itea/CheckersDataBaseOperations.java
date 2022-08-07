package dataBaseConnection.ua.itea;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javacorproject.ua.itea.Cell;
import javacorproject.ua.itea.Checker;

public class CheckersDataBaseOperations {

	public CheckersDataBaseOperations() {
		loadDriver();
		createCheckerTable();
	}

	public void createCheckerTable() {
		Connection conn = getConnection("jdbc:mysql://localhost/", "checkers_game", "root", "");
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("CREATE TABLE IF NOT EXISTS checker (id INT PRIMARY KEY AUTO_INCREMENT, color VARCHAR(30),"
					+ "x_coordinate INT, y_coordinate INT);");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Checker> openGame(Cell[][] boardCells) {
		Connection conn = getConnection("jdbc:mysql://localhost/", "checkers_game", "root", "");
		Statement st = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery("SELECT * FROM checker");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<Checker> listChecker = new ArrayList<>();
		try {
			while (rs.next()) {
				Color color = rs.getString("color").equals("White") ? Color.WHITE : Color.BLACK;
				for (Cell[] cells : boardCells) {
					for (Cell cell : cells) {
						if (Arrays.equals(cell.getCoordinates(),
								new int[] { rs.getInt("x_coordinate"), rs.getInt("y_coordinate") })) {
							listChecker.add(new Checker(cell, color));
						}
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listChecker;
	}

	public boolean saveGame(ArrayList<Checker> listChecker) {

		for (Checker ch : listChecker) {
			try {
				saveOneCheckerToDB(ch);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	private void saveOneCheckerToDB(Checker ch) throws SQLException {
		Connection conn = getConnection("jdbc:mysql://localhost/", "checkers_game", "root", "");
		Statement st = conn.createStatement();

		String checkerColor = ch.getColor() == Color.WHITE ? "White" : "Black";

		st.execute("INSERT INTO checker (color, x_coordinate, y_coordinate) VALUES ('" + checkerColor + "', "
				+ ch.getCell().getCoordinates()[0] + ", " + ch.getCell().getCoordinates()[1] + ");");
		conn.close();
		st.close();
	}

	public void deleteTableChecker() {
		Connection conn = getConnection("jdbc:mysql://localhost/", "checkers_game", "root", "");
		Statement st = null;
		try {
			st = conn.createStatement();

			st.execute("DELETE FROM checker;");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadDriver() {

		System.out.println("Trying to load driver....");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded succesfully.");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error loading driver.");
		}
	}

	public Connection getConnection(String server, String dataBese, String user, String password) {

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(server + dataBese + "?" + "user=" + user + "&password=" + password);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return conn;
	}
}
