package com.emp002.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.emp002.domain.Employee;
import com.emp002.dao.RegionDAO;
import com.emp002.domain.Region;

public class EmployeeDAO {
	private List<Employee> employees = new ArrayList<Employee>();
	
	public EmployeeDAO() {
		Employee e1 = new Employee("EMP001","홍길동","1-1","1-1-1","1-1-1","REG01","DEPT03","POS03",3000000,500000);
		e1.setRegName("부산"); e1.setDeptName("개발"); e1.setPosName("부장");
		employees.add(e1);
		Employee e2 = new Employee("EMP002","박길동","1-1","1-1-1","1-1-1","REG02","DEPT02","POS01",2000000,500000);
		e2.setRegName("서울"); e2.setDeptName("디자인"); e2.setPosName("차장");
		employees.add(e2);
		Employee e3 = new Employee("EMP003","김길동","1-1","1-1-1","1-1-1","REG03","DEPT01","POS02",1000000,500000);
		e3.setRegName("김포"); e3.setDeptName("니로"); e3.setPosName("과장");
		employees.add(e3);
		Employee e4 = new Employee("EMP004","김길동","1-1","1-1-1","1-1-1","REG03","DEPT01","POS02",1000000,500000);
		e4.setRegName("김포"); e4.setDeptName("니로"); e4.setPosName("과장");
		employees.add(e4);
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
	
	//지역명 검색 메소드
	//지역번호 제공 -> 지역명 반환
	/*
	 * public String regName(String regId) { String regName = null; for(Region r :
	 * this.regions) { if(r.getRegNum().equals(regId)) { regName = r.getRegName(); }
	 * } return regName; }
	 */
	
}
