package team.model.coinlist;

public class coinlistDto {

	private int cNo;
	private String cName;
	private int cPrice;
	private int cAmount;
	private int cFirstPrice;
	
	public coinlistDto() {}

	public coinlistDto(int cNo, String cName, int cPrice, int cAmount, int cFirstPrice) {
		super();
		this.cNo = cNo;
		this.cName = cName;
		this.cPrice = cPrice;
		this.cAmount = cAmount;
		this.cFirstPrice = cFirstPrice;
	}
	
	public int getcFirstPrice() {
		return cFirstPrice;
	}

	public void setcFirstPrice(int cFirstPrice) {
		this.cFirstPrice = cFirstPrice;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getcPrice() {
		return cPrice;
	}

	public void setcPrice(int cPrice) {
		this.cPrice = cPrice;
	}

	public int getcAmount() {
		return cAmount;
	}

	public void setcAmount(int cAmount) {
		this.cAmount = cAmount;
	}
	
	
	
	
	
	
	
}
