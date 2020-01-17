package logic;

public class Users {

	String name;
	int wordAmount;
	int wordGroup;

	public Users(String name, int wordAmount, int wordGroup) {
		this.name = name;
		this.wordAmount = wordAmount;
		this.wordGroup = wordGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWordAmount() {
		return wordAmount;
	}

	public void setWordAmount(int wordAmount) {
		this.wordAmount = wordAmount;
	}

	public int getWordLevel() {
		return wordGroup;
	}

	public void setWordLevel(int wordGroup) {
		this.wordGroup = wordGroup;
	}

}
