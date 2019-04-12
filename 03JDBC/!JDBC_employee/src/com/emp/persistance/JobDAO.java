package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.OracleConnection;
import com.emp.domain.Job;

public class JobDAO {
	public List<Job> list(String key, String value) {
		List<Job> result = new ArrayList<Job>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT j.jobId, j.job_title,j.min_basicpay\r\n" + 
					"		,(SELECT count(*) FROM employees WHERE jobId = j.jobId) count_\r\n" + 
					"		FROM jobs j ";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String jobId = rs.getString("jobId");
				String job_title = rs.getString("job_title");
				int min_basicpay = rs.getInt("min_basicpay");
				int count_ = rs.getInt("count_");

				result.add(new Job(jobId, job_title, min_basicpay, count_));
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
	
	public String newJobId() {
		String result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT CONCAT('JOB', LPAD(NVL(SUBSTR(MAX(jobId), 4), 0) + 1, 2, 0))AS newId \r\n" + 
					"FROM jobs";

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
	
	public int jobInsert(String jobId, String job_title, int min_basicpay) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "INSERT INTO jobs(jobId, job_title, min_basicpay)\r\n" + 
					"VALUES(? , ?, ?)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, jobId);
			stmt.setString(2, job_title);
			stmt.setInt(3, min_basicpay);
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
	
	public int jobDelete(String jobId) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "DELETE FROM jobs\r\n" + 
					"WHERE jobId = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, jobId);

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
