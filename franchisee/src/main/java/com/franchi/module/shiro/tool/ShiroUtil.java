package com.franchi.module.shiro.tool;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;

import com.franchi.module.pojo.user.User;
import com.franchi.module.shiro.realm.UserRealm;

public class ShiroUtil {
	public static String md5(String password, String salt) {
		String p = null;
		p = new Md5Hash(password, salt).toHex();
		return p;
	}

	// 清除认证缓存
	public static void clearCachedAuthentication(int currentId) {
		Subject subject = SecurityUtils.getSubject();

		User u = (User) subject.getPrincipal();
		if (currentId == u.getId()) {
			RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils
					.getSecurityManager();
			UserRealm userRealm = (UserRealm) securityManager.getRealms()
					.iterator().next();
			userRealm.clearCachedAuthenticationInfo(subject.getPrincipals());
		}
	}

	// 清除授权缓存
	public static void clearCachedAuthorization(int currentId) {
		Subject subject = SecurityUtils.getSubject();
		User u = (User) subject.getPrincipal();
		if (currentId == u.getId()) {
			RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils
					.getSecurityManager();
			UserRealm userRealm = (UserRealm) securityManager.getRealms()
					.iterator().next();
			userRealm.clearCachedAuthorizationInfo(subject.getPrincipals());
		}
	}
}
