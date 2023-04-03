package team.model.member.DAO;

import team.model.Dao;
import team.model.member.DTO.AccountDto;

public class AccountDao extends Dao {

	// 싱글톤 적용
	private static AccountDao adao = new AccountDao();
	private AccountDao() {}
	public static AccountDao getInstance() { return adao; }
	
	// 메소드 영역
	// 5. 계좌 생성
	public boolean createAcc( AccountDto aDto ) {
		
		String sql = "insert into create_acc( accName, accountNo, accBalance, mNo ) values( ?, ?, ?, ? )";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, aDto.getAccName());
			ps.setString(2, aDto.getAccountNo());
			ps.setInt(3, aDto.getAccBalance());
			ps.setInt(4, aDto.getmNo());
			ps.executeUpdate();
	
			return true;
		} catch( Exception e ) { System.out.println("예외 발생:" + e ); }
		
		return false;
	}
}
