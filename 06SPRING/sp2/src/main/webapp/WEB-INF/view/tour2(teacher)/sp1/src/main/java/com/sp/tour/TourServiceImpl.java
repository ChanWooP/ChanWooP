package com.sp.tour;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("tour.tourService")
public class TourServiceImpl implements TourService{
	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<Tour> listSido() {
		List<Tour> list = null;
		
		try {
			list=dao.selectList("tour.listSido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Tour> listCity(int snum) {
		List<Tour> list = null;
		
		try {
			list=dao.selectList("tour.listCity", snum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int insertSido(Tour dto) throws Exception {
		int seq=0;
		try {
			seq=dao.selectOne("tour.seqSido");
			dto.setSnum(seq);
			dao.insertData("tour.insertSido", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return seq;
	}

	@Override
	public int insertCity(Tour dto) throws Exception{
		int seq=0;
		try {
			seq=dao.selectOne("tour.seqCity");
			dto.setCnum(seq);
			dao.insertData("tour.insertCity", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return seq;
	}

	@Override
	public void deleteSido(int snum) throws Exception {
		try {
			dao.deleteData("tour.deleteSido", snum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteCity(int cnum) throws Exception {
		try {
			dao.deleteData("tour.deleteCity", cnum);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
