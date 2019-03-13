package com.emp001.dao;

import java.util.Arrays;
import java.util.Comparator;
import com.emp001.domain.Region;

public class RegionDAO {
	private Region[] regions = new Region[3];
	private int idx = 0;
	
	public void add(Region region) {
		this.regions[this.idx++] = region;
	}
	
	public String generateRid() {
		String result = null;
		int temp = this.idx + 1;
		
		if(temp <= 99) {
			result = String.format("REG%02d", temp);
		}
		
		return result;
	}
	
	public Region[] list() {
		sort();
		
		Region[] regions2 = this.regions;
		
		return regions2;
	}
	
	private void sort() {
		Arrays.sort(this.regions, new Comparator<Region>() {
			public int compare(Region r1, Region r2) {
				if(r1 == null || r2 == null) return 0;
				return r1.getRegNum().compareTo(r2.getRegNum());
			}
		});
	}
	
}
