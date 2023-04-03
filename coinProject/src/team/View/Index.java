package team.View;

import team.View.Member;

public class Index {
	// 싱글톤
	private static Index index = new Index();
	private Index() {}
	public static Index getInstance() {return index;}
	
	// member index 메소드 호출
	public void index() {
		Member.getInstance().index();
	}
	

}
