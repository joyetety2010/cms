package com.franchi.module.user.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.role.Role;
import com.franchi.module.pojo.user.User;
import com.franchi.module.pojo.user.UserRole;
import com.unioncast.db.Field;
import com.unioncast.db.HBaseDao;


@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDao extends HBaseDao<User> implements IUserDao{

	@Override
	public List<User> listUser(String userName,int nStart, int nOffset) {
		
		Field field = new Field();
		StringBuffer hql = new StringBuffer("FROM User WHERE 1=1 ");
		if(StringUtils.isNotBlank(userName)){
			hql.append("AND userName like ? ");
			field.addStr('%'+userName+'%');
		}
	
		return select(hql.toString(),field,nStart,nOffset);
	}

	@Override
	public List<Resource> listAllResource(int uId) {
		
		Field field = new Field();
		StringBuffer hql = new StringBuffer("select res.* from t_franchisee_user u,t_franchisee_resource res,t_franchisee_user_role ur,t_franchisee_role_res rr where " +
				"u.Id=ur.UserId and ur.RoleId=rr.RoleId  and rr.ResId=res.Id and u.Id=?");
		field.addInt(uId);
		return selectFromSQLByClass(hql.toString(),field, Resource.class);
	}

	
	
	@Override
	public void addUser(User user) {
		insert(user);
	}

	@Override
	public void updateUser(User user) {
		update(user);
	}

	@Override
	public User loadUser(int uId) {
		return getById(uId);
	}

	@Override
	public int deleteUser(int uId) {
		return update("DELETE FROM User WHERE Id IN ("+uId+")");
	}

	@Override
	public List<User> loadByUsername(String userName) {
		Field field = new Field();
		StringBuffer hql = new StringBuffer("FROM User u WHERE 1=1 and u.userName=?");
		field.addStr(userName);
		
		return select(hql.toString(),field);
	}
	
	@Override
	public List<Role> listUserRole(int uid) {
		String hql = "select r from UserRole ur,Role r,User u where u.id=ur.userId and r.id=ur.roleId and u.id=?";
		return this.getSession().createQuery(hql).setParameter(0, uid).list();
	}

	@Override
	public int updateStatus(int userId, int status) {
		String sql = "update t_franchisee_user set status=? where Id=?";
		return this.getSession().createSQLQuery(sql).setParameter(0, status).setParameter(1, userId).executeUpdate();
		
	}

	@Override
	public void addUserRole(UserRole ur) {
		 this.getSession().save(ur);
	}

	@Override
	public void deleteUserRole(UserRole ur) {
		String hql = "delete UserRole u where u.userId=? and u.roleId=?";
		Field field = new Field();
		field.addInt(ur.getUserId());
		field.addInt(ur.getRoleId());
		update(hql,field);
	}

	@Override
	public boolean checkUser(String userName) {
		String sql = "select count(*) from t_franchisee_user u where u.UserName='"+userName+"'";
		BigInteger bg = (BigInteger) this.getSession().createSQLQuery(sql).uniqueResult();
		if(bg.intValue()>0)
		return true;
		return false;
	}

	@Override
	public long getUser(String userName) {
		Field field = new Field();
		StringBuffer hql = new StringBuffer("FROM User WHERE 1=1 ");
		if(StringUtils.isNotBlank(userName)){
			hql.append("AND userName like ? ");
			field.addStr('%'+userName+'%');
		}
		return getTotal(hql.toString(),field);
	}

}
