package com.franchi.module.user.service;

import java.util.List;

import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.role.Role;
import com.franchi.module.pojo.user.User;
import com.franchi.module.pojo.user.UserDTO;
import com.franchi.module.pojo.user.UserRole;



public interface IUserService {
	
	public User addUser(User user,int[] roleId);
	
	public int deleteUser(int id);
	
	public void updateUser(User user,int[] roleId);
	
	public User load(int id);
	
	public User login(String username,String password);
	
	public List<UserDTO> listUser(String userName,int nStart, int nOffset);
	
	public List<Resource> listAllResource(int uid);
	
	public User load(String userName);

	public int updateStatus(int userId, int status);
	
	public void addUserRole(UserRole ur);

	public void deleteUserRole(UserRole ur);

	public boolean checkUser(String userName);
	
	public long getUser(String userName);

	public boolean checkPswd(int parseInt, String passWord);

	public void editPswd(Integer id, String passWord, String oldPsd);
	
	public List<Role> listUserRole(int uid);
}
