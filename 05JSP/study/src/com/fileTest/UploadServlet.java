package com.fileTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/file/upload")
@MultipartConfig( //서블릿 3.0부터 지원. 파일업로드 환경설정
      location = "c:/temp"   //업로드파일저장임시경로
      ,fileSizeThreshold = 1024*1024   //메모리스트림으로저장되는 크기
      ,maxFileSize = 1024*1024*5   //업로드 최대크기
      ,maxRequestSize = 1024*1024*5*5   //form 전체용량
      )
public class UploadServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      process(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      process(req, resp);
   }

   protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");
      
      HttpSession session=req.getSession();
      String root=session.getServletContext().getRealPath("/");
      String pathname=root+"uploads"+File.separator+"pds";
      File f=new File(pathname);
      if(! f.exists())
         f.mkdirs();
      
      // enctype="multipart/form-data"는 서블릿 3.0부터는 Part인터페이스로 접근
      String ct=req.getContentType();
      if(ct!=null&&ct.toLowerCase().startsWith("multipart/")) {
         for(Part p : req.getParts()) {
            String paramName=p.getName();
            String type=p.getContentType();
            
            if(type!=null) { //파일인 경우
               long size=p.getSize();
               String filename=getOriginalFilename(p);
               String path=pathname+File.separator+filename;
               p.write(path); //서버에 저장
               
               req.setAttribute("size", size);
               req.setAttribute("saveFilename", filename);
               
            } else { //파일이 아닌경우
               String value=getParameterValue(p.getInputStream());
               req.setAttribute(paramName, value);
            }
         }
      }
      
      RequestDispatcher rd=req.getRequestDispatcher("/test4/result.jsp");
      rd.forward(req, resp);

   }
   
   //클라이언크가 올린 파일명
   private String getOriginalFilename(Part p) {
      for(String s: p.getHeader("content-disposition").split(";")) {
         if(s.trim().startsWith("filename")) {
            return s.substring(s.indexOf("=")+1).trim().replace("\"", "");
         }
      }
      return null;
   }
   
   //파라미터 넘겨 받기
   private String getParameterValue(InputStream is) throws IOException {
      StringBuffer sb=new StringBuffer();
      
      InputStreamReader reader=new InputStreamReader(is, "UTF-8");
      char[] cc=new char[512];
      int len;
      
      try {
         while((len=reader.read(cc))!=-1) {
            sb.append(cc, 0, len);
         }
         
      } catch(Exception e) {
         
      } finally {
         try {
            is.close();
         } catch(Exception e2) {
            
         }
      }
      
      
      return sb.toString();
   }

}