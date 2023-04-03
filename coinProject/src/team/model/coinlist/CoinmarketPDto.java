package team.model.coinlist;

public class CoinmarketPDto {
	
	private int CMNo;				
	private int CIPrice; 			// 발행금액
	private int CMprice; 			// 현재가격
	private int CMRemaining;		// 잔여량
	private int cNo; 				// 코인정보
	private String cName;
	
	public CoinmarketPDto() {}

	public CoinmarketPDto(int cMNo, int cIPrice, int cMprice, int cMRemaining, int cNo, String cName) {
		super();
		CMNo = cMNo;
		CIPrice = cIPrice;
		CMprice = cMprice;
		CMRemaining = cMRemaining;
		this.cNo = cNo;
		this.cName = cName;
	}



	public String getcName() {
		return cName;
	}



	public void setcName(String cName) {
		this.cName = cName;
	}



	public int getCMNo() {
		return CMNo;
	}

	public void setCMNo(int cMNo) {
		CMNo = cMNo;
	}

	public int getCIPrice() {
		return CIPrice;
	}

	public void setCIPrice(int cIPrice) {
		CIPrice = cIPrice;
	}

	public int getCMprice() {
		return CMprice;
	}

	public void setCMprice(int cMprice) {
		CMprice = cMprice;
	}

	public int getCMRemaining() {
		return CMRemaining;
	}

	public void setCMRemaining(int cMRemaining) {
		CMRemaining = cMRemaining;
	}


	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}
	
	
	

}
