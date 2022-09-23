package jdbc.mini.main.view;

import java.security.Provider.Service;
import java.util.Scanner;

import jdbc.mini.main.model.dao.MainDao;
import jdbc.mini.main.model.service.MainService;
import jdbc.mini.main.vo.Reservation;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	private MainDao dao = new MainDao();
	/**
	 * 첫 시간 화면
	 */
	public void MainList() {
		
		
		
		
		int input =-1;
		
		do {
			try {
				System.out.println("[ 식당 예약 페이지 입니다. ] ");
				System.out.println("1. 예약");
				System.out.println("2. 예약확인");
				
				System.out.print("번호를 선택해 주세요 : ");
				input = sc.nextInt();
				
				
				switch(input) {
				case 1: reservation(); break;
				case 2: reservationCheck(); break;
				case 0: System.out.println("이용해 주셔서 감사합니다." ); break;
				default: System.out.println("선택지에 있는 번호만 선택해 주세요!");
					
				}
			}catch(Exception e) {
				System.out.println("입력 형식이 올바르지 않습니다.");
				sc.nextLine();
			}
		}while(input !=0);
	}



	/**
	 * 예약 화면
	 */
	private void reservation() {
		
		
		
		System.out.println("[ 예약하기 ] ");
		System.out.println(" * 아래 사항을 작성해 주세요.");
		System.out.println("날짜 선택 : ");
		String day = sc.next();
		
		System.out.println("시간 선택 : ");
		int time = sc.nextInt();
		
		
		int NumOfPeople = 0;
		while(true) {
			if(NumOfPeople > 0 && NumOfPeople < 5) {
				System.out.println("인원 선택(1명~4명): ");
				NumOfPeople = sc.nextInt();
				break;
				
			}else {
				System.out.println("1명~4명까지만 예약가능합니다.");
			}
		}
		
		Reservation reser = new Reservation();
		reser.setDay(day);
		reser.setTime(time);
		reser.setNumOfPeople(NumOfPeople);
		
		int result = service.reservation(day, time, NumOfPeople);
		
		if(result >0) commit(conn)
//		System.out.println("메뉴 선택(선택사항) : ");
		
		
	}
	
	
	/**
	 * 예약확인 화면
	 */
	private void reservationCheck() {
		System.out.println("예약번호를 입력해주세요 : ");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
