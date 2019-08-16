package com.marcosbarbero.lab.sec.oauth.jwt.config.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marcosbarbero.lab.sec.oauth.jwt.domain.Authorities;
import com.marcosbarbero.lab.sec.oauth.jwt.domain.Users;
import com.marcosbarbero.lab.sec.oauth.jwt.service.AuthoritiesService;
import com.marcosbarbero.lab.sec.oauth.jwt.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述:
 *
 * @author: MR.zt
 * @date: 2019/3/20 17:45星期三
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Users::getUsername, username);
        Users tbUser = usersService.getOne(queryWrapper);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (tbUser != null) {
            LambdaQueryWrapper<Authorities> eq = new QueryWrapper<Authorities>().lambda().eq(Authorities::getUsername, username);
            List<Map<String, Object>> tbPermissions = authoritiesService.listMaps(eq);
            tbPermissions.forEach(tbPermission -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.get("authority") + "");
                grantedAuthorities.add(grantedAuthority);
            });
        }
        JwtUser jwtUser = new JwtUser(tbUser, grantedAuthorities.stream().collect(Collectors.toSet()));
        log.info("jwtUser:{}", jwtUser);
        return jwtUser;
    }

}
