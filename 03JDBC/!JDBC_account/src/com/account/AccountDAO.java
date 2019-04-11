package com.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.connection.*;

public class AccountDAO {
	
	//계좌조회
	public List<AccountList> list(String key, Map<String, String> value){
		List<AccountList> result = new ArrayList<AccountList>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			//계좌번호 기준, 이름/전화번호 기준
			conn = OracleConnection.connect();
			
			String sql = "SELECT a1.accountId, a1.balance, a1.accountCreateDate \r\n"
							+", a1.lastUpdateDate, a2.accountOwnerName, a2.accountOwnerPhone, a2.accountOwnerId \r\n"
				    		+"FROM Account_ a1, AccountOwner_ a2 \r\n"
				    		+"WHERE a1.accountOwnerId = a2.accountOwnerId \r\n";
				    		
			
			if(key.equals("account")) {
				sql += "AND a1.accountId = ? \r\n";
			}else if(key.equals("name/phone")) {
				sql += "AND a2.accountownername = ? \r\n";
				sql += "AND a2.accountownerphone = ? \r\n";
			}
				    
			sql += "ORDER BY a1.accountId";
			
			stmt = conn.prepareStatement(sql);
			
			if(key.equals("account")) {
				stmt.setString(1, value.get("1"));
			}else if(key.equals("name/phone")) {
				stmt.setString(1, value.get("1"));
				stmt.setString(2, value.get("2"));
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String accountId = rs.getString("accountId");
				int balance = rs.getInt("balance");
				String accountCreateDate = rs.getString("accountCreateDate");
				String lastUpdateDate = rs.getString("lastUpdateDate");
				String accountOwnerName = rs.getString("accountOwnerName"); 
				String accountOwnerPhone = rs.getString("accountOwnerPhone");
				String accountOwnerId = rs.getString("accountOwnerId");
				
				result.add(new AccountList(accountId, balance, accountCreateDate
						, lastUpdateDate, accountOwnerName, accountOwnerPhone, accountOwnerId));
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
	
	//현재 잔액 확인용 메소드
	public int getBalance(String accountId) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			String sql = "SELECT balance \r\n" + 
					"    FROM Account_ \r\n" + 
					"    WHERE accountID = ?";

			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, accountId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt("balance");
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
	//입금 액션 메소드
	public int deposit(AccountHistory ah) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			//트랜잭션 처리
			
			// 자동 commit 취소
			conn.setAutoCommit(false); 
			
			//insert + update
			String sql1 = "INSERT INTO AccountHistory_\r\n" +
					"	(accountId,money,inoutdate,gubun,balance)\r\n" + 
					"    VALUES(?, ?, SYSDATE, '입금', ?)";
			
			stmt = conn.prepareStatement(sql1);
			
			int newBalance = ah.getBalance() + ah.getMoney();
			stmt.setString(1, ah.getAccountId());
			stmt.setInt(2, ah.getMoney());
			stmt.setInt(3, newBalance);
			
			stmt.executeUpdate();
			stmt.close();
			
			String sql2 = "UPDATE Account_\r\n" + 
					"    SET balance = ?\r\n" + 
					"        ,lastupdatedate = SYSDATE\r\n" + 
					"    WHERE accountID = ?";
			
			stmt = conn.prepareStatement(sql2);
			
			stmt.setInt(1, newBalance);
			stmt.setString(2, ah.getAccountId());
			
			stmt.executeUpdate();
			
			//커밋
			conn.commit();
			
			//입금 후 잔액 변동
			result = newBalance;

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
	//출금 액션 메소드
	public int withdraw(AccountHistory ah) {
		int result = -1;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			//트랜잭션 처리
			
			// 자동 commit 취소
			conn.setAutoCommit(false); 
			
			//insert + update
			String sql1 = "INSERT INTO AccountHistory_\r\n" +
					"	(accountId,money,inoutdate,gubun,balance)\r\n" + 
					"    VALUES(?, ?, SYSDATE, '출금', ?)";
			
			stmt = conn.prepareStatement(sql1);
			
			int newBalance = ah.getBalance() - ah.getMoney();
			stmt.setString(1, ah.getAccountId());
			stmt.setInt(2, ah.getMoney());
			stmt.setInt(3, newBalance);
			
			stmt.executeUpdate();
			stmt.close();
			
			String sql2 = "UPDATE Account_\r\n" + 
					"    SET balance = ?\r\n" + 
					"        ,lastupdatedate = SYSDATE\r\n" + 
					"    WHERE accountID = ?";
			
			stmt = conn.prepareStatement(sql2);
			
			stmt.setInt(1, newBalance);
			stmt.setString(2, ah.getAccountId());
			
			stmt.executeUpdate();
			
			//커밋
			conn.commit();
			
			//출금 후 잔액 변동
			result = newBalance;

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
	
	//계좌비밀번호 확인
	public boolean pwCheck(Account a) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			String sql = "SELECT pw \r\n" + 
					"    FROM Account_ \r\n" + 
					"    WHERE accountid =?";
				    		
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, a.getAccountId());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getString("pw").equals(a.getPw());
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
	
	//계좌 자동 생성 메소드
	public String getNewAccountId() {
		String result = "";
			
		Connection conn = null;
		PreparedStatement stmt = null;
			
		try {
				
			conn = OracleConnection.connect();
				
			String sql = "SELECT CONCAT('111-',SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,1,4)\r\n" + 
					"        || '-'\r\n" + 
					"        || SUBSTR(REPLACE(SUBSTR(MAX(accountId),5), '-') + 1,5)) NewAccountId \r\n" + 
					"    FROM account_";

			stmt = conn.prepareStatement(sql);
				
			ResultSet rs = stmt.executeQuery();
				
			while(rs.next()) {
				result = rs.getString("NewAccountId");
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
	
	//계좌 자동 생성 메소드
	public String getNewAccountOwnerId() {
		String result = "";
				
		Connection conn = null;
		PreparedStatement stmt = null;
				
		try {
					
			conn = OracleConnection.connect();
					
			String sql = "SELECT CONCAT('A', TRIM(TO_CHAR(SUBSTR(MAX(accountOwnerId), 2) + 1, '000'))) newAccountOwnerId\r\n" + 
						"    FROM AccountOwner_";

			stmt = conn.prepareStatement(sql);
					
			ResultSet rs = stmt.executeQuery();
					
			while(rs.next()) {
				result = rs.getString("newAccountOwnerId");
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
	
	//계좌주정보, 계좌정보 생성 메소드
	public boolean newAccount(AccountOwner ao, Account a) {
		
		boolean result = false;

		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			conn = OracleConnection.connect();
			
			// 자동 commit 취소
			conn.setAutoCommit(false); 
			
			if(ao != null) {
				//계좌주정보 생성
				String sql1 = "INSERT INTO AccountOwner_ (accountOwnerId,accountOwnerName,accountOwnerPhone)\r\n" + 
						"VALUES(?, ?, ?)";
				stmt = conn.prepareStatement(sql1);
				
				stmt.setString(1, ao.getAccountOwnerId());
				stmt.setString(2, ao.getAccountOwnerName());
				stmt.setString(3, ao.getAccountOwnerPhone());
				
				stmt.executeUpdate();
				stmt.close();
			}
			
			//계좌정보생성
			String sql2 = "INSERT INTO Account_(accountId,accountOwnerId ,balance ,accountCreateDate ,pw ,lastUpdateDate) \r\n" + 
					"VALUES(?, ?, ?, SYSDATE, ?, SYSDATE)";
			
			stmt = conn.prepareStatement(sql2);
			
			stmt.setString(1, a.getAccountId());
			stmt.setString(2, a.getAccountOwnerId());
			stmt.setInt(3, a.getBalance());;
			stmt.setString(4, a.getPw());
			
			stmt.executeUpdate();
			
			//커밋
			conn.commit();
			
			result = true;
			
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
