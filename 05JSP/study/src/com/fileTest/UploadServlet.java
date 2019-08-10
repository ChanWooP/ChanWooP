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
@MultipartConfig( //���� 3.0���� ����. ���Ͼ��ε� ȯ�漳��
      location = "c:/temp"   //���ε����������ӽð��
      ,fileSizeThreshold = 1024*1024   //�޸𸮽�Ʈ����������Ǵ� ũ��
      ,maxFileSize = 1024*1024*5   //���ε� �ִ�ũ��
      ,maxRequestSize = 1024*1024*5*5   //form ��ü�뷮
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
      
      // enctype="multipart/form-data"�� ���� 3.0���ʹ� Part�������̽��� ����
      String ct=req.getContentType();
      if(ct!=null&&ct.toLowerCase().startsWith("multipart/")) {
         for(Part p : req.getParts()) {
            String paramName=p.getName();
            String type=p.getContentType();
            
            if(type!=null) { //������ ���
               long size=p.getSize();
               String filename=getOriginalFilename(p);
               String path=pathname+File.separator+filename;
               p.write(path); //������ ����
               
               req.setAttribute("size", size);
               req.setAttribute("saveFilename", filename);
               
            } else { //������ �ƴѰ��
               String value=getParameterValue(p.getInputStream());
               req.setAttribute(paramName, value);
            }
         }
      }
      
      RequestDispatcher rd=req.getRequestDispatcher("/test4/result.jsp");
      rd.forward(req, resp);

   }
   
   //Ŭ���̾�ũ�� �ø� ���ϸ�
   private String getOriginalFilename(Part p) {
      for(String s: p.getHeader("content-disposition").split(";")) {
         if(s.trim().startsWith("filename")) {
            return s.substring(s.indexOf("=")+1).trim().replace("\"", "");
         }
      }
      return null;
   }
   
   //�Ķ���� �Ѱ� �ޱ�
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