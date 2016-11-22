package com.franchi.module.user.dao;

import java.util.List;

import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.role.Role;
import com.franchi.module.pojo.user.User;
import com.franchi.module.pojo.user.UserRole;



public interface IUserDao {

	public List<User> listUser(String userName,int nStart, int nOffset);
	
	public List<Resource> listAllResource(int uId);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public User loadUser(int uId);
	
	public int deleteUser(int uId);
	
	public List<User> loadByUsername(String userName);
	
	public List<Role> listUserRole(int uid);

	public int updateStatus(int userId, int status);
	
	public void addUserRole(UserRole ur);

	public void deleteUserRole(UserRole ur);

	public boolean checkUser(String userName);
	
	public long getUser(String userName);
}
