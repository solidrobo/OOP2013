
public class Player {
	/*
	 * 
	 * See klassi eesm�rk on talletada m�ngija nime ja punkte
	 * 
	 */
	private String name;
	private int score;
	public String getName() {
		return name;
	}

	Player(String playerName, int playerScore){
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
