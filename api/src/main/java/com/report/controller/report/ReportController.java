package com.report.controller.report;

import com.report.controller.user.UserController;
import com.report.dao.report.ReportMapper;
import com.report.model.report.Report;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ReportMapper reportMapper;

	Logger log = Logger.getLogger(UserController.class);

	@ResponseBody
	@RequestMapping(value = "/queryReport.do")
	public Map<String, Object> login(HttpServletRequest request,@RequestBody(required=false) Map<String,Object> params, HttpSession session) {
		//取参数
        if(params!=null){
            String fileType = (String) params.get("fileType");
            String likeUserName = (String) params.get("likeUserName");
            String notLikeUserName = (String) params.get("notLikeUserName");
            Integer minNum = (Integer) params.get("minNum");
            Integer maxNum = (Integer) params.get("maxNum");
        }
		//查询数据库
		List<Report> list = reportMapper.queryReport(params);
		//返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = null;
		int state = 0;

		result.put("state", state);
		result.put("msg", msg);
		result.put("list", list);
		return result;
	}
}
