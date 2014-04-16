package com.ds.persist.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the relationship database table.
 * 
 */
@Entity
@Table(name="relationship")
@NamedQuery(name="Relationship.findAll", query="SELECT r FROM Relationship r")
public class Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RELATIONSHIP_ID", unique=true, nullable=false)
	private int relationshipId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE", nullable=false)
	private Date createdDate;

	@Column(name="FRIEND_OF_USER_ID", nullable=false)
	private int friendOfUserId;

	@Column(name="USER_ID", nullable=false)
	private int userId;

	public Relationship() {
	}

	public int getRelationshipId() {
		return this.relationshipId;
	}

	public void setRelationshipId(int relationshipId) {
		this.relationshipId = relationshipId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getFriendOfUserId() {
		return this.friendOfUserId;
	}

	public void setFriendOfUserId(int friendOfUserId) {
		this.friendOfUserId = friendOfUserId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}