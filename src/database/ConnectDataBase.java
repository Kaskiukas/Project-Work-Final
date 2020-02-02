package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {

	public static void main(String[] args) {

		ConnectDataBase cdb = new ConnectDataBase();
		cdb.createNewDB();
	}

	public Connection connectDB() {

		// SQLite connection string
		String url = "jdbc:sqlite:C:\\My Files\\My Cooding\\eclipse-workspace\\Java\\01 Project Work\\Kalbos dezute.db";

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void createNewDB() {

		try (Connection conn = this.connectDB()) {
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
