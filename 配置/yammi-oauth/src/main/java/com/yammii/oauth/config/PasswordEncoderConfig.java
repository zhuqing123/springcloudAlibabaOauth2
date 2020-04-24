package com.yammii.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码校验器 
 * 
 * @author yongyan.pu
 * @version $Id: PasswordEncoderConfig.java, v 0.1 2019年1月12日 下午9:15:37 yongyan.pu Exp $
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
