package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 访问范围，类似于菜单权限
 * 
 * @author barry
 *
 */
@Data
@Entity
@Table(name = "customer_scop")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Scop extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 访问名称，如：用户新增
     */
    private String scopName;

    /**
     * 权限编码，如：user.update
     */
    private String scopPerms;

}
