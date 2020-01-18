package com.github.barry.akali.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 支持的客户端
 * 
 * @author barry
 *
 */
@Data
@Entity
@Table(name = "oauth_client")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class OauthClient extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户端唯一标识
     */
    @Column(name = "client_id", unique = true)
    private String clientId;

    /**
     * 客户端凭证，数据库存储的为未加密过的字符串
     */
    private String clientSecret;

    /**
     * 重定向的url，多个可以使用英文逗号隔开
     */
    private String redirectUri;

    /**
     * accessToken的过期时间，单位为：秒
     */
    private Integer accessTokenValidity;

    /**
     * refreshToken的过期时间，单位为：秒
     */
    private Integer refreshTokenValidity;
}
