package com.marcosbarbero.lab.sec.oauth.jwt.config.security;

import com.marcosbarbero.lab.sec.oauth.jwt.config.exception.WebResponseExceptionTranslator;
import com.marcosbarbero.lab.sec.oauth.jwt.config.props.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Configuration
@EnableAuthorizationServer

public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;

    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    JwtTokenConf jwtTokenConf;


    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore,
                                              final ClientDetailsService clientDetailsService) {
        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 复用refresh token
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.SECONDS.toSeconds(7200000));
        tokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.SECONDS.toSeconds(72000000));
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setAuthenticationManager(this.authenticationManager);
        return tokenServices;
    }


    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("webApp")
                .secret(passwordEncoder.encode("123456"))
                //授权模式
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
                //授权范围
                .scopes("webApp");
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(customTokenEnhancer,jwtTokenConf.jwtAccessTokenConverter()));

        endpoints.authenticationManager(this.authenticationManager)
                .accessTokenConverter(jwtTokenConf.jwtAccessTokenConverter())
                .userDetailsService(this.userDetailsService)
                .tokenStore(jwtTokenConf.tokenStore())
                .tokenEnhancer(enhancerChain)
                .exceptionTranslator(webResponseExceptionTranslator);

    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.passwordEncoder(this.passwordEncoder).tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

}
