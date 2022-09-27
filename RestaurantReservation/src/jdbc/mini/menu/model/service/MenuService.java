package jdbc.mini.menu.model.service;
import static jdbc.mini.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import jdbc.mini.menu.model.dao.MenuDAO;
import jdbc.mini.menu.vo.MenuAll;

public class MenuService {
	private MenuDAO dao = new MenuDAO();

	/** 메뉴판 조회
	 * @return
	 * @throws Exception
	 */
	public List<MenuAll> selectMenuAll() throws Exception{
		Connection conn = getConnection();
		
		List<MenuAll> menuList = dao.selectMenuAll(conn);
		
		close(conn);
		
		return menuList;
	}
	
	
}
