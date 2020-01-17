package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {

	// TODO kolkas neaprasytas ir nenaudojamas, gal ir neprireiks UPDATE

	public void autoSave(int id, String name, double capacity) {

		ConnectDataBase cdb = new ConnectDataBase();

		String sql = "UPDATE warehouses SET name = ? , " + "capacity = ? " + "WHERE id = ?";

		try (Connection conn = cdb.connectDB(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, name);
			pstmt.setDouble(2, capacity);
			pstmt.setInt(3, id);
			// update
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
