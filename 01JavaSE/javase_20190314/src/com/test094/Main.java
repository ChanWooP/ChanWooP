package com.test094;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Point> pointList = new ArrayList<>();
		pointList.add(new Point(40, 30));
		pointList.add(new Point(30, 20));
		pointList.add(new Point(20, 10));
		MyComparator myComparator = new MyComparator();
		Collections.sort(pointList, myComparator);
		
		for(Point p : pointList) {
			System.out.println(p.toString());
		}
	}

}
