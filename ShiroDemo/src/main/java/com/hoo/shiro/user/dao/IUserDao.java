package com.hoo.shiro.user.dao;

import java.util.List;

import com.hoo.shiro.resource.pojo.Resource;
import com.hoo.shiro.role.pojo.Role;
import com.hoo.shiro.user.pojo.User;


public interface IUserDao {

	public List<User> listUser();
	
	public User loadByUsername(String username);
	
	public List<User> listByRole(int id);
	
	public List<Resource> listAllResource(int uid);
	
	public List<String> listRoleSnByUser(int uid);
	
	public List<Role> listUserRole(int uid);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public User loadUser(int id);
	
	public void deleteUser(int id);
}
