package com.accendl.account.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqingjian
 * @since 2023-02-18
 */
public interface IUserService extends IService<User> {
    UserDTO findCustomUserByEmail(String email) throws Exception;
}
