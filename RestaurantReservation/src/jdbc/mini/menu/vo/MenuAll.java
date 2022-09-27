package jdbc.mini.menu.vo;

public class MenuAll {

	private int menuNo;	//메뉴번호
	private String menuName;	//메뉴 이름
	private String menuPrice;	//가격
	
	public MenuAll(int menuNo, String menuName, String menuPrice) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	
	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}

	
}
