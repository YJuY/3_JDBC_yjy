package jdbc.mini.main.model.service;

import static jdbc.mini.common.JDBCTemplate.*;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jdbc.mini.main.model.dao.MainDao;
import jdbc.mini.main.vo.Reservation;

public class MainService {

	private MainDao dao = new MainDao();

	/** 예약 서비스
	 * @return result
	 * @throws Exception
	 */
	public int reservation(Reservation reser) throws Exception{
		Connection conn= getConnection();
		
		int result = dao.reservation(conn, reser);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 예약확인 서비스
	 * @param resNo
	 * @param resNumOf 
	 * @return reser
	 * @throws Exception
	 */
	public Reservation reservationCheck(int resNo, String resPhone) throws Exception {
		Connection conn = getConnection();
		
		Reservation reser = null;
		
		if(resPhone != null) {
			reser = dao.reservationCheck(conn, resNo, resPhone);
		}else {
			reser = dao.reservationCheck(conn, resNo,resPhone);
		}
		
		close(conn);
		
		return reser;
	}

	/** 예약 취소 서비스
	 * @param resNo
	 * @return
	 * @throws Exception
	 */
	public int cancelResr(int resNo) throws Exception{
		Connection conn = getConnection();   
		
		int result = dao.cancelResr(conn, resNo);
		
		if(result > 0) commit(conn);
		else		   rollback(conn);
		
		close(conn);
		return result;
	}

//	/** 날자 검사
//	 * @param resDate
//	 * @param date
//	 * @param date2
//	 * @return 
//	 * @throws Exception
//	 */
//	public boolean isWithinRange(String resDate, String date, String date2) throws Exception {
////		if(resDate.length() != 4 || date.length() != 4 || date2.length() != 4){
////	        return false;
////	    }        
////	    
////		resDate = resDate.substring(4, 6) + "-" + resDate.substring(6, 8);
////		date = date.substring(4, 6) + "-" + date.substring(6, 8);
////		date2 = date2.substring(4, 6) + "-" + date2.substring(6, 8);
////	    
////	    LocalDate localdate = LocalDate.parse(resDate);
////	    LocalDate startLocalDate = LocalDate.parse(date);
////	    LocalDate endLocalDate = LocalDate.parse(date2);
////	    endLocalDate = endLocalDate.plusDays(1); // endDate는 포함하지 않으므로 +1일을 해줘야함.
////	    
////	    return ( ! localdate.isBefore( startLocalDate ) ) && ( localdate.isBefore( endLocalDate ) );
//		
//		if(resDate)
//		
//	}




}