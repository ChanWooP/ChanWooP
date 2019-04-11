package com.score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.OracleConnection;

public class ScoreDAO {
	
	public List<Score> list(String key, String value){
		List<Score> result = new ArrayList<Score>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {

			conn = OracleConnection.connect();
			
			/*
			CREATE OR REPLACE VIEW scoresView
			AS
			SELECT sid_, name_, sub1, sub2, sub3
			        ,(sub1 + sub2 + sub3) sum_
			        ,ROUND(((sub1 + sub2 + sub3)/3),2) avg_
			        ,DENSE_RANK() OVER(ORDER BY (sub1 + sub2 + sub3) DESC) rank_
			    FROM scores;
			 */
			
			String sql = "SELECT sid_, name_, sub1, sub2, sub3 \r\n"
						+",sum_, avg_, rank_ \r\n"
						+"FROM scoresView \r\n";
			
			if(key.equals("sid_")) {
				sql += "WHERE sid_ = ? \r\n";
			}else if(key.equals("name_")) {
				sql += "WHERE INSTR(LOWER(name_),LOWER(?)) > 0 \r\n";
			}
			
			sql += "ORDER BY sid_";
			
			stmt = conn.prepareStatement(sql);
			
			if(key != "all") {
				stmt.setString(1, value);
			}
				
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String sid_ = rs.getString("sid_");
				String name_ = rs.getString("name_");
				int sub1 = rs.getInt("sub1");
				int sub2 = rs.getInt("sub2");
				int sub3 = rs.getInt("sub3");
				int sum_ = rs.getInt("sum_");
				double avg_ = rs.getDouble("avg_");
				int rank_ = rs.getInt("rank_");
				result.add(new Score(sid_, name_, sub1, sub2, sub3, sum_, avg_, rank_));
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
	
	public int add(Score s) {
		int result = 0;
		
		//데이터베이스에 자료 입력 후 반환된 행의 결과값을 숫자로 반환
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = OracleConnection.connect();
			
			String sql = "INSERT INTO scores (sid_, name_, sub1, sub2, sub3) \r\n"
						+"VALUES(?, ?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1,s.getSid_());
			stmt.setString(2,s.getName_());
			stmt.setInt(3,s.getSub1());
			stmt.setInt(4,s.getSub2());
			stmt.setInt(5,s.getSub3());
			
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
