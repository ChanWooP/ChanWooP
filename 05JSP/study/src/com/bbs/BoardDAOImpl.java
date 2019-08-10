package com.bbs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.util.DBConn;

public class BoardDAOImpl implements BoardDAO{
	private Connection conn = DBConn.getConnection();
	
	@Override
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql="INSERT INTO BBS(num, name, pwd, subject, content, ipAddr) VALUES(bbs_seq.NEXTVAL, ? ,? ,?, ? ,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getIpAddr());
			
			result = pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					
				}
			}
		}
		
		return result;
	}

	@Override
	public int dataCount() {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT NVL(COUNT(*), 0) FROM bbs";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e2) {
					
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					
				}
			}
		}
		
		return result;
	}

	@Override
	public int dataCount(String condition, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		// 이름검색 - 홍길동 : 홍(O), 홍길(O), 길동(X) 
		// 나머지는 - 안녕하세요 : 안(O) ,안녕(O), 하세요(O)
		// name, subject, content, created : 2000-10-10, 20001010
		try {
			sql = "SELECT COUNT(*) FROM bbs WHERE ";
			
			if(condition.equals("created")) {
				keyword = keyword.replaceAll("-", "");
				sql += "TO_CHAR(created, 'YYYY-MM-DD') = ?";
			}else if(condition.equals("name")) {
				sql += "INSTR(name,?) = 1";
			}else {
				sql += "INSTR(" + condition + ",?) >= 1";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = rs.getInt(1);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e2) {
					
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					
				}
			}
		} 
		
		return result;
	}

	@Override
	public List<BoardDTO> listBoard(int start, int end) {
		List<BoardDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("SELECT * FROM (");
			sb.append("	 SELECT ROWNUM rnum, tb.* FROM(");
			sb.append("	 SELECT ROWNUM, num, subject, name, hitCount, created");
			sb.append("		FROM bbs");
			sb.append("	 	ORDER BY num DESC");
			sb.append("	 )tb WHERE ROWNUM <= ?");
			sb.append(") WHERE rnum >= ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1,  end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getDate("created").toString());
				// rs.getDate("created") : java.sql.Date 형
				// rs.getDate("created").toString() ==> yyyy-mm-dd 형식의 문자열로 변환
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e2) {
					
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					
				}
			}
		}
		
		return list;
	}

	@Override
	public List<BoardDTO> listBoard(int start, int end, String condition, String keyword) {
		List<BoardDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append("SELECT * FROM (");
			sb.append("	 SELECT ROWNUM rnum, tb.* FROM(");
			sb.append("	 SELECT ROWNUM, num, subject, name, hitCount, created");
			sb.append("		FROM bbs WHERE ");
			if(condition.equals("created")) {
				keyword = keyword.replaceAll("-", "");
				sb.append(" TO_CHAR(created, 'YYYY-MM-DD') = ?");
			}else if(condition.equals("name")) {
				sb.append(" INSTR(name,?) = 1");
			}else {
				sb.append(" INSTR(" + condition + ",?) >= 1");
			}
			sb.append("	 	ORDER BY num DESC");
			sb.append("	 )tb WHERE ROWNUM <= ?");
			sb.append(") WHERE rnum >= ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, keyword);
			pstmt.setInt(2,  end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getDate("created").toString());
				// rs.getDate("created") : java.sql.Date 형
				// rs.getDate("created").toString() ==> yyyy-mm-dd 형식의 문자열로 변환
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e2) {
					
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					
				}
			}
		}
		
		return list;
	}

	@Override
	public BoardDTO readBoard(int num) {
		BoardDTO dto = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT num, name, pwd, subject, content, ipAddr, hitCount, created";
			sql += " FROM bbs WHERE num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setPwd(rs.getString("pwd"));
				dto.setContent(rs.getString("content"));
				dto.setIpAddr(rs.getString("ipAddr"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getString("created"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(Exception e2) {
					
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					
				}
			}
		}
		
		return dto;
	}

	@Override
	public BoardDTO preReadBoard(int num, String condition, String keyword) {
		BoardDTO dto=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(keyword!=null && keyword.length()!=0) {
				sb.append("SELECT * FROM ( ");
				sb.append("    SELECT num, subject FROM bbs ");
				sb.append("    WHERE num > ? ");
				
				if(condition.equals("created")) {
					keyword=keyword.replaceAll("-", "");
					sb.append("      AND TO_CHAR(created, 'YYYYMMDD') = ?");
				} else if(condition.equals("name")) {
					sb.append("      AND  INSTR(name, ?) = 1");
				} else {
					sb.append("      AND  INSTR("+condition+", ?) >= 1");
				}
				
				sb.append("    ORDER BY num ASC ");
				sb.append(") WHERE ROWNUM =1 ");
				
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
				pstmt.setString(2, keyword);
				rs = pstmt.executeQuery();
				
			} else {
				sb.append("SELECT * FROM ( ");
				sb.append("    SELECT num, subject FROM bbs ");
				sb.append("    WHERE num > ? ");
				sb.append("    ORDER BY num ASC ");
				sb.append(") WHERE ROWNUM =1 ");
				
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
			}
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return dto;
	}

	@Override
	public BoardDTO nextReadBoard(int num, String condition, String keyword) {
		BoardDTO dto=null;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(keyword!=null && keyword.length()!=0) {
				sb.append("SELECT * FROM ( ");
				sb.append("    SELECT num, subject FROM bbs ");
				sb.append("    WHERE num < ? ");
				
				if(condition.equals("created")) {
					keyword=keyword.replaceAll("-", "");
					sb.append("      AND TO_CHAR(created, 'YYYYMMDD') = ?");
				} else if(condition.equals("name")) {
					sb.append("      AND  INSTR(name, ?) = 1");
				} else {
					sb.append("      AND  INSTR("+condition+", ?) >= 1");
				}
				
				sb.append("    ORDER BY num DESC ");
				sb.append(") WHERE ROWNUM =1 ");
				
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
				pstmt.setString(2, keyword);
				rs = pstmt.executeQuery();
				
			} else {
				sb.append("SELECT * FROM ( ");
				sb.append("    SELECT num, subject FROM bbs ");
				sb.append("    WHERE num < ? ");
				sb.append("    ORDER BY num DESC ");
				sb.append(") WHERE ROWNUM =1 ");
				
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
			}
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setSubject(rs.getString("subject"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return dto;
	}

	@Override
	public int updateHitCount(int num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {//조회수 증가
			sql = "UPDATE bbs SET hitCount = hitCount + 1 WHERE num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		
		return result;
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "UPDATE bbs SET name=?, pwd=?, subject=?, content=? WHERE num =?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getNum());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result;
	}

	@Override
	public int deleteBoard(int num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM bbs WHERE num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
}
