package team;

import team.View.Coinlist;
import team.View.Index;

public class Start {
	public static void main(String[] args) {
		
		
		// 멀티스레드 실행
	    Thread thread = Coinlist.getInstance();
	    thread.start(); 
		
	    // index 실행 메소드 호출 
	    Index.getInstance().index();

		
	}
}
