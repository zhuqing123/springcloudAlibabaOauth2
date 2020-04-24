package com.yammii.oauth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yammii.common.enums.CredentialType;
import com.yammii.common.enums.UserTypeEnum;
import com.yammii.oauth.api.feign.UserFeginClient;
import com.yammii.user.api.model.LoginAppUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yongyan.pu
 * @version $Id: UserDetailServiceImpl.java, v 0.1 2019年1月12日 下午9:09:51 yongyan.pu Exp $
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeginClient       userFeignClient;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        String[] params = username.split("\\|");
        username = params[0];
        CredentialType type = null;
        if (params.length > 1) {
            type = CredentialType.valueOf(params[1]);
        }
        Integer userType = Integer.parseInt(params[2]);
        LoginAppUser loginAppUser = null;
        if (type == null || CredentialType.USERNAME == type) {

            if (userType == UserTypeEnum.POS.getCode()) {
                loginAppUser = userFeignClient.findPosUserByName(username);
            }

            if (userType == UserTypeEnum.BACKEND.getCode()) {
                loginAppUser = userFeignClient.findByUsername(username);
            }

            if (userType == UserTypeEnum.SUPERADMIN.getCode()) {
                loginAppUser = userFeignClient.findSuperadminByUsername(username);
            }

        } else {
            if (params.length > 1 && CredentialType.PHONE == type) {

                if (userType == UserTypeEnum.APP.getCode()) {
                    loginAppUser = userFeignClient.findAppuserByName(username);
                } else if (userType == UserTypeEnum.POS.getCode()) {
                    loginAppUser = userFeignClient.findPosUserByPhone(username);
                } else {
                    loginAppUser = userFeignClient.findSysUserByPhone(username);
                }
                checkUser(loginAppUser);
                loginAppUser.setPassword(passwordEncoder.encode(username));
            }
            if (params.length > 1 && CredentialType.QRCODE == type) {
                loginAppUser = userFeignClient.findAppuserByName(username);
                String encodePassWord = passwordEncoder.encode(username);
                loginAppUser.setPassword(encodePassWord);
            }
        }
        checkUser(loginAppUser);
        if (!loginAppUser.getEnabled()) {
            log.error("用戶已禁用");
            throw new DisabledException("user is disabled");
        }
        return loginAppUser;
    }

    public void checkUser(LoginAppUser loginAppUser) {
        if (loginAppUser == null) {
            log.error("用户不存在");
            throw new AuthenticationCredentialsNotFoundException("user not found");
        }

        if (loginAppUser.getId() == null) {
            log.error("用户不存在");
            throw new AuthenticationCredentialsNotFoundException("user not found");
        }
    }
}
