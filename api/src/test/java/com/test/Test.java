package com.test;

import com.alibaba.fastjson.JSON;

public class Test {
	public static void main(String[] args) {
		JSON json =JSON.parseObject("{}");
		System.out.println(json);
	}
}
