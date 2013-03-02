
public class Player {
	/*
	 * 
	 * See klassi eesm�rk on talletada m�ngija nime ja punkte
	 * 
	 */
	String name;
	int score;
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

}
