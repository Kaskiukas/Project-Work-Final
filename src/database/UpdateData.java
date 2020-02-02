package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {

	// (used) atnaujina zodzio dezutes numeri
	public void autoSave(Integer userId, Integer wordId, Integer boxNr) {

		ConnectDataBase cdb = new ConnectDataBase();

		String sql = "UPDATE Word_in_Box SET Dezutes_Id = ?" + " WHERE Vartotojo_Id = ?"  + " AND" + " Zodzio_Id = ?";

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setInt(1, boxNr);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, wordId);
			// update
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
