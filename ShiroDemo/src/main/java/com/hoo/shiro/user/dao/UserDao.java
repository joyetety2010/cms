package com.hoo.shiro.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoo.basic.dao.BaseDao;
import com.hoo.shiro.resource.pojo.Resource;
import com.hoo.shiro.role.pojo.Role;
import com.hoo.shiro.user.pojo.User;

@Repository("userDao")
@SuppressWarnings({"deprecation","unchecked"})
public class UserDao extends BaseDao<User> implements IUserDao{

	@Override
	public List<User> listUser() {
		return super.list("from User");
	}

	@Override
	public User loadByUsername(String username) {
		return (User)super.queryObject("from User where username=?", username);
	}

	@Override
	public List<User> listByRole(int id) {
		String hql = "select u from User u,Role r,UserRole ur where u.id=ur.userId and r.id=ur.roleId and r.id=?";
		return super.list(hql, id);
	}

	
	@Override
	public List<Resource> listAllResource(int uid) {
		String hql = "select res from User u,Resource res,UserRole ur,RoleResource rr where " +
				"u.id=ur.userId and ur.roleId=rr.roleId  and rr.resId=res.id and u.id=?";
		return this.getSession().createQuery(hql).setParameter(0, uid).list();
	}

	
	
	@Override
	public List<String> listRoleSnByUser(int uid) {
		String hql = "select r.sn from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?";
		return this.getSession().createQuery(hql).setParameter(0, uid).list();
	}

	@Override
	public List<Role> listUserRole(int uid) {
		String hql = "select r from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?";
		return this.getSession().createQuery(hql).setParameter(0, uid).list();
	}

	@Override
	public void addUser(User user) {
		this.add(user);
		
	}

	@Override
	public void updateUser(User user) {
		this.update(user);
	}

	@Override
	public User loadUser(int id) {
		return this.load(id);
	}

	@Override
	public void deleteUser(int id) {
		this.delete(id);
	}

}
