package com.github.barry.akali.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * 缓存当前的clientId查询出来的信息<br>
 * 避免多次进行数据库的查询
 * 
 * @author barry
 * @date 创建时间：2020年1月9日 上午9:11:46
 * @version 1.0
 */
@Slf4j
public abstract class ClientDetailsCacheUtils {

    private static final Map<String, ClientDetails> cacheMap = new HashMap<>();

    /**
     * 保存ClientDetails
     * 
     * @param clientId
     * @param clientDetails
     */
    public static void saveClientDetailsCache(String clientId, ClientDetails clientDetails) {
        cacheMap.put(clientId, clientDetails);
        log.debug("增加clientId的缓存，clientId={},当前缓存大小为={}", clientId, cacheMap.size());
    }

    /**
     * 获取ClientDetails
     * 
     * @param clientId
     * @return
     */
    public static ClientDetails getBykey(String clientId) {
        log.debug("获取clientId的缓存，clientId={}", clientId);
        return cacheMap.get(clientId);
    }

    /**
     * 清除ClientDetails
     * 
     * @param clientId
     */
    public static void clearByKey(String clientId) {
        cacheMap.remove(clientId);
        log.debug("清除clientId的缓存，clientId={},清除后缓存的大小为={}", cacheMap.size());
    }
}
