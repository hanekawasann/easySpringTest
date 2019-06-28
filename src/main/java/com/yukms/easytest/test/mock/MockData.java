package com.yukms.easytest.test.mock;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author hudingpeng hudingpeng@souche.com 2019/6/28 10:31
 */
@Data
public class MockData {
    /** mock的类 */
    private String mockClazz;
    /** mock的方法 */
    private String methodName;
    /** 参数 */
    private List<?> args = new ArrayList<>();
    /** 结果 */
    private Object result;
}
