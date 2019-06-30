package com.yukms.easytest;

import com.yukms.easytest.test.mock.MockDataAspectJ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hudingpeng hudingpeng@souche.com 2019/6/28 14:34
 */
@Configuration
public class TestConfiguration {
    @Bean
    public MockDataAspectJ getMockAspectJ() {
        return new MockDataAspectJ();
    }
}
