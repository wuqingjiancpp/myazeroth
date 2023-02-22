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
            return new UserDTO(user.getId(), user.getPhone(), user.getEmail(), user.getUsername(),
                    user.getPassword(), user.getSecret(), user.getAnswer(), user.getEnabled());
        }
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        User user = new User();
        user.setEmail(userDTO.getUsername());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setSecret(userDTO.getSecret());
        user.setAnswer(userDTO.getAnswer());
        user.setEnabled(true);
        int count=0;
        try {
            count = userMapper.insert(user);
            logger.info("count="+count);
            userDTO.setId(user.getId());
            return userDTO;
        }catch (Exception e){
            logger.info("创建用户失败："+e.getMessage());
            throw new Exception(e);
        }finally {
            if (count == 0){
                return null;
            }
        }

    }

}
