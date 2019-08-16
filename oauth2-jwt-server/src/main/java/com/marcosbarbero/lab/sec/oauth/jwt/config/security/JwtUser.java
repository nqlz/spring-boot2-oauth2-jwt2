package com.marcosbarbero.lab.sec.oauth.jwt.config.security;

import com.marcosbarbero.lab.sec.oauth.jwt.domain.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 功能描述:
 *
 * @author: MR.zt
 * @date: 2019/3/20 17:48星期三
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtUser implements UserDetails {

    private Users admin;
    private Collection<? extends GrantedAuthority> authorities;

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtUser(Users user, List<GrantedAuthority> grantedAuthorities) {
        admin=user;
        authorities = grantedAuthorities;
    }

    // 获取权限信息，目前博主只会拿来存角色。。
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    // 账号是否未过期，默认是false，记得要改一下
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未锁定，默认是false，记得也要改一下
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期，默认是false，记得还要改一下
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 这个有点抽象不会翻译，默认也是false，记得改一下
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 我自己重写打印下信息看的
    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + admin.getId() +
                ", username='" + admin.getUsername() + '\'' +
                ", password='" + admin.getPassword() + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}

