package com.marcosbarbero.lab.sec.oauth.jwt.ds.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/me")
public class UserController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Principal> get(final Principal principal) {
        return ResponseEntity.ok(principal);
    }


    @GetMapping("user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Object> getSelf(@RequestHeader String Authorization) {
        String token;
        List<String> stringList = Arrays.asList("bearer ","Bearer ");
        if (Objects.nonNull(Authorization) ) {
            token = Authorization.replaceFirst(getIndex(stringList,Authorization), "");
        }else {
            return ResponseEntity.ok("没有token怎么玩");
        }
        Jwt decode = JwtHelper.decode(token);

        return ResponseEntity.ok(decode.getClaims());
    }
    private String getIndex(List<String> list,String target){
        StringBuilder res =new StringBuilder();
        list.forEach(item->{
            if (target.contains(item)){
                res .append(item);
                return ;
            }
        });
        return res.toString();
    }
}
