package com.yukms.easytest.user;

import com.yukms.easytest.ServiceTestBase;
import com.yukms.easytest.test.mock.FileToObject;
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
        setResponseMockDataInPackage("getUser.txt", FileToObject.JSON, new UserDaoRequestAsserter() {
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
        setResponseMockDataInPackage("getUser.bat", FileToObject.READ_OBJECT, new UserDaoRequestAsserter() {
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
    public void test_update_void() {
        setResponseMockDataInPackage(null, FileToObject.VOID, new UserDaoRequestAsserter() {
            @Override
            public void update(User user) {
                Assert.assertEquals(1L, user.getId());
                Assert.assertEquals("野兽先辈", user.getName());
                Assert.assertEquals(24, user.getAge());
            }
        });
        User user = new User();
        user.setId(1L);
        user.setName("野兽先辈");
        user.setAge(24);
        userService.update(user);
    }
}