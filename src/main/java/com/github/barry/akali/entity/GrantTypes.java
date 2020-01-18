package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * oauth2的所有授权类型
 * 
 * @author quansr
 *
 */
@Data
@Entity
@Table(name = "grant_types")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class GrantTypes extends BaseEntity {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    /**
     * 类型名称，如：密码模式
     */
    private String grantTypeName;

    /**
     * 类型编码，如：password
     */
    private String grantTypeCode;

}
