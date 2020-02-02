package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.Users;
import logic.Word;

public class SelectData {

	// (used) sudedami zodziai i userlista
	public void selectWordsForStart(ArrayList<Word> userList, Integer gr) {

		ConnectDataBase cdb = new ConnectDataBase();

		String groupId = gr.toString();
		String sql = "SELECT Zodzio_Id, Pavadinimas, reiksme, Tarimas, yra_To, vertimas FROM Words w\r\n"
				+ "JOIN WordGroups wg ON w.Grupes_Id = wg.Grupes_Id\r\n" + "Where w.Grupes_Id = " + groupId;

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {

				int wordId = rs.getInt("Zodzio_Id");
				int group = rs.getInt("Pavadinimas");
				String word = rs.getString("reiksme");
				String phonetic = rs.getString("Tarimas");
				int withTo = rs.getInt("yra_To");
				String translation = rs.getString("Vertimas");

				Word words = new Word(wordId, group, word, phonetic, withTo, translation);
				userList.add(words);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// (used) uzkrauna zodzius i lista po autosave
	public void selectWordsOfUser(Integer userId, ArrayList<Word> intoList, Integer boxNr) {

		ConnectDataBase cdb = new ConnectDataBase();

		String uId = userId.toString();
		String bNr = boxNr.toString();
		String sql = "SELECT Zodzio_Id, Grupes_Id, reiksme, Tarimas, yra_To, vertimas FROM Words \r\n"
				+ "WHERE Zodzio_Id IN (SELECT Zodzio_Id FROM Word_in_Box WHERE Vartotojo_Id = " + uId
				+ " AND Dezutes_Id = " + bNr + ")";

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {

				int wordId = rs.getInt("Zodzio_Id");
				int group = rs.getInt("Grupes_Id");
				String word = rs.getString("reiksme");
				String phonetic = rs.getString("Tarimas");
				int withTo = rs.getInt("yra_To");
				String translation = rs.getString("Vertimas");

				Word words = new Word(wordId, group, word, phonetic, withTo, translation);
				intoList.add(words);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// (used) suranda zodziu grupes id
	public int selectWordGroup(String gruopName) {

		ConnectDataBase cdb = new ConnectDataBase();

		int groupID = 0;
		String sql = "SELECT Grupes_Id FROM WordGroups WHERE pavadinimas = " + "\"" + gruopName + "\"";

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			groupID = rs.getInt("Grupes_Id");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return groupID;
	}

	// (used) iesko DB ar toks vartotojo vardas yra
	public boolean searchUsername(String userName) {

		ConnectDataBase cdb = new ConnectDataBase();

		boolean isUserName = false;
		String sql = "SELECT Vardas FROM Users";

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next() || isUserName) {
				if (rs.getString("Vardas").equalsIgnoreCase(userName)) {
					isUserName = true;
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return isUserName;
	}

	// (used) sudeta vartotoju vardus i lista
	public void allUsersList(ArrayList<String> allUsers) {

		ConnectDataBase cdb = new ConnectDataBase();

		String sql = "SELECT Vardas FROM Users";

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {

				allUsers.add(rs.getString("Vardas"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// (used) sukuria vartotojo objekta (OBJ) ir ideda i lista
	public void selectUser(ArrayList<Users> userOBJ, String userName) {

		ConnectDataBase cdb = new ConnectDataBase();

		String sql = "SELECT * FROM Users WHERE Vardas = " + "\"" + userName + "\"";

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {

				int userId = rs.getInt("Vartotojo_Id");
				String name = rs.getString("Vardas");
				int wordAmount = rs.getInt("Zodziu_skaicius");
				int wordGroup = rs.getInt("Grupes_Id");

				Users user = new Users(userId, name, wordAmount, wordGroup);
				userOBJ.add(user);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
		// (used) suskaiciuoja visus userio zodzius
		public int countAllUserWord(Integer userId) {

			ConnectDataBase cdb = new ConnectDataBase();

			int count = 0;
			String sql = "SELECT COUNT (Zodzio_Id) AS Total FROM Word_in_Box WHERE Vartotojo_Id = " + userId;

			try (Connection conn = cdb.connectDB();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {

				count = rs.getInt("Total");

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return count;
		}	
		
		// (used) suskaiciuoja visus userio ismoktus zodzius
				public int countAllLearnedUserWord(Integer userId) {

					ConnectDataBase cdb = new ConnectDataBase();

					int count = 0;
					String sql = "SELECT COUNT (Zodzio_Id) AS Total FROM Word_in_Box WHERE Vartotojo_Id = " + userId + " AND Dezutes_Id = " + 7;

					try (Connection conn = cdb.connectDB();
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery(sql)) {

						count = rs.getInt("Total");

					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					return count;
				}

}
