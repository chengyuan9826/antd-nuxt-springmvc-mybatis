package com.report.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.report.common.util.Crypt;
import com.report.dao.user.UserMapper;
import com.report.model.user.Role;
import com.report.model.user.User;
import org.apache.log4j.Logger;
import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.report.service.user.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private UserMapper userMapper;

	Logger log = Logger.getLogger(UserController.class);

	@ResponseBody

	@RequestMapping(value = "/login.do")
	public Map<String, Object> login(HttpServletRequest request,@RequestBody Map<String,Object> params, HttpSession session) {
		String username = (String) params.get("username");
		String password = (String) params.get("password");
		User user = userService.queryUserByUsername(username);
		Map<String, Object> result = new HashMap<String, Object>();
		String msg = null;
		int state = 0;
		if (user == null) {
			state = 1;
			msg = "用户不存在。";
		} else if (!Crypt.validateWordpressPassword(username,user.getUserPass())) {
			state = 2;
			msg = "密码错误，请重新输入。";
		} else {
			msg = "验证成功！";
			session.setAttribute("user", user);
		}
		result.put("state", state);
		result.put("msg", msg);
		return result;
	}

	/**
	 * 用户退出
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String loginOut(HttpSession session) {
		Object o = session.getAttribute("user");
		if (o != null) {
			session.removeAttribute("user");
		}
		return "login";
	}

	/**
	 * 查询用户列表
	 * 
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping("/userList.do")
	public String queryUser(ModelMap map, User user) {
		return "user/userList";
	}

	/**
	 * 跳转到查看用户页面
	 * 
	 * @return
	 */
	@RequestMapping("/home.do")
	public String toViewUserPage() {
		return "home";
	}
}
