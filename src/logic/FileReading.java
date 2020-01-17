package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import database.InsertData;
import database.NewTable;

public class FileReading {

	public void wordsFromFileToDB() {
		String filePath = "C:\\My Files\\My Cooding\\eclipse-workspace\\Java\\01 Project Work\\zodziai.csv";
		String lines = null;

		try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {

			while ((lines = br.readLine()) != null) {

				String[] labels = lines.split(",");

				int group = Integer.parseInt(labels[0]); // labels[0].substring(1)); // pradzioj atsirasdavo kabutes
															// (todel pridetas substring)
				String word = labels[1];
				String phonetic = labels[2];
				int withTo = Integer.parseInt(labels[3]);
				String translation = labels[4]; // labels[4].substring(0, (labels[4].length() - 1)); // gale atsirasdavo
												// kabutes

				InsertData insertWorsToDB = new InsertData();
				insertWorsToDB.insertWordsToDB(group, word, phonetic, withTo, translation);

				// TODO galima bandyti aprasyti metoda, kuris ikelia zodziu grupes i zodziu grupes DB tiesiai is failo

			}
		} catch (Exception e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

	public void isDBFile() {

		File kalbosDezute = new File(
				"C:\\My Files\\My Cooding\\eclipse-workspace\\Java\\01 Project Work\\Kalbos dezute.db");

		if (!kalbosDezute.isFile()) {

			NewTable newTables = new NewTable();
			newTables.creatAllTables(); // Sukure DB su visomis lentelemis

			FileReading insertWordsToDB = new FileReading();
			insertWordsToDB.wordsFromFileToDB(); // sukelia zodzius is failo i DB

			InsertData insertWordGroups = new InsertData(); // sukelia zodziugrupe i DB
			insertWordGroups.insertGoupsToDB("I Grupė");
			insertWordGroups.insertGoupsToDB("II Grupė");
			insertWordGroups.insertGoupsToDB("III Grupė");
		}

	}

}
