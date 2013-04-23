import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.*;


public class Panel extends JPanel{
	Body[] bodies = new Body[10];
	Vector[] field = new Vector[10];
	Vector[] force = new Vector[10];
	int numberOfBodies = 0;
	String error="";
	double G = Math.pow(6.674, -1);
	
	Panel(){
	  addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
        	  if (numberOfBodies<bodies.length){
        		  try{
	        		  String input;
	        		  input = JOptionPane.showInputDialog(null, "Enter mass of body" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
	        		  Scanner scanner = new Scanner(input);
	        		  int mass = scanner.nextInt();
	        		  /*
	        		  input = JOptionPane.showInputDialog(null, "Enter size of body" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
	        		  scanner = new Scanner(input);
	        		  int size = scanner.nextInt();
	        		  */
	        		  bodies[numberOfBodies++] = new Body(e.getX(),e.getY(),mass,mass,1,1);
        		  } catch (java.lang.NullPointerException nullpointerEvent){};
        	  } else
        		  error = "max number of bodies reached";
          }
      });
	  
	}
	
	Vector gravitationalField(double x, double y, int mass){
		Vector r = new Vector(x,y);
		Vector field;
		field = r.dot(-G*mass*Math.pow(r.magnitude(), -2));
		return field;
	}
	
	Vector force(Vector field, int mass){
		Vector force = field.dot(mass);
		return force;
	}
	
	void calculate(){
		
		for(int i = 0; i<numberOfBodies;i++){
			field[i] = new Vector(0,0);
			Body body = bodies[i];
			for (int j=0;j<numberOfBodies;j++)
				if(body!=bodies[j])
					field[i]= field[i].add(gravitationalField(body.x-bodies[j].x,body.y-bodies[j].y,bodies[j].mass));
			force[i]= force(field[i],body.mass);
		}
		
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        for(int i=0;i<numberOfBodies;i++){
        	try{
	        	int x = (int)bodies[i].x;
	        	int y = (int)bodies[i].y;
	        	int radius = bodies[i].diameter/2;
	        	g.drawOval(x-radius, y-radius, bodies[i].diameter, bodies[i].diameter);
	        	g.drawString(force[i].toString(),x,y);
        	} catch (java.lang.NullPointerException e){};
        }
        
    }  

}
