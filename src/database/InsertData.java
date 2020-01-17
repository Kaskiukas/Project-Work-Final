package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {

	public void insertUserInBoxDB(Integer userId, Integer boxNr) {
		String sql = "INSERT INTO Boxes(Vartotojo_Id,Dezutes_Nr) VALUES(?,?)";
		ConnectDataBase cdb = new ConnectDataBase();

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, boxNr);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void inserWordInBoxDB(Integer boxId, Integer wordId) {
		String sql = "INSERT INTO Word_in_Box(Dezutes_Id,Zodzio_Id) VALUES(?,?)";
		ConnectDataBase cdb = new ConnectDataBase();

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boxId);
			pstmt.setInt(2, wordId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

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
}