package com.yukms.easytest.user.dao.impl;

import java.util.Random;

import com.yukms.easytest.user.dao.IUserDao;
import com.yukms.easytest.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * yukms 763803382@qq.com 2019/3/26 14:24
 */
@Repository
public class UserDaoImpl implements IUserDao {
    @Override
    public User getUser(long id) {
        User user = new User();
        user.setId(new Random().nextLong());
        user.setName("yukms");
        user.setAge(new Random().nextInt());
        return user;
    }
}
