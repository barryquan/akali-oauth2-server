package com.github.barry.akali.service.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义令牌增强器，可以在token中增加自定义信息
 * 
 * @author barry
 *
 */
@Slf4j
public class CustomerTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        log.debug("进入自定义token配置");
        // 创建一个自定义信息
        // Map<String, Object> additionalInfo = new HashMap<>(1);
        // 设置值
        // additionalInfo.put("organization", authentication.getName());
        // 存进去
        // ((DefaultOAuth2AccessToken)
        // accessToken).setAdditionalInformation(additionalInfo);
        // 返回
        return accessToken;
    }
}
