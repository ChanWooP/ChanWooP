package com.subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.connection.MySQLConnection80;

public class SubjectDAO {

	public List<Subject_> slist() {
		List<Subject_> result = new ArrayList<Subject_>();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT subId, subName, subDate_, subNum, subCount FROM Subject_ ORDER BY subId";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String subId = rs.getString("subId");
				String subName = rs.getString("subName");
				String subDate_ = rs.getString("subDate_");
				String subNum = rs.getString("subNum");
				int subCount = rs.getInt("subCount");

				result.add(new Subject_(subId, subName, subDate_, subNum, subCount));
			}

			rs.close();

		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return result;
	}

	public int addSubject_(Subject_ sb) {

		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			String sql = "INSERT INTO Subject_\r\n" + "(subId, subName, subDate_, subNum, subCount)\r\n"
					+ "VALUES(?, ?, ?, ?, 0)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, sb.getSubId());
			stmt.setString(2, sb.getSubName());
			stmt.setString(3, sb.getSubDate_());
			stmt.setString(4, sb.getSubNum());

			result = stmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return result;

	}
	
	public int delSubject_(String subId) {

		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			String sql = "DELETE FROM Subject_ WHERE subId=?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, subId);

			result = stmt.executeUpdate();

		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				MySQLConnection80.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return result;

	}

}
