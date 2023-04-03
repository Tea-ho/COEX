package team.View;

import java.util.ArrayList;
import java.util.Scanner;

import team.controller.Mcontroller;
import team.model.member.DTO.MemberDto;

public class MemberAdmin {

	private Scanner scanner = new Scanner(System.in);
	
	// 싱글톤 적용
	private static MemberAdmin admin = new MemberAdmin();
	private MemberAdmin() {}
	public static MemberAdmin getInstance() { return admin; }
	
	// 6. 코인등록
	public void regiCoin() {
		System.out.println("-------------------관리자용 페이지[코인 등록]-------------------");
		try {
			System.out.print("코인명: ");		String cName = scanner.next();
			System.out.print("발행량: ");		int cAmount = scanner.nextInt();
			System.out.print("발행가격: ");		int cPrice = scanner.nextInt();
				
			if( Mcontroller.getInstance().regiCoin(cName, cAmount, cPrice) == 1 ) {
				System.out.println("[알림]" + cName + "정상 발행 완료");
			}
			else { System.out.println("[알림] 이미 발행된 코인입니다." ); }
		} catch(Exception e) { System.out.println(e.getMessage()); }
		
	}
		
	// 7. 회원정보 확인
	public void printMemberList() throws Exception{
		System.out.println("-------------------관리자용 페이지[회원 정보]-------------------");
		System.out.printf("%2s \t %10s \t %10s \t %10s \t %10s \t %10s \n", "번호", "아이디", "이름", "연락처", "이메일", "활성상태");
		
		ArrayList<MemberDto> mList = Mcontroller.getInstance().printMemberList();
		
		for( MemberDto x : mList ) {
			System.out.printf( "%2s \t %10s \t %10s \t %10s \t %10s \t %10s \n",
					x.getmNo(), x.getmId(), x.getmName(), x.getmPhone(), x.getmEmail(), (x.getmState() == true ? "활성계정" : "휴먼계정") );
		}
			
		System.out.print("[메뉴] 1.계정 상태 변경 2.뒤로가기");
		int choice = scanner.nextInt();
		if( choice == 1 ) { suspend(); }
	}
		
	// 7-1. 회원 휴먼계정 처리
	public void suspend() throws Exception{
		System.out.print("상태 변경할 회원 번호 입력:");
		int choice = scanner.nextInt();
		int result = Mcontroller.getInstance().suspend(choice);
			
		if( result == 1 ) {System.out.println("[알림] 해당 계정은 휴먼계정으로 상태가 변경됩니다.");}
		else if( result == 2 ) { System.out.println("[알림] 해당 계정을 다시 활성화시킵니다.");}
		else {System.out.println("[알림] 시스템 오류");}
	}
	
}
