package View;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import controller.Ccontroller;

public class CoinPrint { // class S
   
	private Timer timer;
    private static CoinPrint print = new CoinPrint();
    public static CoinPrint getInstance() {return print;}
   
    private CoinPrint() {
	   timer = new Timer();
	   timer.schedule(timerTask, 0, 60000);
    }
   
    TimerTask timerTask = new TimerTask() {
	
	@Override
	public void run() { Ccontroller.getInstance().refresh_coin(); }
};
   
   Scanner sc = new Scanner(System.in);  
   
   public void index(){ // index S
	   Coinlist.getInstance().setStop(true);
	   int cno = sc.nextInt();
	   if(cno==0) {
		   Coinlist.getInstance().setStop(false);
		   return;
	   }
	   else {
		   Coinlist.getInstance().setStop(false);
		   Selling.getInstance().index(cno);
	   }
   } // index E
} // class E
