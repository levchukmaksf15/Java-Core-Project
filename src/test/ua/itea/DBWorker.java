package test.ua.itea;
//package dataBaseConnection.ua.itea;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBWorker {

//	public DBWorker() {
//
//		System.out.println("Trying to load driver....");
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("Driver loaded succesfully.");
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//			System.out.println("Error loading driver.");
//		}
//	}
//
//	public Connection getConnection(String server, String dataBese, String user, String password) {
//
//		Connection conn = null;
//
//		try {
//			conn = DriverManager.getConnection(server + dataBese + "?" + "user=" + user + "&password=" + password);
//		} catch (SQLException ex) {
//			// handle any errors
//			System.out.println("SQLException: " + ex.getMessage());
//			System.out.println("SQLState: " + ex.getSQLState());
//			System.out.println("VendorError: " + ex.getErrorCode());
//		}
//
//		return conn;
//	}
//}
