package com.ysm.www.auth.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Description: TODO  权限实体类
 * @Author MiSinG
 * @Date 2023/7/16
 * @Version V1.0
 **/
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
