package jdbc.mini.main.view;

import java.security.Provider.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import jdbc.mini.main.model.dao.MainDao;
import jdbc.mini.main.model.service.MainService;
import jdbc.mini.main.vo.Reservation;
import jdbc.mini.menu.view.MenuView;
import jdbc.mini.review.view.ReviewView;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	private MenuView menuView = new MenuView();
	
	private ReviewView reviewView = new ReviewView();
	
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
				System.out.println("3. 식당 메뉴");
				System.out.println("4. 예약자 리뷰");
//				System.out.println("4. 직원 페이지");
				System.out.println("0. 종료");
				
				System.out.print("번호를 선택해 주세요 : ");
				input = sc.nextInt();
				
				
				switch(input) {
				case 1: reservation(); break;
				case 2: newCheck(); break;
				case 3: 
					MenuView.menuView(); break;
				case 4: 
					reviewView.reviewView(); break;
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
		String resName = null;
		String resPhone = null;
		String resDate = null;
		String resTime = null;
		int resNumOf = 0;
		
		try {
			System.out.println("[ 예약 ]");
			System.out.println("* 아래 사항을 모두 입력해 주세요.");
			System.out.print("성함(ex:홍길동) : ");
			resName = sc.next();
			sc.nextLine();
			
			System.out.print("전화번호 (- 업이 입력): ");
			resPhone = sc.next();
			sc.nextLine();
			
			// 현재 날짜 한달 후 날짜 구하기
			Calendar cal = Calendar.getInstance();
			String format = "MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String date = sdf.format(cal.getTime());
	
			cal.add(cal.MONTH, +1);
			String date2 = sdf.format(cal.getTime());
			
			System.out.println("예약 가능일시 ["+ date +"~"+ date2 + "]");
			
//			boolean re = false;
//			do {
					System.out.print("달 : ");
					String month = sc.next();
					
					System.out.print("일 : ");
					String day = sc.next();
					
					
					resDate = (month+"월"+day+"일");
					sc.nextLine();
					
//					re = service.isWithinRange(resDate, date, date2);
//					
//				if(re = true) {
//					System.out.println("등록완료1111111111111111111");
//					break;
//				}
//				
//				
//			}while(re);
//			
			

			
			System.out.println("-------------------------------- 예약 시간 --------------------------------");
			System.out.println("-------------------------------=== 오후 ===-------------------------------");
			System.out.println("        0) 12:00  1) 1:00  2) 2:00  3) 3:00 4) 4:00 5) 5:00 6) 6:00      ");
			System.out.println("\n예약 시간을 선택해 주세요 : ");
			
			resTime = sc.next();
			
			switch(resTime) {
			case "0": resTime = "12"; break;
			case "1": resTime = "1"; break;
			case "2": resTime = "2"; break;
			case "3": resTime = "3"; break;
			case "4": resTime = "4"; break;
			case "5": resTime = "5"; break;
			case "6": resTime = "6"; break;
			default: System.out.println("선택지에 있는 번호만 선택해 주세요!"); break;
			
			}
			
			while(true){
				System.out.println("**최대 예약 가능 인원은 4명 입니다.");
				System.out.println("예약인원(ex:4) : ");
				resNumOf = sc.nextInt();
				
				if(resNumOf <= 4) {
					break;
				}
			}
			
			Reservation reser = new Reservation(resName,resPhone,resDate,resTime,resNumOf);
			
			int result = service.reservation(reser);
			
			if(result > 0) {
				System.out.println("****************예약 완료 되었습니다.*******************");
				System.out.println(reser.getResName()+"님 예약해주셔서 감사합니다. 예약정보를 확인해 주세요.");
			}else {
				System.out.println("예약 실패............");
			}
			
			System.out.println();
			
			
			if(reser != null) {
				reservationCheck(0, resPhone);
			} else {
				System.out.println("예약번호가 없습니다.");
			}
			
			
			System.out.println("0.메인화면으로 돌아가기");
			
			
		}catch(Exception e){
			System.out.println("\n<<예약 중 예외발생......>>\n");
			e.printStackTrace();
		}
		
	}
	


	/**
	 * 메인예약확인
	 */
	private void newCheck() {
		System.out.print("예약번호를 입력해주세요 : ");
		int resNo = sc.nextInt();
		
		
		reservationCheck(resNo, "");
		
	}
	
	/**
	 * 예약확인
	 * @param resNumOf2 
	 * @param resTime2 
	 * @param resDate2 
	 */
	private void reservationCheck(int resNo, String resPhone) {
		try {
			
			Reservation reser = service.reservationCheck(resNo, resPhone);
			
			
			if(reser == null) {
				System.out.println("예약번호가 없습니다. 다시 확인해 주세요");
			}
			else if(reser.getResCancel().equals("Y")) {
				System.out.println("취소된 예약입니다.");
			
			} else {
				System.out.println("------------예약 확인----------");
				
				System.out.println("예약 번호 : "+ reser.getResNo());
				System.out.println("예약자 : " + reser.getResName());
				System.out.println("전화번호 : " + reser.getResPhone());
				System.out.println("예약 날짜 : " + reser.getResDate());
				System.out.println("예약 시간 : " + "(오후) "+reser.getResTime() + "시");
				System.out.println("예약 인원 : " + reser.getResNumOf()+" 명");
				System.out.println("-----------------------------");
				subbar(reser);
				
			}
			
			
			
			
			
		}catch(Exception e) {
			System.out.println("\n<<예약 조회중 예외 발생>>\n");
			e.printStackTrace();
			
		}
	}


	/** 예약화인후 서브 메뉴
	 * @param reser
	 */
	private void subbar(Reservation reser) {
		try {
			System.out.println(" 1) 예약취소 ");
			System.out.println(" 0) 돌아가기 ");
			
			System.out.print("선택해 주세요 : ");
			int input = sc.nextInt();
			
			switch(input) {
			case 1: cancelResr(reser.getResNo()); break;
			case 0:  break;
			
			default: System.out.println("\n[ 위에 있는 숫자만 작성해 주세요. ] \n");
			}
			
			
		}catch(Exception e) {
			System.out.println("\n<< 입력 형식이 올바르지 않습니다.>>\n");
		}
		
	}


	/**예약 취소
	 * @param resNo
	 */
	private void cancelResr(int resNo) {
		try {
			System.out.println("\n예약을 취소하시겠습니까?[Y/N] : ");
			char ch = sc.next().toUpperCase().charAt(0);
			
			if(ch =='Y') {
				int result = service.cancelResr(resNo);
				
				if(result > 0) {
					System.out.println("예약이 취소 되었습니다.");
				}else {
					System.out.println("예약취소 실패..");
				}
			}else {
				System.out.println("'N'선택");
			}
		}catch(Exception e) {
			System.out.println("\n<<예약 쉬소중 예외 발생>>\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
