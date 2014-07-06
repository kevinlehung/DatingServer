package com.ds.persist.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the resource database table.
 * 
 */
@Entity
@Table(name="resource")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESOURCE_ID", unique=true, nullable=false)
	private int resourceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", nullable=false)
	private Date createdDate;

	@Column(length=512)
	private String description;

	@Column(nullable=false, length=512)
	private String path;

	@Column(name="RESOURCE_NAME", nullable=false, length=128)
	private String resourceName;

	//bi-directional many-to-one association to UserPhoto
	@OneToMany(mappedBy="resource")
	private List<UserPhoto> userPhotos;

	public Resource(int resourceId) {
		this.resourceId = resourceId;
	}
	
	public Resource() {
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public List<UserPhoto> getUserPhotos() {
		return this.userPhotos;
	}

	public void setUserPhotos(List<UserPhoto> userPhotos) {
		this.userPhotos = userPhotos;
	}

	public UserPhoto addUserPhoto(UserPhoto userPhoto) {
		getUserPhotos().add(userPhoto);
		userPhoto.setResource(this);

		return userPhoto;
	}

	public UserPhoto removeUserPhoto(UserPhoto userPhoto) {
		getUserPhotos().remove(userPhoto);
		userPhoto.setResource(null);

		return userPhoto;
	}

}