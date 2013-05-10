
public class Body {

	//Sisaldab kehadega seotud parameetreid
	
	int mass;
	double x;
	double y;
	int diameter;
	String name;
	Vector speed;
	Vector force;
	
	Body(double x, double y, int mass, int diameter, double dx, double dy){
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.diameter = diameter;
		this.speed = new Vector(-dx,-dy);
	}
	
	void tick(){
		this.x-=speed.x;
		this.y-=speed.y;
	}
	
}
