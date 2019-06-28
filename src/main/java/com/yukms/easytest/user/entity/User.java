package com.yukms.easytest.user.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * yukms 763803382@qq.com 2019/3/26 10:29
 */
@Data
public class User implements Serializable {
    private long id;
    private String name;
    private int age;
    private int num;
}
