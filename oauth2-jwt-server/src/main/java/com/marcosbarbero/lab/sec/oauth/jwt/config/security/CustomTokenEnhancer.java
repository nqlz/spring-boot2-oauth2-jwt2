package com.marcosbarbero.lab.sec.oauth.jwt.config.security;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marcosbarbero.lab.sec.oauth.jwt.domain.Users;
import com.marcosbarbero.lab.sec.oauth.jwt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @author: MR.zt
 * @date: 2019/8/16 16:30
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    UsersService usersService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        JSONObject user = (JSONObject) JSONObject.toJSON(authentication.getPrincipal());;
        Map<String, Object> additionalInfo = new HashMap<>();
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").lambda().eq(Users::getUsername, user.get("username"));
        Map userBaseInfo = usersService.getMap(queryWrapper);
        additionalInfo.put("userBase", userBaseInfo);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
