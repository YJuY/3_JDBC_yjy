package jdbc.mini.main.model.dao;

import static jdbc.mini.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.mini.main.vo.Reservation;
import jdbc.mini.menu.vo.MenuAll;

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
			pstmt.setString(5,reser.getResPhone());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 예약 확인 DAO
	 * @param conn
	 * @return reser
	 * @throws Exception
	 */
	public Reservation reservationCheck(Connection conn, int resNo, String resPhone) throws Exception {
		Reservation reser = null;
		
		try {
			
			if(resPhone != null) {
				String sql = prop.getProperty("reservationCheckPhone");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, resPhone);

				
			}
			if(resNo != 0){
				String sql = prop.getProperty("reservationCheckNo");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, resNo);
			}
			
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reser = new Reservation();
				
				reser.setResNo(rs.getInt("RES_NO"));
				reser.setResName(rs.getString("RES_NM"));
				reser.setResPhone(rs.getString("RES_PHON"));
				reser.setResDate(rs.getString("RES_DATE"));
				reser.setResTime(rs.getString("RES_TIME"));
				reser.setResNumOf(rs.getInt("RES_NUMOF"));
				reser.setResCancel(rs.getString("RES_CANCEL"));
				
			}
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return reser;
	}

	/** 예약 취소 DAO
	 * @param conn
	 * @param resNo
	 * @return
	 * @throws Exception
	 */
	public int cancelResr(Connection conn, int resNo) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("cancelResr");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, resNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	/** 예약번호 모두 조회
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Reservation> selectNoAll(Connection conn) throws Exception{
		List<Reservation> resernoList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectNoAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				int resNo = rs.getInt("RES_NO");

				Reservation reser = new Reservation();
				
				reser.setResNo(resNo);
				
				resernoList.add(reser);

			}
		}finally {
			close(rs);
			close(stmt);
		}
		return resernoList;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
