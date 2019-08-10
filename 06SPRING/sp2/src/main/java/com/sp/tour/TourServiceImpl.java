package com.sp.tour;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("tour.tourServiceImpl")
public class TourServiceImpl implements TourService{
	
	@Autowired
	private CommonDAO dao;
	
	@Override
	public List<Tsido> listTour() {
		List<Tsido> list = null;
		try {
			list = dao.selectList("tour.listTsido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Tcity> listCity(int num) throws Exception {
		List<Tcity> list = null;
		try {
			list = dao.selectList("tour.listTcity", num);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public void insertTsido(String sido) {
		try {
			dao.insertData("tour.insertTsido", sido);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTsido(int num) {
		try {
			dao.deleteData("tour.deleteTsido",num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertTcity(Map<String, Object> map) {
		try {
			dao.insertData("tour.insertTcity", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteTcity(int num) {
		try {
			dao.deleteData("tour.deleteTcity", num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
