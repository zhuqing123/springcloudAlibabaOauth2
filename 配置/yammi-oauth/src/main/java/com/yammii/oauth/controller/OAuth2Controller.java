package com.yammii.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yammii.common.exception.BusinessException;
import com.yammii.common.user.AppUserUtil;
import com.yammii.common.utils.RestResult;
import com.yammii.user.api.model.LoginAppUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 获取当前用户 退出
 * 
 * @author yongyan.pu
 * @version $Id: OAuth2Controller.java, v 0.1 2019年1月12日 下午9:13:16 yongyan.pu Exp $
 */
@Slf4j
@RestController
@Api(value = "获取当前用户", tags = "获取当前用户")
public class OAuth2Controller {

    @Autowired
    private ConsumerTokenServices tokenServices;

    @ApiOperation("获取当前登录用户")
    @GetMapping("/user-me")
    public Authentication principal() {
        //        log.info("获取当前用户");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("user-me:{}", authentication.getName());
        return authentication;
    }

    @ApiOperation("获取当前登录用户")
    @GetMapping(value = "/currentUser", params = "access_token")
    public LoginAppUser currentUser(String access_token) {
        LoginAppUser loginAppUser = AppUserUtil.getCurrentUser();
        log.info("获取当前登录用户,{}", JSON.toJSONString(loginAppUser));
        return loginAppUser;
    }

    @ApiOperation("注销")
    @DeleteMapping(value = "/remove_token", params = "access_token")
    public RestResult<Object> removeToken(String access_token) {
        LoginAppUser loginAppUser = AppUserUtil.getCurrentUser();
        log.info("loginAppUser:{},正在退出登录,{}", loginAppUser.getUsername(), access_token);
        boolean flag = tokenServices.revokeToken(access_token);
        if (flag) {
            log.info("{}，退出登录成功:access_token:,{}", loginAppUser.getUsername(), access_token);
            return new RestResult<>().success(null);
        } else {
            log.info("{},正在退出登录失败:{}", loginAppUser.getUsername(), access_token);
            throw new BusinessException(-1, "remove token failure");
        }
    }
}
