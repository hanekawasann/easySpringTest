package com.yukms.easytest.test.mock;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author hudingpeng hudingpeng@souche.com 2019/6/28 15:09
 */
@Data
public class DataRecord {
    /** 文件夹名 */
    private String folderPath;
    /** 文件名 */
    private List<String> fileNames = new ArrayList<>();

    public boolean addFileNames(String fileName) {
        return fileNames.add(fileName);
    }
}
