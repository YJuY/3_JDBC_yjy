package jdbc.mini.review.vo;

import java.util.List;

public class Review {
	private int resNo;	// 예약 번호
	private int score;	// 리뷰 점수
	private String reviewContents;	// 리뷰 내용
	private String reviewDate;	// 리뷰작성일
	private int reviewNo;	// 리뷰 번호
	private List<Review> reviewLsit; 	// 리뷰 목록
	
	
	public Review() { }


	public int getResNo() {
		return resNo;
	}


	public void setResNo(int resNo) {
		this.resNo = resNo;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getReviewContents() {
		return reviewContents;
	}


	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}


	public String getReviewDate() {
		return reviewDate;
	}


	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}


	public int getReviewNo() {
		return reviewNo;
	}


	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}


	public List<Review> getReviewLsit() {
		return reviewLsit;
	}


	public void setReviewLsit(List<Review> reviewLsit) {
		this.reviewLsit = reviewLsit;
	}
	
	
	
	
}
