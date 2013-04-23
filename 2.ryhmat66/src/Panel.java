import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.*;


public class Panel extends JPanel{
	Body[] bodies = new Body[10];
	int numberOfBodies = 0;
	String error="";
	
	Panel(){
	  addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
        	  if (numberOfBodies<bodies.length){
        		  String input;
        		  input = JOptionPane.showInputDialog(null, "Enter mass of body" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
        		  Scanner scanner = new Scanner(input);
        		  int mass = scanner.nextInt();
        		  input = JOptionPane.showInputDialog(null, "Enter size of body" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
        		  scanner = new Scanner(input);
        		  int size = scanner.nextInt();
        		  bodies[numberOfBodies++] = new Body(e.getX(),e.getY(),mass,size);
        	  } else
        		  error = "max number of bodies reached";
          }
      });
	  
	}
	

	public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        for(int i=0;i<numberOfBodies;i++){
        	g.drawOval(bodies[i].x, bodies[i].y, bodies[i].diameter, bodies[i].diameter);
        }
        g.drawString(error,10,20);
    }  

}
