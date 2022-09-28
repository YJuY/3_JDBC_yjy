package jdbc.mini.review.model.service;

import static jdbc.mini.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import jdbc.mini.review.model.dao.ReviewDAO;
import jdbc.mini.review.vo.Review;

public class ReviewService {

	private ReviewDAO rdao = new ReviewDAO();
	
	
	/** 리뷰보기 서비스
	 * @return reviewList
	 * @throws Exception
	 */
	public List<Review> reviewRead() throws Exception{
		Connection conn = getConnection();
		
		List<Review> reviewList = rdao.reviewRead(conn);
		
		close(conn);
		
		return reviewList;
	}


	/** 리뷰 작성 서비스
	 * @param review
	 * @return result
	 * @throws Exception
	 */
	public int InputReview(Review review) throws Exception {
		Connection conn = getConnection();
		
		int result = rdao.InputReview(conn, review);
		
		if(result >0) commit(conn);
		else		  rollback(conn);
		
		close(conn);
		
		return result;
	}

//
//	/** 평점 평균
//	 * @return
//	 * @throws Exception 
//	 */
//	public Review scoreAvg() throws Exception {
//		Connection conn = getConnection();
//		Review review = rdao.scoreAvg(conn);
//		
//		close(conn);
//		
//		return review;
//	}

}
