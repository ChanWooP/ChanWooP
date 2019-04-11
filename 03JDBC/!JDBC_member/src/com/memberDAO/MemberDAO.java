package com.memberDAO;

import java.sql.*;
import java.util.*;

import com.OracleConnection.*;
import com.member.*;

public class MemberDAO {
	
	//출력메소드
	public List<Member> list(){
		List<Member> result = new ArrayList<Member>();
		
		//데이터베이스에서 읽어온 자료 집합(ResultSet)을 컬렉션(List)에 저장 및 변환
		Connection conn = null; // DB연결용
		PreparedStatement stmt = null; // Query 실행용
		
		try {

			conn = OracleConnection.connect();
			
			String sql = "SELECT mid, name_, phone, email\r\n"+
							"FROM members\r\n"+
							"ORDER BY mid";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				result.add(new Member(mid,name_,phone,email));
			}
			
			rs.close();
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			 try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }
		      try{
		        	 OracleConnection.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
		
		return result;
	}
	
	//입력메소드
	public int add(Member m) {
		int result = 0;
		
		//데이터베이스에 자료 입력 후 반환된 행의 결과값을 숫자로 반환
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = OracleConnection.connect();
			
			
			
			String sql = "INSERT INTO members(mid, name_, phone, email) VALUES(?,?,?,?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,m.getMid());
			stmt.setString(2,m.getName_());
			stmt.setString(3,m.getPhone());
			stmt.setString(4,m.getEmail());
			
			result = stmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				OracleConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return result;
	}
	
}
