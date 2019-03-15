package com.emp002.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.emp002.domain.Employee;

public class EmployeeDAO {
	private List<Employee> employees = new ArrayList<Employee>();
	
	public void add(Employee employee) {
		this.employees.add(employee);
	}
	
	public String generateEid() {
		String result = null;
		int temp = this.employees.size() + 1;
		
		if(temp <= 999) {
			result = String.format("EMP%03d", temp);
		}
		
		return result;
	}
	
	//정렬
	public List<Employee> list(String key){
		//사번, 이름, 지역, 부서, 직위
		//사본 컬렉션 준비
		List<Employee> temp = new ArrayList<Employee>();
		
		//원본 컬렉션 탐색
		for(Employee e : this.employees) {
			temp.add(e);
		}
		
		//사본 컬렉션 정렬
		Collections.sort(temp, new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				int result = 0;
				
				if(key.equals("empId")) {
					result = e1.getEmpId().compareTo(e2.getEmpId());
				}else if(key.equals("empName")) {
					result = e1.getEmpName().compareTo(e2.getEmpName());
				}else if(key.equals("regId")) {
					result = e1.getRegId().compareTo(e2.getRegId());
				}else if(key.equals("deptId")) {
					result = e1.getDeptId().compareTo(e2.getRegId());
				}else if(key.equals("posId")){
					result = e1.getPosId().compareTo(e2.getPosId());
				}
				
				return result;
			}
		});
		//사본 컬렉션 반환
		return temp;
	}
	
	//검색
	public List<Employee> list(String key, String value){
		//사번기준으로 출력하기
		List<Employee> temp = new ArrayList<Employee>();
		
		for(Employee e : this.employees) {
			if(key.equals("empId") && e.getEmpId().equals(value)){
				temp.add(e);
			}else if(key.equals("empName") && e.getEmpName().contains(value)) {
				temp.add(e);
			}else if(key.equals("regId") && e.getRegId().equals(value)) {
				temp.add(e);
			}else if(key.equals("deptId") && e.getDeptId().equals(value)) {
				temp.add(e);
			}else if(key.equals("posId") && e.getPosId().equals(value)) {
				temp.add(e);
			}
		}
		
		return temp;
	}
	
	
}
