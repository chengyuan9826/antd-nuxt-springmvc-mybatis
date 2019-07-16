package com.report.dao.user;

import java.util.List;
import com.report.model.user.User;

public interface UserMapper {

    /**
     * 查询用户列表
     *
     * @return
     */
    List<User> queryUserList();

    User queryUserByUsername(String username);
}
