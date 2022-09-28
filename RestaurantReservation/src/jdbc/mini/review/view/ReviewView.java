package jdbc.mini.review.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.mini.main.vo.Reservation;
import jdbc.mini.menu.vo.MenuAll;
import jdbc.mini.review.model.service.ReviewService;
import jdbc.mini.review.vo.Review;

public class ReviewView {
	
	private Scanner sc = new Scanner(System.in);
	
	private ReviewService rservice = new ReviewService();
	
	public void reviewView(List<Reservation> reserno) {
		int input = -1;
		
		do {
			try {
				reviewRead();
				System.out.println("1) 리뷰 작성");
				System.out.println("0) 돌아가기");
				input = sc.nextInt();
				
				switch(input) {
				case 1: inputReview(reserno); break;
				case 0: System.out.println("메인메뉴로 돌아갑니다."); break;
				
				default: System.out.println("정해진 숫자만 입력해주세요."); break;
				}
			}catch(InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				e.printStackTrace();
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
		}while(input !=0);
	}



	/**
	 * 리뷰보기+평점 평균
	 */
	private void reviewRead() {
		
		 try {
			 List<Review> reviewList = rservice.reviewRead();
			 if(reviewList.isEmpty()) {
				 System.out.println("아직 리뷰가 없습니다.");
			 }else {
				 for(Review r : reviewList) {
				 System.out.printf("번호 : %d|| 평점 : %d || 작성일 : %s || 한줄평 : %s \n--------------------------------------------------------------\n",
						 r.getReviewNo(), r.getReviewScore(), r.getReviewDate(), r.getReviewContents());
				
			 }
			
//			 System.out.println("평균 평점 : "+);
		 }
		 }catch(Exception e) {
			 System.out.println("\n<<리뷰 보기 실행 중 예외 발생>>\n");
			e.printStackTrace();
		 }
		
	}
	
	/**
	 * 리뷰 작성
	 */
	private void inputReview(List<Reservation> reserno) {

		try {
			
			System.out.println("\n\n");
			System.out.print("예약을 통해 식당을 이용하신 분만 리뷰 등록이 가능합니다. \n예약 번호를 입력해주세요 : ");
			int inputresNo = sc.nextInt();
		
			for(Reservation r : reserno) {
				if(r. getResNo() != inputresNo) {
					
				} else if(r. getResNo() == inputresNo) { 
					System.out.print("----------------------- 평점 -----------------------\n");
					System.out.println("  1) 1점  | 2) 2점  | 3) 3점  | 4) 4점   | 5) 5점 ");
					System.out.print("---------------------------------------------------\n");
					System.out.print("평점 입력 : ");
					int reviewScore = sc.nextInt();
					
					String reviewContents = "";
					String input = null;
					
					System.out.print("한줄 평가 입력($end 입력시 종료) : ");
					while(true){
						input = sc.nextLine();
						
						if(input.equals("$end")) {
							break;
						}
						reviewContents += input ;
					}
					
					Review review = new Review();
					review.setReviewScore(reviewScore);
					review.setReviewContents(reviewContents);
					
					int result = rservice.InputReview(review);
					
					if(result > 0) {
						System.out.println("\n[리뷰가 작성되었습니다.]\n");
					}else {
						System.out.println("\n[리뷰 작성 실패...]\n");
					}
					
				}else {
					System.out.println("예약 번호가 없습니다.");
				}
			}
				
				
		}catch(Exception e) {
			System.out.println("\n리뷰 작성 중 오류 발생\n");
			e.printStackTrace();
		}
		
		
	}


}
