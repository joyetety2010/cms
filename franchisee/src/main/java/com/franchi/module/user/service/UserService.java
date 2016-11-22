package com.franchi.module.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import com.franchi.module.exception.FranchiseeException;
import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.role.Role;
import com.franchi.module.pojo.user.User;
import com.franchi.module.pojo.user.UserDTO;
import com.franchi.module.pojo.user.UserRole;
import com.franchi.module.role.dao.IRoleDao;
import com.franchi.module.shiro.tool.ShiroUtil;
import com.franchi.module.user.dao.IUserDao;

@Service("userService")
public class UserService implements IUserService {

	@javax.annotation.Resource(name = "userDao")
	private IUserDao userDao;

	@javax.annotation.Resource(name = "roleDao")
	private IRoleDao roleDao;

	/**
	 * 添加用户
	 */
	@Override
	public User addUser(User user, int[] roleId) {

		if (!StringUtils.isNotBlank(user.getUserName())
				|| !StringUtils.isNotBlank(user.getPassWord())) {
			throw new FranchiseeException("用户名或者密码不能为空！");
		}
		user.setPassWord(ShiroUtil.md5(user.getPassWord(), user.getUserName()));
		userDao.addUser(user);
		for (int tmp : roleId) {
			UserRole ur = new UserRole();
			ur.setRoleId(tmp);
			ur.setUserId(user.getId());
			roleDao.addUserRole(ur);
		}
		return user;
	}

	/**
	 * 删除用户
	 */
	@Override
	public int deleteUser(int id) {
		User user = userDao.loadUser(id);
		if (user == null) {
			throw new FranchiseeException("没有删除的对象！");
		}
		roleDao.deleteUserRole(user.getId());
		return userDao.deleteUser(id);
	}

	/**
	 * 更新用户
	 */
	@Override
	public void updateUser(User user, int[] roleId) {
		userDao.updateUser(user);
		roleDao.deleteUserRole(user.getId());
		for (int tmp : roleId) {
			UserRole ur = new UserRole();
			ur.setRoleId(tmp);
			ur.setUserId(user.getId());
			roleDao.addUserRole(ur);
		}
		
	}

	@Override
	public User load(int id) {
		return userDao.loadUser(id);
	}

	@Override
	public User login(String username, String password) {

		List<User> u = userDao.loadByUsername(username);
		if (u == null || u.size()==0) {
			throw new UnknownAccountException("用户名或者密码出错");
		}
		if (!u.get(0).getPassWord().equals(ShiroUtil.md5(password, username))) {
			throw new IncorrectCredentialsException("用户名或者密码出错");
		}
		if (u.get(0).getStatus() == 0) {
			throw new LockedAccountException("用户已经被锁定");
		}
		return u.get(0);

	}

	@Override
	public List<UserDTO> listUser(String userName, int nStart, int nOffset) {
		List<User> us =  userDao.listUser(userName, nStart, nOffset);
		List<UserDTO> ut = new ArrayList<UserDTO>();
		for(User u: us){
			List<Role> rs =userDao.listUserRole(u.getId());
			UserDTO uo = new UserDTO();
			uo.setUser(u);
			StringBuffer sb = new StringBuffer();
			for(Role r :rs){
				sb.append(r.getId()+",");
			}
			uo.setRoles(sb.toString());
			ut.add(uo);
		}
		return ut;
	}

	@Override
	public List<Resource> listAllResource(int uid) {
		return userDao.listAllResource(uid);
	}

	@Override
	public User load(String userName) {
		List<User> users = userDao.loadByUsername(userName);
		if(users == null)
			return null;
		return users.get(0);
	}

	@Override
	public int updateStatus(int userId, int status) {
		return userDao.updateStatus(userId,status);
	}

	@Override
	public void addUserRole(UserRole ur) {
		userDao.addUserRole(ur);
		ShiroUtil.clearCachedAuthorization(ur.getUserId());
	}

	@Override
	public void deleteUserRole(UserRole ur) {
		userDao.deleteUserRole(ur);
		ShiroUtil.clearCachedAuthorization(ur.getUserId());
	}

	@Override
	public boolean checkUser(String userName) {
		return userDao.checkUser(userName);
	}

	@Override
	public long getUser(String userName) {
		return userDao.getUser(userName);
	}

	@Override
	public boolean checkPswd(int parseInt, String passWord) {
		User u = userDao.loadUser(parseInt);
		if(u.getPassWord().equals(ShiroUtil.md5(passWord, u.getUserName())))
			return true;
		return false;
	}

	@Override
	public void editPswd(Integer id, String passWord,String oldPsd) {
		User u = userDao.loadUser(id);
		if(u.getPassWord().equals(ShiroUtil.md5(oldPsd, u.getUserName()))){
			u.setPassWord(ShiroUtil.md5(passWord, u.getUserName()));
			userDao.updateUser(u);
			ShiroUtil.clearCachedAuthentication(id);
		}
	}

	@Override
	public List<Role> listUserRole(int uid) {
		return userDao.listUserRole(uid);
	}


}
