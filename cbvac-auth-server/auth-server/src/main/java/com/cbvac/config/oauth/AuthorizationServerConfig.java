package com.cbvac.config.oauth;

import com.cbvac.config.RandomAuthenticationKeyGenerator;
import com.cbvac.service.impl.RedisAuthorizationCodeServices;
import com.cbvac.service.impl.RedisClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-16:32
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证管理器
     *
     * @see SecurityConfig 的authenticationManagerBean()
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * 使用jwt或者redis  默认redis
     */
    @Value("${access_token.store-jwt:false}")
    private boolean storeWithJwt;
    /**
     * 登陆后返回的json数据是否追加当前用户信息 默认true<br>
     */
    @Value("${access_token.add-userinfo:true}")
    private boolean addUserInfo;
    @Autowired
    private RedisAuthorizationCodeServices redisAuthorizationCodeServices;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;

    @SuppressWarnings("rawtypes")
    @Autowired
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;

    /**
     * 令牌存储
     */
    @Bean
    public TokenStore tokenStore() {
        if (storeWithJwt) {
            return new JwtTokenStore(accessTokenConverter());
        }
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());
        return redisTokenStore;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
        // 授权码模式下，code存储
        //		endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
        endpoints.authorizationCodeServices(redisAuthorizationCodeServices);
        if (storeWithJwt) {
            endpoints.accessTokenConverter(accessTokenConverter());
        } else {
            endpoints.tokenEnhancer((accessToken, authentication) -> {
                addLoginUserInfo(accessToken, authentication);
                return accessToken;
            });
        }
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
        endpoints.reuseRefreshTokens(false);
    }

    /**
     * 将当前用户信息追加到登陆后返回的json数据里
     *
     * @param accessToken
     * @param authentication
     */
    private void addLoginUserInfo(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (!addUserInfo) {
            return;
        }
        if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            Authentication userAuthentication = authentication.getUserAuthentication();
            Object principal = userAuthentication.getPrincipal();
            if (principal instanceof User) {
                User loginUser = (User) principal;
                // 旧的附加参数
                Map<String, Object> map = new HashMap<>(defaultOAuth2AccessToken.getAdditionalInformation());
                map.put("userInfo", loginUser);
                defaultOAuth2AccessToken.setAdditionalInformation(map);
            }
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        //开放oauth2的授权端点
        //允许表单认证
        //        security.allowFormAuthenticationForClients();
        //允许check_token访问
        security.checkTokenAccess("permitAll()");

    }

    /**
     * 我们将client信息存储到oauth_client_details表里<br>
     * 并将数据缓存到redis
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(redisClientDetailsService);
        redisClientDetailsService.loadAllClientToCache();
    }

    @Autowired
    public UserDetailsService userDetailsService;

    /**
     * jwt签名key，可随意指定<br>
     * 如配置文件里不设置的话，冒号后面的是默认值
     */
    @Value("${access_token.jwt-signing-key:yammii123!#}")
    private String signingKey;

    /**
     * Jwt资源令牌转换器<br>
     * 参数access_token.store-jwt为true时用到
     *
     * @return accessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                OAuth2AccessToken oAuth2AccessToken = super.enhance(accessToken, authentication);
                addLoginUserInfo(oAuth2AccessToken, authentication);
                return oAuth2AccessToken;
            }
        };
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) jwtAccessTokenConverter
                .getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailsService);

        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);

        jwtAccessTokenConverter.setSigningKey(signingKey);

        return jwtAccessTokenConverter;
    }
}
