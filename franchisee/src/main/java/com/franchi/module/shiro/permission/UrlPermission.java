package com.franchi.module.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * 创建自己的permission
 * @author Administrator
 *
 */
public class UrlPermission implements Permission {
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlPermission() {
	}
	
	public UrlPermission(String url) {
		this.url = url;
	}

	@Override
	public boolean implies(Permission p) {
		if(!(p instanceof UrlPermission)) return false;
		UrlPermission up = (UrlPermission)p;
		// /admin/role/**
		
		//根据资源路径比较两者之间是否一致
		PatternMatcher patternMatcher = new AntPathMatcher();
		return patternMatcher.matches(this.getUrl(), up.getUrl());
	}

}
