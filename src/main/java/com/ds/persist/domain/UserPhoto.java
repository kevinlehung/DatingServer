package com.ds.persist.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_photo database table.
 * 
 */
@Entity
@Table(name="user_photo")
@NamedQuery(name="UserPhoto.findAll", query="SELECT u FROM UserPhoto u")
public class UserPhoto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_PHOTO_ID", unique=true, nullable=false)
	private int userPhotoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	//bi-directional many-to-one association to Resource
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RESOURCE_ID", nullable=false)
	private Resource resource;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID", nullable=false)
	private User user;

	public UserPhoto() {
	}

	public int getUserPhotoId() {
		return this.userPhotoId;
	}

	public void setUserPhotoId(int userPhotoId) {
		this.userPhotoId = userPhotoId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Resource getResource() {
		return this.resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}