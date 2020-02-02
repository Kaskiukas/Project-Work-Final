package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import database.InsertData;
import database.NewTable;

public class FileReading {

	// (used) sukelia zodzius is failo i DB
	public void wordsFromFileToDB() {
		
		String filePath = "C:\\My Files\\My Cooding\\eclipse-workspace\\Java\\01 Project Work\\zodziai.csv";
		String lines = null;

		try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {    
			
			br.readLine();
			
			while ((lines = br.readLine()) != null) {

				String[] labels = lines.split(",");
				
				int group = Integer.parseInt(labels[0]); //labels[0].substring(1)); // kai pradzioj atsiranda kabutes (todel pridetas substring)          
				String word = labels[1];
				String phonetic = labels[2];
				int withTo = Integer.parseInt(labels[3]);
				String translation = labels[4]; // labels[4].substring(0, (labels[4].length() - 1)); // kai gale atsiranda kabutes

				InsertData id = new InsertData();
				id.insertWordsToDB(group, word, phonetic, withTo, translation);		// sudeda zodzius i DB

			}
		} catch (Exception e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}
	
	// (used) Tikrina ar egzistuoja tokia duomenu baze
	public void isDBFile() {

		File filenName = new File(
				"C:\\My Files\\My Cooding\\eclipse-workspace\\Java\\01 Project Work\\Kalbos dezute.db");

		if (!filenName.isFile()) {

			NewTable nt = new NewTable();
			nt.creatAllTables(); // Sukure DB su visomis lentelemis

			FileReading fr = new FileReading();
			fr.wordsFromFileToDB(); // sukelia zodzius is failo i DB

			InsertData id = new InsertData(); 
			id.insertGoupsToDB("I Grupė");		// ikelia zodziu grupe i DB
			id.insertGoupsToDB("II Grupė");
			id.insertGoupsToDB("III Grupė");
		}

	}

}
