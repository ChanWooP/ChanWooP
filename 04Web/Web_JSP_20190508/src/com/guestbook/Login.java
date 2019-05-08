package com.guestbook;
 
public class Login {
   
   private String id_, pw_;
   private int grade_;
   private String name_, phone_, email_;
 
   public Login() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   
 
   public Login(String id_, String pw_, int grade_, String name_, String phone_, String email_) {
      super();
      this.id_ = id_;
      this.pw_ = pw_;
      this.grade_ = grade_;
      this.name_ = name_;
      this.phone_ = phone_;
      this.email_ = email_;
   }
 
   public String getId_() {
      return id_;
   }
 
   public void setId_(String id_) {
      this.id_ = id_;
   }
 
   public String getPw_() {
      return pw_;
   }
 
   public void setPw_(String pw_) {
      this.pw_ = pw_;
   }
 
   public int getGrade_() {
      return grade_;
   }
 
   public void setGrade_(int grade_) {
      this.grade_ = grade_;
   }
 
   public String getName_() {
      return name_;
   }
 
   public void setName_(String name_) {
      this.name_ = name_;
   }
 
   public String getPhone_() {
      return phone_;
   }
 
   public void setPhone_(String phone_) {
      this.phone_ = phone_;
   }
 
   public String getEmail_() {
      return email_;
   }
 
   public void setEmail_(String email_) {
      this.email_ = email_;
   }
 
   
   
 
}
