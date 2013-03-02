public class dieRoll {
 private int throw1;

 public void roll() {  
         throw1 = (int)(Math.random()*6) + 1;  
    }
 
public dieRoll() // default to six-sided dice  
 {  
      roll();  // Calls the roll() method to roll the dice.  
 }

 public int getValue1() {  
         return throw1;  
    }
 
}