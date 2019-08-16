package com.marcosbarbero.lab.sec.oauth.jwt.service.impl;

import com.marcosbarbero.lab.sec.oauth.jwt.domain.Users;
import com.marcosbarbero.lab.sec.oauth.jwt.repository.UsersDao;
import com.marcosbarbero.lab.sec.oauth.jwt.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

}
