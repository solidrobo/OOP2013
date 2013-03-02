import javax.swing.JOptionPane;

public class main {

	/*
	 * 
	 * Peaklass milles kasutatakse player klassi ja täringu klassi
	 *
	 */
	static int scoreLimit = 10;
	public static void main(String[] args) {
		dieRoll die = new dieRoll();
		String input = JOptionPane.showInputDialog(null, "Sisesta 1. mängija nimi" ,  "NB!", JOptionPane.QUESTION_MESSAGE);  
		Player player1 = new Player(input,0);
		input = JOptionPane.showInputDialog(null, "Sisesta 2. mängija nimi" ,  "NB!", JOptionPane.QUESTION_MESSAGE);  
		Player player2 = new Player(input,0);
		boolean player1Turn=true;
		while((player2.score < scoreLimit) & (player1.score< scoreLimit)){
			if(player1Turn){
				JOptionPane.showMessageDialog(null, String.format("On mängija %s kord" ,player1.name), "Info", 1);
				die.roll();
				int result = die.getValue1();
				JOptionPane.showMessageDialog(null, String.format("Mängija %s viskas %d silma" ,player1.name, result), "Info", 1);
				player1.score+=result;
				JOptionPane.showMessageDialog(null, String.format("Mängija %s kogu skoor on %d silma" ,player1.name, player1.score), "Info", 1);
				player1Turn=false;
			}
			else {
				JOptionPane.showMessageDialog(null, String.format("On mängija %s kord" ,player2.name), "Info", 1);
				die.roll();
				int result = die.getValue1();
				JOptionPane.showMessageDialog(null, String.format("Mängija %s viskas %d silma" ,player2.name, result), "Info", 1);
				player2.score+=result;
				JOptionPane.showMessageDialog(null, String.format("Mängija %s kogu skoor on %d silma" ,player2.name, player2.score), "Info", 1);
				player1Turn=true;
			}
	
		}
		if(player1.score > player2.score){
			JOptionPane.showMessageDialog(null, String.format("Võitis mängija %s\nKogu skoor %d silma" ,player1.name, player1.score), "Info", 1);
		}
		else{
			JOptionPane.showMessageDialog(null, String.format("Võitis mängija %s\nKogu skoor %d silma" ,player2.name, player2.score), "Info", 1);
		}
	
		}

}
