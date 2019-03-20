/**
 * Project Name:vote
 * File Name:StringUtil.java
 * Package Name:com.util
 * Date:2015年5月11日下午1:10:58
 * Copyright (c) 2015, www.landfalcon.com All Rights Reserved.
 *
*/

package com.report.common.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	
	public static final String ENCODING= "UTF-8";
	/**
	 * 字符串转换为字符串数据
	 */
	public static String[] strToArr(String str){
		if(!StringUtil.isEmptyp(str)){
			String[] arr=str.split(",");
			return arr;
		}
		return null;
	}
	/**
	 * 字符串是否为空
	 * true 为空
	 * false 不为空
	 */
	public static boolean isEmptyp(String str){
		if(null != str && str.length() > 0){
			return false;
		}
		return true;
	}
	
	/**
	 * 字符串不为空
	 */
	public static boolean isNotEmpty(String str){
		return !isEmptyp(str);
	}
	
	/**
	 * 
	 * 
	 */
	public static String changeUtf(String paramer,String encoding){
		byte[] bb=null;
		String  name="";
		try {
			bb = paramer.getBytes("ISO-8859-1");
			name= new String(bb, ENCODING); //再用"utf-8"格式表示name
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		return name;
	}
}

