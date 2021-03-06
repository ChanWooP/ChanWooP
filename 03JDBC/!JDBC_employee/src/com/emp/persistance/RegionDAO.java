package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.OracleConnection;
import com.emp.domain.Region;

public class RegionDAO {

	public List<Region> list(String key, String value) {
		List<Region> result = new ArrayList<Region>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT r.regid, r.reg_name \r\n"
					+ "        ,(SELECT count(*) FROM employees WHERE regId = r.regId) \"count_\"\r\n"
					+ "FROM regions r";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String regId = rs.getString("regId");
				String reg_name = rs.getString("reg_name");
				int count_ = rs.getInt("count_");

				result.add(new Region(regId, reg_name, count_));
			}

			rs.close();

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

	public String newRegionId() {
		String result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT CONCAT('REG', LPAD(NVL(SUBSTR(MAX(regId), 4), 0) + 1, 2, 0)) \r\n"
					+ "	AS newId FROM regions";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				result = rs.getString("newId");
			}

			rs.close();

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

	public int regionInsert(String regId, String reg_name) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "INSERT INTO regions(regId, reg_name)\r\n" +
							"VALUES(?, ?)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, regId);
			stmt.setString(2, reg_name);

			result = stmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			try {
				// 롤백
				conn.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

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

	public int regionDelete(String regId) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "DELETE FROM regions\r\n" + "WHERE regId = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, regId);

			result = stmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			try {
				// 롤백
				conn.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
			}

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
