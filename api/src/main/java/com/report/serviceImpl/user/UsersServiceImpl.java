package com.report.serviceImpl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.report.dao.user.UserMapper;
import com.report.model.user.Role;
import com.report.model.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.service.user.IUserService;

@Service
public class UsersServiceImpl implements IUserService {

	Logger log = Logger.getLogger(UsersServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryUserByUsername(String username) {
		return userMapper.queryUserByUsername(username);
	}

}
