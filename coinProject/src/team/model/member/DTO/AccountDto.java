package team.model.member.DTO;

import java.util.Random;

public class AccountDto {
	
	// 필드 영역
	protected int accNo;
	protected String accName;
	protected String accountNo;
	protected int accBalance;
	protected int mNo;
	
	// 생성자 영역
	public AccountDto() {}

	public AccountDto(int accNo, String accName, String accountNo, int accBalance, int mNo) {
		this.accNo = accNo;
		this.accName = accName;
		this.accountNo = accountNo;
		this.accBalance = accBalance;
		this.mNo = mNo;
	}

	@Override
	public String toString() {
		return "AccountDto [accNo=" + accNo + ", accName=" + accName + ", accountNo=" + accountNo + ", accBalance="
				+ accBalance + ", mNo=" + mNo + "]";
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}
	
	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public int getAccBalance() {
		return accBalance;
	}


	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}


	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	
	public String createAccount( ) {
		
		int acNo1 = creatNo(); 	int acNo2 = creatNo();
		if( acNo2 < 100000 ) { acNo2 *= 10; }
		
		this.accountNo = ("은행코드" + "-" + acNo1 + "-" + acNo2);
		return accountNo;
	}
	
	public int creatNo() {
		Random random = new Random();
		int result = random.nextInt(899)+100;
		return result;
	}

	public void complete( ) {
		System.out.println("[알림] 계좌를 만들어주셔서 감사합니다.");
	}
	
}
