public class dieRoll {
	/*
	 * 
	 * Selle klassi eesm�rk on t2ringut veeretada
	 *
	 */
private int throw1;

 public void roll() {  // t2ringuveeretamine
         throw1 = (int)(Math.random()*6) + 1;  
    }
 
public dieRoll() {  // paneb t2ringuveeretamise toimuma
      roll();  
	}

 public int getValue() {  // siit saab veeretamise tulemuse k2tte
         return throw1;  
    }
 
}