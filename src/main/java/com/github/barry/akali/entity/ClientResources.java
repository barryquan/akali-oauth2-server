package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "oauth_client_resources")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ClientResources extends BaseEntity {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * 客户资源
     */
    @OneToOne
    private CustomerResource customerResource;

    /**
     * 客户端
     */
    @OneToOne
    private OauthClient oauthClient;

}
