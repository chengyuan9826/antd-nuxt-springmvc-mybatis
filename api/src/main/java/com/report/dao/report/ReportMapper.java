package com.report.dao.report;

import com.report.model.report.Report;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

	/**
	 * 查询所有用户信息
	 * 
	 * @param condition
	 * 
	 * @return
	 */
	List<Report> queryReport(Map<String, Object> condition);

}
