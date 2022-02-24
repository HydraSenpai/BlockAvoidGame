package FoxGame;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Screen extends JPanel{

	static final int FPS = 60;
	static final int BOARD_WIDTH =  600;
	static final int BOARD_HEIGHT = 600;
	static final int UNIT_SIZE = 10;
	static int score = 0;
	static int lives = 3;
	static Color currentColor = Color.white;
	static float mouseX;
	static float mouseY;
	
	Point locationMouse;
	
	Player player;
	
	ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>();
	
	Random random = new Random();
	
	TimerTask taskMoveRabbits;
	Timer timerRabbit;
	TimerTask fpsTT;
	Timer fpsT;
	
	JLabel scoreLabel;
	JLabel timeLabel;
	double time;
	
	int titleHeight;

	
	Screen(int title){
		
		timeLabel = new JLabel();
		timeLabel.setBounds(3,4,200,30);
		timeLabel.setFont(new Font("MV Boli",Font.PLAIN,25));
		timeLabel.setForeground(Color.white);
		time = 0.0;
		
		scoreLabel = new JLabel();
		scoreLabel.setBounds(220,4,200,30);
		scoreLabel.setForeground(Color.white);
		scoreLabel.setFont(new Font("MV Boli",Font.PLAIN,25));
		scoreLabel.setText("Rabbits: " + Rabbit.getAlive());
		
		
		titleHeight = title;
		System.out.println(titleHeight);
		
		this.add(scoreLabel);
		this.add(timeLabel);
		this.setBounds(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.setCursor(null);
		
		

		player = new Player();
		start();
		
		
		fpsT = new Timer();
		fpsTT = new TimerTask() {

			@Override
			public void run() {
				time+=0.016;
				DecimalFormat df = new DecimalFormat("0.00");
				String format = df.format(time);
				timeLabel.setText("Time: " + format);
				scoreLabel.setText("Enemies: " + Rabbit.getAlive());
				locationMouse = MouseInfo.getPointerInfo().getLocation();
				repaint();
				moveRabbits();
				movePlayer();
				checkWalls();
				checkCollision();
				
				
				//System.out.println("Player x coordinate: " + locationMouse.x);
				//System.out.println("Rabbit x coordinate: " + rabbits.get(0).getX()*UNIT_SIZE);
				//checkFinished();
			}
		};
		fpsT.scheduleAtFixedRate(fpsTT, 1000/FPS, 1000/FPS);
		
		timerRabbit = new Timer();
		taskMoveRabbits = new TimerTask() {
			@Override
			public void run() {
				spawnRabbit();
			}

		};
		timerRabbit.scheduleAtFixedRate(taskMoveRabbits, 5000, 5000);
		
	}

	private void start(){
		createRabbits();
	}

	private void createRabbits() {
		int initialNumber = random.nextInt(1)+1;
		for(int x=0;x<initialNumber;x++) {
			rabbits.add(new Rabbit(random.nextInt(BOARD_WIDTH/UNIT_SIZE),random.nextInt(BOARD_HEIGHT/UNIT_SIZE),UNIT_SIZE));
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.setColor(currentColor);
		for(int x=0;x<rabbits.size();x++) {
			g.fillRect((int)(rabbits.get(x).getX()*UNIT_SIZE), (int)(rabbits.get(x).getY()*UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
		}

		
		g.setColor(Color.yellow);
		g.fillRect((int)(player.getX()), (int)player.getY(), UNIT_SIZE, UNIT_SIZE);
	}

	private void moveRabbits() {
		for(int x=0;x<rabbits.size();x++) {
				rabbits.get(x).setX(rabbits.get(x).getX()+(rabbits.get(x).getSpeed())*rabbits.get(x).getXdirection());
				rabbits.get(x).setY(rabbits.get(x).getY()+(rabbits.get(x).getSpeed())*rabbits.get(x).getYdirection());		
			}
	}
	
	private void movePlayer() {
		SwingUtilities.convertPointFromScreen(locationMouse, this);
		//System.out.println("x is " + locationMouse.getX());
		//System.out.println("y is " + locationMouse.getY());
		if(locationMouse.getX() < 0) {
			locationMouse.x = 0;
		} else if(locationMouse.getX() > 574) {
			locationMouse.x = 574;
		}
		
		if(locationMouse.getY()<0) {
			locationMouse.y = 0;
		} else if (locationMouse.getY() > 551) {
			locationMouse.y = 551;
		}
		player.setX(locationMouse.getX());
		player.setY(locationMouse.getY()-titleHeight);
		
	}
	
	private void changeDirection() {
		int direction = 0;
		for(int x=0;x<rabbits.size();x++) {
			direction = random.nextInt(4);
			switch(direction) {
			case 0:
				rabbits.get(x).setXdirection(1);
				rabbits.get(x).setYdirection(1);
				break;
			case 1:
				rabbits.get(x).setXdirection(-1);
				rabbits.get(x).setYdirection(1);
				break;
			case 2:
				rabbits.get(x).setXdirection(1);
				rabbits.get(x).setYdirection(-1);
				break;
			case 3:
				rabbits.get(x).setXdirection(-1);
				rabbits.get(x).setYdirection(-1);
				break;
			}
			
		}
	}
	
	private void checkWalls() {
		for(int x=0;x<rabbits.size();x++) {
			if(((rabbits.get(x).getX()*UNIT_SIZE)+UNIT_SIZE+16) >= BOARD_WIDTH && rabbits.get(x).getXdirection() == 1){
				rabbits.get(x).setXdirection(-1);
			}
			if((rabbits.get(x).getX()) <= 0 && rabbits.get(x).getXdirection() == -1) {	//works
				rabbits.get(x).setXdirection(1);
			}
			if(((rabbits.get(x).getY()*UNIT_SIZE)+UNIT_SIZE+39) >= BOARD_HEIGHT && rabbits.get(x).getYdirection() == 1) {
				rabbits.get(x).setYdirection(-1);
			}
			if((rabbits.get(x).getY()) <= 0 && rabbits.get(x).getYdirection() == -1) {	//works
				System.out.println(rabbits.get(x).getX());
				rabbits.get(x).setYdirection(1);
			}
		}
		
	}
	
	private void checkCollision() {
		for(int x=0;x<rabbits.size();x++) {
			if(touching1(player,rabbits.get(x))) {
				try {
					//Thread.sleep(5000, 0);
					JOptionPane.showMessageDialog(null, "You died! You lasted " + Math.round(time) + " seconds, with a score of " + Rabbit.getAlive());
					System.exit(0);
				} catch (Exception e) {
					System.out.println("Couldn't stop game");
				}
				
				System.exit(0);
			}
			for(int y=0;y<rabbits.size();y++) {
				if(rabbits.get(x) == rabbits.get(y)) {
				} else {
					if(touching(rabbits.get(x),rabbits.get(y))) {
						changeColour();
						rabbits.remove(y);
						rabbits.get(0);
						Rabbit.decrementAlive();
						rabbits.add(new Rabbit(random.nextInt(BOARD_WIDTH/UNIT_SIZE),random.nextInt(BOARD_HEIGHT/UNIT_SIZE),UNIT_SIZE));
						break;
					}
				}
			}
		}
	}
	
	
	public boolean touching(Rabbit rabbit1, Rabbit rabbit2) {
		if(((rabbit1.getX()*UNIT_SIZE)+UNIT_SIZE)>=rabbit2.getX()*UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)+UNIT_SIZE>=rabbit2.getY()*UNIT_SIZE) {
			if((rabbit1.getX()*UNIT_SIZE)+UNIT_SIZE<=(rabbit2.getX()*UNIT_SIZE)+UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)+UNIT_SIZE<=(rabbit2.getY()*UNIT_SIZE)+UNIT_SIZE) {
				return true;
			} else if(((rabbit1.getX()*UNIT_SIZE))>=rabbit2.getX()*UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)+UNIT_SIZE>=rabbit2.getY()*UNIT_SIZE) {
				if((rabbit1.getX()*UNIT_SIZE)<=(rabbit2.getX()*UNIT_SIZE)+UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)+UNIT_SIZE<=(rabbit2.getY()*UNIT_SIZE)+UNIT_SIZE) {
					return true;
				} else if(((rabbit1.getX()*UNIT_SIZE))>=rabbit2.getX()*UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)>=rabbit2.getY()*UNIT_SIZE) {
					if((rabbit1.getX()*UNIT_SIZE)<=(rabbit2.getX()*UNIT_SIZE)+UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)<=(rabbit2.getY()*UNIT_SIZE)+UNIT_SIZE) {
						return true;
					} else if(((rabbit1.getX()*UNIT_SIZE))>=rabbit2.getX()*UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)>=rabbit2.getY()*UNIT_SIZE) {
						if((rabbit1.getX()*UNIT_SIZE)<=(rabbit2.getX()*UNIT_SIZE)+UNIT_SIZE&&(rabbit1.getY()*UNIT_SIZE)<=(rabbit2.getY()*UNIT_SIZE)+UNIT_SIZE) {
							return true;
						}
						}
				}
		}
		}
		return false;
	
	}


	
	public boolean touching1(Player player, Rabbit rabbit) {
		
		
		Point currentRabbit = new Point();
		currentRabbit.x=(int) (rabbit.getX()*UNIT_SIZE);
		currentRabbit.y=(int) (rabbit.getY()*UNIT_SIZE);

		
		
		Point currentPlayer = new Point();
		currentPlayer.x =(int) player.getX();
		currentPlayer.y =(int) player.getY();

		
		//System.out.println("Player x " + currentPlayer.getX());
		//System.out.println("Rabbit x " + currentRabbit.getX());
		
		if(currentPlayer.x+UNIT_SIZE>=currentRabbit.x && currentPlayer.y+UNIT_SIZE>=currentRabbit.y) {
			if(currentPlayer.x+UNIT_SIZE<=currentRabbit.x+UNIT_SIZE && currentPlayer.y+UNIT_SIZE<=currentRabbit.y+UNIT_SIZE) {
				return true;
			} else if(currentPlayer.x>=currentRabbit.x && currentPlayer.y+UNIT_SIZE>=currentRabbit.y) {
				if(currentPlayer.x<=currentRabbit.x+UNIT_SIZE && currentPlayer.y+UNIT_SIZE<=currentRabbit.y+UNIT_SIZE) {
					return true;
				} else if(currentPlayer.x>=currentRabbit.x && currentPlayer.y>=currentRabbit.y) {
					if(currentPlayer.x<=currentRabbit.x+UNIT_SIZE && currentPlayer.y<=currentRabbit.y+UNIT_SIZE) {
						return true;
					} else if(currentPlayer.x>=currentRabbit.x && currentPlayer.y>=currentRabbit.y) {
						if(currentPlayer.x<=currentRabbit.x+UNIT_SIZE && currentPlayer.y<=currentRabbit.y+UNIT_SIZE) {
							return true;
						}
						}
				}
		}
		}
		
		return false;
	
	}
	
	
	
	private void changeColour() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		currentColor = new Color(r,g,b);
	}

	private void spawnRabbit() {
	
		int choice = random.nextInt(3);
		
		switch(choice) {
		case 1:
			rabbits.add(new Rabbit(0+random.nextInt(15),0+random.nextInt(15),UNIT_SIZE));
			rabbits.get(Rabbit.getAlive()-1).setXdirection(1);
			rabbits.get(Rabbit.getAlive()-1).setYdirection(1);
			
			break;
		case 2:
			rabbits.add(new Rabbit((570-random.nextInt(15))/UNIT_SIZE,0+random.nextInt(15),UNIT_SIZE));
			rabbits.get(Rabbit.getAlive()-1).setXdirection(-1);
			rabbits.get(Rabbit.getAlive()-1).setYdirection(1);
			break;
		case 3:
			rabbits.add(new Rabbit(0+random.nextInt(15),(545-random.nextInt(15))/UNIT_SIZE,UNIT_SIZE));
			rabbits.get(Rabbit.getAlive()-1).setXdirection(1);
			rabbits.get(Rabbit.getAlive()-1).setYdirection(-1);
			break;
		case 0:
			rabbits.add(new Rabbit((570-random.nextInt(15))/UNIT_SIZE,(545-random.nextInt(15))/UNIT_SIZE,UNIT_SIZE));
			rabbits.get(Rabbit.getAlive()-1).setXdirection(-1);
			rabbits.get(Rabbit.getAlive()-1).setYdirection(-1);
			break;
		}
		
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
}