package com.ds.web.webservice.bean;

/**
 * 
 * @author hunglevn@outlook.com
 * 
 */
public class UserProfileWsBean extends BaseWsBean {
	private String fullName;
	private String aboutMe;
	private String[] photoUrls;
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
	public String[] getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	
}
