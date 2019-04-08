package com.test001;

//1단계 라이브러리 import
import java.sql.*;

public class HelloJDBC {

	public static void main(String[] args) {

		try {
			//2단계. Register the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//3단계. Open a connection
			DriverManager.getConnection("jdbc:oracle:thin:@211.238.142.52:1521:xe","qkrcksdn","1234");
			
			System.out.println("오라클 연결 테스트 성공!");
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
