package com.github.barry.akali.service.security;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import com.github.barry.akali.entity.ClientGrantTypes;
import com.github.barry.akali.entity.ClientResources;
import com.github.barry.akali.entity.CustomerResource;
import com.github.barry.akali.entity.GrantTypes;
import com.github.barry.akali.entity.OauthClient;
import com.github.barry.akali.repository.ClientGrantTypesRepository;
import com.github.barry.akali.repository.ClientResourcesRepository;
import com.github.barry.akali.repository.OauthClientRepository;
import com.github.barry.akali.utils.ClientDetailsCacheUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义的ClientDetailsService<br>
 * 用于根据clientId从数据库中加载对应的client的信息
 * 
 * @author barry
 * @date 创建时间：2020年1月3日 上午11:45:06
 * @version 1.0
 */
@Slf4j
public class CustomerClientDetailsService implements ClientDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientResourcesRepository clientResourcesRepository;

    @Autowired
    private ClientGrantTypesRepository clientGrantTypesRepository;

    @Autowired
    private OauthClientRepository oauthClientRepository;

    private static final String DEFAULT_SCOPE = "all";

    /**
     * 自定义获取client方法 <br>
     * 增加clientId的缓存功能，减少与数据库的交互
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.debug("进入自定义的ClientDetailsService，clientId={}", clientId);
        ClientDetails cacheClientDetails = ClientDetailsCacheUtils.getBykey(clientId);
        if (Objects.nonNull(cacheClientDetails)) {
            return cacheClientDetails;
        }
        OauthClient oauthClient = oauthClientRepository.findByClientIdAndIsActive(clientId, Boolean.TRUE);

        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientSecret(passwordEncoder.encode(oauthClient.getClientSecret()));
        Set<String> resourceSet = getResourcesByClientId(clientId);
        Set<String> grantTypesSet = getGrantTypesSetByClientId(clientId);
        clientDetails.setResourceIds(resourceSet); // 可访问的资源
        clientDetails.setAuthorizedGrantTypes(grantTypesSet); // 支持验证的类型
        clientDetails.setClientId(clientId);
        clientDetails.setScope(Arrays.asList(DEFAULT_SCOPE));
        clientDetails.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValidity());
        clientDetails.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValidity());
        ClientDetailsCacheUtils.saveClientDetailsCache(clientId, clientDetails);
        clientDetails.setRegisteredRedirectUri(getRedirectUri(oauthClient));
        return clientDetails;
    }

    /**
     * 转换重定向地址
     * 
     * @param oauthClient
     * @return
     */
    private Set<String> getRedirectUri(OauthClient oauthClient) {
        log.debug("进入自定义的ClientDetailsService.getRedirectUri，clientId={}，重定向地址为={}", oauthClient.getClientId(),
                oauthClient.getRedirectUri());
        if (!StringUtils.hasText(oauthClient.getRedirectUri())) {
            return null;
        }

        return Arrays.asList(oauthClient.getRedirectUri().split(",")).stream().collect(Collectors.toSet());
    }

    /**
     * 通过clientId获取支持的验证类型
     * 
     * @param clientId
     * @return
     */
    private Set<String> getGrantTypesSetByClientId(String clientId) {
        log.debug("进入自定义的ClientDetailsService.getGrantTypesSetByClientId，clientId={}", clientId);
        List<ClientGrantTypes> clientGrantTypesList = clientGrantTypesRepository.findByOauthClientClientId(clientId);
        Set<String> grantTypeSet = clientGrantTypesList.stream()//
                .map(ClientGrantTypes::getGrantTypes)//
                .filter(Objects::nonNull)//
                .filter(GrantTypes::getIsActive)//
                .map(GrantTypes::getGrantTypeCode)//
                .filter(StringUtils::hasText) //
                .distinct() //
                .collect(Collectors.toSet());
        log.debug("数据库查询出来的客户端支持的授权类型有={}", grantTypeSet);
        return grantTypeSet;
    }

    /**
     * 通过clientId获取对应可以访问的资源
     * 
     * @param clientId
     * @return
     */
    private Set<String> getResourcesByClientId(String clientId) {
        log.debug("进入自定义的ClientDetailsService.getResourcesByClientId，clientId={}", clientId);
        List<ClientResources> clientResourcesList = clientResourcesRepository.findByOauthClientClientId(clientId);
        Set<String> resourcesSet = clientResourcesList.stream()//
                .map(ClientResources::getCustomerResource)//
                .filter(Objects::nonNull)//
                .filter(CustomerResource::getIsActive)//
                .map(CustomerResource::getResourceCode)//
                .filter(StringUtils::hasText) //
                .distinct() //
                .collect(Collectors.toSet());
        log.debug("数据库查询出来的客户端支持的资源有={}", resourcesSet);
        return resourcesSet;
    }

}
