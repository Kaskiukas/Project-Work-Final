package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

	// (used) iraso vartotoja i DB
	public void insertUserToDB(String name, Integer wordAmount, Integer groupId) {
		String sql = "INSERT INTO Users(Vardas,Zodziu_skaicius,Grupes_Id) VALUES(?,?,?)";
		ConnectDataBase cdb = new ConnectDataBase();

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setInt(2, wordAmount);
			pstmt.setInt(3, groupId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// (used) sudeda zodzius i DB
	public void insertWordsToDB(Integer group, String word, String phonetic, Integer withTo, String translation) {
		String sql = "INSERT INTO Words(Grupes_Id,Reiksme,Tarimas,yra_To,Vertimas) VALUES(?,?,?,?,?)";
		ConnectDataBase cdb = new ConnectDataBase();

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, group);
			pstmt.setString(2, word);
			pstmt.setString(3, phonetic);
			pstmt.setInt(4, withTo);
			pstmt.setString(5, translation);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// (used) sukelia zodziu grupe i DB
	public void insertGoupsToDB(String groupName) {
		String sql = "INSERT INTO WordGroups(Pavadinimas) VALUES(?)";
		ConnectDataBase cdb = new ConnectDataBase();

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, groupName);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// (used) issaugoja vartotojo pasirinktus zodziu DB
	public void insertAutoSave(Integer userId, Integer wordId, Integer boxNr) {
		
		String sql = "INSERT INTO Word_in_Box(Vartotojo_Id,Dezutes_Id,Zodzio_Id) VALUES(?,?,?)";
		ConnectDataBase cdb = new ConnectDataBase();

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, boxNr);
			pstmt.setInt(3, wordId);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}