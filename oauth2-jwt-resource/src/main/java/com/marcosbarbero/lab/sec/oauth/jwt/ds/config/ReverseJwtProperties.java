package com.marcosbarbero.lab.sec.oauth.jwt.ds.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * @author Administrator
 */
@ConfigurationProperties("security")
public class ReverseJwtProperties {

    @Setter
    @Getter
    private JwtProperties jwt;

    @Data
    public static class JwtProperties {

        private Resource publicKey;
    }

}
