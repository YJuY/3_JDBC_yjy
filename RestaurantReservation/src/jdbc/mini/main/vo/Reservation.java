package jdbc.mini.main.vo;

public class Reservation {
	
	private int resNo;		// 예약번호
	private String resName;	// 예약이름
	private String resDate;	// 예약날짜
	private String resTime;	// 예약시간
	private int resNumOf;	// 예약인원
	private String resCancel;//예약취소 여부

	public Reservation() { }
	
	public Reservation(int resNo, String resName, String resDate, String resTime, int resNumOf, String resCancel) {
		super();
		this.resNo = resNo;
		this.resName = resName;
		this.resDate = resDate;
		this.resTime = resTime;
		this.resNumOf = resNumOf;
		this.resCancel = resCancel;
	}

	public Reservation(String resName, String resDate, String resTime, int resNumOf) {
		super();
		this.resName = resName;
		this.resDate = resDate;
		this.resTime = resTime;
		this.resNumOf = resNumOf;
	}

	


	// getter/setter
	public int getResNo() {
		return resNo;
	}

	public void setResNo(int resNo) {
		this.resNo = resNo;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = resDate;
	}

	public String getResTime() {
		return resTime;
	}

	public void setResTime(String resTime) {
		this.resTime = resTime;
	}

	public int getResNumOf() {
		return resNumOf;
	}

	public void setResNumOf(int resNumOf) {
		this.resNumOf = resNumOf;
	}

	public String getResCancel() {
		return resCancel;
	}

	public void setResCancel(String resCancel) {
		this.resCancel = resCancel;
	}




	
	
	
}




