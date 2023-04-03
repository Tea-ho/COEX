package team.model.member.DTO;

public class MemberDto {

	// 필드 영역
	private int mNo;
	private String mId;
	private String mPw;
	private String mName;
	private String mPhone;
	private String mEmail;
	private boolean mState;
	
	// 생성자 영역
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(int mNo, String mId, String mPw, String mName, String mPhone, String mEmail, boolean mState) {
		super();
		this.mNo = mNo;
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mState = mState;
	}
	
	// 메소드 영역
	@Override
	public String toString() {
		return "MemberDto [mNo=" + mNo + ", mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mPhone=" + mPhone
				+ ", mEmail=" + mEmail + ", mState=" + mState + "]";
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	
	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public boolean getmState() {
		return mState;
	}

	public void setmState(boolean mState) {
		this.mState = mState;
	}
	
	
	
	
}
