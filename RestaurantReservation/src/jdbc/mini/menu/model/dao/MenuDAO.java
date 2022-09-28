package jdbc.mini.menu.model.dao;
import static jdbc.mini.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.mini.menu.vo.MenuAll;

public class MenuDAO {
	// 필드(== 멤버 변수)
		private Statement stmt;
		private PreparedStatement pstmt;
		private ResultSet rs;
		private Properties prop;
		
		public MenuDAO() { // 기본 생성자
			try {
				prop = new Properties();
				prop.loadFromXML(new FileInputStream("menu-query.xml"));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		/** 메뉴판 조회
		 * @param conn
		 * @return menuList
		 * @throws Exception
		 */
		public List<MenuAll> selectMenuAll(Connection conn) throws Exception {
			List<MenuAll> menuList = new ArrayList<>();
			
			try {
				String sql = prop.getProperty("selectMenuAll");
				
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					
					String menuName = rs.getString("MENU_NM");
					String menuPrice = rs.getString("MENU_PRICE");
					
					MenuAll menu = new MenuAll();
					
					menu.setMenuName(menuName);
					menu.setMenuPrice(menuPrice);
					
					menuList.add(menu);
				}
			}finally {
				close(rs);
				close(stmt);
			}
			return menuList;
		}


		
		
		
		
		
		
		
		
		
		
		
		
		
}
