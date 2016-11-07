package com.hoo.cms.role.dao;

import java.util.List;

import com.hoo.cms.pojo.user.Role;

public interface IRoleDao {
	public List<Role> selectRole();
	
	public void deleteRoleUser(int rid);

	public void addRole(Role role);

	public void deleteRole(int id);

	public void updateRole(Role role);

	public Role loadRole(int id);
}
