package com.hoo.basic.dao;

import java.util.List;
import java.util.Map;

import com.hoo.basic.model.Pager;
import com.hoo.basic.pojo.User;


public interface IUserDao {
	
	List<User> getUser(String hql, Object[] args);
	
	List<User> getUser(String hql,Object []args, Map<String, Object> alias);
	
	Pager<User> findUser(String hql, Object[] args);
	
	Pager<User> findUser(String hql, Object[] args, Map<String,Object> alias);
	
	User getUserById(int id);
	
	void deleteUser(int id);

	List<User> listUserBySql(String string, Object[] objects, Class<User> class1);

	List<User> listUserBySql(String string, Object[] objects,
			Map<String, Object> alias, Class<User> class1);
	
	
}
