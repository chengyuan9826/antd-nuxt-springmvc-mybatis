/**
 * Project Name:vend_mybatisdao_demo
 * File Name:DynamicDataSource .java
 * Package Name:cn.easysw.server.testpackage.routing
 * Date:2014年4月1日下午5:28:01
 * Copyright (c) 2014, www.windo-soft.com All Rights Reserved.
 *
 */

package com.report.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName:s <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年4月1日 下午5:28:01 <br/>
 * 
 * @author zuokangsheng
 * @version
 * @since JDK 1.6
 * @see
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> contentType = new ThreadLocal<String>();
	
	public static void setType(String type){
		contentType.set(type);
	}
	
	public void clear(){
		contentType.remove();
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		return contentType.get();
	}
}
