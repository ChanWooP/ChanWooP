package com.guestbook;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import com.connection.MySQLConnection80;
 
public class AdminGuestbookDAO {
 
   public List<Guestbook> glist() {
      List<Guestbook> result = new ArrayList<Guestbook>();
 
      Connection conn = null;
      PreparedStatement stmt = null;
 
      try {
 
         conn = MySQLConnection80.connect();
 
         String sql = "SELECT ssn, name_, sdate, ipaddress, blind, pw, contents FROM Guestbook";
         stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
 
         while (rs.next()) {
 
            int ssn = rs.getInt("ssn");
            String name_ = rs.getString("name_");
            String sdate = rs.getString("sdate");
            String ipaddress = rs.getString("ipaddress");
            int blind = rs.getInt("blind");
            String pw = rs.getString("pw");
            String contents = rs.getString("contents");
 
            result.add(new Guestbook(ssn, name_, sdate, ipaddress, blind, pw, contents));
 
         }
 
         rs.close();
 
      } catch (Exception e) {
 
      } finally {
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            MySQLConnection80.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
 
      return result;
   }
   
 
   
   public int blind(String ssn, int blind) {
      //UPDATE guestbook SET blind = 1 WHERE ssn = 101;
      int result = 0;
      
      Connection conn = null;
      PreparedStatement stmt = null;
      
      
      try {
         
         conn = MySQLConnection80.connect();
         
         //INSERT만 존재
         String sql = "UPDATE guestbook SET blind = ? WHERE ssn = ?";
         stmt = conn.prepareStatement(sql);
         
         stmt.setInt(1, blind);
         stmt.setString(2, ssn); 
         
         result = stmt.executeUpdate();
         stmt.close();
               
      } catch (Exception e) {
 
      } finally {
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            MySQLConnection80.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
      return result;
   }
   
   public int newSsn() {
       int result = 0;

       Connection conn = null;
       PreparedStatement stmt = null;

       try {

           conn = MySQLConnection80.connect();

           String sql = "SELECT (MAX(ssn) + 1) AS newSsn FROM Guestbook";
           stmt = conn.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();

           while (rs.next()) {

               result = rs.getInt("newSsn");
               
           }

           rs.close();

       } catch (Exception e) {

       } finally {
           try {
               if (stmt != null)
                   stmt.close();
           } catch (SQLException se2) {
           }
           try {
               MySQLConnection80.close();
           } catch (SQLException se) {
               se.printStackTrace();
           }
       }

       return result;
   }
   
   public String addguestbook(String name_, String pw, String contents, String ipaddress) {
       
       String result = "fail";
       Connection conn = null;
       PreparedStatement stmt = null;
       
       
       try {
           
           conn = MySQLConnection80.connect();
           
           //INSERT만 존재
           String sql1 = "INSERT INTO Guestbook (ssn, name_, sdate, ipaddress, blind, pw, contents) \r\n" + 
                   "   VALUES (?, ?, SYSDATE(), ?, 0, ?, ?)";
           stmt = conn.prepareStatement(sql1);
           
           stmt.setInt(1, newSsn()); 
           stmt.setString(2, name_);
           stmt.setString(3, ipaddress);
           stmt.setString(4, pw);
           stmt.setString(5, contents);
           
           stmt.executeUpdate();
           stmt.close();
           
           result = "success";
           
       } catch (Exception e) {

       } finally {
           try {
               if (stmt != null)
                   stmt.close();
           } catch (SQLException se2) {
           }
           try {
               MySQLConnection80.close();
           } catch (SQLException se) {
               se.printStackTrace();
           }
       }
       
       return result;

   }
   
  
}
