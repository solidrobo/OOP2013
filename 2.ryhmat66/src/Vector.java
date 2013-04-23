
public class Vector {

	double x;
	double y;
	
	public Vector(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	double magnitude(){
		return Math.hypot(x, y);
	}
	
	//dotproduct with a constant
	Vector dot(double c){
		Vector result = new Vector(x*c,y*c);
		return result;
	}
	Vector add(Vector v){
		return new Vector(x+v.x,y+v.y);
	}
	public String toString(){
		return String.format("%.2f %.2f",x,y);
	}
}
