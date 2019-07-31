package com.report.dao.user;

import java.util.List;
import java.util.Map;

import com.report.model.user.Role;
import com.report.model.user.User;

public interface UserMapper {

	/**
	 * 根据用户ID查询一个用户资料
	 * 
	 * @param userId
	 * @return
	 */
	User queryUserById(Integer userId);

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	User queryUserByUsername(String username);

	/**
	 * 查询所有用户数量
	 * 
	 * @param condition
	 * 
	 * @return
	 */
	Integer queryUserCount(Map<String, Object> condition);

	/**
	 * 查询所有用户信息
	 * 
	 * @param condition
	 * 
	 * @return
	 */
	List<User> queryAllUser(Map<String, Object> condition);

	/**
	 * 保存用户账号
	 * 
	 * @param user
	 * @return
	 */
	int saveUserAccount(User user);

	/**
	 * 保存一个用户资料
	 * 
	 * @param user
	 */
	int saveUserProfile(User user);

	/**
	 * 保存用户角色
	 * 
	 * @param user
	 */
	void saveUserRole(User user);

	/**
	 * 删除用户名
	 */
	int deleteUserLogin(int id);

	/**
	 * 删除用户资料
	 * 
	 * @param id
	 * @return
	 */
	int deleteUserProfile(int id);

	/**
	 * 查询所有角色列表
	 * 
	 * @return
	 */
	List<Role> queryRoleList();

	/**
	 * 删除用户角色
	 * 
	 * @param id
	 */
	void deleteUserRole(int id);

	/**
	 * 插入一个用户角色
	 * 
	 * @param userId
	 * @param roleId
	 */
	void insertUserRole(int userId, int roleId);
}
