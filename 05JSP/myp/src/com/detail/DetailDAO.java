package com.detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBConn;

public class DetailDAO {
	
	private Connection conn = DBConn.getConnection();
	
	public int insertDetail(DetailDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO cardetail(carId, bodyId, detailId, detailName, detailPrice)"
					+"VALUES(?, ?, DETAIL_SEQ.NEXTVAL, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCarId());
			pstmt.setString(2, dto.getBodyId());
			pstmt.setString(3, dto.getDetailName());
			pstmt.setInt(4, dto.getDetailPrice());
			result = pstmt.executeUpdate();
			
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
		}
		
		return result;
	}
	
	public int deleteDetail(String detailId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM cardetail where detailId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, detailId);
			result = pstmt.executeUpdate();
			
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
		}
		
		return result;
	}
	
	public int updateDetail(DetailDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "UPDATE cardetail SET bodyId=?, detailname=?, detailPrice=? WHERE detailId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBodyId());
			pstmt.setString(2, dto.getDetailName());
			pstmt.setInt(3, dto.getDetailPrice());
			pstmt.setString(4, dto.getDetailId());
			result = pstmt.executeUpdate();
			
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
		}
		
		return result;
	}
	
	public List<DetailDTO> list(String carId){
		List<DetailDTO> list = new ArrayList<DetailDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT carId, bodyName, detailId, detailName, detailPrice, c.bodyId  \r\n" + 
					"FROM cardetail c JOIN carbody b ON c.bodyid = b.bodyid\r\n" + 
					"WHERE carId = ? ORDER BY c.bodyId, c.detailPrice";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DetailDTO dto = new DetailDTO();

				dto.setCarId(rs.getString("carId"));
				dto.setBodyId(rs.getString("bodyName"));
				dto.setDetailId(rs.getString("detailId"));
				dto.setDetailName(rs.getString("detailName"));
				dto.setDetailPrice(rs.getInt("detailPrice"));
				dto.setBodyId2(rs.getString("bodyId"));
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
		}
		
		return list;
	}
	
	public List<Map<String, String>> bodyList() {
		List<Map<String, String>> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = "SELECT bodyId, bodyName FROM carbody ORDER BY bodyId";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, String> map=new HashMap<>();
				map.put("bodyId", rs.getString("bodyId"));
				map.put("bodyName", rs.getString("bodyName"));
				
				list.add(map);
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
			if(rs !=null) {
				try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	
}
