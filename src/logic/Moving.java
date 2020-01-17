package logic;

import java.util.ArrayList;
import java.util.Random;

public class Moving {

	// Metodas random zodziam parinkti
	public int RandomGenerator(ArrayList<Word> randomList) {

		Random randomGenerator = new Random();
		return randomGenerator.nextInt(randomList.size());
	}

	// metodas papildyti pirma dezute naujais zodziais
	public void updateBox1ByNewWords(ArrayList<Word> fromList, ArrayList<Word> returnList, ArrayList<Word> toList,
			int newWordsQuantity) {

		updateBoxFromMovingList(returnList, toList);
		if (toList.size() < newWordsQuantity) {
			newWordsQuantity -= toList.size();
			for (int i = 0; i < newWordsQuantity; i++) {

				int index = RandomGenerator(fromList);
				Word movingWord = fromList.remove(index);
				toList.add(movingWord);
			}
		}
	}

	// metodas perkelti zodzius is tarpines dezutes i aukstesne
	public void updateBoxFromMovingList(ArrayList<Word> fromList, ArrayList<Word> toList) {

		toList.addAll(fromList);
		fromList.clear();
	}

}
