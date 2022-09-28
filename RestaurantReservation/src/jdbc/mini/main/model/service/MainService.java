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
import jdbc.mini.menu.vo.MenuAll;

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

	/** 번호 리스트 조회
	 * @return
	 */
	public List<Reservation> selectNoAll() throws Exception{
		Connection conn = getConnection();
		
		List<Reservation> reserno = dao.selectNoAll(conn);
		
		close(conn);
		
		return reserno;
	}




}