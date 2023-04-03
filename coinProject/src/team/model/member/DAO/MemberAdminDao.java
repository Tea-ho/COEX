package team.model.member.DAO;

import java.util.ArrayList;

import team.model.Dao;
import team.model.member.DTO.CoinDto;
import team.model.member.DTO.MemberDto;
import team.model.selling.Sdao;

public class MemberAdminDao extends Dao {

	// 싱글톤 적용
	private static MemberAdminDao dao = new MemberAdminDao();
	private MemberAdminDao() {}
	public static MemberAdminDao getInstance() { return dao; }
	
	// 6-1. 코인 등록 권한 확인
	public boolean adminCoin( int mNo ) {
		String sql = "select * from member where mNo = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mNo);
			rs = ps.executeQuery();
			if( rs.next() ) { 
				if(rs.getString(2).equals("admin")) { return true; }
			}
			else { return false; }
		} catch(Exception e) { System.out.println("예외발생: " + e);}
		return false;
	}

	// 6-2. 코인 중복 체크
	public int coinCheck( String cName ) {
		String sql = "select * from coinlist where cName = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cName);
			rs = ps.executeQuery();
			if( rs.next() ) { return 2; }
			else { return 1; }
		} catch(Exception e) { System.out.println("예외발생: " + e);}
		return 3;
	}
		
	// 6-3. 코인 등록 처리
	public int regiCoin( CoinDto cDto ) {
			
		String sql = "insert into coinlist( cName, cPrice, cAmount ) values( ?, ?, ? )";
			
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, cDto.getcName());
			ps.setInt(2, cDto.getcPrice());
			ps.setInt(3, cDto.getcAmount());
			ps.executeUpdate();
			Sdao.getInstance().copy();
			return 1;
		} catch( Exception e ) { System.out.println("예외 발생:" + e ); }
			
		return 2;
	}

		// 7. 회원 정보 출력
	public ArrayList<MemberDto> printMemberList(){
		
		ArrayList<MemberDto> mList = new ArrayList<>();
			
		String sql = "select * from member";
		try {			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				MemberDto dto = new MemberDto( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7));
				mList.add(dto);
			}
		} catch( Exception e ) { System.out.println("예외 발생:" + e ); }
		return mList;
	}
		
	// 8. 휴먼 계정처리
	public int suspend( int mNo ) {
			
		String sql = "select * from member where mNo =?";
			
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mNo);
			rs = ps.executeQuery();
			if( rs.next()) {
						
				String usql = "update member set mState = ? where mNo = ?";
				ps = con.prepareStatement(usql);
				if( rs.getBoolean(7) ) {
					ps.setBoolean(1, false);
					ps.setInt(2, mNo);
					ps.executeUpdate();
					return 1; // 휴먼 처리
				}
				else { 
					ps.setBoolean(1, true);
					ps.setInt(2, mNo);
					ps.executeUpdate();
					return 2; // 활성화
				}
			}

		}catch( Exception e ) { System.out.println("예외 발생:" + e ); }
		return 3; // 시스템 오류
	}		
}
