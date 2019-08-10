package com.score;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class ScoreDAO {
	private Connection conn = DBConn.getConnection();

	public void insertScore(ScoreDTO dto) throws Exception {
		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{call insertScore(?,?,?,?,?,?)}";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dto.getHak());
			cstmt.setString(2, dto.getName());
			cstmt.setString(3, dto.getBirth());
			cstmt.setInt(4, dto.getKor());
			cstmt.setInt(5, dto.getEng());
			cstmt.setInt(6, dto.getMat());

			// 프로시저에서의 리턴값은 insert 개수가 아니라 프로시저
			cstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public void updateScore(ScoreDTO dto) throws Exception {
		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{call updateScore(?,?,?,?,?,?)}";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dto.getName());
			cstmt.setString(2, dto.getBirth());
			cstmt.setInt(3, dto.getKor());
			cstmt.setInt(4, dto.getEng());
			cstmt.setInt(5, dto.getMat());
			cstmt.setString(6, dto.getHak());

			cstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public void deleteScore(String hak) throws Exception {
		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{call deleteScore(?)}";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, hak);

			cstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public int dataCount() {
		int result = 0;

		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{CALL dataCountScore(?)}";
			cstmt = conn.prepareCall(sql);

			// OUT 파라미터의 타입 설정. OUT 파라미터는 반드시 타입을 설정해야 함
			cstmt.registerOutParameter(1, OracleTypes.INTEGER);

			cstmt.executeUpdate();
			result = cstmt.getInt(1); // OUT 파라미터 넘겨 받기

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e) {
				}
			}
		}

		return result;
	}

	public List<ScoreDTO> listScore(int start, int end) {
		List<ScoreDTO> list = new ArrayList<>();
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "{CALL listScore(?,?,?)}";
			cstmt = conn.prepareCall(sql);

			// OUT 파라미터
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// IN 파라미터
			cstmt.setInt(2, start);
			cstmt.setInt(3, end);

			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);

			while (rs.next()) {
				ScoreDTO dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAve(rs.getInt("ave"));
				dto.setRank(rs.getInt("rank"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}

	public ScoreDTO readScore(String hak) {
		ScoreDTO dto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "{CALL readScore(?,?)}";
			cstmt = conn.prepareCall(sql);

			// OUT 파라미터
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			// IN 파라미터
			cstmt.setString(2, hak);

			cstmt.executeUpdate();
			rs = (ResultSet) cstmt.getObject(1);

			if (rs.next()) {
				dto = new ScoreDTO();

				dto.setHak(rs.getString("hak"));
				dto.setName(rs.getString("name"));
				dto.setBirth(rs.getString("birth"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMat(rs.getInt("mat"));
				dto.setTot(rs.getInt("tot"));
				dto.setAve(rs.getInt("ave"));
				dto.setRank(rs.getInt("rank"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return dto;
	}

	public List<Integer> averageScore() {
		List<Integer> list = new ArrayList<>();
		CallableStatement cstmt = null;

		String sql;

		try {
			sql = "{CALL averageScore(?,?,?)}";
			cstmt = conn.prepareCall(sql);

			cstmt.registerOutParameter(1, OracleTypes.INTEGER);
			cstmt.registerOutParameter(2, OracleTypes.INTEGER);
			cstmt.registerOutParameter(3, OracleTypes.INTEGER);

			cstmt.executeUpdate();

			list.add(cstmt.getInt(1));
			list.add(cstmt.getInt(2));
			list.add(cstmt.getInt(3));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return list;
	}
}
