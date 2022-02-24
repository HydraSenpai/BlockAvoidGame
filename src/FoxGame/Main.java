package FoxGame;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		StartPage startPage = new StartPage();
		
		
		Timer Timer = new Timer();
		TimerTask TimerTask = new TimerTask() {
			@Override
			public void run() {
				if(!startPage.isShowing()) {
					new Board();
					Timer.cancel();
					
				}
			}

		};
		Timer.scheduleAtFixedRate(TimerTask, 1000, 1000);
		
		
		
	}
	
	

}


