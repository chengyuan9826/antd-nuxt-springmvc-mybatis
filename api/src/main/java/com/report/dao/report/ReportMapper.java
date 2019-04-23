package com.report.dao.report;

import com.report.model.report.Report;
import com.report.model.wp.Post;
import com.report.model.wp.PostMeta;

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

	/**
	 * 插入一个文章
	 * @param posts
	 * @return
	 */
	Integer insertPost(Post posts);

	/**
	 * 插入文章分类ID
	 * @param map
	 * @return
	 */
	Integer insertTermRelations(Map<String,Object> map);

	/**
	 * 插入文章元数据，也就是缩略图
	 * @param postMeta
	 * @return
	 */
	Integer insertPostMeta(PostMeta postMeta);

}
