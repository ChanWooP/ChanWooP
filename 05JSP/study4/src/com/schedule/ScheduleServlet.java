package com.schedule;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.util.MyServlet;

import net.sf.json.JSONObject;

@WebServlet("/schedule/*")
public class ScheduleServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		// AJAX���� �α����� �ȵ� ��� 403�̶�� ���� �ڵ带 ������.
		String header=req.getHeader("AJAX");
		if(header!=null && header.equals("true")  && info==null) {
			resp.sendError(403);
			return;
		}
		
		if(info==null) {
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
		
		if(uri.indexOf("month.do")!=-1) {
			monthSchedule(req, resp);
		}else if(uri.indexOf("day.do")!=-1) {
			daySchedule(req, resp);
		}else if(uri.indexOf("year.do")!=-1) {
			yearSchedule(req, resp);
		}else if(uri.indexOf("insert.do")!=-1) {
			insertSubmit(req, resp);
		}else if(uri.indexOf("update.do")!=-1) {
			updateSubmit(req, resp);
		}else if(uri.indexOf("delete.do")!=-1) {
			deleteSubmit(req, resp);
		}
	}
	
	private void monthSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		ScheduleDAO dao=new ScheduleDAO();
		
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;  // 0 ~ 11
		int todayYear=year;
		int todayMonth=month;
		int todayDate=cal.get(Calendar.DATE);
		
		String y=req.getParameter("year");
		String m=req.getParameter("month");
		
		if(y!=null)
			year=Integer.parseInt(y);
		if(m!=null)
			month=Integer.parseInt(m);
		
		// year�� month�� 1���� ����
		cal.set(year, month-1, 1);
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		int week=cal.get(Calendar.DAY_OF_WEEK); // 1~7
		
		// ù���� year�⵵ month�� 1�� ���� ��¥
		Calendar scal=(Calendar)cal.clone();
		scal.add(Calendar.DATE, -(week-1));
		int syear=scal.get(Calendar.YEAR);
		int smonth=scal.get(Calendar.MONTH)+1;
		int sdate=scal.get(Calendar.DATE);
		
		// ���������� year�⵵ month�� �������� ����� ��¥
		Calendar ecal=(Calendar)cal.clone();
		// year�⵵ month�� ����
		ecal.add(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		// year�⵵ month�� �������� �����
		ecal.add(Calendar.DATE, 7-ecal.get(Calendar.DAY_OF_WEEK));
		int eyear=ecal.get(Calendar.YEAR);
		int emonth=ecal.get(Calendar.MONTH)+1;
		int edate=ecal.get(Calendar.DATE);

		// ������ ��������
		String startDay=String.format("%04d%02d%02d", syear, smonth, sdate);
		String endDay=String.format("%04d%02d%02d", eyear, emonth, edate);
		List<ScheduleDTO> list=dao.listMonth(startDay, endDay, info.getUserId());
		
		String s;
		String [][]days=new String[cal.getActualMaximum(Calendar.WEEK_OF_MONTH)][7];
		
		// 1�� ���� ���� ��¥ �� ���� ���
		// startDay�� ó���ϰ� endDay�� ó������ ����(�ݺ��� ó����������)
		int cnt;
		for(int i=1; i<week; i++) {
			s=String.format("%04d%02d%02d", syear, smonth, sdate);
			days[0][i-1]="<span class='textDate preMonthDate' data-date='"+s+"' >"+sdate+"</span>";
			
			cnt=0;
			for(ScheduleDTO dto:list) {
				// int sd=Integer.parseInt(dto.getSday());
				int sd=Integer.parseInt(dto.getSday().substring(4));
				// int cn=Integer.parseInt(s);
				int cn=Integer.parseInt(s.substring(4));
				
				if(cnt==4) {
					days[0][i-1]+="<span class='scheduleMore' data-date='"+s+"' >"+"more..."+"</span>";
					break;
				}
				
				if(sd==cn) {
					days[0][i-1]+="<span class='scheduleSubject' data-date='"+s+"' data-num='"+dto.getNum()+"' >"+dto.getSubject()+"</span>";
					cnt++;
				} else if(sd>cn){
					break;
				}
			}
			
			sdate++;
		}
		
		// year�⵵ month�� ��¥ �� ���� ���
		int row, n=0;
		
		jump:
		for(row=0; row<days.length; row++) {
			for(int i=week-1; i<7; i++) {
				n++;
				s=String.format("%04d%02d%02d", year, month, n);
				
				if(i==0) {
					days[row][i]="<span class='textDate sundayDate' data-date='"+s+"' >"+n+"</span>";
				} else if(i==6) {
					days[row][i]="<span class='textDate saturdayDate' data-date='"+s+"' >"+n+"</span>";
				} else {
					days[row][i]="<span class='textDate nowDate' data-date='"+s+"' >"+n+"</span>";
				}
				
				cnt=0;
				for(ScheduleDTO dto:list) {
					// int sd=Integer.parseInt(dto.getSday());
					int sd=Integer.parseInt(dto.getSday().substring(4));
					// int cn=Integer.parseInt(s);
					int cn=Integer.parseInt(s.substring(4));

					if(cnt==4) {
						days[row][i]+="<span class='scheduleMore' data-date='"+s+"' >"+"more..."+"</span>";
						break;
					}
					
					if(sd==cn) {
						days[row][i]+="<span class='scheduleSubject' data-date='"+s+"' data-num='"+dto.getNum()+"' >"+dto.getSubject()+"</span>";
						cnt++;
					} else if(sd>cn){
						break;
					}
				}
				
				if(n==cal.getActualMaximum(Calendar.DATE)) {
					week=i+1;
					break jump;
				}
			}
			week=1;
		}
		
		// year�⵵ month�� ������ ��¥ ���� ���� ���
		if(week!=7) {
			n=0;
			for(int i=week; i<7; i++) {
				n++;
				s=String.format("%04d%02d%02d", eyear, emonth, n);
				days[row][i]="<span class='textDate nextMonthDate' data-date='"+s+"' >"+n+"</span>";
				
				cnt=0;
				for(ScheduleDTO dto:list) {
					// int sd=Integer.parseInt(dto.getSday());
					int sd=Integer.parseInt(dto.getSday().substring(4));
					// int cn=Integer.parseInt(s);
					int cn=Integer.parseInt(s.substring(4));
					
					if(cnt==4) {
						days[row][i]+="<span class='scheduleMore' data-date='"+s+"' >"+"more..."+"</span>";
						break;
					}
					
					if(sd==cn) {
						days[row][i]+="<span class='scheduleSubject' data-date='"+s+"' data-num='"+dto.getNum()+"' >"+dto.getSubject()+"</span>";
						cnt++;
					} else if(sd>cn){
						break;
					}
				}
				
			}
		}
		
		String today=String.format("%04d%02d%02d", todayYear, todayMonth, todayDate);
		
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		req.setAttribute("todayYear", todayYear);
		req.setAttribute("todayMonth", todayMonth);
		req.setAttribute("todayDate", todayDate);
		req.setAttribute("today", today);
		req.setAttribute("days", days);
		
		forward(req, resp, "/WEB-INF/views/schedule/month.jsp");
	}

	private void daySchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		ScheduleDAO dao=new ScheduleDAO();
		
		String date=req.getParameter("date");
		String snum=req.getParameter("num");
		
		Calendar cal=Calendar.getInstance();
		
		// ���ó�¥
		String today=String.format("%04d%02d%02d",
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
		if(date==null || ! Pattern.matches("^\\d{8}$", date)) {
			date=today;
		}
		// ���� ������ ����� ��, ��, �� 
		int year=Integer.parseInt(date.substring(0, 4));
		int month=Integer.parseInt(date.substring(4, 6));
		int day=Integer.parseInt(date.substring(6));
		
		cal.set(year, month-1, day);
		year=cal.get(Calendar.YEAR);
		month=cal.get(Calendar.MONTH)+1;
		day=cal.get(Calendar.DATE);
		
		cal.set(year, month-1, 1);
		int week=cal.get(Calendar.DAY_OF_WEEK);
		
		// ���̺��� ���� ��ü���� ����Ʈ �������� ��������
		date=String.format("%04d%02d%02d", year, month, day);
		List<ScheduleDTO> list=dao.listDay(date, info.getUserId());
		
		int num=0;
		ScheduleDTO dto=null;
		if(snum!=null) {
			num=Integer.parseInt(snum);
			dto=dao.readSchedule(num);
		}
		if(dto==null&&list.size()>0) {
			dto=dao.readSchedule(list.get(0).getNum());
		}
		
		// �����ް� ������ 1���� ��¥
		Calendar cal2=(Calendar)cal.clone();
		cal2.add(Calendar.MONTH, -1);
		cal2.set(Calendar.DATE, 1);
		String preMonth=String.format("%04d%02d%02d",
				cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH)+1, cal2.get(Calendar.DATE));
		
		cal2.add(Calendar.MONTH, 2);
		String nextMonth=String.format("%04d%02d%02d",
				cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH)+1, cal2.get(Calendar.DATE));
		
		// ù���� year�⵵ month�� 1�� ���� ��¥
		Calendar scal=(Calendar)cal.clone();
		scal.add(Calendar.DATE, -(week-1));
		int syear=scal.get(Calendar.YEAR);
		int smonth=scal.get(Calendar.MONTH)+1;
		int sdate=scal.get(Calendar.DATE);
		
		// ���������� year�⵵ month�� �������� ����� ��¥
		Calendar ecal=(Calendar)cal.clone();
		// year�⵵ month�� ����
		ecal.add(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		// year�⵵ month�� �������� �����
		ecal.add(Calendar.DATE, 7-ecal.get(Calendar.DAY_OF_WEEK));
		int eyear=ecal.get(Calendar.YEAR);
		int emonth=ecal.get(Calendar.MONTH)+1;
		
		String s;
		String [][]days=new String[cal.getActualMaximum(Calendar.WEEK_OF_MONTH)][7];
		
		// 1�� ���� ���� ��¥
		for(int i=1; i<week; i++) {
			s=String.format("%04d%02d%02d", syear, smonth, sdate);
			days[0][i-1]="<span class='textDate preMonthDate' data-date='"+s+"' >"+sdate+"</span>";
			sdate++;
		}
		
		// year�⵵ month�� ��¥
		int row, n=0;
		jump:
		for(row=0; row<days.length; row++) {
			for(int i=week-1; i<7; i++) {
				n++;
				s=String.format("%04d%02d%02d", year, month, n);
				
				if(i==0) {
					days[row][i]="<span class='textDate sundayDate' data-date='"+s+"' >"+n+"</span>";
				} else if(i==6) {
					days[row][i]="<span class='textDate saturdayDate' data-date='"+s+"' >"+n+"</span>";
				} else {
					days[row][i]="<span class='textDate nowDate' data-date='"+s+"' >"+n+"</span>";
				}
				
				if(n==cal.getActualMaximum(Calendar.DATE)) {
					week=i+1;
					break jump;
				}
			}
			week=1;
		}
		
		// year�⵵ month�� ������ ��¥ ����
		if(week!=7) {
			n=0;
			for(int i=week; i<7; i++) {
				n++;
				s=String.format("%04d%02d%02d", eyear, emonth, n);
				days[row][i]="<span class='textDate nextMonthDate' data-date='"+s+"' >"+n+"</span>";
			}
		}
		
		req.setAttribute("year", year);
		req.setAttribute("month", month);
		req.setAttribute("day", day);
		req.setAttribute("date", date);
		req.setAttribute("today", today);
		req.setAttribute("preMonth", preMonth);
		req.setAttribute("nextMonth", nextMonth);
		
		req.setAttribute("days", days);
		req.setAttribute("dto", dto);
		req.setAttribute("list", list);

		forward(req, resp, "/WEB-INF/views/schedule/day.jsp");
	}

	private void yearSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String syear=req.getParameter("year");
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		
		int todayYear=cal.get(Calendar.YEAR);
		String today=String.format("%04d%02d%02d",
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));

		if(syear!=null)
			year=Integer.parseInt(syear);
		if(year<1900)
			year=cal.get(Calendar.YEAR);

		String days[][][] = new String[12][6][7];
		
		int row, col, month_of_day;
		String s;
		for(int m = 1; m<=12; m++) {
			cal.set(year, m-1, 1);
            row = 0;
            col = cal.get(Calendar.DAY_OF_WEEK)-1;
            month_of_day=cal.getActualMaximum(Calendar.DATE);
            for(int i = 1; i<=month_of_day; i++)  {
            	s=String.format("%04d%02d%02d", year, m, i);
            	
            	if(col==0) {
            		days[m-1][row][col]="<span class='textDate sundayDate' data-date='"+s+"' >"+i+"</span>";
				} else if(col==6) {
					days[m-1][row][col]="<span class='textDate saturdayDate' data-date='"+s+"' >"+i+"</span>";
				} else {
					days[m-1][row][col]="<span class='textDate nowDate' data-date='"+s+"' >"+i+"</span>";
				}            	

                col++;
                if(col > 6) {
                      col = 0;
                      row++;
                }
            }
        }
		
		req.setAttribute("year", year);

		req.setAttribute("todayYear", todayYear);
		req.setAttribute("today", today);
		req.setAttribute("days", days);

		forward(req, resp, "/WEB-INF/views/schedule/year.jsp");
	}
	
	private void insertSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		String state="true";
		
		ScheduleDAO dao=new ScheduleDAO();
		ScheduleDTO dto=new ScheduleDTO();
			
		dto.setUserId(info.getUserId());
		dto.setSubject(req.getParameter("subject"));
		dto.setColor(req.getParameter("color"));
		dto.setSday(req.getParameter("sday").replaceAll("-", ""));
		dto.setEday(req.getParameter("eday").replaceAll("-", ""));
		dto.setStime(req.getParameter("stime").replaceAll(":", ""));
		dto.setEtime(req.getParameter("etime").replaceAll(":", ""));
		if(req.getParameter("allDay")!=null) {
			dto.setStime("");
			dto.setEtime("");
		}
			
		if(dto.getStime().length()==0&&dto.getEtime().length()==0&&dto.getSday().equals(dto.getEday()))
			dto.setEday("");
			
		dto.setRepeat(Integer.parseInt(req.getParameter("repeat")));
		if(req.getParameter("repeat_cycle").length()!=0) {
			dto.setRepeat_cycle(Integer.parseInt(req.getParameter("repeat_cycle")));
				
			dto.setEday("");
			dto.setStime("");
			dto.setEtime("");
		}
		dto.setMemo(req.getParameter("memo"));
			
		int result=dao.insertSchedule(dto);
		if(result<1)
				state="false";
		
		JSONObject job=new JSONObject();
		job.put("state", state);
		 
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.print(job.toString());		
	}
	
	private void updateSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		String state="true";
		ScheduleDAO dao=new ScheduleDAO();
		ScheduleDTO dto=new ScheduleDTO();
			
		dto.setUserId(info.getUserId());
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setSubject(req.getParameter("subject"));
		dto.setColor(req.getParameter("color"));
		dto.setSday(req.getParameter("sday").replaceAll("-", ""));
		dto.setEday(req.getParameter("eday").replaceAll("-", ""));
		dto.setStime(req.getParameter("stime").replaceAll(":", ""));
		dto.setEtime(req.getParameter("etime").replaceAll(":", ""));
		if(req.getParameter("allDay")!=null) {
			dto.setStime("");
			dto.setEtime("");
		}
			
		if(dto.getStime().length()==0&&dto.getEtime().length()==0&&dto.getSday().equals(dto.getEday()))
			dto.setEday("");
			
		dto.setRepeat(Integer.parseInt(req.getParameter("repeat")));
		if(req.getParameter("repeat_cycle").length()!=0) {
			dto.setRepeat_cycle(Integer.parseInt(req.getParameter("repeat_cycle")));
				
			dto.setEday("");
			dto.setStime("");
			dto.setEtime("");
		}
		dto.setMemo(req.getParameter("memo"));
			
		int result=dao.updateSchedule(dto);
		if(result<1)
			state="false";
		
		JSONObject job=new JSONObject();
		job.put("state", state);
		 
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.print(job.toString());		
	}
	
	private void deleteSubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		String cp=req.getContextPath();
		
		ScheduleDAO dao=new ScheduleDAO();
		String date=req.getParameter("date");
		int num=Integer.parseInt(req.getParameter("num"));
		
		dao.deleteSchedule(num, info.getUserId());
		
		resp.sendRedirect(cp+"/schedule/day.do?date="+date);
	}
}
