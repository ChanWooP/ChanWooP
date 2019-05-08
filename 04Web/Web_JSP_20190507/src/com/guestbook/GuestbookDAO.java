package com.guestbook;

import java.util.*;
import java.sql.*;
import com.connection.*;

public class GuestbookDAO {
	public List<Guestbook> glist() {
		List<Guestbook> result = new ArrayList<Guestbook>();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			// SELECT -> 최초 실행시
			String sql = "SELECT ssn, name_, title, sdate, ipaddress, blind, pw, contents FROM Guestbook WHERE blind=0";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				int ssn = rs.getInt("ssn");
				String name_ = rs.getString("name_");
				String sdate = rs.getString("sdate");
				String ipaddress = rs.getString("ipaddress");
				int blind = rs.getInt("blind");
				String contents = rs.getString("contents");

				result.add(new Guestbook(ssn, name_, sdate, ipaddress, blind, contents));

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

	public int totalCount() {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MySQLConnection80.connect();

			String sql = "SELECT COUNT(*) count_ FROM guestbook WHERE blind=0";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count_");
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace();
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
