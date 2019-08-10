package com.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//ServletContextListener : ������ ���۵ǰų� ����� �� �߻��ϴ� �̺�Ʈ�� ó���ϴ� ������
@WebListener
public class WebAppInit implements ServletContextListener{
	private String pathname = "/WEB-INF/count.txt";
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// ������ ����� ��
		saveFile();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// ������ ���۵� ��
		pathname = sce.getServletContext().getRealPath(pathname);
		
		loadFile();
	}
	
	private void loadFile() {
		long toDay=0, yesterDay=0, total=0;
		
		File f = new File(pathname);
		if(! f.exists())
			return;
		
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			String str = br.readLine();
			if(str!=null) {
				String[] ss = str.split(":");
				if(ss.length==3) {
					toDay = Long.parseLong(ss[0].trim());
					yesterDay= Long.parseLong(ss[1].trim());
					total = Long.parseLong(ss[2].trim());
					
					CountManager.init(toDay, yesterDay, total);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveFile() {
		long toDay=0, yesterDay=0, total=0;
		
		toDay = CountManager.getToDayCount();
		yesterDay = CountManager.getYesterDayCount();
		total = CountManager.getTotalCount();
		
		try(PrintWriter out = new PrintWriter(new FileWriter(pathname))){
			String s = toDay+":"+yesterDay+":"+total;
			out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
