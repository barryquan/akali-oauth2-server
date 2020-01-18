package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 登录用户实体
 * 
 * @author barry
 * @date 创建时间：2020年1月2日 下午5:58:32
 * @version 1.0
 */
@Data
@Entity
@Table(name = "oauth_user")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class User extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -3890269175519570042L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
