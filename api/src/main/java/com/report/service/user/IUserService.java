package com.report.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.report.model.user.User;

public interface IUserService {

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User queryUserByUsername(String username);

}
