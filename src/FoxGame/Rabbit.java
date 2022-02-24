package FoxGame;

import java.util.Random;

public class Rabbit {

	
	private double speed=0.3;
	private double x;
	private double y;
	private double oppositex;
	private double oppositey;
	private int xdirection=1;
	private int ydirection=1;
	private int lives;
	private static int numberAlive=0;
	private Random rand = new Random();
	
	Rabbit(int x, int y,int size){
		this.x = x;
		this.y = y;
		this.setOppositex(x + size);
		this.setOppositey(y + size);
		
		numberAlive++;
		
		if(rand.nextInt(2)+1 == 1) {
			xdirection = -1;
		}

		if(rand.nextInt(2)+1 == 1) {
			ydirection = -1;
		}
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public static int getAlive() {
		return numberAlive;
	}
	public static void decrementAlive() {
		numberAlive--;
	}

	public int getXdirection() {
		return xdirection;
	}

	public void setXdirection(int xdirection) {
		this.xdirection = xdirection;
	}

	public int getYdirection() {
		return ydirection;
	}

	public void setYdirection(int ydirection) {
		this.ydirection = ydirection;
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
