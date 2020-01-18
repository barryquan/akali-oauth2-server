package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色表
 * 
 * @author barry
 *
 */
@Data
@Entity
@Table(name = "oauth_role")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Role extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称，如：系统管理员
     */
    private String roleName;

    /**
     * 角色代码，如：ROLE_ADMIN<br>
     * 
     */
    private String roleCode;

}
