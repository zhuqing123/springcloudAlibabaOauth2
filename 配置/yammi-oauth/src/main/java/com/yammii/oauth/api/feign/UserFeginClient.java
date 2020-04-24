package com.yammii.oauth.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yammii.user.api.model.LoginAppUser;

/**
 * @author yongyan.pu
 * @version $Id: UserFeginClient.java, v 0.1 2019年1月23日 下午5:12:53 yongyan.pu Exp $
 */
@FeignClient("yammii-user")
public interface UserFeginClient {

    /**
     * 根据用户名查找
     * @param username 用户名
     * @param userType 用户类型
     * @return
     */
    @GetMapping(value = "/v1.0/rpc/findByName", params = "username")
    LoginAppUser findByUsername(@RequestParam("username") String username);

    /**
     * pos端用户 
     * 
     * @param username
     * @return
     */
    @GetMapping(value = "/v1.0/rpc/findPosUserByName", params = "username")
    LoginAppUser findPosUserByName(@RequestParam("username") String username);

    /**
     * app用戶登录
     * 
     * @param username 用户名
     * @return
     */
    @GetMapping(value = "/v1.0/rpc/findAppuserByPhone", params = "phone")
    LoginAppUser findAppuserByName(@RequestParam("phone") String phone);

    /**
     * 
     * 根据电话号码查询用户信息
     * @param phone
     * @return
     */
    @GetMapping(value = "/v1.0/rpc/findSysUserByPhone", params = "phone")
    LoginAppUser findSysUserByPhone(@RequestParam("phone") String phone);

    /**
     * 超级管理员登录
     * @param username
     * @return
     */
    @GetMapping(value = "/v1.0/rpc/findSuperadminByUsername", params = "username")
    LoginAppUser findSuperadminByUsername(@RequestParam("username") String username);

    /**
     * POS端登录
     * @param phone
     * @return
     */
    @GetMapping(value = "/v1.0/rpc/findPosUserByPhone", params = "phone")
    LoginAppUser findPosUserByPhone(@RequestParam("phone") String phone);

}
