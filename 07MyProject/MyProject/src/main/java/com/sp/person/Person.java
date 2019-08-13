package com.sp.person;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Person {
	private int personId;
	private String personName;
	private String personTel;
	private String personEmail;
	private String personAddr1;
	private String personAddr2;
	private String personPost;
	private String personPhoto;
	
	private long gap;
	
	private int fileid;
	private String personid;
	private long filesize;
	private String originalfilename;
	private String savefilename;
	
	private List<MultipartFile> upload;
	
	public long getGap() {
		return gap;
	}
	public void setGap(long gap) {
		this.gap = gap;
	}
	public List<MultipartFile> getUpload() {
		return upload;
	}
	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonTel() {
		return personTel;
	}
	public void setPersonTel(String personTel) {
		this.personTel = personTel;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonAddr1() {
		return personAddr1;
	}
	public void setPersonAddr1(String personAddr1) {
		this.personAddr1 = personAddr1;
	}
	public String getPersonAddr2() {
		return personAddr2;
	}
	public void setPersonAddr2(String personAddr2) {
		this.personAddr2 = personAddr2;
	}
	public String getPersonPost() {
		return personPost;
	}
	public void setPersonPost(String personPost) {
		this.personPost = personPost;
	}
	public String getPersonPhoto() {
		return personPhoto;
	}
	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

}
