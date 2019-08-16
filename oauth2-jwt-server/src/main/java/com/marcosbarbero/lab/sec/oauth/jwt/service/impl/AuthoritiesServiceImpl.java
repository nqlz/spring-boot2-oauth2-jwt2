package com.marcosbarbero.lab.sec.oauth.jwt.service.impl;

import com.marcosbarbero.lab.sec.oauth.jwt.domain.Authorities;
import com.marcosbarbero.lab.sec.oauth.jwt.repository.AuthoritiesDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marcosbarbero.lab.sec.oauth.jwt.service.AuthoritiesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MR.zt
 * @since 2019-08-16
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesDao, Authorities> implements AuthoritiesService {

}
