package com.g01502.exam.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class Md5PasswordEncoder implements PasswordEncoder {
    private static final String SALT = "glb";

    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex((SALT + rawPassword).getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encoded = encode(rawPassword);
        return encoded.equals(encodedPassword);
    }
}

