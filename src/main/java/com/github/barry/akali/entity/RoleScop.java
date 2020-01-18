package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色访问范围控制
 * 
 * @author quansr
 *
 */
@Data
@Entity
@Table(name = "customer_role_scop")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class RoleScop extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * 角色
     */
    @OneToOne
    private Role role;

    /**
     * 访问权限
     */
    @OneToOne
    private Scop scop;

}
