package View;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Ccontroller;
import model.coinlist.CoinmarketPDto;
import model.coinlist.coinlistDto;

public class Coinlist extends Thread implements Color{
	
	//싱글톤
	private static Coinlist coinlist = new Coinlist();
	private Coinlist() {}
	public static Coinlist getInstance() {return coinlist;}
	
	boolean stop = false;
	
	public boolean isStop() { return stop; }
	public void setStop(boolean stop) { this.stop = stop; }
	
	@Override
	public void run() {
		while(true) {
			if(stop) {
				ArrayList<CoinmarketPDto> result = Ccontroller.getInstance().print_coin();
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n---------------------코인리스트----------------------");
				System.out.println("번호\t코인이름\t현재가격\t전날가격\t전날대비");
				for(int i=0;i<result.size();i++) {
					double profit = ((double)result.get(i).getCMprice()/(double)result.get(i).getCIPrice())*100-100;
					profit = Math.round(profit);
					System.out.printf("%d\t%s\t%d\t%d\t",
							result.get(i).getcNo(),result.get(i).getcName(),result.get(i).getCMprice(),result.get(i).getCIPrice()
							);
					if(profit>=0) {System.out.println(GREEN+profit+"%"+RESET);}
					else {System.out.println(RED+profit+"%"+RESET);}
				}
				System.out.print("뒤로가기 :0 / 코인선택 : ");
				try {Thread.sleep(5000); }	// 5초에 한번씩 업데이트
				catch (Exception e) {}
			}	
			else {Thread.yield();}
		} // while e
	} // run e
} // class e
