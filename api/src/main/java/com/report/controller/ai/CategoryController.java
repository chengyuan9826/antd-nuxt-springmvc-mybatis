package com.report.controller.ai;

import com.report.dao.report.ReportMapper;
import com.report.model.wp.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ReportMapper reportMapper;

    Logger log = Logger.getLogger(CategoryController.class);

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询分类树形结构
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list.do")
    public Map<String, Object> detail() {
        Map<String, Object> result = new HashMap<String, Object>();
        //返回结果
        String msg = "查询成功";
        int state = 0;
        List<Category> list = null;
        //更新数据库
        try {
            list = reportMapper.queryCategoryList();

        } catch (Exception e) {
            state = -1;
            msg = e.getMessage();
        }
        result.put("state", state);
        result.put("msg", msg);
        result.put("list", list);
        return result;
    }


}
