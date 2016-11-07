package com.hoo.cms.role.service;

import java.util.List;

import com.hoo.cms.pojo.user.Role;

public interface IRoleService {
	public void add(Role role);
	public void delete(int id);
	public void update(Role role);
	public Role load(int id);
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
