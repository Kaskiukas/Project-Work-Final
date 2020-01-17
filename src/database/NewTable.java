package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewTable {

	public void creatAllTables() {

		// SQL statement for creating a new table
		
		String wordGroup = "CREATE TABLE IF NOT EXISTS WordGroups (\n" 
				+ "    Grupes_Id integer PRIMARY KEY AUTOINCREMENT,\n"
				+ "    Pavadinimas text NOT NULL\n" 
				+ "    );";

		createNewTable(wordGroup);

		String wordTable = "CREATE TABLE IF NOT EXISTS Words (\n" 
				+ "    Zodzio_Id integer PRIMARY KEY AUTOINCREMENT,\n"
				+ "    Grupes_Id integer NOT NULL REFERENCES WordGroups (Grupes_Id),\n" 
				+ "    Reiksme text NOT NULL,\n"
				+ "    Tarimas text NOT NULL,\n"
				+ "    yra_To integer NOT NULL,\n"
				+ "    Vertimas text NOT NULL\n"
				+ "    );";

		createNewTable(wordTable);

		String usersTable = "CREATE TABLE IF NOT EXISTS Users (\n" 
				+ "    Vartotojo_Id integer PRIMARY KEY AUTOINCREMENT,\n"
				+ "    Vardas text NOT NULL,\n" 
				+ "    Zodziu_skaicius text NOT NULL,\n" 
				+ "    Grupes_Id integer NOT NULL REFERENCES WordGroups (Grupes_Id)\n"
				+ "    );";

		createNewTable(usersTable);

		String boxTable = "CREATE TABLE IF NOT EXISTS Boxes (\n" 
				+ "    Dezutes_Id integer PRIMARY KEY AUTOINCREMENT,\n"
				+ "    Vartotojo_Id integer NOT NULL REFERENCES Users (Vartotojo_Id),\n" 
				+ "    Dezutes_Nr integer NOT NULL\n"
				+ "    );";

		createNewTable(boxTable);

		String wordInBox = "CREATE TABLE IF NOT EXISTS Word_in_Box (\n" 
				+ "    Id integer PRIMARY KEY AUTOINCREMENT,\n"
				+ "    Dezutes_Id integer NOT NULL REFERENCES Boxes (Dezutes_Id),\n"
				+ "    Zodzio_Id integer NOT NULL REFERENCES Words (Zodzio_Id)\n" 
				+ "    );";

		createNewTable(wordInBox);
	}

	// Create a new table in the database

	public void createNewTable(String query) {

		ConnectDataBase ndb = new ConnectDataBase();

		try (Connection conn = ndb.connectDB(); 
				Statement stmt = conn.createStatement()) {
			stmt.execute(query); 										// create a new table
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
