package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {

	// (used) istrina saugojimo informacija kaip zodis jau ismoktas
	public void deleteAutoSave(Integer userId, Integer wordId, Integer boxNr) {

		ConnectDataBase cdb = new ConnectDataBase();

		String sql = "DELETE FROM Word_in_Box WHERE Dezutes_Id = ?" + " AND" + "  Vartotojo_Id = ?"  + " AND" + " Zodzio_Id = ?";

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setInt(1, boxNr);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, wordId);
			// execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
