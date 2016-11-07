package com.hoo.cms.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtil {

	public static String toMD5(String data){
		return DigestUtils.md5Hex(data);
	}
	
	public static String encodeBase64(String data){  
        return Base64.encodeBase64String(data.getBytes());  
    }  
	
	public static String decodeBase64(String data){  
        return new String(Base64.decodeBase64(data.getBytes()));  
    }  
}
