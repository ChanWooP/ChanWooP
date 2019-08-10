package com.sp.tour;

import java.util.List;

public interface TourService {
	public List<Tour> listSido();
	public List<Tour> listCity(int snum);
	
	public int insertSido(Tour dto) throws Exception;
	public int insertCity(Tour dto) throws Exception;

	public void deleteSido(int snum) throws Exception;
	public void deleteCity(int cnum) throws Exception;
}
