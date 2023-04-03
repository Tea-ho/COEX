package team.model.coinlist;

import java.util.ArrayList;


import team.model.Dao;

public class Cdao extends Dao{
	// 싱글톤
	private static Cdao cdao = new Cdao();
	private Cdao() {}
	public static Cdao getInstance() {return cdao;}
	
	// 코인 출력 메소드
	public ArrayList<CoinmarketPDto> print_coin() {
		ArrayList<CoinmarketPDto> clist = new ArrayList<>();
		String sql = "select cm.CMNo , cm.CIPrice , cm.CMprice , cm.CMRemaining , c.cNo , c.cname\r\n"
				+ "    from coinlist c, coinmarketp cm\r\n"
				+ "    where c.cNo = cm.cno;";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				CoinmarketPDto dto = new CoinmarketPDto(
						rs.getInt(1), 
						rs.getInt(2), 
						rs.getInt(3), 
						rs.getInt(4),  
						rs.getInt(5),
						rs.getString(6)
						);
				clist.add(dto);
			}
			return clist;
		}catch (Exception e) {System.out.println(e);}
		return null;
	} // print_coin e
	
	public void refresh_coin() {
		ArrayList<CoinmarketPDto> clist = print_coin();
		for(int i=0;i<clist.size();i++) {
			String sql = "update coinmarketp set CIPrice = ? where cNo = ? ;";
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, clist.get(i).getCMprice());
				ps.setInt(2, clist.get(i).getcNo());
				ps.executeUpdate();
			}catch (Exception e) {System.out.println(e);}
		}
		
		
	}
	

}
