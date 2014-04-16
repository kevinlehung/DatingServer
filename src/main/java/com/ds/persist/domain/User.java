package com.ds.persist.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public interface Status {
		public static final String ACTIVE = "ACTIVE";
		public static final String INACTIVE = "INACTIVE";
		public static final String BAN = "BAN";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID", unique=true, nullable=false)
	private int userId;

	@Column(name="DATE_CREATED", nullable=false)
	private Date dateCreated;

	@Column(name="DATE_UPDATED")
	private Date dateUpdated;

	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;

	@Column(nullable=false, length=200)
	private String password;

	@Column(nullable=false, length=1)
	private String status;

	@Column(name="USER_EMAIL", nullable=false, length=50)
	private String userEmail;

	@Column(name="USER_NAME", length=50)
	private String userName;

	//bi-directional many-to-one association to Profile
	@OneToOne(mappedBy="user")
	private Profile profile;

	//bi-directional many-to-one association to UserPhoto
	@OneToMany(mappedBy="user")
	private List<UserPhoto> userPhotos;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<UserPhoto> getUserPhotos() {
		return this.userPhotos;
	}

	public void setUserPhotos(List<UserPhoto> userPhotos) {
		this.userPhotos = userPhotos;
	}

	public UserPhoto addUserPhoto(UserPhoto userPhoto) {
		getUserPhotos().add(userPhoto);
		userPhoto.setUser(this);

		return userPhoto;
	}

	public UserPhoto removeUserPhoto(UserPhoto userPhoto) {
		getUserPhotos().remove(userPhoto);
		userPhoto.setUser(null);

		return userPhoto;
	}

}