package com.marcosbarbero.lab.sec.oauth.jwt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 *
 * @author: MR.zt
 * @date: 2019/8/16 14:45
 */
@MapperScan("com.marcosbarbero.lab.sec.oauth.jwt.repository")
@Configuration
public class ApplicationCfg {
}
