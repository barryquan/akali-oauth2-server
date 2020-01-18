package com.github.barry.akali.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.barry.akali.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 自定义的可访问的资源
 * 
 * @author barry
 *
 */
@Data
@Entity
@Table(name = "customer_resources")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CustomerResource extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资源服务名称，如：用户服务
     */
    private String resourceName;

    /**
     * 资源服务代码，如：user-service<br>
     * 在spring boot项目中，对应于配置项：spring.application.name
     */
    private String resourceCode;
}
