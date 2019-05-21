package com.yukms.easytest.user;

import com.yukms.easytest.user.dao.IUserDao;
import com.yukms.easytest.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * yukms 763803382@qq.com 2019/3/26 11:20
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void update(User user) {

    }

}
