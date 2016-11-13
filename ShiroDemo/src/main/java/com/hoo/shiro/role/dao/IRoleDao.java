package com.hoo.shiro.role.dao;

import java.util.List;

import com.hoo.shiro.resource.pojo.Resource;
import com.hoo.shiro.role.pojo.Role;
import com.hoo.shiro.role.pojo.RoleResource;
import com.hoo.shiro.user.pojo.UserRole;


public interface IRoleDao {

	public List<Role> listRole();
	
	public UserRole loadUserRole(int uid,int roleId);
	
	public void addUserRole(int uid,int roleId);
	
	public void deleteUserRole(int uid,int roleId);
	
	/**
	 * 删除某个用户的所有角色
	 * @param uid
	 */
	public void deleteUserRoles(int uid);
	/**
	 * 根据角色id获取可以访问的所有资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> listRoleResource(int roleId);
	
	public void addRoleResource(int roleId,int resId);
	
	public void deleteRoleResource(int roleId,int resId);
	
	public RoleResource loadResourceRole(int roleId,int resId);

	public void addRole(Role role);

	public void deleteRole(int id);

	public Role loadRole(int id);

	public void updateRole(Role role);
}
