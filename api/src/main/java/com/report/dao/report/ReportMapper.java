package com.report.dao.report;

import com.report.model.report.Report;
import com.report.model.wp.Category;
import com.report.model.wp.Post;
import com.report.model.wp.PostMeta;

import java.util.List;
import java.util.Map;

public interface ReportMapper {

    /**
     * 查询所有用户信息
     *
     * @param condition
     * @return
     */
    List<Report> queryReport(Map<String, Object> condition);

    /**
     * 插入一个文章
     *
     * @param posts
     * @return
     */
    Integer insertPost(Post posts);

    /**
     * 插入文章分类ID
     *
     * @param map
     * @return
     */
    Integer insertTermRelations(Map<String, Object> map);

    /**
     * 插入文章元数据，也就是缩略图
     *
     * @param postMeta
     * @return
     */
    Integer insertPostMeta(PostMeta postMeta);

    /**
     * 把该用户下的文章全部发布
     *
     * @param username
     * @return
     */
    Integer batchPublish(String username);

    /**
     * 查询分类信息
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> queryTermInfo(Map<String, Object> params);

    /**
     * 查询文章列表
     * @param param
     * @return
     */
    List<Post> queryPostList(Map<String,Object> param);

    /**
     * 查询文章详情
     * @param param
     * @return
     */
    Post queryPostDetail(Map<String,Object> param);

    /**
     * 从options表中查询数据
     * @param optionKey
     * @return
     */
    String queryOption(String optionKey);

    /**
     *
     * @return
     */
    List<Category> queryCategoryList();

    /**
     * 查询一个文章ID前面的一个ID
     * @param id
     * @return
     */
    Integer selectPrevId(int id);

    /**
     * 查询一个文章ID后面的一个ID
     * @param id
     * @return
     */
    Integer selectNextId(int id);
}
