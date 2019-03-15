package com.emp002.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.emp002.domain.Department;

public class DepartmentDAO {
	private List<Department> departments = new ArrayList<Department>();
	
	public void add(Department department) {
		this.departments.add(department);
	}
	
	public String generateDid() {
		String result = null;
		int temp = this.departments.size() + 1;
		
		if(temp <= 99) {
			result = String.format("DEPT%02d", temp);
		}
		
		return result;
	}
	
	public List<Department> list() {
		
		List<Department> temp = new ArrayList<Department>();
		
		for(Department d : this.departments) {
			temp.add(d);
		}
		
		Collections.sort(temp, new Comparator<Department>() {
			public int compare(Department d1, Department d2) {
				return d1.getDeptNum().compareTo(d2.getDeptNum());
			}
		});
		
		return temp;
	}
	
	public String getDeptName(String deptId) {
		String deptName = null;
		for(Department d : this.departments) {
			if(d.getDeptNum().equals(deptId)) {
				deptName = d.getDeptName();
			}
		}
		return deptName;
	}
}
