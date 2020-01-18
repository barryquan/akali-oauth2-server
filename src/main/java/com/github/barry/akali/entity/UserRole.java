package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 中间表，管理用户和角色
 * 
 * @author barry
 *
 */
@Data
@Entity
@Table(name = "oauth_user_role")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class UserRole extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户资源
     */
    @OneToOne
    private User user;

    /**
     * 客户端
     */
    @OneToOne
    private Role role;

}
