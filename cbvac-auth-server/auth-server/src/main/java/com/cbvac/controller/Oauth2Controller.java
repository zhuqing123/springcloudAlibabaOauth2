package com.cbvac.controller;

import com.cbvac.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-10-16:02
 * @Description:
 */
@Api(value = "oauth2接口（退出，得到当前登录用户）")
@RestController
public class Oauth2Controller {

    public static final Logger LOGGER = LoggerFactory.getLogger(Oauth2Controller.class);

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "服务类部得到当前登录人")
    @GetMapping("/user")
    public Principal getCurrentUser(Principal user) {
        return user;
    }

    @ApiOperation(value = "登出")
    @DeleteMapping("/logout")
    public ResultVo logout(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNoneBlank(authorization) && StringUtils.startsWith(authorization, OAuth2AccessToken.BEARER_TYPE)) {
            authorization = StringUtils.remove(authorization, OAuth2AccessToken.BEARER_TYPE).trim();
            if (this.consumerTokenServices.revokeToken(authorization)) {
                return ResultVo.success();
            }
        }
        return ResultVo.fail();
    }
}
