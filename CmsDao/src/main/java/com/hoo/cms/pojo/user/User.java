package com.hoo.cms.pojo.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="T_User")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="UserName",length=36)
	private String userName;
	
	@Column(name="PassWord",length=128)
	private String passWord;
	
	@Length(max=36,message="用户名超长")
	private String nickName;
	
	@Column(name="Email",length=128)
	private String email;
	
	@Column(name="Phone",length=50)
	private String phone;
	
	@Column(name="Status")
	private Integer status;
	
	@Column(name="CreateTime")
	private Date createTime;
	
	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User() {
	}

	public User(Integer id, String userName, String passWord, String nickName,
			String email, String phone, Integer status) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.nickName = nickName;
		this.email = email;
		this.phone = phone;
		this.status = status;
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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
