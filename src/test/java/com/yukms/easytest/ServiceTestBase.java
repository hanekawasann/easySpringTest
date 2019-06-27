package com.yukms.easytest;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yukms.easytest.test.mock.DataMocker;
import com.yukms.easytest.test.mock.FileToObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

/**
 * 基础测试类
 *
 * @author yukms 763803382@qq.com 2019/3/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ServiceTestBase extends WebApplicationObjectSupport {
    @Before
    public void initContext() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        // 初始化Request
        httpServletRequest.setSession(getMockHttpSession());
        RequestContextHolder.setRequestAttributes(new DispatcherServletWebRequest(httpServletRequest));
    }

    private MockHttpSession getMockHttpSession() {
        MockHttpSession mockHttpSession = new MockHttpSession();
        // 初始化Session
        return mockHttpSession;
    }

    @After
    public void resetContext() {
        RequestContextHolder.setRequestAttributes(null);
        // DataMocker在同一个单元测试类的测试方法间会互相影响，所以执行每一个测试方法之后都清除mock数据
        DataMocker.clearData();
    }

    private HttpServletRequest getHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    /**
     * 设置request参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    protected final void setRequestAttribute(String name, Object value) {
        getHttpRequest().setAttribute(name, value);
    }

    /**
     * 设置session参数
     *
     * @param name  参数名
     * @param value 参数值
     */
    protected final void setSessionAttribute(String name, Object value) {
        HttpServletRequest request = getHttpRequest();
        HttpSession session = request.getSession(true);
        session.setAttribute(name, value);
    }

    /**
     * 设置mock数据
     *
     * @param fileName     mock文件名
     * @param fileToObject 文件转换为对象的方式
     * @param mockObj      需要mock的对象
     */
    protected final void setMockDataInPackage(String fileName, FileToObject fileToObject, Object mockObj) {
        InputStream inputStream = null;
        if (StringUtils.isNotBlank(fileName)) {
            inputStream = this.getClass().getResourceAsStream(fileName);
        }
        DataMocker.setResponseMockData(fileName, inputStream, fileToObject, mockObj);
    }

    /**
     * 设置mock数据
     *
     * @param fileName mock文件名
     * @param mockObj  需要mock的对象
     */
    protected void setMockDataInPackage(String fileName, Object mockObj) {
        InputStream inputStream = null;
        FileToObject fileToObject;
        if (StringUtils.isBlank(fileName)) {
            fileToObject = FileToObject.VOID;
        } else {
            inputStream = this.getClass().getResourceAsStream(fileName);
            if (fileName.endsWith(".json")) {
                fileToObject = FileToObject.JSON;
            } else {
                fileToObject = FileToObject.READ_OBJECT;
            }
        }
        DataMocker.setResponseMockData(fileName, inputStream, fileToObject, mockObj);
    }
}