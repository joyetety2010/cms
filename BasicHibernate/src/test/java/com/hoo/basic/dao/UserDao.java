package com.hoo.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoo.basic.model.Pager;
import com.hoo.basic.pojo.User;

@Repository(value="userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public List<User> getUser(String hql, Object[] args) {
		return this.list(hql, args);
	}

	@Override
	public List<User> getUser(String hql, Object[] args,
			Map<String, Object> alias) {
		return this.list(hql, args, alias);
	}

	@Override
	public Pager<User> findUser(String hql, Object[] args) {
		return this.find(hql, args);
	}

	@Override
	public Pager<User> findUser(String hql, Object[] args,
			Map<String, Object> alias) {
		return this.find(hql, args, alias);
	}

	@Override
	public User getUserById(int id) {
		return this.load(id);
	}

	public void deleteUser(int id){
		this.delete(id);
	}

	@Override
	public List<User> listUserBySql(String string, Object[] objects,
			Class<User> class1) {
		return this.listBySql(string, objects, class1);
	}

	@Override
	public List<User> listUserBySql(String string, Object[] objects,
			Map<String, Object> alias, Class<User> class1) {
		return this.listBySql(string,objects,alias,class1);
	}

}
