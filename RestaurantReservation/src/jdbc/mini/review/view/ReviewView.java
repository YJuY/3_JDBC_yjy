package jdbc.mini.review.view;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.mini.menu.vo.MenuAll;
import jdbc.mini.review.model.service.ReviewService;
import jdbc.mini.review.vo.Review;

public class ReviewView {
	
	private Scanner sc = new Scanner(System.in);
	
	private ReviewService rservice = new ReviewService();
	
	public void reviewView() {
		int input = -1;
		
		do {
			try {
				reviewRead();
				System.out.println("1) 리뷰 작성");
				System.out.println("0) 돌아가기");
				
				switch(input) {
				case 1: reviewWrite();
				case 0: System.out.println("메인메뉴로 돌아갑니다."); break;
				
				default: System.out.println("정해진 숫자만 입력해주세요."); break;
				}
			}catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
		}while(input !=0);
	}

	

	/**
	 * 리뷰보기
	 */
	private void reviewRead() {
		
		 try {
			 List<Review> reviewList = rservice.reviewRead();
			 for(Review review : reviewList) {
			 System.out.printf("평점 | %d | 작성일 | %s"
			 				 + "한줄평 | ",review.getScore(), review.getReviewDate(), review.getReviewContents());
		 }
		 }catch(Exception e) {
			 System.out.println("\n<<리뷰 보기 실행 중 예외 발생>>\n");
			e.printStackTrace();
		 }
		 
		
		
		
	}
	
	/**
	 * 리뷰 작성
	 */
	private void reviewWrite() {
		
		System.out.println("예약번호를 입력해주세요 : ");
		int input = 0;
		if(input=)
		int input = sc.nextInt();
		
	}
}
