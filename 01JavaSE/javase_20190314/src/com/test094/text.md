###Interface Comparator<T>

정의
<br>
정렬 가능한 클래스(Comparable 인터페이스를 구현한 클래스)들의 기본 정렬 기준과 다르게 정렬 하고 싶을 때 사용하는 인터페이스
<br>
<br>
패키지
<br>
java.util.Comparator
<br>
<br>
특징
<br>
1.주로 익명 클래스로 사용된다.
<br>
2.기본적인 정렬 방법인 오름차순 정렬을 내림차순으로 정렬할 때 많이 사용한다.
<br>
<br>
compare() 메서드 작성법
<br>
1.첫 번째 파라미터로 넘어온 객체 < 두 번째 파라미터로 넘어온 객체: 음수 리턴
<br>
2.첫 번째 파라미터로 넘어온 객체 == 두 번째 파라미터로 넘어온 객체: 0 리턴
<br>
3.첫 번째 파라미터로 넘어온 객체 > 두 번째 파라미터로 넘어온 객체: 양수 리턴
<br>
4.음수 또는 0이면 객체의 자리가 그대로 유지되고 양수이면 두 객체의 자리가 변경된다
<br>
5.비교하는 첫번째 객체의 위치에 따라 오름차순 내림차순으로 정렬할 수 있다
<br>
<br>
정렬하기
<br>
<br>
메인

```java
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
```
오름차순 정렬

```java
import java.awt.Point;
import java.util.Comparator;

class MyComparator implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		if (p1.x > p2.x) {
			return 1;
		}else if(p1.x < p2.x){ 
			return -1;
		}else {
			return 0;
		}
	}
}
//java.awt.Point[x=20,y=10]
//java.awt.Point[x=30,y=20]
//java.awt.Point[x=40,y=30]
```
내림차순 정렬

```java
import java.awt.Point;
import java.util.Comparator;

class MyComparator implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		if (p2.x > p1.x) {
			return 1;
		}else if(p2.x < p1.x){ 
			return -1;
		}else {
			return 0;
		}
	}
}
//java.awt.Point[x=40,y=30]
//java.awt.Point[x=30,y=20]
//java.awt.Point[x=20,y=10]
```
익명클래스 이용

```java
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Comparator<Point> myComparator = new Comparator<Point>() {
			  @Override
			  public int compare(Point p1, Point p2) { 
				  if (p2.x > p1.x) {
						return 1;
					}else if(p2.x < p1.x){ 
						return -1;
					}else {
						return 0;
					}
			  }
			};

			List<Point> pointList = new ArrayList<>();
			pointList.add(new Point(20, 10));
			pointList.add(new Point(40, 10));
			pointList.add(new Point(30, 10));
			Collections.sort(pointList, myComparator);
			
			for(Point m :pointList) {
				System.out.println(m.toString());
			}

	}

}
//java.awt.Point[x=40,y=10]
//java.awt.Point[x=30,y=10]
//java.awt.Point[x=20,y=10]
```