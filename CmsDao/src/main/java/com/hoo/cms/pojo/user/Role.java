package com.hoo.cms.pojo.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="T_Role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="Name",length=128)
	@Length(max=128,message="角色名称太长")
	private String name;
	
	@Column(name="RoleType",length=50)
	@Enumerated(EnumType.STRING)
	@Length(max=50)
	private RoleType roleType;

	
	
	public Role() {
	}

	public Role(Integer id, String name, RoleType roleType) {
		this.id = id;
		this.name = name;
		this.roleType = roleType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
	
	
}
