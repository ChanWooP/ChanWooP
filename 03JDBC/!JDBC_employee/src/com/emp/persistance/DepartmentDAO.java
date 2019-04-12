package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.OracleConnection;
import com.emp.domain.Department;

public class DepartmentDAO {
	public List<Department> list(String key, String value) {
		List<Department> result = new ArrayList<Department>();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT d.deptId, d.dept_name\r\n" + 
					"		,(SELECT count(*) FROM employees WHERE deptId = d.deptId) count_\r\n" + 
					"		FROM departments d";

			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String deptId = rs.getString("deptId");
				String dept_name = rs.getString("dept_name");
				int count_ = rs.getInt("count_");

				result.add(new Department(deptId, dept_name, count_));
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
	
	public String newDepartmentId() {
		String result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "SELECT CONCAT('DEPT', LPAD(NVL(SUBSTR(MAX(deptId), 5), 0) + 1, 2, 0))AS newId \r\n" + 
					"FROM departments";

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
	
	public int departmentInsert(String deptId, String dept_name) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "INSERT INTO departments(deptId, dept_name)\r\n" + 
					"VALUES(?, ?)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, deptId);
			stmt.setString(2, dept_name);

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
	
	public int departmentDelete(String deptId) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			String sql = "DELETE FROM departments\r\n" + 
					"WHERE deptId = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, deptId);

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
