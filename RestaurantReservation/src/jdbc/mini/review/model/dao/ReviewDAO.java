package jdbc.mini.review.model.dao;

import static jdbc.mini.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.mini.review.vo.Review;


public class ReviewDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ReviewDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("review-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 리뷰 보기 DAO
	 * @return reviewList
	 * @throws Exception
	 */
	public List<Review> reviewRead(Connection conn) throws Exception{
		List<Review> reviewList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("reviewRead");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int reviewNo = rs.getInt("REVIEW_NO");
				int reviewScore = rs.getInt("REVIEW_SCORE");
				String reviewContents = rs.getString("REVIEW_CONTENTS");
				String reviewDate = rs.getString("REVIEW_DATE");
//				int avgscore = rs.getInt("AVG_SCORE");
				
				
				Review review = new Review();
				review.setReviewNo(reviewNo);
				review.setReviewScore(reviewScore);
				review.setReviewContents(reviewContents);
				review.setReviewDate(reviewDate);
//				review.setAvgscore(avgscore);
				
				reviewList.add(review);

			}
		}finally {
			close(rs);
			close(stmt);
		}
		return reviewList;
	}

	/** 리뷰 작성 DAO
	 * @param conn
	 * @param review
	 * @return result
	 * @throws Exception
	 */
	public int InputReview(Connection conn, Review review) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("InputReview");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, review.getReviewScore());
			pstmt.setString(2, review.getReviewContents());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


//	/**평점 평균DAO
//	 * @param conn
//	 * @return
//	 * @throws Exception
//	 */
//	public Review scoreAvg(Connection conn) throws Exception{
//		Review review = new Review();
//		
//		
//		try {
//			String sql = prop.getProperty("scoreAvg");
//			
//			stmt = conn.createStatement();
//			
//			rs = stmt.executeQuery(sql);
//			
//			if(rs.next()) {
//				int avgscore = rs.getInt("AVG_SCORE");
//				
//				review.setAvgscore(avgscore);
//			}
//			
//		}finally {
//			close(rs);
//			close(stmt);
//		}
//		
//		return review;
//	}



}
