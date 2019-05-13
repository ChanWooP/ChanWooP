package com.exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connection.MySQLConnection80;

public class SubjectinfoDAO {

	public List<Subjectinfo> subjectList() {

		List<Subjectinfo> result = new ArrayList<Subjectinfo>();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT sid_, os.subjectid_, sname_, sdate_, scount_, examcount_, status_\r\n"
					+ "   FROM openSubject os, Subjectinfo si\r\n" + "   WHERE os.subjectid_ = si.subjectid_";

			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				String sid_ = rs.getString("sid_");
				String subjectid_ = rs.getString("os.subjectid_");
				String sname_ = rs.getString("sname_");
				String sdate_ = rs.getString("sdate_");
				int scount_ = rs.getInt("scount_");
				int examcount_ = rs.getInt("examcount_");
				String status_ = rs.getString("status_");

				result.add(new Subjectinfo(sid_, subjectid_, sname_, sdate_, scount_, examcount_, status_));

			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
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

	public int addsubjectList(Subjectinfo si) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			conn.setAutoCommit(false);

			// INSERT만 존재
			if (si.getScount_() == 1) {
				String sql1 = " INSERT INTO openSubject (subjectid_, sname_, sdate_) \r\n" + "   VALUES (?, ?, ?)";
				stmt = conn.prepareStatement(sql1);

				stmt.setString(1, si.getSubjectid_());
				stmt.setString(2, si.getSname_());
				stmt.setString(3, si.getSdate_());

				result = stmt.executeUpdate();
				stmt.close();

			}

			String sql2 = "INSERT INTO Subjectinfo (sid_, subjectid_, scount_, examcount_, status_) \r\n"
					+ "   VALUES (?, ?, ?, 0, \"예정\")";

			stmt = conn.prepareStatement(sql2);

			stmt.setString(1, this.newSid());
			stmt.setString(2, si.getSubjectid_());
			stmt.setInt(3, si.getScount_());

			stmt.executeUpdate();
			stmt.close();

			// 커밋 액션 진행
			conn.commit();

			result = 1;

		} catch (Exception e) {
			try {
				// 롤백 액션 진행
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

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
	public int delsubjectList(String sid, String subid, int scount) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			conn.setAutoCommit(false);

			if (scount == 1) {

				String sql2 = "DELETE FROM Subjectinfo WHERE subjectid_ = ?";

				stmt = conn.prepareStatement(sql2);

				stmt.setString(1, subid);

				stmt.executeUpdate();
				stmt.close();

				String sql1 = " DELETE FROM openSubject WHERE subjectid_ = ?";
				stmt = conn.prepareStatement(sql1);

				stmt.setString(1, subid);

				result = stmt.executeUpdate();
				stmt.close();

			} else {

				String sql3 = "DELETE FROM Subjectinfo WHERE sid_=? AND subjectid_ = ?";

				stmt = conn.prepareStatement(sql3);

				stmt.setString(1, sid);
				stmt.setString(2, subid);

				stmt.executeUpdate();
				stmt.close();

			}

			// 커밋 액션 진행
			conn.commit();

			result = 1;

		} catch (Exception e) {
			try {
				// 롤백 액션 진행
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

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

	public int upSubjectList(String subjectid_, String sname_, String sdate_, int scount_, String sid_) {

		int result = 0;

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			conn.setAutoCommit(false);

			String sql1 = " UPDATE openSubject SET sname_=?, sdate_=?   WHERE subjectid_ = ?";
			stmt = conn.prepareStatement(sql1);

			stmt.setString(1, sname_);
			stmt.setString(2, sdate_);
			stmt.setString(3, subjectid_);

			result = stmt.executeUpdate();
			stmt.close();

			
			String sql2 = "UPDATE Subjectinfo SET scount_=?   WHERE sid_=?";

			stmt = conn.prepareStatement(sql2);

			stmt.setInt(1, scount_);
			stmt.setString(2, sid_);

			stmt.executeUpdate();
			stmt.close();

			// 커밋 액션 진행
			conn.commit();

			result = 1;

		} catch (Exception e) {
			try {
				// 롤백 액션 진행
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

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

	public String newSid() {

		String result = "";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = MySQLConnection80.connect();

			String sql = "SELECT CONCAT('S', LPAD(IFNULL(SUBSTR(MAX(sid_), 2), 0) + 1, 3, 0)) AS newsid_ FROM Subjectinfo";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				result = rs.getString("newsid_");

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

}
