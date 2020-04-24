package com.yammii.oauth.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启session共享
 * @author yongyan.pu
 * @version $Id: SessionConfig.java, v 0.1 2019年1月12日 下午9:16:16 yongyan.pu Exp $
 */
@EnableRedisHttpSession
public class SessionConfig {

}
