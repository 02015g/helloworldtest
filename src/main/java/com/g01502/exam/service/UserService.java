package com.g01502.exam.service;

import com.g01502.exam.model.entity.User ;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author guolebin
* @description 针对表【user】的数据库操作Service
* @createDate 2025-12-30 14:01:38
*/
public interface UserService extends IService<User> {
      public User userLogin(String username, String password, HttpServletRequest request);
}
