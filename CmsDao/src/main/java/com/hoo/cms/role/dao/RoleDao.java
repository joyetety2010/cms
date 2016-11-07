package com.hoo.cms.role.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoo.basic.dao.BaseDao;
import com.hoo.cms.pojo.user.Role;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public List<Role> selectRole() {
		String hql = " from Role";
		return this.list(hql);
	}

	@Override
	public void deleteRoleUser(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=? ", rid);
	}

	@Override
	public void addRole(Role role) {
		this.add(role);
	}

	@Override
	public void deleteRole(int id) {
		this.delete(id);

	}

	@Override
	public void updateRole(Role role) {
		this.update(role);
	}

	@Override
	public Role loadRole(int id) {
		return this.load(id);
	}

}
