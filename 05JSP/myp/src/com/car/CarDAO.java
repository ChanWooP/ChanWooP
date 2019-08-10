package com.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class CarDAO {
	Connection conn = DBConn.getConnection();
	
	public int insertCar(CarDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "INSERT INTO car(brandId,carId,carName,carContent,carImage) VALUES(?, CAR_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBrandId());
			pstmt.setString(2, dto.getCarName());
			pstmt.setString(3, dto.getCarContent());
			pstmt.setString(4, dto.getCarImage());
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
	
	public int updateCar(CarDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "UPDATE car SET carName=?, carContent=?, carImage=? WHERE carId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCarName());
			pstmt.setString(2, dto.getCarContent());
			pstmt.setString(3, dto.getCarImage());
			pstmt.setString(4, dto.getCarId());
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

	public List<CarDTO> list(String BrandId){
		List<CarDTO> list = new ArrayList<CarDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT brandId,carId,carName,carContent,carImage FROM car WHERE brandId = ? ORDER BY carId";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, BrandId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarDTO dto = new CarDTO();
				dto.setBrandId(rs.getString("brandId"));
				dto.setCarContent(rs.getString("carContent"));
				dto.setCarId(rs.getString("carId"));
				dto.setCarImage(rs.getString("carImage"));
				dto.setCarName(rs.getString("carName"));
				
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
	
	public CarDTO readBrand(String CarId) {
		CarDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "SELECT brandId,carId,carName,carContent,carImage FROM car WHERE carId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CarId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new CarDTO();
				dto.setBrandId(rs.getString("brandId"));
				dto.setCarContent(rs.getString("carContent"));
				dto.setCarId(rs.getString("carId"));
				dto.setCarImage(rs.getString("carImage"));
				dto.setCarName(rs.getString("carName"));
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
	
	public int deleteCar(String carId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "DELETE FROM car WHERE carId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carId);
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
} 
