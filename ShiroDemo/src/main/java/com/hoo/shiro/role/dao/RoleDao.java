package com.hoo.shiro.role.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoo.basic.dao.BaseDao;
import com.hoo.shiro.resource.pojo.Resource;
import com.hoo.shiro.role.pojo.Role;
import com.hoo.shiro.role.pojo.RoleResource;
import com.hoo.shiro.user.pojo.UserRole;

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao{

	@Override
	public List<Role> listRole() {
		return super.list("from Role");
	}

	@Override
	public UserRole loadUserRole(int uid, int roleId) {
		String hql = "select ur from UserRole ur where ur.userId=? and ur.roleId=?";
		Object[] field = new Object[]{
			uid,roleId
		};
		return (UserRole)super.queryObject(hql,field);
	}

	@Override
	public void addUserRole(int uid, int roleId) {
		UserRole ur = null;
		ur = loadUserRole(uid, roleId);
		if(ur==null) {
			ur = new UserRole();
			ur.setRoleId(roleId);
			ur.setUserId(uid);
			this.getSession().save(ur);
		}
		
	}

	@Override
	public void deleteUserRole(int uid, int roleId) {
		UserRole ur = null;
		ur = loadUserRole(uid, roleId);
		if(ur!=null) {
			this.getSession().delete(ur);
		}
		
	}

	@Override
	public void deleteUserRoles(int uid) {
		String hql = "delete UserRole ur where ur.userId=?";
		super.updateByHql(hql, uid);
		
	}

	@Override
	public List<Resource> listRoleResource(int roleId) {
		String hql = "select res from Role role,Resource res,RoleResource rr where " +
				"role.id=rr.roleId and res.id=rr.resId and role.id=?";
		return this.getSession().createQuery(hql).setParameter(0, roleId).list();
	}

	@Override
	public void addRoleResource(int roleId, int resId) {
		RoleResource rr = null;
		rr = loadResourceRole(roleId, resId);
		if(rr==null) {
			rr = new RoleResource();
			rr.setResId(resId);
			rr.setRoleId(roleId);
			this.getSession().save(rr);
		}
		
	}

	@Override
	public void deleteRoleResource(int roleId, int resId) {
		RoleResource rr = null;
		rr = loadResourceRole(roleId, resId);
		if(rr!=null) {
			this.getSession().delete(rr);
		}
		
	}

	@Override
	public RoleResource loadResourceRole(int roleId, int resId) {
		String hql = "select rr from RoleResource rr where rr.roleId=? and rr.resId=?";
		Object[] field = new Object[]{
				roleId,resId
			};
		return (RoleResource)super.queryObject(hql, field);
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
	public Role loadRole(int id) {
		return this.loadRole(id);
	}

	@Override
	public void updateRole(Role role) {
		this.update(role);
	}

}
