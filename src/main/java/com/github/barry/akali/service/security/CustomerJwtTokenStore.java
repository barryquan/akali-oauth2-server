package com.github.barry.akali.service.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义TokenStore<br>
 * 可以实现token的存储等相关功能<br>
 * 如果使用，主要使用情形为刷新token的情况
 * 
 * @author barry
 * @date 创建时间：2020年1月7日 下午3:02:46
 * @version 1.0
 */
@Slf4j
public class CustomerJwtTokenStore extends JwtTokenStore {

    public CustomerJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        super(jwtTokenEnhancer);
    }

    /**
     * 涉及保存token的所有模式都会触发
     */
    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        log.debug("进入自定义的token保存");
    }

    /**
     * 首次生成会调用进行保存刷新的token<br>
     * 如果是刷新模(refresh_token)式，不会触发这个方法
     */
    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        log.debug("进入自定义的刷新token保存");
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        log.debug("进入自定义的刷新token移除");
    }
}
