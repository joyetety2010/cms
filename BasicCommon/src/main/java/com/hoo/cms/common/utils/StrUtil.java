package com.hoo.cms.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class StrUtil {

	public static String toStr(String sStr, String sDefault){
		return StringUtils.defaultString(sStr, sDefault);
	}
	
	public static Integer toInt(String sStr, int sDefault){
		return NumberUtils.toInt(sStr, sDefault);
	}
	
	public static Float toFloat(String sStr, float sDefault){
		return NumberUtils.toFloat(sStr, sDefault);
	}
	
	public static Double toDouble(String sStr, double sDefault){
		return NumberUtils.toDouble(sStr, sDefault);
	}
	
	public static Long toLong(String sStr, long sDefault){
		return NumberUtils.toLong(sStr, sDefault);
	}
	
	public static Short toShort(String sStr, short sDefault){
		return NumberUtils.toShort(sStr, sDefault);
	}
	
	public static boolean toBoolean(String sValue, boolean nDefault){
		if(sValue == null || sValue.isEmpty()) return nDefault;
		return Boolean.valueOf(sValue);
	}
}
