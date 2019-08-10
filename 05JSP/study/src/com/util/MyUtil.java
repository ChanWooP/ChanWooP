package com.util;

public class MyUtil {
	/**
	 * 페이지 수를 계산하는 메소드
	 * @param rows		한 화면에 출력할 데이터 개수
	 * @param dataCount	전체 데이터의 수
	 * @return			총 페이지 수
	 */
	public int pageCount(int rows, int dataCount) {
		if(dataCount <= 0)
			return 0;
		
		return dataCount / rows + (dataCount % rows > 0 ? 1 : 0);
	}
	
	/**
	 * 페이징 처리(GET 방식)
	 * @param current_page	현재 표시할 페이지
	 * @param total_page	전체 페이지 수
	 * @param list_url		링크를 설정할 uri
	 * @return				페이징 처리 결과
	 */
	public String paging(int current_page, int total_page, String list_url) {
		StringBuffer sb = new StringBuffer();
		
		int numPerBlock = 10; // 페이징을 10개단위로 보여주겠다
		int currentPageSetup, n, page;
		
		if(current_page < 1 || total_page < 1)
			return "";
		
		if(list_url.indexOf("?") != -1) {
			list_url += "&";
		}else {
			list_url += "?";
		}
		
		//currentPageSetup : 표시할페이지 - 1
		currentPageSetup = (current_page/numPerBlock) * numPerBlock;
		if(current_page % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		sb.append("<div id='paginate'>");
		
		//처음, 이전(10페이지 앞)
		n = current_page - numPerBlock;
		if(total_page > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='"+ list_url +"page=1'>[처음]</a>&nbsp;");
			sb.append("<a href='"+ list_url +"page="+ n +"'>[이전]</a>&nbsp;");
		}
		
		//페이징 
		page = currentPageSetup + 1;
		while(page <= total_page && page<=(currentPageSetup+numPerBlock)) {
			if(page == current_page) {
				sb.append("<span style='color:Fuchsia;'>"+ page + "</span>&nbsp;");
			}else {
				sb.append("<a href='"+ list_url +"page="+ page +"'>"+ page +"</a>&nbsp;");
			}
			page++;
		}
		
		//다음(10페이지 후), 마지막 페이지
		n = current_page + numPerBlock;
		if(n>total_page) n = total_page;
		if(total_page - currentPageSetup > numPerBlock) {
			sb.append("<a href='"+ list_url +"page="+ n +"'>[다음]</a>&nbsp;");
			sb.append("<a href='"+ list_url +"page="+ total_page +"'>[끝]</a>");
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public String paging2(int current_page, int total_page, String list_url) {
		StringBuffer sb = new StringBuffer();
		
		int numPerBlock = 10; // 페이징을 10개단위로 보여주겠다
		int currentPageSetup, n, page;
		
		// 페이징 할 데이터가 없으면 빈값을 리턴
		if(current_page < 1 || total_page < 1)
			return "";
		
		// 파라미터가 없을 경우와 있을 경우를 위한 것
		if(list_url.indexOf("?") != -1) {
			list_url += "&";
		}else {
			list_url += "?";
		}
		
		//currentPageSetup : 표시할페이지 - 1
		currentPageSetup = (current_page/numPerBlock) * numPerBlock;
		if(current_page % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		System.out.println(currentPageSetup);
		sb.append("<div id='paginate'>");
		
		//처음, 이전(10페이지 앞)
		n = (current_page == 1) ? current_page : current_page-1 ;

			sb.append("<a href='"+ list_url +"page=1'>[처음]</a>&nbsp;");
			sb.append("<a href='"+ list_url +"page="+ n +"'>[이전]</a>&nbsp;");

		
		//페이징 
		page = currentPageSetup + 1;
		System.out.println(page);
		while(page <= total_page && page<=(currentPageSetup+numPerBlock)) {
			if(page == current_page) {
				sb.append("<span style='color:Fuchsia;'>"+ page + "</span>&nbsp;");
			}else {
				sb.append("<a href='"+ list_url +"page="+ page +"'>"+ page +"</a>&nbsp;");
			}
			page++;
		}
		
		//다음(10페이지 후), 마지막 페이지
		n = (current_page == total_page) ? current_page : current_page+1 ;
	
		
			sb.append("<a href='"+ list_url +"page="+ n +"'>[다음]</a>&nbsp;");
			sb.append("<a href='"+ list_url +"page="+ total_page +"'>[끝]</a>");
			

		sb.append("</div>");
		
		return sb.toString();
	}
}


