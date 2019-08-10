package com.listener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//HttpSessionListener : HttpSessionEvent�� ó���ϴ� ������, ������ �����ǰų� �Ҹ�� ��  ����
@WebListener
public class CountManager implements HttpSessionListener{
	private static int currentCount;
	private static long toDayCount, yesterDayCount, totalCount;
	
	public CountManager() {
		// Ÿ�̸� (������ �Ǹ� ���� �ο��� ���� �ο����� �����ϰ� �����ο��� 0)
		TimerTask cron = new TimerTask() {
			
			@Override
			public void run() {
				yesterDayCount = toDayCount;
				toDayCount = 0;
			}
		};
		
		Timer timer = new Timer();
		Calendar cal = Calendar.getInstance();
		
		// ���ù� 12�� 0�� 0�� 0ms
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		//�Ϸ翡 �ѹ��� cron ��ü�� run() �޼ҵ� ȣ��
		timer.schedule(cron, cal.getTime(), 1000*60*60*24);
	}
	
	public static void init(long toDay, long yesterDay, long total) {
		toDayCount = toDay;
		yesterDayCount = yesterDay;
		totalCount = total;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		// ������ ����� �� �� ȣ��
		//HttpSession session = evt.getSession();
		//session.setMaxInactiveInterval(10);
		
		synchronized (this) { // ����ȭ��
			currentCount++;
			toDayCount++;
			totalCount++;
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		// ������ �Ҹ�� �� ȣ��
		
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
