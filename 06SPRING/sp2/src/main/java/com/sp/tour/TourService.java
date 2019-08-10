package com.sp.tour;

import java.util.List;
import java.util.Map;

public interface TourService {
	public List<Tsido> listTour();
	public List<Tcity> listCity(int num) throws Exception;
	public void insertTsido(String sido);
	public void deleteTsido(int num);
	public void insertTcity(Map<String, Object> map);
	public void deleteTcity(int num);
}
