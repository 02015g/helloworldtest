package com.g01502.exam.controller;

import com.g01502.exam.common.BaseResponse;
import com.g01502.exam.common.ResultUtils;
import com.g01502.exam.model.entity.User;
import com.g01502.exam.exception.ErrorCode;
import com.g01502.exam.exception.ThrowUtils;
import com.g01502.exam.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Resource
    private UserService userService;
    
    @Resource
    private AuthenticationManager authenticationManager;
    
    @GetMapping("/hw")
    public String hw() {
        return "Hello World";
    }
    
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody User user, HttpServletRequest request) {
        ThrowUtils.throwIf(user==null, ErrorCode.PARAMS_ERROR);
        // 使用 Spring Security 进行认证
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // 通过用户名密码登录（可以使用用户名 test，密码 123456）
        String userName = user.getUsername();
        String userPassword = user.getPassword();
        User userLogin = userService.userLogin(userName, userPassword, request);
        return ResultUtils.success(userLogin);
    }
}
