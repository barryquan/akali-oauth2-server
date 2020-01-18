package com.github.barry.akali.service.security;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.transaction.annotation.Transactional;

import com.github.barry.akali.entity.OauthCode;
import com.github.barry.akali.repository.OauthCodeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义授权码服务类<br>
 * 只有授权模式response_type=code或token时才会使用到
 * 
 * @author barry
 *
 */
@Slf4j
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class CustomerAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

//    protected final ConcurrentHashMap<String, OAuth2Authentication> authorizationCodeStore = new ConcurrentHashMap<String, OAuth2Authentication>();

    @Autowired
    private OauthCodeRepository oauthCodeRepository;

    @Override
    public void store(String code, OAuth2Authentication authentication) {
        log.debug("本次生成的验证码为={}", code);
//        this.authorizationCodeStore.put(code, authentication);
        OauthCode oauthCode = new OauthCode();
        oauthCode.setCode(code);
        oauthCode.setAuthentication(SerializationUtils.serialize(authentication));
        oauthCode.setModifiedDate(LocalDateTime.now());
        oauthCodeRepository.save(oauthCode);
    }

    /**
     * 自定义删除方式，可以删除，也是增加自己的授权码使用次数
     */
    @Override
    public OAuth2Authentication remove(String code) {
        log.debug("本次需要删除的验证码为={}", code);
//        OAuth2Authentication auth = this.authorizationCodeStore.remove(code);
        OauthCode oauthCode = oauthCodeRepository.findByCode(code);
        if (Objects.isNull(oauthCode) || Objects.equals(Boolean.FALSE, oauthCode.getIsActive())) {
            return null;
        }
        oauthCodeRepository.deleteById(oauthCode.getId());
        OAuth2Authentication auth = SerializationUtils.deserialize(oauthCode.getAuthentication());
        return auth;
    }

}
