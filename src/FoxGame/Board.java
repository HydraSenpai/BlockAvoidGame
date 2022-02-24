package FoxGame;



import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;



public class Board extends JFrame {

	Screen screen;
	int insetTop;
	
	Board(){
		this.setSize(600,600);
		this.setTitle("Smooth Fox vs Rabbit");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setCursor(this.getToolkit().createCustomCursor(new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),new Point(), null ) );
		insetTop = this.getInsets().top; 
		
		this.setLayout(null);
		this.setResizable(false);
		
		
		
		screen = new Screen(insetTop);
		  
		
		
		this.add(screen);
		this.setVisible(true);
	}
}
