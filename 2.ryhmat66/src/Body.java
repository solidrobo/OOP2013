
public class Body {

	//Sisaldab kehadega seotud parameetreid
	
	double mass;
	double x;
	double y;
	Vector speed;
	boolean isAlive;
	boolean isLog;
	
	Body(double x, double y, double mass, int diameter, double dx, double dy){
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.speed = new Vector(-dx,-dy);
		this.isAlive=true;
		this.isLog=false;
	}
	
	void tick(){
		if(isAlive){
			this.x-=speed.x;
			this.y-=speed.y;
		}
	}
	
}
