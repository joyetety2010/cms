package com.franchi.module.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.franchi.module.pojo.resource.Resource;
import com.franchi.module.pojo.user.User;
import com.franchi.module.shiro.tool.InitServlet;
import com.franchi.module.user.service.IUserService;

public class UserRealm extends AuthorizingRealm {

	/**
	 * 授权验证方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 根据用户登录认证通过信息获取其数据库对应信息
		User user = ((User) principals.getPrimaryPrincipal());
		int uid = user.getId();
		// 获取数据库用户能够访问的资源
		IUserService userService = (IUserService) InitServlet
				.getBean("userService");
		List<Resource> reses = userService.listAllResource(uid);
		List<String> permissions = new ArrayList<String>();
		for (Resource r : reses) {
			permissions.add(r.getUrl());
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 设置数据库用户资源
		info.setStringPermissions(new HashSet<String>(permissions));
		return info;
	}

	/**
	 * 登陆认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		String password = new String((char[]) token.getCredentials());
		IUserService userService = (IUserService) InitServlet
				.getBean("userService");
		User user = userService.login(username, password);
		// 登陆认证
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,
				user.getPassWord(), this.getName());
		// 设置言值
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getUserName()));
		return info;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		// System.out.println("清除授权的缓存");

		/*
		 * Cache c = this.getAuthorizationCache();
		 * 
		 * Set<Object> keys = c.keys(); for(Object o:keys) {
		 * System.out.println("授权缓存:"+o+"-----"+c.get(o)+"----------"); }
		 */
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		// System.out.println("清除认证的缓存");
		/*
		 * Cache c = this.getAuthenticationCache(); Set<Object> keys = c.keys();
		 * for(Object o:keys) {
		 * System.out.println("认证缓存:"+o+"----------"+c.get(o)+"----------"); }
		 */
		User user = ((User) principals.getPrimaryPrincipal());
		SimplePrincipalCollection spc = new SimplePrincipalCollection(
				user.getUserName(), getName());
		super.clearCachedAuthenticationInfo(spc);
	}

}
