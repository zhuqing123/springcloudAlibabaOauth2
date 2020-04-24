package com.cbvac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-16:43
 * @Description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User(s,this.bCryptPasswordEncoder.encode("123456"),true,true,true,true,new HashSet<>());
    }
}
