package com.hoo.shiro.tools;

import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroUtil {
	public static String md5(String password,String salt) {
		String p = null;
		p = new Md5Hash(password, salt).toHex();
		return p;
	}
}
