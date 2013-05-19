import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	int numberOfBodies = 0;

	int relative_x;
	int relative_y;

	double scale= Math.pow(10,7);
	//gravitatsiooniline konstant skaleeritud ülesse 10 miljardit korda
	double G = 6.674*Math.pow(10, -11)/scale;
	double earthMass= 5.972*Math.pow(10, 24)/scale;
	
	Panel(){
		bodies[numberOfBodies++] = new Body(this.getBounds().getCenterX(),this.getBounds().getCenterY(),earthMass,100,0,0);
		JButton reset = new JButton("RESET");
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				numberOfBodies=1;
			}
		});
		this.add(reset);
		this.invalidate();
	//iga hiirevajutuse peale luuakse uus keha
	  addMouseListener(new MouseAdapter() {
		  
          public void mousePressed(MouseEvent e) {
        	  if (numberOfBodies<bodies.length){
        		  try{
	        		  String input;
	        		  Scanner scanner;
	
	        		  if(numberOfBodies==0){
	        			 // bodies[numberOfBodies++] = new Body(e.getX(),e.getY(),100,100,0,0);
	        		  }
	        		  else{
	        			
	        			  input = JOptionPane.showInputDialog(null, "Enter speed <dx dy>" ,  "NB!", JOptionPane.QUESTION_MESSAGE);
		        		  scanner = new Scanner(input);
		        		  double dx = scanner.nextDouble();
		        		  double dy = scanner.nextDouble();
		        		  
		        		  bodies[numberOfBodies++] = new Body(relative_x-e.getX(),relative_y-e.getY(),1,1,dx,dy);
		        		  scanner.close();

	        		  }
        		  } catch (java.lang.NullPointerException nullpointerEvent){};
        	  } else
        		  JOptionPane.showMessageDialog(null, "Maximum number of bodies reached", "Info", JOptionPane.INFORMATION_MESSAGE);
          }
          
      });
	  
	}
	
	//Arvutatakse ühe keha poolt tekitatud gravitatsiooniline väliühes punktis
	Vector gravitationalField(double x, double y, double mass){
		Vector r = new Vector(x,y);
		Vector field;
		field = r.dot(-G*mass*Math.pow(r.magnitude(), -2));
		return field;
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
    
        try {
			img = ImageIO.read(new File("img/satellite.png"));
			img2 = ImageIO.read(new File("img/earrth.png"));
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
               if(i==0)
	        		g.drawImage(img2, x-img2.getWidth()/2, y-img2.getHeight()/2, null);
               else{
	        		g.drawImage(img, x-img.getWidth()/2, y-img.getHeight()/2, null);
	        	}
	        	double distance = Math.sqrt(Math.pow(Math.abs(relative_x-x),2) + Math.pow(Math.abs(relative_y-y),2));
	        	if(distance<=256/2-img.getWidth()/2)
	        		bodies[i].isAlive=false;
	        	} catch (java.lang.NullPointerException e){};
        } 
    }  
}
