package com.yukms.easytest.test;

/**
 * @author hudingpeng hudingpeng@souche.com 2019/6/28 14:44
 */
public final class EasyTestConfig {
    private static boolean record;

    public static boolean isRecord() {
        return record;
    }

    public static void setRecord(boolean record) {
        EasyTestConfig.record = record;
    }
}
