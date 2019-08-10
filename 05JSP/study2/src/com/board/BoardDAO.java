package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.BoardDTO;
import com.util.DBConn;

public class BoardDAO {
	private Connection conn = DBConn.getConnection();
	
	public void insertBoard(BoardDTO dto, String mode) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			int seq = 0;
			sql = "SELECT board_seq.NEXTVAL FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				seq = rs.getInt(1);
			rs.close();
			pstmt.close();
			rs = null;
			pstmt = null;
			
			if(mode.equals("created")) {
				// 새글인 경우
				dto.setBoardNum(seq);
				dto.setGroupNum(seq);
				dto.setDepth(0);
				dto.setOrderNo(0);
				dto.setParent(0);
			}else {
				// 답변인 경우
				dto.setBoardNum(seq);
				updateOrderNo(dto.getGroupNum(), dto.getOrderNo());
				dto.setDepth(dto.getDepth()+1);
				dto.setOrderNo(dto.getOrderNo()+1);
			}
			
			sql = "INSERT INTO board(boardNum, userId, subject, content, groupNum, depth, orderNo, parent)"
					+"VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			//setter
			pstmt.setInt(1, dto.getBoardNum());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getGroupNum());
			pstmt.setInt(6, dto.getDepth());
			pstmt.setInt(7, dto.getOrderNo());
			pstmt.setInt(8, dto.getParent());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					
				}catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int dataCount(String condition, String keyword) {
		int result=0;
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;

		try {
			sql = "SELECT COUNT(*) FROM board b  "
					+"   JOIN member1 m ON b.userId = m.userId "
			        +"   WHERE  ";
			if(condition.equals("created")) {
				keyword=keyword.replaceAll("-", "");
				sql += " TO_CHAR(created, 'YYYYMMDD') = ?";
			} else if(condition.equals("userName")) {
				sql += " INSTR(userName, ?) = 1";
			} else {
				sql += " INSTR("+condition+", ?) >= 1";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs=pstmt.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
			
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

		return result;
	}
	
	//답변일 경우 orderNo 변경
	public void updateOrderNo(int groupNum, int orderNo) {
		String sql = "UPDATE board SET orderNo=orderNo+1 WHERE groupNum=? AND orderNo > ?";
		
		//JDK 7부터 사용가능. 
		//try~catch~resources를 사용
		//자동으로 pstmt는 close 됨
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, groupNum);
			pstmt.setInt(2, orderNo);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int dataCount() {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql="SELECT NVL(COUNT(*), 0) FROM board";
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next())
				result=rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
				
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return result;
	}
	
	public List<BoardDTO> listBoard(int start, int end){
		List<BoardDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			//boardNum, userId, userName, subject, created, hitCount, groupNum, orderNo, depth
			//board과 member1 테이블을 EQUI JOIN
			//정렬 : groupNum DESC, orderNo ASC
			
			sb.append("SELECT * FROM( ");
			sb.append("	SELECT ROWNUM rnum, tb.* FROM( ");
			sb.append("		SELECT b.boardNum, m.userId, m.userName, b.subject, TO_CHAR(b.created, 'YYYY-MM-DD') created, b.hitCount, b.groupNum, b.orderNo, b.depth ");
			sb.append("		FROM board b JOIN member1 m ON b.userid = m.userid ");
			sb.append("		ORDER BY groupNum DESC, orderNo ASC ");
			sb.append("	) tb ");
			sb.append("	WHERE ROWNUM <= ? ");
			sb.append(") WHERE rnum >= ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();
				
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserName(rs.getString("userName"));
				dto.setSubject(rs.getString("subject"));
				dto.setCreated(rs.getString("created"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setOrderNo(rs.getInt("orderNo"));
				dto.setDepth(rs.getInt("depth"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

		return list;
	}
	
	public List<BoardDTO> listBoard(int start, int end, String condition, String keyword) {
		List<BoardDTO> list=new ArrayList<>();

		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT * FROM (");
			sb.append("    SELECT ROWNUM rnum, tb.* FROM (");
			sb.append("        SELECT boardNum, b.userId, userName, subject, ");
			sb.append("                    hitCount, created, groupNum, orderNo, depth ");
			sb.append("        FROM board b");
			sb.append("        JOIN member1 m ON b.userId = m.userId ");
			sb.append("        WHERE ");
			if(condition.equals("created")) {
				keyword=keyword.replaceAll("-", "");
				sb.append("      TO_CHAR(created, 'YYYYMMDD') = ?");
			} else if(condition.equals("userName")) {
				sb.append("      INSTR(userName, ?) = 1");
			} else {
				sb.append("      INSTR("+condition+", ?) >= 1");
			}
			sb.append("        ORDER BY groupNum DESC, orderNo ASC ");
			sb.append("    ) tb WHERE ROWNUM <= ?");
			sb.append(") WHERE rnum >= ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, keyword);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserName(rs.getString("userName"));
				dto.setSubject(rs.getString("subject"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getDate("created").toString());
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setDepth(rs.getInt("depth"));
				dto.setOrderNo(rs.getInt("orderNo"));
				
				list.add(dto);
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
		
		return list;
	}
	
	public BoardDTO readBoard(int num) {
		BoardDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT b.boardNum, m1.userId, m1.userName, b.subject, b.content\r\n" + 
					"        , TO_CHAR(b.created,'YYYY-MM-DD') created, b.hitCount, b.groupNum, b.orderNo, b.depth, b.parent\r\n" + 
					"    FROM board b \r\n" + 
					"    JOIN member1 m1\r\n" + 
					"    ON b.userid = m1.userid\r\n" + 
					"    WHERE b.boardNum = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserName(rs.getString("userName"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setOrderNo(rs.getInt("orderNo"));
				dto.setDepth(rs.getInt("depth"));
				dto.setParent(rs.getInt("parent"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return dto;
	}
	
	// 조회수 증가하기
	public int updateHitCount(int num)  {
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql="UPDATE board SET hitCount=hitCount+1  WHERE boardNum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return result;
	}
	
	 // 이전글
    public BoardDTO preReadBoard(int groupNum, int orderNo, String condition, String keyword) {
        BoardDTO dto=null;

        PreparedStatement pstmt=null;
        ResultSet rs=null;
        StringBuffer sb = new StringBuffer();

        try {
            if(keyword!=null && keyword.length() != 0) {
                sb.append("SELECT ROWNUM, tb.* FROM ( ");
                sb.append("  SELECT boardNum, subject FROM board b JOIN member1 m ON b.userId=m.userId ");
                if(condition.equals("userName")) {
                	sb.append("     WHERE (INSTR(userName, ?) = 1)  ");
                } else if(condition.equals("created")) {
                	keyword=keyword.replaceAll("-", "");
                	sb.append("     WHERE (TO_CHAR(created, 'YYYYMMDD') = ?) ");
                } else {
                	sb.append("     WHERE (INSTR(" + condition + ", ?) >= 1) ");
                }
                sb.append("                   AND ((groupNum = ? AND orderNo < ? ) ");
                sb.append("                   OR (groupNum > ? )) ");
                sb.append("         ORDER BY groupNum ASC, orderNo DESC ");
                sb.append(") tb WHERE ROWNUM=1 ");

                pstmt=conn.prepareStatement(sb.toString());
                pstmt.setString(1, keyword);
                pstmt.setInt(2, groupNum);
                pstmt.setInt(3, orderNo);
                pstmt.setInt(4, groupNum);
			} else {
                sb.append("SELECT ROWNUM, tb.* FROM ( ");
                sb.append("  SELECT boardNum, subject FROM board b JOIN member1 m ON b.userId=m.userId ");
                sb.append("         WHERE ((groupNum = ? AND orderNo < ? ) ");
                sb.append("                      OR (groupNum > ? )) ");
                sb.append("         ORDER BY groupNum ASC, orderNo DESC ");
                sb.append(") tb WHERE ROWNUM=1 ");

                pstmt=conn.prepareStatement(sb.toString());
                pstmt.setInt(1, groupNum);
                pstmt.setInt(2, orderNo);
                pstmt.setInt(3, groupNum);
			}

            rs=pstmt.executeQuery();

            if(rs.next()) {
                dto=new BoardDTO();
                dto.setBoardNum(rs.getInt("boardNum"));
                dto.setSubject(rs.getString("subject"));
            }
        } catch (Exception e) {
            e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
				
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
    
        return dto;
    }
    
    // 다음글
    public BoardDTO nextReadBoard(int groupNum, int orderNo, String condition, String keyword) {
        BoardDTO dto=null;

        PreparedStatement pstmt=null;
        ResultSet rs=null;
        StringBuffer sb = new StringBuffer();

        try {
            if(keyword!=null && keyword.length() != 0) {
                sb.append("SELECT ROWNUM, tb.* FROM ( ");
                sb.append("  SELECT boardNum, subject FROM board b JOIN member1 m ON b.userId=m.userId ");
                if(condition.equals("userName")) {
                	sb.append("     WHERE (INSTR(userName, ?) = 1)  ");
                } else if(condition.equals("created")) {
                	keyword=keyword.replaceAll("-", "");
                	sb.append("     WHERE (TO_CHAR(created, 'YYYYMMDD') = ?) ");
                } else {
                	sb.append("     WHERE (INSTR(" + condition + ", ?) >= 1) ");
                }
                sb.append("                   AND ((groupNum = ? AND orderNo > ? ) ");
                sb.append("                   OR (groupNum < ? )) ");
                sb.append("                   ORDER BY groupNum DESC, orderNo ASC ");
                sb.append(" ) tb WHERE ROWNUM=1 ");

                pstmt=conn.prepareStatement(sb.toString());
                pstmt.setString(1, keyword);
                pstmt.setInt(2, groupNum);
                pstmt.setInt(3, orderNo);
                pstmt.setInt(4, groupNum);
			} else {
                sb.append("SELECT ROWNUM, tb.* FROM ( ");
                sb.append("    SELECT boardNum, subject FROM board b JOIN member1 m ON b.userId=m.userId ");
                sb.append("                   WHERE ((groupNum = ? AND orderNo > ? ) ");
                sb.append("                               OR (groupNum < ? )) ");
                sb.append("                   ORDER BY groupNum DESC, orderNo ASC ");
                sb.append(") tb WHERE ROWNUM=1 ");

                pstmt=conn.prepareStatement(sb.toString());
                pstmt.setInt(1, groupNum);
                pstmt.setInt(2, orderNo);
                pstmt.setInt(3, groupNum);
            }

            rs=pstmt.executeQuery();

            if(rs.next()) {
                dto=new BoardDTO();
                dto.setBoardNum(rs.getInt("boardNum"));
                dto.setSubject(rs.getString("subject"));
            }
        } catch (Exception e) {
            e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
				
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}

        return dto;
    }
    
    public int deleteBoard(int num) {
    	int result = 0;
    	PreparedStatement pstmt = null;
    	String sql;
    	
    	try {
			sql = "DELETE FROM board WHERE boardNum\r\n" + 
					"    IN(\r\n" + 
					"        SELECT boardNum FROM board \r\n" + 
					"         START WITH boardNum = ?\r\n" + 
					"         CONNECT BY PRIOR boardNum = parent\r\n" + 
					"    )";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
    	
    	
    	return result;
    }
}	
