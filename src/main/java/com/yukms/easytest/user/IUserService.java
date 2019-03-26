package com.yukms.easytest.user;

import com.yukms.easytest.user.entity.User;

/**
 * yukms 763803382@qq.com 2019/3/26 10:29
 */
public interface IUserService {

     User getUser(long id);

     void update(User user);

}
