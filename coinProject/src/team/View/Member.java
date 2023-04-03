package team.View;

import java.util.Scanner;
import team.controller.Mcontroller;

public class Member {
	
	// 스캨너
	private Scanner scanner = new Scanner(System.in);
	
	// 싱글톤 적용
	private static Member member = new Member();
	private Member() {}
	public static Member getInstance() { return member; }
	
	// 메소드 영역
	// 0. 인덱스
	public void index() {
		while(true) {
			System.out.println("-------------------EZ 코인 거래소-------------------");
			System.out.print("[메뉴] 1.회원가입 2.로그인 3.아이디찾기 4.비밀번호찾기 5.계정활성");
			try{
			int choice = scanner.nextInt();
			
			if( choice == 1 ) { signUp(); }
			else if( choice == 2) { login(); }
			else if( choice == 3 ) { searchID(); }
			else if( choice == 4 ) { searchPW(); }
			else if( choice == 5 ) { active(); }
			else { System.out.println("[알림] 번호를 다시 입력해주세요."); }
			}
			catch(Exception e){ System.out.println("예외발생: " + e);}
		}
	}
	
	// 1. 회원가입
	public void signUp() throws Exception {
		System.out.println("-------------------회원가입 페이지-------------------");
		System.out.print("아이디(5자리 이상): ");		String mId = scanner.next();
		System.out.print("비밀번호: ");				String mPw = scanner.next();
		System.out.print("비밀번호 확인: ");			String mPwC = scanner.next();
		System.out.print("이름: ");				String mName = scanner.next();
		System.out.print("연락처(하이픈 생략): ");		String mPhone = scanner.next();
		System.out.print("이메일: ");				String mEmail = scanner.next();
		
		boolean check = checkSignUp( mId, mPw, mPwC, mName, mPhone, mEmail );
			
		if( check ) { 
			int result = Mcontroller.getInstance().signUp(mId, mPw, mName, mPhone, mEmail);
			if(  result == 1 ) { System.out.println("[알림] 회원가입 완료!"); }
			else if( result == 2 ) { System.out.println("[알림] 아이디 중복입니다."); }
			else if( result == 3 ) { System.out.println("[알림] 시스템 오류 관리자 문의!"); }
		}
	}
	
	// 1-1. 회원가입 양식 유효성 검사 (Front에서 검사해서 데이터 들어옴)
	public boolean checkSignUp( String mId, String mPw, String mPwC, String mName, String mPhone, String mEmail  )throws Exception{
		
		boolean checkId = true;
		boolean checkPw = true;
		boolean checkName = true;
		boolean checkPhone = true;
		boolean checkEmail = true;
		
		if( mId.length() < 5 ) { System.out.println("[알림] 아이디 5자리 이상 입력!"); checkId = false; }
		if( !mPw.equals(mPwC) ) { System.out.println("[알림] 비밀번호 불일치!"); checkPw = false; }
		if( mName.length() < 2) { System.out.println("[알림] 이름 확인!"); checkName = false; }
		if( mPhone.length() != 11 ) { System.out.println("[알림] 번호 확인!"); checkPhone = false; }
		if( !(mEmail.contains("@")) ) { System.out.println("[알림] 잘못된 양식!(@입력)"); checkEmail = false; }

		if( checkId && checkPw && checkName && checkPhone &&  checkEmail ) { return true;}
		else { return false; }
		
	}
	
	// 2. 로그인
	public void login() throws Exception {

		System.out.println("-------------------로그인 페이지-------------------");
		System.out.print("아이디: ");		String mId = scanner.next();
		System.out.print("비밀번호: ");		String mpw = scanner.next();
		
		int result = Mcontroller.getInstance().login(mId, mpw);

		if( result > 0 ) {
			System.out.println("[알림] 로그인 성공");
			if( Mcontroller.getInstance().adminCoin() ) { loginIndex( mId ); }
			else { loginIndex(); }
		}
		else if( result == -1 ) { System.out.println("[알림] 휴먼 계정입니다."); }
		else { System.out.println("[알림] 로그인 실패"); }
	}
	
