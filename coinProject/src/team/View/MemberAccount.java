package team.View;

import java.util.Scanner;

import team.controller.Mcontroller;
import team.model.member.DTO.AccountDto;
import team.model.member.DTO.Accountextends.AccountBitsum;
import team.model.member.DTO.Accountextends.AccountCoinone;
import team.model.member.DTO.Accountextends.AccountUpbit;

public class MemberAccount {

	private Scanner scanner = new Scanner(System.in);
	
	// 싱글톤 적용
	private static MemberAccount macc = new MemberAccount();
	private MemberAccount() {}
	public static MemberAccount getInstance() { return macc; }
	
	
	// 5. 계좌생성 (상속 활용)
	public void createAcc() {
		
		AccountDto acc = new AccountDto();
		
		boolean result = true;
		
		System.out.println("-------------------계좌생성 페이지-------------------");
		try {
			System.out.print("[계좌선택] 1.업비트 2.빗썸 3.코인원");		int choice = scanner.nextInt();
		
			if( choice == 1 ) {
				acc = new AccountUpbit();
				String accName = "업비트";		String accountNo = acc.createAccount();
				System.out.println("[생성 계좌 정보] 계좌은행:"  + accName + " 계좌번호:" + accountNo );
				result = Mcontroller.getInstance().createAcc(accName,accountNo,0);
				
				if( result ) { acc.complete(); }
			}
			else if( choice == 2 ) {
				acc = new AccountBitsum();
				String accName = "빗썸";		String accountNo = acc.createAccount();
				System.out.println("[생성 계좌 정보] 계좌은행:"  + accName + " 계좌번호:" + accountNo );
				result = Mcontroller.getInstance().createAcc(accName,accountNo,0);
				if( result ) { acc.complete(); }
			}
			else if( choice == 3 ) {
				acc = new AccountCoinone();
				String accName = "코인원";		String accountNo = acc.createAccount();
				System.out.println("[생성 계좌 정보] 계좌은행:"  + accName + " 계좌번호:" + accountNo );
				result = Mcontroller.getInstance().createAcc(accName,accountNo,0);
				if( result ) { acc.complete(); }
			}
		}
		catch(Exception e) { System.out.println( e.getMessage() ); }
		
	}
}
