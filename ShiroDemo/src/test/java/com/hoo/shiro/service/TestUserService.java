package com.hoo.shiro.service;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.hoo.shiro.user.pojo.User;
import com.hoo.shiro.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestUserService {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Inject
	private IUserService userService;

	/*@Before
	public void setUp() {
		//此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		//SystemContext.setRealPath("D:\\teach_source\\class2\\j2EE\\dingan\\cms-da\\src\\main\\webapp");
	}
	
	@After
	public void tearDown() {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}*/
	
	@Test
	public void testCache() {
		String username = "abc1";
		User u = new User();
		u.setUserName(username);
		u.setNickName("空号111");
		u.setStatus(1);
		u.setPassWord("111");
		userService.add(u);
		
		User tu = userService.loadByUsername(username);
		System.out.println(tu.getNickName());
	}
	
	@Test
	public void testAdd() {
		User u = new User();
		u.setUserName("kh");
		u.setNickName("空号");
		u.setStatus(1);
		u.setPassWord("111");
		userService.add(u);
	}
	
	@Test
	public void testRoleUser() {
		User user = userService.login("kh", "111");
		System.out.println(user);
	}
	
	@Test
	public void testUserRes() {
		System.out.println(userService.listAllResource(3));
	}
	
	
}
