package logic;

public class Word {

	int wordId;
	int group;
	String word;
	String phonetic;
	int withTo;
	String translation;

	public Word(Integer wordId, Integer group, String word, String phonetic, Integer withTo, String translation) {

		this.wordId = wordId;
		this.group = group;
		this.word = word;
		this.phonetic = phonetic;
		this.withTo = withTo;
		this.translation = translation;
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPhonetic() {
		return phonetic;
	}

	public void setPhonetic(String phonetic) {
		this.phonetic = phonetic;
	}

	public int getWithTo() {
		return withTo;
	}

	public void setWithTo(int withTo) {
		this.withTo = withTo;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

}
