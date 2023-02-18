package com.accendl.account.service.impl;

import com.accendl.account.entity.Authorities;
import com.accendl.account.mapper.AuthoritiesMapper;
import com.accendl.account.service.IAuthoritiesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-02-18
 */
@Service
public class AuthoritiesServiceImpl extends ServiceImpl<AuthoritiesMapper, Authorities> implements IAuthoritiesService {

    @Autowired
    private AuthoritiesMapper authoritiesMapper;

}
