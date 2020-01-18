package com.github.barry.akali.service.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.github.barry.akali.entity.RoleScop;
import com.github.barry.akali.entity.Scop;
import com.github.barry.akali.repository.RoleScopRepository;
import com.github.barry.akali.utils.ClientDetailsCacheUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义的token生成<br>
 * 可以自定义生成token或者调用父类生成方法前修改相关的信息
 * 
 * @author barry
 * @date 创建时间：2020年1月3日 下午5:39:45
 * @version 1.0
 */
@Slf4j
public class CustomerTokenService extends DefaultTokenServices {

    @Autowired
    private RoleScopRepository roleScopRepository;

    public CustomerTokenService(TokenStore tokenStore, TokenEnhancer accessTokenEnhancer,
            ClientDetailsService clientDetailsService) {
        super.setTokenStore(tokenStore); // token存储
        super.setTokenEnhancer(accessTokenEnhancer); // token生成包装
        super.setClientDetailsService(clientDetailsService); // 客户端服务
        super.setSupportRefreshToken(true);
    }

    /**
     * 重写Token的生成，根据用户的真实角色获取对应的scope<br>
     * 增加去除clientId的缓存功能
     */
    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        log.debug("进入自定义的Token创建,创建的用户名为={}", authentication.getName());
        Set<String> scopeSet = findScopeByRoleList(authentication.getAuthorities());
        OAuth2AccessToken token;
        if (!CollectionUtils.isEmpty(scopeSet)) {
            OAuth2Request newOauth2Request = authentication.getOAuth2Request().narrowScope(scopeSet);
            OAuth2Authentication newOAuth2Authentication = new OAuth2Authentication(newOauth2Request, authentication);
            token = super.createAccessToken(newOAuth2Authentication);
        } else {
            token = super.createAccessToken(authentication);
        }

        ClientDetailsCacheUtils.clearByKey(authentication.getOAuth2Request().getClientId());
        return token;
    }

    /**
     * 根据用户角色获取对应的操作权限<br>
     * 
     * @param authorities
     * @return
     */
    private Set<String> findScopeByRoleList(Collection<GrantedAuthority> authorities) {
        List<RoleScop> roleScopList = roleScopRepository.findByRoleRoleCodeInAndIsActive(
                authorities.stream().map(GrantedAuthority::getAuthority).distinct().collect(Collectors.toList()),
                Boolean.TRUE);
        Set<String> scopeSet = roleScopList.stream()//
                .map(RoleScop::getScop)//
                .map(Scop::getScopPerms)//
                .filter(StringUtils::hasText) //
                .distinct() //
                .collect(Collectors.toSet());
        log.debug("根据角色获取到的访问权限为={}", scopeSet);
        return scopeSet;
    }

    /**
     * 重写刷新token的方法，加入clientId的清除
     */
    @Override
    @Transactional(noRollbackFor = { InvalidTokenException.class, InvalidGrantException.class })
    public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest)
            throws AuthenticationException {
        log.debug("进行自定义的刷新的token的方法CustomerTokenService.refreshAccessToken");
        OAuth2AccessToken token = super.refreshAccessToken(refreshTokenValue, tokenRequest);
        ClientDetailsCacheUtils.clearByKey(tokenRequest.getClientId());
        return token;
    }
}
