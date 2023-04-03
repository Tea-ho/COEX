package team.controller;

import java.util.ArrayList;

import team.model.member.DAO.AccountDao;
import team.model.member.DAO.MemberAdminDao;
import team.model.member.DAO.MemberDao;
import team.model.member.DTO.AccountDto;
import team.model.member.DTO.CoinDto;
import team.model.member.DTO.MemberDto;

public class Mcontroller {
	
	private static Mcontroller mController = new Mcontroller();
	private Mcontroller() { }
	public static Mcontroller getInstance() { return mController; }

	private int logSession = 0;
	
	// 메소드 영역
	// 1. 회원가입
	public int signUp( String mId, String mPw, String mName, String mPhone, String mEmail ) {
		
		int result = MemberDao.getInstance().idCheck(mId);
		
		// 기능1: 유효성 검사 (1: 아이디 중복 x , 2: 아이디 중복 o, 3: 회원가입 미처리)
		if( result == 2) { return 2; }
		else if( result == 3){ return 3; }
		MemberDto mDto = new MemberDto(0, mId, mPw, mName, mPhone, mEmail, true);
		return MemberDao.getInstance().signUp(mDto);
	}
	
	// 2. 로그인
	public int login( String mId, String mPw ) {
		int result = MemberDao.getInstance().login(mId,mPw);
		if( result == 0 ) { return 0; }
		else if( result == -1 ) { return -1; }
		else { logSession = result; return result;  }
	}
	
	// 3. 아이디 찾기
	public String searchID( String mPhone ) {
		return MemberDao.getInstance().searchID(mPhone);
	}
	
	public String searchID( String mName, String mEmail ) {
		return MemberDao.getInstance().searchID(mName, mEmail);
	}
	
	// 4. 비밀번호 찾기
	public String searchPW( String mId, String mPhone ) {
		return MemberDao.getInstance().searchPW(mId, mPhone);
	}
	
	// 5. 계좌 등록
	public boolean createAcc( String accName, String accountNo, int accBalance ) {
		AccountDto aDto = new AccountDto( 0, accName, accountNo, accBalance, logSession );
		return AccountDao.getInstance().createAcc(aDto);
	}
	
	// 6-1. 코인 등록 권한 확인
	public boolean adminCoin() {
		return MemberAdminDao.getInstance().adminCoin(logSession);
	}
	
	
	// 6-2,3. 코인 등록
	public int regiCoin(String cName, int cAmount, int cPrice ) {
		
		int result = MemberAdminDao.getInstance().coinCheck(cName);
		
		if( result == 2 ) { return 2; }
		else if( result == 3 ) { return 3;}
		CoinDto cDto = new CoinDto(0, cName, cPrice, cAmount);
		return MemberAdminDao.getInstance().regiCoin(cDto);
	}
	
	// 7. 회원 정보 출력
	public ArrayList<MemberDto> printMemberList(){
		return MemberAdminDao.getInstance().printMemberList();
	}
	
	// 8. 휴먼 계정처리
	public int suspend( int mNo ) {
		return MemberAdminDao.getInstance().suspend(mNo);
	}
	
	public int active( String mId, String mPw ) {
		return MemberDao.getInstance().active(mId,mPw);
	}
	
	
	// 9. 회원 탈퇴
	public boolean leave( String mpw ) {
		return MemberDao.getInstance().leave( logSession, mpw);
	}
	
	public int getLogSession() {
		return logSession;
	}
	
	public void setLogSession(int logSession) {
		this.logSession = logSession;
	}
	

}
