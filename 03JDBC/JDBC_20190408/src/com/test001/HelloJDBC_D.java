package com.test001;

//1단계. 라이브러리 import
import java.sql.*;

public class HelloJDBC_D {

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
			String sql = "DELETE FROM employees WHERE id_ = 104";
			stmt = conn.createStatement();
			
			//5단계. 쿼리 실행 결과 분석 및 출력
			int result = stmt.executeUpdate(sql); 
			System.out.printf("%d개의 행이 삭제되었습니다.%n",result);
			
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
