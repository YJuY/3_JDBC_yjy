package jdbc.mini.main.view;

import java.security.Provider.Service;
import java.util.Scanner;

import jdbc.mini.main.model.dao.MainDao;
import jdbc.mini.main.model.service.MainService;
import jdbc.mini.main.vo.Reservation;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
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
				System.out.println("0. 종료");
				
				System.out.print("번호를 선택해 주세요 : ");
				input = sc.nextInt();
				
				
				switch(input) {
				case 1: reservation(); break;
				case 2: newCheck(); break;
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
		String resDate = null;
		String resTime = null;
		int resNumOf = 0;
		
		try {
			System.out.println("[ 예약 ]");
			System.out.println("* 아래 사항을 모두 입력해 주세요.");
			System.out.print("성함(ex:홍길동) : ");
			resName = sc.next();
			
			System.out.print("예약 날짜(ex:09월27일 or 09.27) : ");
			resDate = sc.next();
			
			System.out.println("--------------예약 시간--------------");
			System.out.println("--------------=== 오후 ==------------");
			System.out.println("12:00  12:30  1:00  1:30  2:00  2:30");
			System.out.println("3:00  3:30  4:00  4:30  5:00  5:30");
			System.out.println("6:00 ");
			
			System.out.println("예약 시간(ex: 12시 or 1시20분 or 2 or 3:30 or 4시) : ");
			resTime = sc.next();
			
			while(true){
				System.out.println("**최대 예약 가능 인원은 4명 입니다.");
				System.out.println("예약인원(ex:4) : ");
				resNumOf = sc.nextInt();
				
				if(resNumOf <= 4) {
					break;
				}
			}
			
			Reservation reser = new Reservation(resName,resDate,resTime,resNumOf);
			
			int result = service.reservation(reser);
			
			if(result > 0) {
				System.out.println("****************예약 완료 되었습니다.*******************");
				
			}else {
				System.out.println("예약 실패............");
			}
			
			System.out.println();
			
//			reservationCheck(reser.getResNo());
			
			if(reser != null) {
				System.out.println("------------예약 확인----------");
				System.out.println("예약 번호 : "+ reser.getResNo());
				System.out.println("예약자 : " + reser.getResName());
				System.out.println("예약 날짜 : " + reser.getResDate());
				System.out.println("예약 시간 : " + reser.getResTime());
				System.out.println("예약 인원 : " + reser.getResNumOf());
				System.out.println("-----------------------------");
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
		System.out.println("예약번호를 입력해주세요 : ");
		int resNo = sc.nextInt();
		
		reservationCheck(resNo);
		
	}
	
	/**
	 * 예약확인
	 */
	private void reservationCheck(int resNo) {
		try {
			String resTime = null;
			String resName = null;
			String resDate = null;
			int resNumOf = 0;
			String resCancel = null;
			
			
			
			Reservation reser = service.reservationCheck(resNo);
//			reser.setResNo(getInt("RES_NO"));
			
			if(reser != null) {
				System.out.println("------------예약 확인----------");
				System.out.println("예약 번호 : "+ reser.getResNo());
				System.out.println("예약자 : " + reser.getResName());
				System.out.println("예약 날짜 : " + reser.getResDate());
				System.out.println("예약 시간 : " + reser.getResTime());
				System.out.println("예약 인원 : " + reser.getResNumOf());
				System.out.println("-----------------------------");
			} else {
				System.out.println("예약번호가 없습니다.");
			}
			
			
		}catch(Exception e) {
			System.out.println("\n<<예약 조회중 예외 발생>>\n");
			e.printStackTrace();
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
