package com.ds.web.webservice.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class UserProfileWsBean extends BaseWsBean {
	private String fullName;
	private String aboutMe;
	private List<String> photoUrls = new ArrayList<String>();
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	
}
