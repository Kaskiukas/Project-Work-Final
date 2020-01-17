package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {

	// TODO reikalingas metodas (kolkas neparasytas ir nenaudojamas) kuris:
	/*
	 * istrintu visa informacija apie vartotojui priskirtus zodzius, dezutes,
	 * panaudojamas tik kaip "ismokstami visi pasirinktos grupes zodziai ir norima
	 * pasirinkti nauja zodziu grupe
	 */

	public void delete(int id) {

		ConnectDataBase cdb = new ConnectDataBase();

		String sql = "DELETE FROM  ";

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setInt(1, id);
			// execute the delete statement
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
