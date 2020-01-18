package com.github.barry.akali.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.barry.akali.entity.ClientResources;

/**
 * 数据库操作Repository
 * 
 * @author barry
 * @date 创建时间：2020年1月2日 下午6:03:25
 * @version 1.0
 */
@Repository
public interface ClientResourcesRepository extends JpaRepository<ClientResources, Integer> {

    List<ClientResources> findByOauthClientClientId(String clientId);

}
