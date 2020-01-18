package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 客户端支持的授权模式
 * 
 * @author barry
 *
 */
@Data
@Entity
@Table(name = "oauth_client_grant_types")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ClientGrantTypes extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户端
     */
    @OneToOne
    private OauthClient oauthClient;

    /**
     * 授权模式
     */
    @OneToOne
    private GrantTypes grantTypes;

}
