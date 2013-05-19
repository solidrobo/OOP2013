
public class Body {

	//Sisaldab kehadega seotud parameetreid
	
	double mass;
	double x;
	double y;
	int diameter;
	String name;
	Vector speed;
	Vector force;
	boolean isAlive;
	
	Body(double x, double y, double mass, int diameter, double dx, double dy){
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.diameter = diameter;
		this.speed = new Vector(-dx,-dy);
		this.isAlive=true;
	}
	
	void tick(){
		if(isAlive){
			this.x-=speed.x;
			this.y-=speed.y;
		}
	}
	
}
