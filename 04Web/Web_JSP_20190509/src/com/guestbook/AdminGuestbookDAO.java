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
    
 
    
    public int changeblind(String ssn, String blind) {
        //UPDATE guestbook SET blind = 1 WHERE ssn = 101;
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        
        try {
            
            conn = MySQLConnection80.connect();
            
            //INSERT만 존재
            String sql = "UPDATE guestbook SET blind = ? WHERE ssn = ?";
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, Integer.parseInt(blind));
            stmt.setInt(2, Integer.parseInt(ssn)); 
            
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
    
    public int picturesave(String pic_name_, String content_) {
        //UPDATE guestbook SET blind = 1 WHERE ssn = 101;
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        
        try {
            
            conn = MySQLConnection80.connect();
            
            //INSERT만 존재
            String sql = "INSERT INTO Picture (pic_name_, content_) VALUES (?, ?);";
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, pic_name_);
            stmt.setString(2, content_); 
            
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
    
    public List<Picture> plist() {
        
        List<Picture> result = new ArrayList<Picture>();
 
        Connection conn = null;
        PreparedStatement stmt = null;
 
        try {
 
            conn = MySQLConnection80.connect();
 
            String sql = "SELECT pic_name_, content_ FROM Picture";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
 
            while (rs.next()) {
    
                String pic_name_ = rs.getString("pic_name_");
                String content_ = rs.getString("content_");
                
                result.add(new Picture(pic_name_, content_));
 
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