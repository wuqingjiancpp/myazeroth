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

import java.io.IOException;

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
        if (user == null){
            logger.error("用户不存在");
            return null;
        }else{
            logger.info(user.toString());
            return UserDTO.builder().id(user.getId())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .secret(user.getSecret())
                    .answer(user.getAnswer())
                    .enabled(user.getEnabled())
                    .build();
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user = new User(null, userDTO.getUsername(), userDTO.getUsername(), userDTO.getPassword(),
                userDTO.getSecret(), userDTO.getAnswer());
        int count=0;
        try {
            count = userMapper.insert(user);
            logger.info("count="+count);
            if(count == 0){
                return null;
            }else{
                return UserDTO.builder().id(user.getId()).build();
            }
        }catch (Exception e){
            logger.info("创建用户失败："+e.getMessage());
            throw new Exception(e);
        }

    }

    @Override
    public boolean updatePassword(String username, String password) throws Exception {
        User user = new User();
        user.setPassword(password);
        User where = new User();
        where.setUsername(username);
        Wrapper<User> wrapper = new QueryWrapper<>(where);
        try {
            int count = userMapper.update(user, wrapper);
            if (count>0){
                logger.info("count="+count);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception(e);
        }

    }

}
