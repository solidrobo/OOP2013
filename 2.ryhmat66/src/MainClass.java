import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainClass extends JFrame {

	private static final long serialVersionUID = 1548233817403693158L;

	public static void main(String[] args) {
		
		//luuakse uus frame koos tahvliga ning paigutatakse see ekraani keskele
		JFrame f = new JFrame("Spaaaaaaace");
		final Panel panel = new Panel();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setBounds(screenSize.width/2-300, screenSize.height/2-300, 600, 600);
		f.add(panel);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addWindowListener(new WindowListener(){
		     public void windowClosing(WindowEvent e){
		    	 panel.closeWriter();
		        return; 
		     }
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		    }
		    );
		panel.setBackground(Color.black);
		//Tsükliliselt arvutatakse süsteemi olek ning joonistatakse tahvel uuesti

		while (true) {
			try {
				panel.repaint();
				panel.calculate();
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				panel.closeWriter();
			}
		}

	}
	
}
