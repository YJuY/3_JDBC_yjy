package jdbc.mini.review.model.service;

import static jdbc.mini.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import jdbc.mini.review.model.dao.ReviewDAO;
import jdbc.mini.review.vo.Review;

public class ReviewService {

	private ReviewDAO rdao = new ReviewDAO();
	/** 리뷰보기 서비스
	 * @return
	 * @throws Exception
	 */
	public List<Review> reviewRead() throws Exception{
		Connection conn = getConnection();
		
		List<Review> reviewList = rdao.reviewRead(conn);
		
		close(conn);
		
		return reviewList;
	}

}
