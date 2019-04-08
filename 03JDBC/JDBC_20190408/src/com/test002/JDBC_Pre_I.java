package com.test002;

import java.sql.*;

import com.OracleConnection.*;

public class JDBC_Pre_I {

	public static void main(String[] args) {
		Connection conn = null; // DB연결용
		PreparedStatement stmt = null; // Query 실행용
		
		try {
			//2단계. Register the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//3단계. Open a connection
			conn = OracleConnection.connect();
			
			System.out.println("오라클 연결 성공");
			
			//4단계. 쿼리 준비 및 실행
			//주의) 쿼리 끝 부분에 ';' 문자 없다
			String sql = "INSERT INTO Employees VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,105);
			stmt.setInt(2,25);
			stmt.setString(3, "hello");
			stmt.setString(4, "world");
			
			//5단계. 쿼리 실행 결과 분석 및 출력
			int result = stmt.executeUpdate(); 
			System.out.printf("%d개의 행이 삽입되었습니다.%n",result);
			
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
		        	 OracleConnection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
	}

}
