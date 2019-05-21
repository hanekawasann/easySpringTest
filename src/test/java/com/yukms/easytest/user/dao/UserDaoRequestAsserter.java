package com.yukms.easytest.user.dao;

import com.yukms.easytest.user.entity.User;

/**
 * yukms 763803382@qq.com 2019/3/26 14:26
 */
public abstract class UserDaoRequestAsserter implements IUserDao {
    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public void update(User user) {

    }
}
