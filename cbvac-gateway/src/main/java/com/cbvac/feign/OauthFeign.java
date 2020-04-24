package com.cbvac.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author: zhuqing
 * @Date: 2020-04-10-17:25
 * @Description:
 */
@FeignClient(name = "auth-server", fallbackFactory = Oauth2FeignFactory.class)
public interface OauthFeign {

    @PostMapping(value = "/oauth/token")
    Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);
}

@Component
class Oauth2FeignFactory implements FallbackFactory<OauthFeign> {

    public static final Logger LOGGER = LoggerFactory.getLogger(Oauth2FeignFactory.class);

    @Override
    public OauthFeign create(Throwable throwable) {
        LOGGER.error("服务调用失败", throwable);
        return new OauthFeign() {
            @Override
            public Map<String, Object> postAccessToken(Map<String, String> parameters) {
                return null;
            }
        };
    }
}

