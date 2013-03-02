
public class player {
	private String name;
	private int score;
	public String getName() {
		return name;
	}

	player(String playerName, int playerScore){
		name = playerName;
		score = playerScore;
	}
	
	public String toString(){
		return name + " " + score;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
