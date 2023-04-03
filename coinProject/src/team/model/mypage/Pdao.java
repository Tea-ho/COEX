package team.model.mypage;

import java.util.ArrayList;

import team.controller.Mcontroller;
import team.controller.Pcontroller;
import team.model.Dao;

public class Pdao extends Dao{
	
	// 싱글톤
	private static Pdao pdao = new Pdao();
	private Pdao() { }
	public static Pdao getInstance() { return pdao; }
		
	// 1. 계좌 정보 확인( aName[이름] , aAcount[계좌번호] , aBalance[계좌잔고] , aAmount[코인 잔여개수]  )
	public ArrayList<mypageDto> checkAccount( int mNo  ) { // 인수 mNo , mNo -> 반환 : true[성공] / false[실패]
		
		ArrayList<mypageDto> list = new ArrayList<>();
				
		String sql = "select ac.accNo , m.mName , ac.accName , ac.accountNo , ac.accBalance from member m , create_acc ac "
				+ " where ac.mNo = m.mNo and m.mNo = ? ";
		
		try {

			ps = con.prepareStatement(sql);
			
			ps.setInt(1, mNo);
			
			rs = ps.executeQuery();
			while( rs.next() ) {
				mypageDto dto = new mypageDto( rs.getInt(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getInt(5) ); 
				
				list.add(dto);
				
			}
			
		}catch (Exception e) {System.out.println("DB 에러 : " + e ) ;}
		return list;
	}
	
	// 2. 계좌입금
	public boolean deposit( int mNo , int adeposit , int accNo , int accBalance   ) { 
		String sql1 = "insert into account ( mNo , adeposit ) values ( ? , ? )";
		String sql2 = "update create_acc set accBalance = ( accBalance + ? ) where accNo = ? and mNo = ? ";
		
		try {
			ps = con.prepareStatement(sql1);
			
			ps.setInt(1, mNo);
			ps.setInt(2, adeposit);
			ps.executeUpdate();
			
			ps = con.prepareStatement(sql2);
			
			ps.setInt(1, adeposit);
			ps.setInt(2, accNo);
			ps.setInt(3, mNo);
			
			ps.executeUpdate();
			
			return true;
		}catch (Exception e) { System.out.println("DB 오류 : " + e);}
		return false;
	}
	
	// 2.1 입금계좌 찾기
	public int getaccBalance( int mNo ) {
		String sql = "select * from create_acc accBalance where mNo = ? ";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mNo);
			rs = ps.executeQuery();
			while ( rs.next() ) { return rs.getInt(4); }
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	// 2.2 입금계좌번호 찾기
	public int getAdeposit (int mNo) {
		String sql = "select * from account adeposit where mNo = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mNo);
			rs = ps.executeQuery();
			if ( rs.next() ) { return rs.getInt(6); }
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	
	// 3. 계좌출금
	public boolean withdraw( int mNo , int withdraw , int accNo , int accBalance    ) {
		String sql1 = "insert into account ( mNo , withdraw ) values ( ? , ? )"; 
		String sql2 = "update create_acc set accBalance = (accBalance - ?) where accNo = ? and mNo =?";
		
		// 출금 금액이 입금 금액 초과 불가하게 유효성 검사
		int max_withdraw =  Pcontroller.getInstance().getaccBalance(Mcontroller.getInstance().getLogSession());
		if ( Pcontroller.getInstance().getaccBalance(Mcontroller.getInstance().getLogSession()) - withdraw < 0 ) {
			System.out.println("[거래실패] 출금 가능한 금액을 초과했습니다.");
			System.out.println("최대 출금 금액 : " + max_withdraw );
			return false;
		}
		
		try {
			ps = con.prepareStatement(sql1);
			
			ps.setInt(1, mNo);
			ps.setInt(2, withdraw);
			
			ps = con.prepareStatement(sql2);
			
			ps.setInt(1, withdraw);
			ps.setInt(2, accNo);
			ps.setInt(3, mNo);
			
			ps.executeUpdate();

			
			return true;
		}catch (Exception e) { System.out.println("DB 오류 : " + e);}
		return false;
	}
	

	
}