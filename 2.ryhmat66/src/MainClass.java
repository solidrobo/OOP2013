import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;


public class MainClass extends JFrame {

	public static void main(String[] args) {
		
		//luuakse uus freim koos tahvliga ning paigutatakse see ekraani keskele
		JFrame f = new JFrame("Spaaaaaaace");
		Panel panel = new Panel();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds(screenSize.width/2-300, screenSize.height/2-300, 600, 600);
		f.add(panel);
		f.setVisible(true);
		
		//Tsükkliliselt arvutatakse süsteemi olek ning joonistatakse tahvel uuesti
		for (;;) {
			try {
				panel.repaint();
				panel.calculate();
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
			}
		}

	}
	
	

	public int roll() {  // t2ringuveeretamine
     	int throw1 = (int)(Math.random()*6) + 1;
     	return throw1; 
	}
}
