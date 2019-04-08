package com.test001;

//1단계. 라이브러리 import
import java.sql.*;

public class HelloJDBC_S {

	public static void main(String[] args) {
		
		Connection conn = null; // DB연결용
		Statement stmt = null; // Query 실행용
		
		try {
			//2단계. Register the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//3단계. Open a connection
			conn = DriverManager.getConnection("jdbc:oracle:thin:@211.238.142.52:1521:xe","qkrcksdn","1234");
			
			System.out.println("오라클 연결 성공");
			
			//4단계. 쿼리 준비 및 실행
			//주의) 쿼리 끝 부분에 ';' 문자 없다
			String sql = "SELECT id_, age, first_, last_\r\n" + // "\r\n" 줄바꿈
					 "    FROM employees";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			
			//5단계. 쿼리 실행 결과 분석 및 출력
			while(rs.next()) {
				int id_ = rs.getInt("id_");
				int age = rs.getInt("age");
				String first_ = rs.getString("first_");
				String last_ = rs.getString("last_");
				
				System.out.printf("%d / %d / %s / %s%n"
						, id_, age, first_, last_);
			}
			
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			//6단계. Clean-up environment
			 try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
	}

}
