package com.cbvac.constant;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-16:52
 * @Description:
 */
public class PermitAllUrl {


    /**
     * 监控中心和swagger需要访问的url
     */
    public static final String[] ENDPOINTS = {
            "/v2/**",
            "/v1.0/swagger-ui.html", "/swagger-ui.html","/swagger-ui.html/**","/webjars/**",
            "/swagger-resources/**",
            "/druid/**","/rpc/**" };

    /**
     * 需要放开权限的url
     *
     * @param urls 自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS;
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }

}
