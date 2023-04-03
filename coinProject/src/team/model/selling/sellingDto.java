package team.model.selling;

public class sellingDto {
	
	// 1. 필드
	private String cname;
	private int cmprice;
	private int cmremaining;
	private int recent_trade;
	private int pcsumprice;
	private int pcamount;
	private double rate;
	private int proceeds;
	

	
	// 2. 생성자
	public sellingDto() {}
	
	public sellingDto( String cname, int cmprice, int cmremaining, int recent_trade, int pcsumprice, int pcamount, double rate, int proceeds ) {
		this.cname = cname;
		this.cmprice = cmprice;
		this.cmremaining = cmremaining;
		this.recent_trade = recent_trade;
		this.pcsumprice = pcsumprice;
		this.pcamount = pcamount;
		this.rate = rate;
		this.proceeds = proceeds;
	}
	
	public sellingDto( String cname , int pcsumprice , int pcamount , int cmprice , int proceeds , double rate ) {
		this.cname = cname;
		this.pcsumprice = pcsumprice;
		this.pcamount = pcamount;
		this.cmprice = cmprice;
		this.proceeds = proceeds;
		this.rate = rate;
	}


	// 3. 메소드

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}


	public int getCmprice() {
		return cmprice;
	}

	public void setCmprice(int cmprice) {
		this.cmprice = cmprice;
	}

	public int getCmremaining() {
		return cmremaining;
	}

	public void setCmremaining(int cmremaining) {
		this.cmremaining = cmremaining;
	}
	
	public int getRecent_trade() {
		return recent_trade;
	}

	public void setRecent_trade(int recent_trade) {
		this.recent_trade = recent_trade;
	}

	public int getPcsumprice() {
		return pcsumprice;
	}

	public void setPcsumprice(int pcsumprice) {
		this.pcsumprice = pcsumprice;
	}

	public int getPcamount() {
		return pcamount;
	}

	public void setPcamount(int pcamount) {
		this.pcamount = pcamount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getProceeds() {
		return proceeds;
	}


	public void setProceeds(int proceeds) {
		this.proceeds = proceeds;
	}
	
}




	