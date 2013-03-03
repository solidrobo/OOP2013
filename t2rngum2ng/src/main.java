import javax.swing.JOptionPane;

public class main {

	/*
	 * 
	 * Peaklass milles kasutatakse player klassi ja t�ringuveeretamise klassi
	 *
	 */
	static int scoreLimit = 91; // V6itmiseks vajalik punktisumma
	public static void main(String[] args) {
		dieRoll die = new dieRoll(); // t2ringu veeretamiseks tehakse isend
		
		// m2ngijate nimede kysimine ja vastavate isendite loomine
		String input = JOptionPane.showInputDialog(null, "Sisesta 1. m�ngija nimi" ,  "NB!", JOptionPane.QUESTION_MESSAGE);  
		Player player1 = new Player(input,0);
		input = JOptionPane.showInputDialog(null, "Sisesta 2. m�ngija nimi" ,  "NB!", JOptionPane.QUESTION_MESSAGE);  
		Player player2 = new Player(input,0);
		
		boolean player1Turn=true; //alguses on esimese m2ngija kord
		
		
		while((player2.score < scoreLimit) & (player1.score< scoreLimit)){  //j2tkata, kuni kumbki pole v6itnud
			// kui on esimese m2ngija kord:
			if(player1Turn){ 
				JOptionPane.showMessageDialog(null, String.format("On m�ngija %s kord" ,player1.name), "Info", 1); // info kuvamine
				die.roll(); // veeretamine
				int result = die.getValue(); // kysitakse veeretamise tulemus
				JOptionPane.showMessageDialog(null, String.format("M�ngija %s viskas %d silma" ,player1.name, result), "Info", 1); // tulemuse kuvamine
				player1.score+=result; // tulemus lisatakse m2ngija skoorile
				JOptionPane.showMessageDialog(null, String.format("M�ngija %s kogu skoor on %d silma" ,player1.name, player1.score), "Info", 1); // skoori kuvamine
				player1Turn=false; // teise m2ngija kord veeretada
			}
			// kui ei ole esimese m2ngija kord:
			else { 
				JOptionPane.showMessageDialog(null, String.format("On m�ngija %s kord" ,player2.name), "Info", 1);
				die.roll();
				int result = die.getValue();
				JOptionPane.showMessageDialog(null, String.format("M�ngija %s viskas %d silma" ,player2.name, result), "Info", 1);
				player2.score+=result;
				JOptionPane.showMessageDialog(null, String.format("M�ngija %s kogu skoor on %d silma" ,player2.name, player2.score), "Info", 1);
				player1Turn=true; // esimese m2ngija kord
			}
	
		}
		// V6itja kuvamine
		// kui v6itis esimene m2ngija:
		if(player1.score > player2.score){ 
			JOptionPane.showMessageDialog(null, String.format("V�itis m�ngija %s\nKogu skoor %d silma" ,player1.name, player1.score), "Info", 1);
		}
		// kui esimene m2ngija ei v6itnud:
		else{
			JOptionPane.showMessageDialog(null, String.format("V�itis m�ngija %s\nKogu skoor %d silma" ,player2.name, player2.score), "Info", 1);
		}
	
		}

}
