package com.github.barry.akali.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 授权码自定义保存
 * 
 * @author barry
 * @date 创建时间：2020年1月2日 下午5:58:32
 * @version 1.0
 */
@Data
@Entity
@Table(name = "oauth_code")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class OauthCode extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3890269175519570042L;

    /**
     * 随机生成的授权码
     */
    @Column(name = "code")
    private String code;

    /**
     * 授权实体，对应的类为OAuth2Authentication.class
     */
    @Lob
    @Column(name = "authentication")
    private byte[] authentication;

}
