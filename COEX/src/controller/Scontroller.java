package controller;

import java.util.ArrayList;

import model.mypage.Pdao;
import model.mypage.mypageDto;
import model.selling.Sdao;
import model.selling.sellingDto;

public class Scontroller {
	
	private static Scontroller sc = new Scontroller();
	private Scontroller() {}
	public static Scontroller getInstance() {
		return sc;
	}
	
	
	public sellingDto getCoinInfo( int cNo , int mNo) {
		return Sdao.getInstance().getCoinInfo(cNo, mNo);
	}
	
	public boolean buy_coin( int ctprice , int ctvolume , int cno , int mno ) {
	
		sellingDto dto = Sdao.getInstance().getCoinInfo(cno, mno);
		
		if( dto.getCmremaining() <= 0 && dto.getCmremaining() < ctvolume ) {
			return false;
		}
		
		return Sdao.getInstance().buy_coin(ctprice, ctvolume, cno, mno);
	}
	
	public boolean checkAccount( int mno ) {
		ArrayList<mypageDto> acc = Pdao.getInstance().checkAccount(mno);
		
		if( acc == null ) {
			return false;
		}
		return true;
	}
	
	public boolean sell_coin( int ctprice , int ctvolume , int cno , int mno ) {
		
		sellingDto dto = Sdao.getInstance().getCoinInfo(cno, mno);
		
		
		if( dto.getPcamount() < ctvolume ) {
			return false;
		}
		
		return Sdao.getInstance().sell_coin(ctprice, ctvolume, cno, mno);
	}
	
	public ArrayList<sellingDto> get_personalInfo( int mNo ) {
		return Sdao.getInstance().get_personalInfo(mNo);
	}
	
	public void copy() {
		Sdao.getInstance().copy();
	}
	
}
