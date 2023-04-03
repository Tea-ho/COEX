package team.model.member.DTO.Accountextends;

import team.model.member.DTO.AccountDto;

public class AccountCoinone extends AccountDto {
	
	final String bankcCode = "03";

	public AccountCoinone() {	}
	
	public AccountCoinone( int accNo, String accName, String accountNo, int accBalance, int mNo ) {
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
		System.out.println("[알림] 빗썸 계좌를 만들어주셔서 감사합니다.");
	}
	
}
