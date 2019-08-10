package com.sp.table;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("table.TableService")
public class TableServiceImpl implements TableService{

	@Autowired
	private CommonDAO dao;
	
	@Override
	public void createBoardTable(Map<String, Object> map) throws Exception {
		try {
			dao.updateData("table.createBoardTable", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void dropBoardTable(Map<String, Object> map) throws Exception {
		try {
			dao.updateData("table.dropBoardTable", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
