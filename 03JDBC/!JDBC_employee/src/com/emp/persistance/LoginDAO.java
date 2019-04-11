package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.connection.OracleConnection;

public class LoginDAO {
	
	//로그인
	public String login(String id, String pw) {
		String result = null;
		
		//전달받은 id, pw를 가지고 데이터베이스 조회
		//SELECT id FROM login WHERE id=? AND pw=?
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			String sql = "SELECT id_ FROM login WHERE id_=? AND pw_=?";

			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, pw);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getString("id_");
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
	

	//패스워드 변경
	public int pwChange(String id, String oldPw, String newPw) {
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			//전달받은 id_, oldPw, newPw를 가지고 데이터베이스 수정.
			//UPDATE login SET pw_ = ? WHERE id_ = ? AND pw_ = ?
			String sql = "UPDATE login SET pw_ = ? WHERE id_ = ? AND pw_ = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, newPw);
			stmt.setString(2, id);
			stmt.setString(3, oldPw);
			
			result = stmt.executeUpdate();
			

		}catch(ClassNotFoundException | SQLException e) {
			try {
				//롤백
				conn.rollback();
			}catch(SQLException e1) {
				e.printStackTrace();
			}
			
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
}
