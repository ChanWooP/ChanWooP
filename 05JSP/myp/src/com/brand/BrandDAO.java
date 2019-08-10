package com.brand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class BrandDAO {
	private Connection conn = DBConn.getConnection();
	
	public int insertBrand(BrandDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO carbrand(brandId,brandName,brandImage) VALUES(BRAND_SEQ.NEXTVAL, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBrandName());
			pstmt.setString(2, dto.getBrandImage());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public int updateBrand(BrandDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "UPDATE carbrand SET brandName=?, brandImage=? WHERE brandId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBrandName());
			pstmt.setString(2, dto.getBrandImage());
			pstmt.setString(3, dto.getBrandId());
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	public int deleteBrand(String brandId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM carbrand WHERE brandId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brandId);
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
	
	public List<BrandDTO> list(){
		List<BrandDTO> list = new ArrayList<BrandDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT brandId, brandName, brandImage FROM CARBRAND ORDER BY brandId";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BrandDTO dto = new BrandDTO();
				dto.setBrandId(rs.getString("brandId"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setBrandImage(rs.getString("brandImage"));
				
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
	
	public BrandDTO readBrand(String brandId) {
		BrandDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT brandId, brandName, brandImage FROM CARBRAND WHERE brandId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, brandId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new BrandDTO();
				dto.setBrandId(rs.getString("brandId"));
				dto.setBrandName(rs.getString("brandName"));
				dto.setBrandImage(rs.getString("brandImage"));
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
		
		return dto;
	}
	
}
