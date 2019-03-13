package com.emp002.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.emp002.domain.Position;


public class PositionDAO {
	private List<Position> positions = new ArrayList<Position>();
	
	public void add(Position position) {
		this.positions.add(position);
	}
	
	public String generatePid() {
		String result = null;
		int temp = this.positions.size() + 1;
		
		if(temp <= 99) {
			result = String.format("POS%02d", temp);
		}
		
		return result;
	}
	
	public List<Position> list() {
		
		List<Position> temp = new ArrayList<Position>();
		
		for(Position p : this.positions) {
			temp.add(p);
		}
		
		Collections.sort(temp, new Comparator<Position>() {
			public int compare(Position p1, Position p2) {
				return p1.getPosNum().compareTo(p2.getPosNum());
			}
		});
		
		return temp;
	}

}
