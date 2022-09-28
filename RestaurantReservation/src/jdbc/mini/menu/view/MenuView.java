package jdbc.mini.menu.view;

import java.awt.Menu;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.mini.menu.model.service.MenuService;
import jdbc.mini.menu.vo.MenuAll;

public class MenuView {
	private Scanner sc = new Scanner(System.in);

	private MenuService service = new MenuService();
	
	/**
	 * 메뉴 화면
	 */
	public void menuView() {
		int input =-1;
		
		do {
			try {
				
				selectMenuAll();
				System.out.println("0) 돌아가기");
				input = sc.nextInt();
				
				
			}catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>\n");
				e.printStackTrace();
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
		}while(input != 0);
		
	}

	/**
	 * 메뉴판 조회
	 */
	private void selectMenuAll() {
		 try {
			 System.out.println("***********[ 메뉴판 ]***********");
			 
			 List<MenuAll> menuList = service.selectMenuAll();
			 
			 if(menuList.isEmpty()) {
				 System.out.println("메뉴 존재 x");
			 }else {
				 for(MenuAll menu : menuList) {
					 System.out.printf("    %7s    %s\n",menu.getMenuName(), menu.getMenuPrice());
				 }
			 }
			 System.out.println("*******************************");
		 }catch(Exception e) {
			 System.out.println("\n<<메뉴 조회 중 예외 발생>>\n");
				e.printStackTrace();
		 }
		
	}


	
	
}
