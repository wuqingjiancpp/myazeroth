package com.accendl.account.service.impl;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.entity.User;
import com.accendl.account.mapper.UserMapper;
import com.accendl.account.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO findCustomUserByEmail(String email) throws Exception {
        User tmp = new User();
        tmp.setUsername(email);
        Wrapper<User> usersWrapper = new QueryWrapper<>(tmp);
        User user = userMapper.selectOne(usersWrapper);
        logger.info(user.toString());
        if (user == null){
            throw new RuntimeException("用户不存在");
        }else{
            return new UserDTO(user.getId(), user.getPhone(), user.getEmail(), user.getUsername(),
                    user.getPassword(), user.getSecret(), user.getAnswer(), user.getEnabled());
        }
    }

}
