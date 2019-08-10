package com.listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//HttpSessionListener : HttpSessionEvent를 처리하는 리스너, 세션이 생성되거나 소멸될 때  실행
@WebListener
public class CountManager implements HttpSessionListener{
	private static int currentCount;
	private static long toDayCount, yesterDayCount, totalCount;
	
	public CountManager() {
		// 타이머 (자정이 되면 어제 인원은 오늘 인원으로 설정하고 오늘인원은 0)
		TimerTask cron = new TimerTask() {
			
			@Override
			public void run() {
				yesterDayCount = toDayCount;
				toDayCount = 0;
			}
		};
		
		Timer timer = new Timer();
		Calendar cal = Calendar.getInstance();
		
		// 오늘밥 12시 0분 0초 0ms
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		//하루에 한번씩 cron 객체의 run() 메소드 호출
		timer.schedule(cron, cal.getTime(), 1000*60*60*24);
	}
	
	public static void init(long toDay, long yesterDay, long total) {
		toDayCount = toDay;
		yesterDayCount = yesterDay;
		totalCount = total;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		// 세션이 만들어 질 때 호출
		//HttpSession session = evt.getSession();
		//session.setMaxInactiveInterval(10);
		
		synchronized (this) { // 동기화블럭
			currentCount++;
			toDayCount++;
			totalCount++;
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		// 세션이 소멸될 때 호출
		
		synchronized (this) {
			currentCount--;
			if(currentCount<0) currentCount=0;
		}
	}

	public static int getCurrentCount() {
		return currentCount;
	}

	public static long getToDayCount() {
		return toDayCount;
	}

	public static long getYesterDayCount() {
		return yesterDayCount;
	}

	public static long getTotalCount() {
		return totalCount;
	}

}
