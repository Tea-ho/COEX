package controller;

import java.util.ArrayList;

import model.coinlist.Cdao;
import model.coinlist.CoinmarketPDto;
import model.coinlist.coinlistDto;

public class Ccontroller {
	//1. 싱글톤
	private static Ccontroller ccontroller = new Ccontroller();
	private Ccontroller() {}
	public static Ccontroller getInstance() {return ccontroller;}
	
	public ArrayList<CoinmarketPDto> print_coin() {
		return Cdao.getInstance().print_coin();
	}
	
	public void refresh_coin() {
		Cdao.getInstance().refresh_coin();
	}
	
	
		
}
