package com.guestbook;
 
public class Picture {
    
    private String pic_name_, content_;
 
    public Picture() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    public Picture(String pic_name_, String content_) {
        super();
        this.pic_name_ = pic_name_;
        this.content_ = content_;
    }
 
    public String getPic_name_() {
        return pic_name_;
    }
 
    public void setPic_name_(String pic_name_) {
        this.pic_name_ = pic_name_;
    }
 
    public String getContent_() {
        return content_;
    }
 
    public void setContent_(String content_) {
        this.content_ = content_;
    }
    
    
 
}
 
