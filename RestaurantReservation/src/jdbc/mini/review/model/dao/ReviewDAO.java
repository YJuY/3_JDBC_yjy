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
	 * @return
	 * @throws Exception
	 */
	public List<Review> reviewRead(Connection conn) throws Exception{
		List<Review> reviewList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("reviewRead");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int score = rs.getInt("SCORE");
				String reviewContents = rs.getString("REVIEW_CONTENTS");
				String reviewDate = rs.getString("REVIEW_DATE");
				
				Review review = new Review();
				review.setScore(score);
				review.setReviewDate(reviewDate);
				review.setReviewContents(reviewContents);
				
				reviewList.add(review);

			}
		}finally {
			close(rs);
			close(stmt);
		}
		return reviewList;
	}



}
