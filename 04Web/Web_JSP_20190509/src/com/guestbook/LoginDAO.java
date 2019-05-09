package com.guestbook;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.connection.MySQLConnection80;
 
public class LoginDAO {
 
   public Login adlogin(String id_, String pw_, int grade_) {
 
      Login result = null;
 
      Connection conn = null;
      PreparedStatement stmt = null;
 
      try {
 
         conn = MySQLConnection80.connect();
 
         String sql = "SELECT id_, pw_ grade_, name_, phone, email\r\n" + "   FROM login l, members m\r\n"
               + "   WHERE l.id_ = m.mid_\r\n" + "   AND id_ = ?\r\n" + "   AND pw_ = ?\r\n" + "   AND grade_ = ?";
         stmt = conn.prepareStatement(sql);
 
         stmt.setString(1, id_);
         stmt.setString(2, pw_);
         stmt.setInt(3, grade_);
 
         ResultSet rs = stmt.executeQuery();
 
         while (rs.next()) {
 
            String name_ = rs.getString("name_");
            String phone_ = rs.getString("phone");
            String email_ = rs.getString("email");
 
            result = new Login(id_, pw_, grade_, name_, phone_, email_);
 
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
 
   
}
