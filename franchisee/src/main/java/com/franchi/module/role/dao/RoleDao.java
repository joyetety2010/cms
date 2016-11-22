package com.franchi.module.role.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.role.Role;
import com.franchi.module.pojo.role.RoleResource;
import com.franchi.module.pojo.user.UserRole;
import com.unioncast.db.HBaseDao;


@SuppressWarnings({ "unchecked"})
@Repository("roleDao")
public class RoleDao extends HBaseDao<Role> implements IRoleDao{

	@Override
	public List<Role> listRole() {
		StringBuffer hql = new StringBuffer("FROM User WHERE 1=1");
		return select(hql.toString());
	}

	@Override
	public UserRole loadUserRole(int uid, int roleId) {
		String hql = "select ur from UserRole ur where ur.userId=? and ur.roleId=?";
		return (UserRole) this.getSession().createQuery(hql).setParameter(1, uid).setParameter(2, roleId).uniqueResult();
	}

	@Override
	public void addUserRole(UserRole ur) {
		this.getSession().save(ur);
	}

	@Override
	public int deleteUserRole(int uId) {
		StringBuffer hql = new StringBuffer("DELETE FROM t_FRANCHISEE_user_role WHERE UserId = ?");
		return this.getSession().createSQLQuery(hql.toString()).setParameter(0, uId).executeUpdate(); 
	}


	@Override
	public List<Resource> listRoleResource(int roleId) {
		String hql = "select res from Role role,Resource res,RoleResource rr where " +
				"role.id=rr.roleId and res.id=rr.resId and role.id=?";
		return this.getSession().createQuery(hql).setParameter(0, roleId).list();
	}

	@Override
	public void addRoleResource(RoleResource rr) {
		this.getSession().save(rr);
		
	}

	@Override
	public void deleteRoleResource(RoleResource rr) {
		this.getSession().delete(rr);
	}


	@Override
	public void addRole(Role role) {
		insert(role);
	}

	@Override
	public Role loadRole(int rId) {
		String hql = "select Role from Role where Id=?";
		return (Role) this.getSession().createQuery(hql).setParameter(0, rId).uniqueResult();
	}
}
