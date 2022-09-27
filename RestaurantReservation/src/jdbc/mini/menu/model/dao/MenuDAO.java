package jdbc.mini.menu.model.dao;
import static jdbc.mini.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
