package com.g01502.exam.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.g01502.exam.exception.BusinessException;
import com.g01502.exam.exception.ErrorCode;
import com.g01502.exam.model.entity.User;
import com.g01502.exam.service.UserService;
import com.g01502.exam.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author guolebin
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-12-30 14:01:38
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User userLogin(String userName, String userPassword, HttpServletRequest request) {
        if (StrUtil.hasBlank(userName, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        String encryptPassword = getEncryptPassword(userPassword);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        queryWrapper.eq("password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        request.getSession().setAttribute("userlogin", user);
        return user;
    }
    public String getEncryptPassword(String userPassword) {
        // 盐值，混淆密码
        final String SALT = "glb";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }
}




