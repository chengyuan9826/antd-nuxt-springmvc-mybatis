package com.report.controller.ai;

import com.report.common.datasource.DynamicDataSource;
import com.report.common.util.Cache;
import com.report.controller.report.ReportController;
import com.report.dao.report.ReportMapper;
import com.report.model.wp.Post;
import com.report.service.user.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private ReportMapper reportMapper;

    Logger log = Logger.getLogger(PostController.class);

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询文章列表
     *
     * @param request
     * @param param
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.do")
    public Map<String, Object> list(HttpServletRequest request, @RequestBody(required = true)Map<String, Object> param, HttpSession session) {
        //取参数
        //返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        String msg = "查询成功";
        int state = 0;
        List<Post> list = null;
        //设置默认值
        if (param.get("pageNum") == null) {
            param.put("pageNum", 1);
        }
        if (param.get("pageSize") == null) {
            param.put("pageSize", 20);
        }
        //计算分页参数 pageNum 从1开始，这里做个兼容，如果传了0，也作为1处理
        int pageNum = (int) param.get("pageNum");
        if (pageNum == 0) {
            pageNum = 1;
        }
        int pageSize = (int) param.get("pageSize");
        int offset = (pageNum - 1) * pageSize;
        param.put("limit",pageSize);
        param.put("offset",offset);
        //操作数据库
        try {
            list = reportMapper.queryPostList(param);
            //查询baseurl
            String siteUrl = (String)Cache.get("siteUrl");
            if(siteUrl == null){
                siteUrl = reportMapper.queryOption("siteurl");
                Cache.set("siteUrl",siteUrl);
            }

            String imgUrl;
            //处理list，给imgUrl添加上前缀
            for (int i = 0; i < list.size(); i++) {
                imgUrl = list.get(i).getThumb_url();
                imgUrl = siteUrl+"/wp-content/uploads/"+imgUrl;
                list.get(i).setThumb_url(imgUrl);
            }
        } catch (Exception e) {
            state = -1;
            msg = e.getMessage();
        }
        result.put("state", state);
        result.put("msg", msg);
        result.put("list", list);
        return result;
    }

    /**
     * 查询文章详情
     *
     * @param request
     * @param param
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail.do")
    public Map<String, Object> detail(HttpServletRequest request, @RequestBody(required = true)Map<String, Object> param, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        //取参数
        if (param.get("postId") == null) {
            result.put("state", -1);
            result.put("msg", "文章ID不能为空");
            return result;
        }
        //返回结果
        String msg = "查询成功";
        int state = 0;
        Post post = null;
        //更新数据库
        try {
            post = reportMapper.queryPostDetail(param);
        } catch (Exception e) {
            state = -1;
            msg = e.getMessage();
        }
        result.put("state", state);
        result.put("msg", msg);
        result.put("post", post);
        return result;
    }
}
