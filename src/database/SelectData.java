package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.AllLists;
import logic.Word;

public class SelectData {

	public void selectWordsForStart(ArrayList<Word> userList, Integer gr) {

		ConnectDataBase cdb = new ConnectDataBase();

		String groupId = gr.toString();
		String sql = "SELECT Zodzio_Id, pavadinimas, reiksme, Tarimas, yra_To, vertimas FROM Words w\r\n"
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

	// TODO Sis metodas dar nepabaigtas ir nenaudojamas

	public void selectWordsOfUser(String userName) {

		ConnectDataBase cdb = new ConnectDataBase();
		AllLists al = new AllLists();

		String sql = ""; // TODO parasyti Query kuris is DB isrinktu pagal dezutes ir sudeliotu zodzius i
							// listus

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

				if (rs.getInt("Dezutes_Id") == 0) {
					al.getUserList().add(words);
				}
				if (rs.getInt("Dezutes_Id") == 1) {
					al.getBox1().add(words);
				}
				if (rs.getInt("Dezutes_Id") == 2) {
					al.getBox2().add(words);
				}
				if (rs.getInt("Dezutes_Id") == 3) {
					al.getBox3().add(words);
				}
				if (rs.getInt("Dezutes_Id") == 4) {
					al.getBox4().add(words);
				}
				if (rs.getInt("Dezutes_Id") == 5) {
					al.getBox5().add(words);
				}
				if (rs.getInt("Dezutes_Id") == 6) {
					al.getBox6().add(words);
				}

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public int selectWordGroup(String gruopName) {

		ConnectDataBase cdb = new ConnectDataBase();

		int groupID = 0;
		String sql = "SELECT Grupes_Id FROM WordGroups wg WHERE pavadinimas = " + "\"" + gruopName + "\"";

		try (Connection conn = cdb.connectDB();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			groupID = rs.getInt("Grupes_Id");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return groupID;
	}

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
}
