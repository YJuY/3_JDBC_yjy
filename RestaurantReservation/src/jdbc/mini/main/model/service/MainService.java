package jdbc.mini.main.model.service;

import static jdbc.mini.common.JDBCTemplate.*;

import java.sql.Connection;
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
	 * @return reser
	 * @throws Exception
	 */
	public Reservation reservationCheck(int resNo) throws Exception {
		Connection conn = getConnection();
		
		Reservation reser = dao.reservationCheck(conn, resNo);
		
		if(reser != null) {
			System.out.println("값없음");
		}
		
		close(conn);
		
		return reser;
	}




}