package View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Mcontroller;
import controller.Scontroller;
import model.coinlist.Cdao;
import model.selling.Sdao;
import model.selling.sellingDto;

public class Selling implements Color {
	
	Scanner scanner = new Scanner(System.in);
	DecimalFormat df = new DecimalFormat("#,###");
	
	private static Selling sell = new Selling();
	private Selling () { }
	public static Selling getInstance() { return sell; }
	
	
	
	public void index( int cNo ) {
		
		while( true ) {
			
			sellingDto dto = Scontroller.getInstance().getCoinInfo(cNo, Mcontroller.getInstance().getLogSession() );
			
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
			
			if( ch == 1 ) { buy_coin( cNo ); }
			else if( ch == 2 ) { sell_coin( cNo ); }
			else if ( ch == 3 ) { break; }
			else if ( ch == 4 ) { my_portfolio(); } 
			
		}
		
	}
	
	// 코인 매수
	public void buy_coin( int cNo ) {
		
		sellingDto dto = Scontroller.getInstance().getCoinInfo(cNo, Mcontroller.getInstance().getLogSession() );
		
		System.out.println("[" + dto.getCname() + "] / " + 
							"[ 가격 : " + dto.getCmprice() + "] / " +
							"[ 잔여코인수량 : " + dto.getCmremaining() + "]");
		System.out.print("매수할 갯수를 입력해주세요 : ");
		int ctvolume = scanner.nextInt();
		
		boolean result = Scontroller.getInstance().buy_coin(
				dto.getCmprice() , ctvolume, cNo, Mcontroller.getInstance().getLogSession() );
		
		if( result ) { System.out.println( ctvolume + "개 매수가 완료되었습니다. 매수가격 : " + df.format(dto.getCmprice() ) );}
		else{ System.out.println( RED + "잔여 수량 이상 구매할 수 없습니다" + RESET ); }
	
	}
	
	public void sell_coin( int cNo ) {
		
		sellingDto dto = Scontroller.getInstance().getCoinInfo(cNo, Mcontroller.getInstance().getLogSession() );
		
		System.out.println("[" + dto.getCname() + "] / " + 
							"[ 시장가 : " + dto.getCmprice() + " ] / " +
							"[ 보유코인수량 : " + dto.getPcamount() + "]");
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
				cmprice , ctvolume, cNo, Mcontroller.getInstance().getLogSession());
		
		if( result ) { System.out.println("매도가 완료되었습니다. 매도가격 : " + df.format(dto.getCmprice() ) );}
		else { System.out.println(RED + "보유하신 코인 이상 판매할 수 없습니다." + RESET ); }
		
	}
	
	
	
	
	// ------------------------------------------------------------------------------------------
	
	// 개인 손익 확인
	public void my_portfolio() {
		
		ArrayList<sellingDto> list =  
				Scontroller.getInstance().get_personalInfo( Mcontroller.getInstance().getLogSession());
		
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
