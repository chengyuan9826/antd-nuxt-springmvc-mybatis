package com.test;

import com.alibaba.fastjson.JSON;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		//day从周日开始
		int day = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DAY_OF_WEEK,-day+2);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(c.getTime()));
	}
}
