package com.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class BoardDAO {
	private Connection conn = DBConn.getConnection();
	
	public int insertBoard(BoardDTO dto) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql="INSERT INTO boardgallery(boardId, userId, subject, content_, galleryimage) VALUES(BBS_SEQ.NEXTVAL, ?, ?, ?, ?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent_());
			pstmt.setString(4, dto.getGalleryimage());
			result=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
		}
		
		return result;
	}
	
	public List<BoardDTO> listBoard(int start, int end) {
		List<BoardDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql = "SELECT * FROM(\r\n" + 
					"SELECT ROWNUM rnum, tb.* FROM(\r\n" + 
					"SELECT b.boardId, b.userId, u.username, b.subject \r\n" + 
					"        ,TO_CHAR(created, 'YYYY-MM-DD') created\r\n" + 
					"        ,hitCount\r\n" + 
					"FROM boardgallery b JOIN user_ u ON b.userId = u.userid\r\n" + 
					"ORDER BY b.boardid DESC) tb WHERE ROWNUM<=?\r\n" + 
					")where rnum >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();
				
				dto.setBoardId(rs.getString("boardId"));
				dto.setUserId(rs.getString("userId"));
				dto.setSubject(rs.getString("subject"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getString("created"));
				dto.setUserName(rs.getString("username"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public int dataCount() {
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql="SELECT NVL(COUNT(*), 0) FROM boardgallery";
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
	
	public int updateHitCount(String num)  {
		int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql=" UPDATE boardgallery SET hitCount=hitCount+1 WHERE boardId=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
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
	
	public BoardDTO readBoard(String boardId) {
		BoardDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql="SELECT boardId, b.userId, userName, subject, content_\r\n" + 
					"		,created, hitCount, GALLERYIMAGE\r\n" + 
					"		FROM boardgallery b JOIN user_ u ON b.userId=u.userId\r\n" + 
					"	WHERE boardId  = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardId);

			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new BoardDTO();
				dto.setBoardId(rs.getString("boardId"));
				dto.setUserId(rs.getString("userId"));
				dto.setUserName(rs.getString("userName"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent_(rs.getString("content_"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getString("created"));
				dto.setGalleryimage(rs.getString("GALLERYIMAGE"));
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
	
	public int deleteBoard(String boardId, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			if(userId.equals("admin")) {
				sql="DELETE FROM boardgallery WHERE boardId=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardId);
				result = pstmt.executeUpdate();
			}else {
				sql="DELETE FROM boardgallery WHERE boardId=? AND userId=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, boardId);
				pstmt.setString(2, userId);
				result = pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}		
		return result;
	}
	
	public int updateBoard(BoardDTO dto, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		sql="UPDATE boardgallery SET subject=?, content_=?, GALLERYIMAGE=? WHERE boardId=? AND userId=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent_());
			pstmt.setString(3, dto.getGalleryimage());
			pstmt.setString(4, dto.getBoardId());
			pstmt.setString(5, userId);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}		
		return result;
	}
}
