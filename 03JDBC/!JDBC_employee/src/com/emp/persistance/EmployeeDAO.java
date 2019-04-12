package com.emp.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.OracleConnection;
import com.emp.domain.Employee;

public class EmployeeDAO {
	public List<Employee> list(String key, String value) {
		List<Employee> result = new ArrayList<Employee>();
		
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = OracleConnection.connect();

			/*
			CREATE OR REPLACE VIEW empView2
			AS
			SELECT e.empId, name_, ssn, hiredate, phone
			        , r.regId, r.reg_name, d.deptId, d.dept_name, j.jobId, j.job_title
			        , e.basicpay, e.extrapay, (e.basicpay + e.extrapay) pay
			    FROM employees e, regions r, departments d, jobs j
			    WHERE e.regId = r.regId
			    AND e.deptId = d.deptId
			    AND e.jobId = j.jobId;
			 */
			
			String sql = "SELECT empId, name_, ssn, hiredate, phone\r\n" + 
					"        , regId, reg_name, deptId, dept_name, jobId, job_title\r\n" + 
					"        , basicpay, extrapay, (basicpay + extrapay) total\r\n" + 
					"    FROM empView2 \r\n";
			
			if(key.equals("empid")) {
				sql += "WHERE empId = ? ORDER BY empId";
			}else if(key.equals("name_")) {
				sql += "WHERE INSTR(name_, ?) > 0 ORDER BY empId";
			}else if(key.equals("reg_name")) {
				sql += "WHERE INSTR(reg_name, ?) > 0 ORDER BY empId";
			}else if(key.equals("dept_name")) {
				sql += "WHERE INSTR(dept_name, ?) > 0 ORDER BY empId";
			}else if(key.equals("job_title")) {
				sql += "WHERE INSTR(job_title, ?) > 0  ORDER BY empId";
			}else if(key.equals("sort")) {
				sql += String.format(" ORDER BY %s", value);
			}
			
			stmt = conn.prepareStatement(sql);
			
			if(key.equals("sort") == false) {
				stmt.setString(1, value);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String empId = rs.getString("empId");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String hiredate = rs.getString("hiredate");
				String phone = rs.getString("phone");
				String regId = rs.getString("regId");
				String reg_name = rs.getString("reg_name");
				String deptId = rs.getString("deptId");
				String dept_name = rs.getString("dept_name");
				String jobId = rs.getString("jobId");
				String job_title = rs.getString("job_title");
				int basicpay = rs.getInt("basicpay");
				int extrapay = rs.getInt("extrapay");
				int total = rs.getInt("total");

				result.add(new Employee(empId, name_, ssn, hiredate, phone, regId, reg_name, deptId, dept_name, jobId, job_title, basicpay, extrapay,total));
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
}
