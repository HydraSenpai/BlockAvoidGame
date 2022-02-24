package FoxGame;


public class Player {

	private double x=10;
	private double y=10;
	private double oppositex;
	private double oppositey;
	
	Player(){
	}
		
	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(double d) {
		this.y = d;
	}

	public double getOppositex() {
		return oppositex;
	}

	public void setOppositex(double oppositex) {
		this.oppositex = oppositex;
	}

	public double getOppositey() {
		return oppositey;
	}

	public void setOppositey(double oppositey) {
		this.oppositey = oppositey;
	}

}
