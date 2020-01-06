package com.dimple.framework.security;

import com.dimple.project.system.domain.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @className: LoginUser
 * @description: Login user identity permission
 * @author: Dimple
 * @date: 10/22/19
 */
@Data
public class LoginUser implements UserDetails {


    /**
     * token
     */
    private String token;

    /**
     * login time
     */
    private Long loginTime;

    /**
     * expire time
     */
    private Long expireTime;

    /**
     * Login IP address
     */
    private String ip;

    /**
     * location
     */
    private String location;

    /**
     * Browser type
     */
    private String browser;

    /**
     * operating system
     */
    private String os;

    /**
     * Permission list
     */
    private Set<String> permissions;

    /**
     * User information
     */
    private SysUser user;


    public LoginUser() {
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    /**
     * Whether the account has not expired, which cannot be verified
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Specifies whether the user is unlocked. Locked users cannot authenticate
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (passwords) have expired, which prevents authentication
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Available, disabled users cannot authenticate
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
