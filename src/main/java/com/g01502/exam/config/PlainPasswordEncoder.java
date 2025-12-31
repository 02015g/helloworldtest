package com.g01502.exam.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 明文密码编码器，直接使用字符串存储和匹配密码（不加密）
 */
public class PlainPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // 直接返回原始密码，不进行加密
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // 直接比较字符串
        return rawPassword.toString().equals(encodedPassword);
    }
}

