package com.franchi.module.role.dao;

import java.util.List;

import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.role.Role;
import com.franchi.module.pojo.role.RoleResource;
import com.franchi.module.pojo.user.UserRole;



public interface IRoleDao {

	public List<Role> listRole();
	
	public UserRole loadUserRole(int uid,int roleId);
	
	public void addUserRole(UserRole ur);
	
	public int deleteUserRole(int uId);
	
	/**
	 * 根据角色id获取可以访问的所有资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> listRoleResource(int roleId);
	
	public void addRoleResource(RoleResource rr);
	
	public void deleteRoleResource(RoleResource rr);

	public void addRole(Role role);
	
	public Role loadRole(int rId);


}
