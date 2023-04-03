package team.controller;

import java.util.ArrayList;

import team.model.mypage.Pdao;
import team.model.mypage.mypageDto;

public class Pcontroller {

	// 싱글톤
	private static Pcontroller pcon = new Pcontroller();
	public Pcontroller() { }
	public static Pcontroller getInstance() { return pcon; }
	
	// 로그인 섹션 값 : Mcontroller.getInstance().getLogSession()
	
	// 1. 계좌정보 출력
	public ArrayList<mypageDto> checkAccount( int mNo  ) {
		return Pdao.getInstance().checkAccount(mNo);
	}
	
	// 2. 계좌입금
	public boolean deposit(  int mNo , int adeposit , int accNo , int accBalance  ) {
		return Pdao.getInstance().deposit( mNo, adeposit , accNo ,  accBalance  );
	}
	
	// 2.1 입금 계좌 같은것찾기
	public int getaccBalance( int mNo ) {
		return Pdao.getInstance().getaccBalance(mNo);
	}
	
	// 2.2 계좌 입금금액 같은것찾기
	public int getAdeposit(int mNo) {
		return Pdao.getInstance().getAdeposit(mNo);
	}
	
	// 3. 계좌출금
	public boolean withdraw( int mNo , int withdraw , int accNo , int accBalance ) {
		return Pdao.getInstance().withdraw ( mNo, withdraw , accNo , accBalance );
	}
	
	
	
}
