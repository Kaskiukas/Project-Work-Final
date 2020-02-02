package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import database.InsertData;
import userWindow.Boxes;

public class Moving {

	// (used) parenka random indeksa
	public int RandomGenerator(ArrayList<Word> randomList) {

		Random randomGenerator = new Random();
		return randomGenerator.nextInt(randomList.size());
	}

	// (used) papildo pirma dezute naujais zodziais
	public void updateBox1ByNewWords(ArrayList<Word> fromList, ArrayList<Word> returnList, ArrayList<Word> toList,
			int newWordsQuantity) {

		updateBoxFromMovingList(returnList, toList); // perkelia zodzius is tarpines dezutes i aukstesne
		if (toList.size() < newWordsQuantity) {
			newWordsQuantity -= toList.size();
			for (int i = 0; i < newWordsQuantity; i++) {

				int index = RandomGenerator(fromList);
				Word movingWord = fromList.remove(index);
				toList.add(movingWord);
			}
		}
	}

	// (used) perkelia zodzius is tarpines dezutes i aukstesne
	public void updateBoxFromMovingList(ArrayList<Word> fromList, ArrayList<Word> toList) {

		toList.addAll(fromList);
		fromList.clear();
	}

	// (used) issaugoja vartotojo pasirinktus zodziu DB
	public void autoSave(ArrayList<Word> fromList, int userId, int boxNr) {

		for (Word word : fromList) {
			int wordId = word.getWordId();

			InsertData is = new InsertData();
			is.insertAutoSave(userId, wordId, boxNr);
		}
	}

	// (used) iesko pirmo netuscio listo
	public Map<Integer, ArrayList<Word>> notEmptyList() {

		Map<Integer, ArrayList<Word>> map = new HashMap<>();

		if (!Boxes.all.getBox1().isEmpty()) {
			map.put(1, Boxes.all.getBox1());
		} else {
			if (!Boxes.all.getBox2().isEmpty()) {
				map.put(2, Boxes.all.getBox2());
			} else {
				if (!Boxes.all.getBox3().isEmpty()) {
					map.put(3, Boxes.all.getBox3());
				} else {
					if (!Boxes.all.getBox4().isEmpty()) {
						map.put(4, Boxes.all.getBox4());
					} else {
						if (!Boxes.all.getBox5().isEmpty()) {
							map.put(5, Boxes.all.getBox5());
						} else {
							if (!Boxes.all.getBox6().isEmpty()) {
								map.put(6, Boxes.all.getBox6());
							} else {
								map.put(1, Boxes.all.getBox1());
							}				
						}
					}
				}
			}
		}
		return map;
	}

}
