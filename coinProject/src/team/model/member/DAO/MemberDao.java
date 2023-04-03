package team.model.member.DAO;

import java.sql.DriverManager;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import team.controller.Mcontroller;
import team.model.Dao;
import team.model.member.DTO.CoinDto;
import team.model.member.DTO.MemberDto;

public class MemberDao extends Dao{

	// 싱글톤 적용
	private static MemberDao mdao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { return mdao; }
	
	// 메소드 영역
	// 1-1. 회원가입 (아이디 중복 체크)
	public int idCheck( String mId ) {
		String sql = "select * from member where mId = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mId);
			rs = ps.executeQuery();
			if( rs.next() ) { return 2; }
			else { return 1; }
		} catch(Exception e) { System.out.println("예외발생: " + e);}
		return 3;
	}
	
	// 1-2. 회원가입 처리
	public int signUp( MemberDto mDto ) {
		
		String sql = "insert into member( mId, mPw, mName, mPhone, mEmail, mState ) values( ?,?,?,?,?,? )";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mDto.getmId());
			ps.setString(2, mDto.getmPw());
			ps.setString(3, mDto.getmName());
			ps.setString(4, mDto.getmPhone());
			ps.setString(5, mDto.getmEmail());
			ps.setBoolean(6, mDto.getmState());
			ps.executeUpdate();
			return 1;
		}catch( Exception e ) { System.out.println("예외 발생:" + e ); }
		return 3;
	}
	
	// 2. 로그인
	public int login( String mId, String mPw ){
		
		String sql = "select * from member where mId = ? and mPw = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mId);
			ps.setString(2, mPw);
			rs = ps.executeQuery();
			if( rs.next() ) { 	
				if(rs.getBoolean(7)) { return rs.getInt(1); }
				else { return -1; }
			}
		} catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	// 3-1. 아이디 찾기(핸드폰) (오버로딩)
	public String searchID( String mPhone ) {
		
		String sql = "select * from member where mPhone =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mPhone);
			rs = ps.executeQuery();
			if( rs.next()) { return rs.getString(2); }
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 3-2. 아이디 찾기(이름, 이메일)
	public String searchID( String mName, String mEmail ) {

		String sql = "select * from member where mName = ? and mEmail =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mName);
			ps.setString(2, mEmail);
			rs = ps.executeQuery();
			if( rs.next()) { return rs.getString(2); }
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 4. 비밀번호 찾기
	public String searchPW( String mId, String mPhone ) {
		String sql = "select * from member where mId = ? and mPhone =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mId);
			ps.setString(2, mPhone);
			rs = ps.executeQuery();
			if( rs.next()) { return rs.getString(3); }
		}catch (Exception e) {System.out.println(e);}
		return null;
	}	
	
	// 9. 회원 탈퇴
	public boolean leave( int mNo, String mPw ) {
		
		String sql = "select * from member where mNo = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, mNo);
			rs = ps.executeQuery();
			if( rs.next() ) {
				if(rs.getString(3).equals(mPw)) {
					String dsql = "delete from member where mNo = ? and mPw = ?";
					ps = con.prepareStatement(dsql);
					
					ps.setInt(1, mNo);
					ps.setString(2, mPw);
					ps.executeUpdate();
					return true;
				}
			}	
		} catch( Exception e ) { System.out.println("예외 발생:" + e ); }
		return false;
	}
	
	// 10. 회원 계정 활성화
	public int active( String mId, String mPw ){
				
		String sql = "select * from member where mId = ? and mPw = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mId);
			ps.setString(2, mPw);
			rs = ps.executeQuery();
			if( rs.next() ) {
				
				String usql = "update member set mState = ? where mId = ?";
				// 아이디 중복검사 하기 때문에, 고유값으로 사용 가능
				ps = con.prepareStatement(usql);
				if( rs.getBoolean(7) ) { return 2; } // 이미 활성화되있는 계정
				else {
					ps.setBoolean(1, true);
					ps.setString(2, mId);
					ps.executeUpdate();
					return 1; // 활성화 완료
				} 
			}
		}catch (Exception e) {System.out.println(e);}
		return 3;
	}
}
