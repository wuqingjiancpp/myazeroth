package com.accendl.account.service.impl;

import com.accendl.account.dto.CustomUser;
import com.accendl.account.entity.Users;
import com.accendl.account.mapper.UsersMapper;
import com.accendl.account.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    public boolean getByName(String username) {
        Users users = new Users();
        users.setUsername(username);
        Wrapper<Users> usersWrapper = new QueryWrapper<>();
        users = usersMapper.selectOne(usersWrapper);
        System.out.println(users);
        return true;
    }

    @Override
    public CustomUser findCustomUserByEmail(String email) throws Exception {
        Users tmp = new Users();
        tmp.setUsername(email);
        Wrapper<Users> usersWrapper = new QueryWrapper<>(tmp);
        Users users = usersMapper.selectOne(usersWrapper);
        if (users == null){
            return null;
        }else{
            return new CustomUser(2, users.getUsername(), users.getPassword());
        }
    }


}
