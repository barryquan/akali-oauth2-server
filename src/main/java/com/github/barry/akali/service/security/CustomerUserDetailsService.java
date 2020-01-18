package com.github.barry.akali.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;

import com.github.barry.akali.entity.User;
import com.github.barry.akali.entity.UserRole;
import com.github.barry.akali.repository.UserRepository;
import com.github.barry.akali.repository.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义用户服务实现，从数据库中获取用户信息
 * 
 * @author barry
 * @date 创建时间：2020年1月2日 下午5:57:26
 * @version 1.0
 */
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     * 查询不到角色时的默认角色
     */
    private String defaultRole = "ROLE_USER";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("进入自定义的CustomerUserDetailsService，username={}", username);
        User user = userRepository.findByUsernameAndIsActive(username, Boolean.TRUE);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户" + username + " 不存在于系统中");
        }
        Collection<GrantedAuthority> roleList = getRoleByUser(user);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), roleList);
    }

    /**
     * 获取用户的角色
     * 
     * @param user
     * @return
     */
    private Collection<GrantedAuthority> getRoleByUser(User user) {
        Collection<GrantedAuthority> roleList = new ArrayList<>();
        List<UserRole> userRoleList = userRoleRepository.findByUserIdAndIsActive(user.getId(), Boolean.TRUE);
        userRoleList.forEach(ur -> {
            if (Objects.equals(Boolean.TRUE, ur.getRole().getIsActive())) {
                roleList.add(new SimpleGrantedAuthority(ur.getRole().getRoleCode()));
            }
        });
        if (CollectionUtils.isEmpty(roleList)) {
            roleList.add(new SimpleGrantedAuthority(defaultRole));
        }
        return roleList;
    }

}
