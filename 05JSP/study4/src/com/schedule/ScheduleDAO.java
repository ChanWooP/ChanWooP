package com.schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class ScheduleDAO {
	private Connection conn = DBConn.getConnection();

	public List<ScheduleDTO> listMonth(String startDay, String endDay, String userId) {
		List<ScheduleDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT num, subject, sday, eday, stime, etime, ");
			sb.append("               color, repeat, repeat_cycle ");
			sb.append("  FROM schedule");
			sb.append("  WHERE userId = ? AND ");
			sb.append("     ( ");
			sb.append("        ( ");
			sb.append("           TO_DATE(sday, 'YYYYMMDD') >= TO_DATE(?, 'YYYYMMDD') ");
			sb.append("               AND TO_DATE(sday, 'YYYYMMDD') <= TO_DATE(?, 'YYYYMMDD')  ");
			sb.append("         ) OR ("); // �ݺ�����
			sb.append("           repeat=1 AND repeat_cycle!=0 AND ");
			sb.append(
					"              TO_CHAR(ADD_MONTHS(sday, 12 * repeat_cycle*TRUNC(((SUBSTR(?,1,4)-SUBSTR(sday,1,4))/repeat_cycle))), 'YYYYMMDD')>=? ");
			sb.append(
					"              AND TO_CHAR(ADD_MONTHS(sday, 12 * repeat_cycle*TRUNC(((SUBSTR(?,1,4)-SUBSTR(sday,1,4))/repeat_cycle))), 'YYYYMMDD') <=? ");
			sb.append("         )");
			sb.append("    ) ");
			sb.append("  ORDER BY sday ASC, num DESC ");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, startDay);
			pstmt.setString(3, endDay);

			pstmt.setString(4, startDay);
			pstmt.setString(5, startDay);
			pstmt.setString(6, startDay);
			pstmt.setString(7, endDay);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScheduleDTO dto = new ScheduleDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setSday(rs.getString("sday"));
				dto.setEday(rs.getString("eday"));
				dto.setStime(rs.getString("stime"));
				dto.setEtime(rs.getString("etime"));
				dto.setColor(rs.getString("color"));
				dto.setRepeat(rs.getInt("repeat"));
				dto.setRepeat_cycle(rs.getInt("repeat_cycle"));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}

		return list;
	}

	public int insertSchedule(ScheduleDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("INSERT INTO schedule(");
			sb.append(" num, userId, subject, color, sday, eday,");
			sb.append(" stime, etime, repeat, repeat_cycle, memo) ");
			sb.append(" VALUES(schedule_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getColor());
			pstmt.setString(4, dto.getSday());
			pstmt.setString(5, dto.getEday());
			pstmt.setString(6, dto.getStime());
			pstmt.setString(7, dto.getEtime());
			pstmt.setInt(8, dto.getRepeat());
			pstmt.setInt(9, dto.getRepeat_cycle());
			pstmt.setString(10, dto.getMemo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return result;
	}

	public List<ScheduleDTO> listDay(String date, String userId) {
		List<ScheduleDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT num, subject, sday, eday, color,");
			sb.append("               TO_CHAR(created, 'YYYY-MM-DD') created ");
			sb.append("  FROM schedule");
			sb.append("  WHERE userId = ? AND ");
			sb.append("   ( ");
			sb.append("      ( ");
			sb.append("         TO_DATE(sday, 'YYYYMMDD') = TO_DATE(?, 'YYYYMMDD') ");
			sb.append(
					"         OR (eday IS NOT NULL AND TO_DATE(sday, 'YYYYMMDD') >= TO_DATE(?, 'YYYYMMDD') AND TO_DATE(eday, 'YYYYMMDD') <= TO_DATE(?, 'YYYYMMDD')) ");
			sb.append("      ) OR ( "); // �ݺ�����
			sb.append(
					"           repeat=1 AND MOD(MONTHS_BETWEEN(TO_DATE(sday, 'YYYYMMDD'), TO_DATE(?, 'YYYYMMDD'))/12, repeat_cycle) = 0  ");
			sb.append("      ) ");
			sb.append("   ) ");
			sb.append("  ORDER BY num DESC ");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, date);
			pstmt.setString(3, date);
			pstmt.setString(4, date);
			pstmt.setString(5, date);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScheduleDTO dto = new ScheduleDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setSday(rs.getString("sday"));
				dto.setEday(rs.getString("eday"));
				dto.setColor(rs.getString("color"));
				dto.setCreated(rs.getString("created"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}

		return list;
	}

	public ScheduleDTO readSchedule(int num) {
		ScheduleDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("SELECT num, subject, sday, eday, stime, etime, ");
			sb.append("      color, repeat, repeat_cycle, memo, created ");
			sb.append("  FROM schedule");
			sb.append("  WHERE num = ? ");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);

			String period, s;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new ScheduleDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
				dto.setSday(rs.getString("sday"));
				s = dto.getSday().substring(0, 4) + "-" + dto.getSday().substring(4, 6) + "-"
						+ dto.getSday().substring(6);
				dto.setSday(s);
				dto.setEday(rs.getString("eday"));
				if (dto.getEday() != null && dto.getEday().length() == 8) {
					s = dto.getEday().substring(0, 4) + "-" + dto.getEday().substring(4, 6) + "-"
							+ dto.getEday().substring(6);
					dto.setEday(s);
				}
				dto.setStime(rs.getString("stime"));
				if (dto.getStime() != null && dto.getStime().length() == 4) {
					s = dto.getStime().substring(0, 2) + ":" + dto.getStime().substring(2);
					dto.setStime(s);
				}
				dto.setEtime(rs.getString("etime"));
				if (dto.getEtime() != null && dto.getEtime().length() == 4) {
					s = dto.getEtime().substring(0, 2) + ":" + dto.getEtime().substring(2);
					dto.setEtime(s);
				}

				period = dto.getSday();
				if (dto.getStime() != null && dto.getStime().length() != 0) {
					period += " " + dto.getStime();
				}
				if (dto.getEday() != null && dto.getEday().length() != 0) {
					period += " ~ " + dto.getEday();
				}
				if (dto.getEtime() != null && dto.getEtime().length() != 0) {
					period += " " + dto.getEtime();
				}
				dto.setPeriod(period);

				dto.setColor(rs.getString("color"));
				dto.setRepeat(rs.getInt("repeat"));
				dto.setRepeat_cycle(rs.getInt("repeat_cycle"));
				dto.setMemo(rs.getString("memo"));
				dto.setCreated(rs.getString("created"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}

		return dto;
	}

	public int updateSchedule(ScheduleDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append("UPDATE schedule SET ");
			sb.append("  subject=?, color=?, sday=?, eday=?, stime=?, ");
			sb.append("  etime=?, repeat=?, repeat_cycle=?, memo=? ");
			sb.append("  WHERE num=? AND userId=?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getColor());
			pstmt.setString(3, dto.getSday());
			pstmt.setString(4, dto.getEday());
			pstmt.setString(5, dto.getStime());
			pstmt.setString(6, dto.getEtime());
			pstmt.setInt(7, dto.getRepeat());
			pstmt.setInt(8, dto.getRepeat_cycle());
			pstmt.setString(9, dto.getMemo());
			pstmt.setInt(10, dto.getNum());
			pstmt.setString(11, dto.getUserId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return result;
	}

	public int deleteSchedule(int num, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM schedule WHERE num=? AND userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, userId);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return result;
	}
}
