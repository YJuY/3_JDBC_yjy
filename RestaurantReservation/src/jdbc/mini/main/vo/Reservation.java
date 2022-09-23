package jdbc.mini.main.vo;

public class Reservation {
	private String day;
	private int time;
	private int NumOfPeople;
	private int menu;
	
	public Reservation() { }

	// getter/setter
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNumOfPeople() {
		return NumOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		NumOfPeople = numOfPeople;
	}

	public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}


	
	
	
}




