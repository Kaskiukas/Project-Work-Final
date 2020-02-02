package logic;

public class Users {

	int userId;
	String name;
	int wordAmount;
	int wordGroup;

	public Users(String name, int wordAmount, int wordGroup) {
		this.name = name;
		this.wordAmount = wordAmount;
		this.wordGroup = wordGroup;
	}

	public Users(int userId, String name, int wordAmount, int wordGroup) {
		this.userId = userId;
		this.name = name;
		this.wordAmount = wordAmount;
		this.wordGroup = wordGroup;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getWordGroup() {
		return wordGroup;
	}

	public void setWordGroup(int wordGroup) {
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

}
