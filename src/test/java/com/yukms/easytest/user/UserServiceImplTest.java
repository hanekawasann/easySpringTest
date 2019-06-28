package com.yukms.easytest.user;

import com.yukms.easytest.ServiceTestBase;
import com.yukms.easytest.test.EasyTestConfig;
import com.yukms.easytest.user.dao.UserDaoRequestAsserter;
import com.yukms.easytest.user.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * yukms 763803382@qq.com 2019/3/26 11:43
 */
public class UserServiceImplTest extends ServiceTestBase {

    @Autowired
    private IUserService userService;

    @Test
    public void test_getUser_json() {
        EasyTestConfig.setRecord(true);
        setMockDataInPackage("getUser.json", new UserDaoRequestAsserter() {
            @Override
            public User getUser(long id) {
                Assert.assertEquals(1, id);
                return null;
            }
        });

        long id = 1L;
        User user = userService.getUser(id);
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals("野兽先辈", user.getName());
        Assert.assertEquals(24, user.getAge());
    }

    @Test
    public void test_getUser_bat() {
        setMockDataInPackage("getUser.bat", new UserDaoRequestAsserter() {
            @Override
            public User getUser(long id) {
                Assert.assertEquals(1, id);
                return null;
            }
        });

        long id = 1L;
        User user = userService.getUser(id);
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals("野兽先辈", user.getName());
        Assert.assertEquals(24, user.getAge());
    }
}