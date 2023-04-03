package team.model.mypage;

public class mypageDto {
	private int aNo;			// 계좌 고유번호
	private String mName;		// 계좌 멤버이름	- mNo를 통해 mNo를 fk키로 쓰고 있는 acc 정보 중 mName을 가져오려함.
	private String accName;		// 계좌 은행		- accNo를 통해 accName 가져오기
	private String accountNo;	// 계좌 번호		- mNo를 통해 mNo를 fk키로 쓰고 있는 acc 정보 중 accountNo를 가져오려함.
	private int accBalance;		// 계좌 잔고		- accNo를 통해 accBalance 가져오기
	private int mNo;			// 회원번호(fk)	
	private int accNo;			// 생성계좌(fk)
	
	public mypageDto() {}
	
	
	
	public mypageDto(int aNo, String mName, String accountNo, int accBalance, String accName, int mNo, int accNo) {
		super();
		this.aNo = aNo;
		this.mName = mName;
		this.accountNo = accountNo;
		this.accBalance = accBalance;
		this.accName = accName;
		this.mNo = mNo;
		this.accNo = accNo;
	}

	public mypageDto( int accNo ,String mName, String accName, String accountNo, int accBalance) {
		super();
		this.accNo = accNo;
		this.mName = mName;
		this.accName = accName;
		this.accountNo = accountNo;
		this.accBalance = accBalance;
	}

	public int getaNo() {
		return aNo;
	}

	public void setaNo(int aNo) {
		this.aNo = aNo;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getaccBalance() {
		return accBalance;
	}

	public void setaBalance(int accBalance) {
		this.accBalance = accBalance;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	@Override
	public String toString() {
		return "mypageDto [aNo=" + aNo + ", mName=" + mName + ", accountNo=" + accountNo + ", accBalance=" + accBalance
				+ ", accName=" + accName + ", mNo=" + mNo + ", accNo=" + accNo + "]";
	}

	
	
	

	
}