package team.View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import team.controller.Mcontroller;
import team.controller.Scontroller;
import team.model.coinlist.Cdao;
import team.model.mypage.Pdao;
import team.model.mypage.mypageDto;
import team.model.selling.Sdao;
import team.model.selling.sellingDto;

public class Selling implements Color {
	
	Scanner scanner = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("#,###");
	
	private static Selling sell = new Selling();
	private Selling () { }
	public static Selling getInstance() { return sell; }
	
	
	int mno = Mcontroller.getInstance().getLogSession();
	ArrayList<mypageDto> list = Pdao.getInstance().checkAccount(mno);
	
	public void index( int cNo ) {
		
		while( true ) {
			ArrayList<mypageDto> list = Pdao.getInstance().checkAccount(mno);
		
			System.out.println("===== 내 계좌 =====");
			System.out.println("계좌No\t\t계좌명\t\t거래은행\t\t계좌번호\t\t계좌잔고");
			
			for( mypageDto dto : list ) {
				System.out.println( dto.getAccNo() + "\t\t" + dto.getmName() + "\t\t" +
				dto.getAccName() + "\t\t" + dto.getAccountNo() + "\t\t" + dto.getaccBalance() );
			}
			
			System.out.println();
			System.out.print("[뒤로가기 : -1 ] 계좌를 선택하여주세요 : ");
			int accNo = scanner.nextInt();
			
			if( accNo == -1 ) { break; }
			
			
			while( true ) {
				
				sellingDto dto = Scontroller.getInstance().getCoinInfo(cNo, mno);
				
				System.out.println();
				System.out.println("=== 코인 상세보기 ===");
				System.out.println("코인명 : " + dto.getCname() );
				System.out.println("현재가 : " + df.format( dto.getCmprice() ) );
				System.out.println("최근거래가 : " + df.format( dto.getRecent_trade() ) );
				System.out.println("=================");
				System.out.println("보유단가 : " + GREEN + df.format( dto.getPcsumprice() ) + RESET + " 원" );
				System.out.println("보유수량 : " + GREEN + df.format( dto.getPcamount() ) + RESET + " 개" );
				if( dto.getRate() < 0 ) { System.out.print("손익(%) : " + BLUE + dto.getRate() + RESET + " %\t\t" ); }
				else { System.out.print("손익(%) : " + RED +dto.getRate() + RESET + "%\t\t" ); }
				if( dto.getProceeds() < 0 ) { System.out.println("손익(원) : " + BLUE + df.format( dto.getProceeds() ) + RESET + "원" ); }
				else { System.out.println("손익(원) : " + RED + df.format( dto.getProceeds() ) + RESET + " 원" ); }
				
				System.out.println();
				System.out.println("1. 매수하기 / 2. 매도하기 / 3. 뒤로가기");
				int ch = scanner.nextInt();
				
				if( ch == 1 ) { buy_coin( cNo , accNo ); }
				else if( ch == 2 ) { sell_coin( cNo  , accNo ); }
				else if ( ch == 3 ) { break; }
				else if ( ch == 4 ) { my_portfolio(); } 
				
			}
		}
		
	}
	
	// 코인 매수
	public void buy_coin( int cNo , int accNo ) {
		
		int check = Sdao.getInstance().myBalance(mno, accNo);
		
		if( check < 0 ) {
			System.out.println( RED + "계좌의 잔액이 부족합니다." + RESET + "[ 현재 잔고 : " + check + " ]");
			System.out.println(CYAN + "계좌에 입금 후 거래 가능합니다." + RESET );
		}else {
			
			check = Sdao.getInstance().myBalance(mno, accNo);
			
			sellingDto dto = Scontroller.getInstance().getCoinInfo(cNo, mno);
			
			System.out.println("[" + dto.getCname() + "] / " + 
								"[ 가격 : " + dto.getCmprice() + "] / " +
								"[ 잔여코인수량 : " + dto.getCmremaining() + "]" +
								"[ 보유잔고 : " + check + " ]");
			System.out.print("매수할 갯수를 입력해주세요 : ");
			int ctvolume = scanner.nextInt();
			int accbalance = dto.getCmprice()*ctvolume ;

			if( accbalance < check ) {
				
				accbalance = check - (dto.getCmprice()*ctvolume);
				
				Scontroller.getInstance().myBalance_update(mno, accNo, accbalance);
				
				int recheck = Sdao.getInstance().myBalance(mno, accNo);
				
				boolean result = Scontroller.getInstance().buy_coin(
						dto.getCmprice() , ctvolume, cNo, mno );
				
				if( result ) { System.out.println( ctvolume + "개 매수가 완료되었습니다. 매수가격 : " + df.format(dto.getCmprice() ) );}
				else{ System.out.println( RED + "잔여 수량 이상 구매할 수 없습니다" + RESET ); }
				
			}else {
				System.out.println(RED + "잔액이 부족합니다. 금액을 입금해주세요!!" + RESET );
				return;
			}
			
		}
	}
	
	
	
	
	public void sell_coin( int cNo , int accNo ) {
		
		int check = Sdao.getInstance().myBalance(mno, accNo);
		
		sellingDto dto = Scontroller.getInstance().getCoinInfo( cNo, mno );
		
		ArrayList<mypageDto> list = Pdao.getInstance().checkAccount(mno);
		
		System.out.println("[" + dto.getCname() + "] / " + 
							"[ 시장가 : " + dto.getCmprice() + " ] / " +
							"[ 보유코인수량 : " + dto.getPcamount() + "]" +
							"[ 보유잔고 : " + check + " ]");
		System.out.print("매도할 갯수를 입력해주세요 : ");
		int ctvolume = scanner.nextInt();
		
		
		System.out.print("( -1: 시장가) 매도할 가격을 입력해주세요 : ");
		int price = scanner.nextInt();
		int cmprice = 0;
		if( price == -1 ) {
			cmprice = dto.getCmprice();
		}else {
			cmprice = price;
		}
		
		
		boolean result = Scontroller.getInstance().sell_coin(
				cmprice , ctvolume, cNo, mno );
		
		if( result ) { 
			System.out.println("매도가 완료되었습니다. 매도가격 : " + df.format(cmprice) );
			
			int accbalance = check + (cmprice*ctvolume) ;
			
			Scontroller.getInstance().myBalance_update(mno, accNo, accbalance);
			
			int recheck = Sdao.getInstance().myBalance(mno, accNo);
			
			System.out.println("남은잔고 : " + recheck );
			
		}
		else { System.out.println(RED + "보유하신 코인 이상 판매할 수 없습니다." + RESET ); }
		
		
		
	}
	
	 
	
	
	// ------------------------------------------------------------------------------------------
	
	// 개인 손익 확인
	public void my_portfolio() {
		
		ArrayList<sellingDto> list =  
				Scontroller.getInstance().get_personalInfo( mno );
		
		System.out.println("======================= 포트폴리오 =======================");
		System.out.println("코인명\t\t평단가\t\t보유개수\t\t현재가격\t\t예상수익금\t\t예상수익률");
		
		for( sellingDto dto : list ) {
			System.out.println( 
					dto.getCname() + "\t\t" + dto.getPcsumprice() + "\t\t" +
					dto.getPcamount() + "\t\t" + dto.getCmprice() + "\t\t" +
					dto.getProceeds() + "\t\t" + dto.getRate()
			);
		}
		
		System.out.println();
		System.out.println("[메뉴] 1.뒤로가기");
		int ch = scanner.nextInt();
		
		if( ch == 1 ) { return; }
	}
	
	
		
}
