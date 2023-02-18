package com.accendl.account.service;

import com.accendl.account.dto.CustomUser;
import com.accendl.account.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-02-18
 */
public interface IUsersService extends IService<Users> {
    boolean getByName(String username);

    CustomUser findCustomUserByEmail(String email) throws Exception;
}
