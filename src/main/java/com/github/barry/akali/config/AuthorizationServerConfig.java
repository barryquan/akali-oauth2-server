package com.github.barry.akali.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.github.barry.akali.service.security.CustomerAuthorizationCodeServices;
import com.github.barry.akali.service.security.CustomerClientDetailsService;
import com.github.barry.akali.service.security.CustomerJwtTokenStore;
import com.github.barry.akali.service.security.CustomerTokenEnhancer;
import com.github.barry.akali.service.security.CustomerTokenService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * oauth2 授权服务器配置
 * 
 * @author barry
 *
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final @NonNull AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customerClientDetailsService());
    }

    @Bean
    public ClientDetailsService customerClientDetailsService() {
        return new CustomerClientDetailsService();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).tokenServices(customerTokenServices())
                .authorizationCodeServices(authorizationCodeServices());
    }

    /**
     * 自定义授权码服务类
     * 
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new CustomerAuthorizationCodeServices();
    }

    @Bean
    public AuthorizationServerTokenServices customerTokenServices() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        return new CustomerTokenService(tokenStore(), tokenEnhancerChain, customerClientDetailsService());
    }

    /**
     * 令牌增强器
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomerTokenEnhancer();
    }

    /**
     * 令牌转换器，（非）对称密钥加密
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 非对称密钥加密
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"),
                "123456".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2"));
        return converter;
    }

    /**
     * token store 实现
     *
     * @return JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new CustomerJwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 资源服务器
     *
     * @param security security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                // 能够验证和解析 token
                .checkTokenAccess("isAuthenticated()")
                // 能够访问我们的公钥
                .tokenKeyAccess("isAuthenticated()");
    }
}
