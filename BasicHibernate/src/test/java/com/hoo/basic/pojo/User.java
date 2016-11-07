package com.hoo.basic.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String userName;
	
	

	public User() {
	}

	public User(Integer id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
