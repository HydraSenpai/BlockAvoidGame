package FoxGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartPage extends JFrame implements ActionListener{

	Screen screen;
	JButton button;
	JLabel title;
	JLabel instructionsTitle;
	JLabel instruction1;
	JLabel instruction2;
	JLabel instruction3;
	
	public StartPage() {
		this.setBounds(0, 0, 600, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		title = new JLabel();
		title.setBounds(110,20,500,50);
		title.setFocusable(false);
		title.setText("Welcome to my game!");
		title.setFont(new Font("MV Boli",Font.PLAIN,35));
		title.setForeground(Color.white);
		
		instructionsTitle = new JLabel();
		instructionsTitle.setBounds(195,100,300,50);
		instructionsTitle.setFocusable(false);
		instructionsTitle.setText("Instructions:");
		instructionsTitle.setFont(new Font("MV Boli",Font.PLAIN,30));
		instructionsTitle.setForeground(Color.white);
		
		instruction1 = new JLabel();
		instruction1.setBounds(155,200,300,50);
		instruction1.setFocusable(false);
		instruction1.setText("1. Enemies spawn from corners");
		instruction1.setFont(new Font("MV Boli",Font.PLAIN,20));
		instruction1.setForeground(Color.white);
		
		instruction2 = new JLabel();
		instruction2.setBounds(155,250,300,50);
		instruction2.setFocusable(false);
		instruction2.setText("2. Avoid enemies");
		instruction2.setFont(new Font("MV Boli",Font.PLAIN,20));
		instruction2.setForeground(Color.white);
		
		instruction3 = new JLabel();
		instruction3.setBounds(20,300,550,50);
		instruction3.setFocusable(false);
		instruction3.setText("3. You have one life, touch an enemy and you're dead");
		instruction3.setFont(new Font("MV Boli",Font.PLAIN,20));
		instruction3.setForeground(Color.white);
		
		button = new JButton();
		button.setBounds(140,440,300,80);
		button.setFocusable(false);
		button.addActionListener(this);
		button.setText("Press to start game!!!");
		button.setFont(new Font("MV Boli",Font.PLAIN,25));
		
		this.add(instruction3);	
		this.add(instruction2);
		this.add(instruction1);
		this.add(title);
		this.add(instructionsTitle);
		this.add(button);
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			this.dispose();
			
		}
		
	}
	
}
