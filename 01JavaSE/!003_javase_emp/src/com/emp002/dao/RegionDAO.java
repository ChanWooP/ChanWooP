package com.emp002.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.emp002.domain.Region;

public class RegionDAO {
	//List 컬렉션 저장소 준비
	//저장소 크기, 인덱스 자동 관리
	//휘발성 저장소
	//물리적 저장은 가능하다
	private List<Region> regions = new ArrayList<Region>();

	public void add(Region region) {
		this.regions.add(region);
	}
	
	public String generateRid() {
		String result = null;
		int temp = this.regions.size() + 1;
		
		if(temp <= 99) {
			result = String.format("REG%02d", temp);
		}
		
		return result;
	}
	
	public List<Region> list() {
		
		List<Region> temp = new ArrayList<Region>();
		
		for(Region r : this.regions) {
			temp.add(r);
		}
		
		Collections.sort(temp, new Comparator<Region>() {
			public int compare(Region r1, Region r2) {
				return r1.getRegNum().compareTo(r2.getRegNum());
			}
		});
		
		return temp;
	}
	
	//지역명 검색 메소드
	//지역번호 제공 -> 지역명 반환
	public String getRegName(String regId) {
		String regName = null;
		for(Region r : this.regions) {
			if(r.getRegNum().equals(regId)) {
				regName = r.getRegName();
			}
		}
		return regName;
	}
}