	// 2-1. 로그인 후 메뉴 출력 (일반 회원)
	public void loginIndex() throws Exception{
		while(true) {
			System.out.print("[메뉴] 1.계좌생성, 2.코인확인, 3.계좌확인, 4.로그아웃 5.회원탈퇴");
			int choice = scanner.nextInt();
			
			if( choice == 1 ) { MemberAccount.getInstance().createAcc(); }
			else if( choice == 2 ) { CoinPrint.getInstance().index();  } 
			else if( choice == 3 ) { Mypage.getInstance().mypage(); } 
			else if( choice == 4 ) { 
				Mcontroller.getInstance().setLogSession(0);
				System.out.println("[알림] 정상적으로 로그아웃되었습니다.");
				break;
			} 
			else if( choice == 5 ) {  if(leave()) { break; }; } 
		}
	}
	
	// 2-1. 로그인 후 메뉴 출력 (관리자) - 오버로딩 적용
	public void loginIndex(String admin) throws Exception{
		while(true) {
			System.out.print("[메뉴] 1.계좌생성, 2.코인확인, 3.계좌확인, 4.로그아웃 5.회원탈퇴 6.회원정보확인 7.코인등록[관리자용]");
			int choice = scanner.nextInt();
			
			if( choice == 1 ) { MemberAccount.getInstance().createAcc(); }
			else if( choice == 2 ) { CoinPrint.getInstance().index(); } 
			else if( choice == 3 ) { Mypage.getInstance().mypage(); } 
			else if( choice == 4 ) { 
				Mcontroller.getInstance().setLogSession(0);
				System.out.println("[알림] 정상적으로 로그아웃되었습니다.");
				break;
			} 
			else if( choice == 5 ) { if(leave()) { break; } } 
			else if( choice == 6 ) { MemberAdmin.getInstance().printMemberList();  }
			else if( choice == 7 ) { MemberAdmin.getInstance().regiCoin(); }
		}
	}

	// 3. 아이디 찾기
	public void searchID()throws Exception{
		System.out.println("-------------------아이디 찾기-------------------");
		System.out.print("[선택] 1.핸드폰으로 아이디찾기 2.이메일로 아이디찾기");
		int choice = scanner.nextInt();
		
		if( choice == 1) {
			System.out.print("번호 입력: ");	String mPhone = scanner.next();
			
			if( Mcontroller.getInstance().searchID(mPhone) == null ) {
				System.out.println("[알림] 번호를 다시 확인해주세요.");
			}
			else {
				System.out.println("아이디: " + Mcontroller.getInstance().searchID(mPhone));
			}	
		}
		
		else if( choice == 2) {
			System.out.print("이름 입력: ");	String mName = scanner.next();
			System.out.print("이메일 입력: ");	String mEmail = scanner.next();
			
			if( Mcontroller.getInstance().searchID(mName, mEmail) == null ) {
				System.out.println("[알림] 입력하신 정보를 다시 확인해주세요.");
			}
			else {
				System.out.println("아이디: " + Mcontroller.getInstance().searchID(mName, mEmail));
			}
		}
	}
	
	// 4. 비밀번호 찾기
	public void searchPW() throws Exception{
		System.out.println("-------------------비밀번호 찾기-------------------");
		System.out.print("아이디 입력: ");	String mId = scanner.next();
		System.out.print("번호 입력: ");	String mPhone = scanner.next();
		
		if( Mcontroller.getInstance().searchPW(mId, mPhone) == null ) {
			System.out.println("[알림] 입력하신 정보를 다시 확인해주세요.");
		}
		else {
			System.out.println("비밀번호: " + Mcontroller.getInstance().searchPW(mId, mPhone));
		}
	}
		
	// 8. 회원탈퇴
	public boolean leave() throws Exception {
		System.out.println("-------------------회원탈퇴 페이지-------------------");
		System.out.print("비밀번호: ");		String mpw = scanner.next();
		
		if( Mcontroller.getInstance().leave(mpw) ) { System.out.println("[알림] 회원 탈퇴 정상 완료"); return true;}
		else { System.out.println("[알림] 비밀번호가 다릅니다."); return false; }
	}
	
	// 9. 계정 활성화
	public void active() throws Exception{
		System.out.println("-------------------휴먼 계정 복구 페이지-------------------");
		System.out.print("아이디: ");		String mId = scanner.next();
		System.out.print("비밀번호: ");		String mpw = scanner.next();
		
		int result = Mcontroller.getInstance().active(mId, mpw);
			
		if( result == 1 ) {System.out.println("[알림] 휴먼상태가 해제되었습니다. 아이디 사용 가능");}
		else if( result == 2 ) { System.out.println("[알림] 휴먼 계정이 아닙니다.");}
		else {System.out.println("[알림] 시스템 오류");}
	}
	
}
