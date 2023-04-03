package team.View;

import java.util.ArrayList;
import java.util.Scanner;

import team.controller.Mcontroller;
import team.controller.Pcontroller;
import team.model.mypage.mypageDto;

public class Mypage {

	// 싱글톤
	private static Mypage mypage = new Mypage();
	public Mypage() { }
	public static Mypage getInstance() { return mypage; }
	
	// 필드
	Scanner scanner = new Scanner(System.in);
	
	// 1. 계좌메인화면
	public void mypage(){
		while(true) {
			try {
			System.out.println("1. 계좌확인 2. 입금 3. 출금 4.뒤로가기" ); int ach = scanner.nextInt();
			if ( ach == 1 ) { checkAccount( ); } // 계좌확인[계좌번호, 계좌잔고]
			else if ( ach == 2 ) { deposit( ); } // 계좌입금
			else if ( ach == 3 ) { withdarw( ); } // 계좌출금
			else if ( ach == 4 ) { break; }// 뒤로가기
			} catch (Exception e) {System.out.println("DB 에러 이유 : " + e);}
		
	
		
		} // while end
	} // mypage end 
	
	// 1.1 계좌확인
	public void checkAccount(  ) { 
		
		
		ArrayList<mypageDto> list = Pcontroller.getInstance().checkAccount(Mcontroller.getInstance().getLogSession() );
		
		System.out.println("============================= 계좌 정보 ==============================");
		System.out.println("계좌NO" + "\t 계좌명" + "\t 거래은행"+ "\t 계좌번호"+ "\t \t 계좌잔고" );
		for ( mypageDto dto : list ) {
			System.out.println(  dto.getAccNo() + "\t" + dto.getmName() + "\t" + dto.getAccName()+ "\t" +dto.getAccountNo() + "\t" +  dto.getaccBalance()  );
		}
		
	}
	
	// 1.2 계좌입금
	public void deposit( ) {
		
		System.out.println("입금할 계좌 No를 입력해주세요."); int 은행accNo = scanner.nextInt();
		System.out.println("입금할 금액을 입력해주세요."); int inMoney = scanner.nextInt();
		
		int accBalance = Pcontroller.getInstance().getaccBalance(Mcontroller.getInstance().getLogSession() );
		
		
		boolean result = Pcontroller.getInstance().deposit( Mcontroller.getInstance().getLogSession() , inMoney , 은행accNo ,accBalance );
		if ( result ) { 

			System.out.println("[거래성공]" + 은행accNo + "번 계좌에 입금 완료되었습니다.");}
			
		else { System.out.println("[거래실패] 계좌입금 실패하였습니다.");}
	}
	
	// 1.3 계좌출금
	public void withdarw() { // if 출금 금액이 계좌금액보다 크다면 실패하고 "계좌에 있는 금액 이하를 입력해주세요."
		
		System.out.println("출금할 계좌 No를 입력해주세요."); int 은행accNo = scanner.nextInt();
		System.out.println("출금할 금액을 입력해주세요."); int outMoney = scanner.nextInt();
		
		int accBalance = Pcontroller.getInstance().getaccBalance(Mcontroller.getInstance().getLogSession() );
				
		boolean result = Pcontroller.getInstance().withdraw(Mcontroller.getInstance().getLogSession(), outMoney, 은행accNo , accBalance);
		if ( result ) { 
			
			System.out.println("[거래성공]" + 은행accNo + "번 계좌에 출금 완료되었습니다.");}

		else { System.out.println("[거래실패] 계좌출금 실패하였습니다.");}
	}
	
	// 1.4 뒤로가기
	public void AccountBack() throws Exception{
		Member.getInstance().loginIndex();
	}
	
}