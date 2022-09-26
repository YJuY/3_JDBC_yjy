package jdbc.mini.main.model.dao;

import static jdbc.mini.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import jdbc.mini.main.vo.Reservation;

public class MainDao {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public MainDao() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("main-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 예약 DAO
	 * @param conn
	 * @param reser
	 * @return result
	 * @throws Exception
	 */
	public int reservation(Connection conn, Reservation reser) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("reservation");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,reser.getResName());
			pstmt.setString(2,reser.getResDate());
			pstmt.setString(3, reser.getResTime());
			pstmt.setInt(4, reser.getResNumOf());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 예약 확인 DAO
	 * @param conn
	 * @param resNo
	 * @return reser
	 * @throws Exception
	 */
	public Reservation reservationCheck(Connection conn, int resNo) throws Exception {
		Reservation reser = null;
		
		try {
			String sql = prop.getProperty("reservationCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, resNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reser = new Reservation();
				
				reser.setResNo(rs.getInt("RES_NO"));
				reser.setResName(rs.getString("RES_NM"));
				reser.setResDate(rs.getString("RES_DATE"));
				reser.setResTime(rs.getString("RES_TIME"));
				reser.setResNumOf(rs.getInt("RES_NUMOF"));
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reser;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
