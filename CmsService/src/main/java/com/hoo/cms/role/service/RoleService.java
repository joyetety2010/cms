package com.hoo.cms.role.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoo.cms.exception.CmsException;
import com.hoo.cms.pojo.user.Role;
import com.hoo.cms.pojo.user.User;
import com.hoo.cms.role.dao.IRoleDao;
import com.hoo.cms.user.dao.IUserDao;

@Service("roleService")
public class RoleService implements IRoleService {
	
	@Inject
	private IRoleDao roleDao;
	
	@Inject
	private IUserDao userDao;
	
	
	
	

	@Override
	public void add(Role role) {
		roleDao.addRole(role);
	}

	@Override
	public void delete(int id) {
		List<User> us = userDao.listRoleUsers(id);
		if(us!=null&&us.size()>0) throw new CmsException("删除的角色对象中还有用户，不能删除");
		roleDao.deleteRole(id);
	}

	@Override
	public void update(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public Role load(int id) {
		return roleDao.loadRole(id);
	}

	@Override
	public List<Role> listRole() {
		return roleDao.selectRole();
	}

	@Override
	public void deleteRoleUsers(int rid) {
		roleDao.deleteRoleUser(rid);
	}

}
