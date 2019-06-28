package com.yukms.easytest.user;

import javax.annotation.Resource;

import com.yukms.easytest.user.dao.IUserDao;
import com.yukms.easytest.user.entity.User;
import org.springframework.stereotype.Service;

/**
 * yukms 763803382@qq.com 2019/3/26 11:20
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }
}
