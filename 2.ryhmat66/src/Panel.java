import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	Body[] bodies = new Body[10];
	//jõu ja gravitatsiooni välja vektorid
	Vector[] field = new Vector[10];
	Vector[] force = new Vector[10];
	int numberOfBodies = 0;
	String error = "";
	int relative_x;
	int relative_y;
	
	//gravitatsiooniline konstant skaleeritud ülesse 10 miljardit korda
	double G = Math.pow(6.674, -2);
	
	Panel(){
		
	//iga hiirevajutuse peale luuakse uus keha
	  addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
        	  if (numberOfBodies<bodies.length){
        		  try{
	        		  String input;
	        		  input = JOptionPane.showInputDialog(null, "Enter mass of body" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
	        		  Scanner scanner = new Scanner(input);
	        		  int mass = scanner.nextInt();
	        		  scanner.close();
	        		  /*
	        		  input = JOptionPane.showInputDialog(null, "Enter size of body" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
	        		  scanner = new Scanner(input);
	        		  int size = scanner.nextInt();
	        		  */
	        		  input = JOptionPane.showInputDialog(null, "Enter speed <dx dy>" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
	        		  scanner = new Scanner(input);
	        		  double dx = scanner.nextDouble();
	        		  double dy = scanner.nextDouble();
	        		  if(numberOfBodies==0)
	        			  bodies[numberOfBodies++] = new Body(e.getX(),e.getY(),mass,mass,dx,dy);
	        		  else
	        			  bodies[numberOfBodies++] = new Body(relative_x-e.getX(),relative_y-e.getY(),mass,mass,dx,dy);
	        		  scanner.close();
        		  } catch (java.lang.NullPointerException nullpointerEvent){};
        	  } else
        		  error = "max number of bodies reached";
          }
      });
	  
	}
	
	//Arvutatakse ühe keha poolt tekitatud gravitatsiooniline väliühes punktis
	Vector gravitationalField(double x, double y, int mass){
		Vector r = new Vector(x,y);
		Vector field;
		field = r.dot(-G*mass*Math.pow(r.magnitude(), -2));
		return field;
	}
	//Arvutatakse ühele kehale mõjuv jõud
	Vector force(Vector field, int mass){
		Vector force = field.dot(mass);
		return force;
	}
	
	void calculate(){
		//iga keha asukohas arvutatakse välja gravitatsiooniline väli liites kokku kõikide kehade poolt tekitatud gravitatsioonilised väljad
		for(int i = 0; i<numberOfBodies;i++){
			field[i] = new Vector(0,0);
			Body body = bodies[i];
			for (int j=0;j<numberOfBodies;j++)
				if(body!=bodies[j])
					field[i]= field[i].add(gravitationalField(bodies[j].x-body.x,bodies[j].y-body.y,bodies[j].mass));
			bodies[i].speed=bodies[i].speed.add(field[i]);
			bodies[i].tick();
			force[i]= force(field[i],body.mass);
		}
		
	}
	//joonistatakse iga keha tahvlile ning lisatakse jõud
	public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        Rectangle r = this.getBounds();
        int zero_x = r.width/2;
        int zero_y = r.height/2;

        BufferedImage img=null;
        BufferedImage img2=null;
        //Image explosion=null;
		try {
			img = ImageIO.read(new File("img/satellite.png"));
			img2 = ImageIO.read(new File("img/earrth.png"));
			//explosion = Toolkit.getDefaultToolkit().createImage("explosion.gif");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(int i=0;i<numberOfBodies;i++){
        	try{
               relative_x=zero_x+(int)bodies[0].x;
               relative_y=zero_y+(int)bodies[0].y;
	        	int x =relative_x- (int)bodies[i].x;
	        	int y =relative_y- (int)bodies[i].y;
	        	//int radius = bodies[i].diameter/2;
	        	if(i==0)
	        		g.drawImage(img2, x-img2.getWidth()/2, y-img2.getHeight()/2, null);
	        	else{
	        		g.drawImage(img, x-img.getWidth()/2, y-img.getHeight()/2, null);
	        	}
	        		
	        	//g.drawOval(x-radius, y-radius, bodies[i].diameter, bodies[i].diameter);
	        	//g.drawString(field[i].toString()+"speed:"+bodies[i].speed.toString(),x,y);
        	} catch (java.lang.NullPointerException e){};
        }
        
    }  

}
