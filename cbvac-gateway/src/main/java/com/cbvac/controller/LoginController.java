package com.cbvac.controller;

import com.cbvac.enums.ResultEnum;
import com.cbvac.feign.OauthFeign;
import com.cbvac.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuqing
 * @Date: 2020-04-10-17:21
 * @Description:
 */
@RestController
public class LoginController {

    @Autowired
    private OauthFeign oauthFeign;


    @GetMapping(value = "/login")
    public ResultVo login(@RequestParam String userName, @RequestParam String password) {
        Map<String, String> parameters = new HashMap<String, String>() {
            {
                put("username", userName);
                put("password", password);
                put("grant_type", "password");
                put("scope", "pos");
                put("client_id", "pos");
                put("client_secret", "pos");
            }
        };

        Map<String, Object> o = this.oauthFeign.postAccessToken(parameters);
        return new ResultVo(ResultEnum.SUCCESS, o);
    }
}
