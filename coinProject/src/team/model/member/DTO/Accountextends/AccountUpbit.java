package team.model.member.DTO.Accountextends;

import team.model.member.DTO.AccountDto;

public class AccountUpbit extends AccountDto {

	final String bankcCode = "01";

	public AccountUpbit() {	}
	
	public AccountUpbit( int accNo, String accName, String accountNo, int accBalance, int mNo ) {
		this.accNo = accNo;
		this.accName = accName;
		this.accountNo = accountNo;
		this.accBalance = accBalance;
		this.mNo = mNo;
	} 
	
	public String createAccount( ) {
		
		int acNo1 = creatNo(); 	int acNo2 = creatNo();
		if( acNo2 < 100000 ) { acNo2 *= 10; }
		
		this.accountNo = (bankcCode + "-" + acNo1 + "-" + acNo2);
		return accountNo;
	}
	
	public void complete( ) {
		System.out.println("[알림] 업비트 계좌를 만들어주셔서 감사합니다.");
	}
	
}
