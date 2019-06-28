package com.yukms.easytest.test.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.yukms.easytest.test.mock.DataRecord;
import com.yukms.easytest.test.mock.MockData;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author hudingpeng hudingpeng@souche.com 2019/6/28 11:42
 */
@Log4j2
public final class DataRecordUtils {
    private static final ThreadLocal<DataRecord> RECORD_DATA = TransmittableThreadLocal.withInitial(DataRecord::new);
    private static final YamlConfig CONFIG = new YamlConfig();
    private static final String FOLDER_NAME_SPLIT = "_";
    private static String FILE_PATH_SPLIT = "\\";
    private static String FILE_SUFFIX = ".yaml";
    private static String CLASS_METHOD_SPLIT = "#";
    private static String RECORD_FILE_PATH_PREFIX;

    static {
        YamlConfig.WriteConfig writeConfig = CONFIG.writeConfig;
        // 保存默认字段
        writeConfig.setWriteDefaultValues(true);
        // 文件字段顺序与字段定义顺序相同
        writeConfig.setKeepBeanPropertyOrder(true);
        // 格式化输出
        writeConfig.setCanonical(true);
        // 不换行
        writeConfig.setWrapColumn(Integer.MAX_VALUE);
        // 中文不转义
        writeConfig.setEscapeUnicode(false);
        // 总是输出类名
        writeConfig.setWriteClassname(YamlConfig.WriteClassName.ALWAYS);

        File file = new File(".record");
        if (!file.exists()) {
            file.mkdirs();
        }
        RECORD_FILE_PATH_PREFIX = file.getAbsolutePath();
        log.info("记录根目录" + RECORD_FILE_PATH_PREFIX);
    }

    public static void recordData(ProceedingJoinPoint point, Object result) throws IOException {
        DataRecord dataRecord = RECORD_DATA.get();
        // 检查主目录
        String folderPath = dataRecord.getFolderPath();
        if (StringUtils.isBlank(folderPath)) {
            folderPath = getFolderPath(point);
        }
        Class<?> mockClazz = point.getTarget().getClass();
        Method method = AspectJUtils.getMethod(point);
        // 保存Mock文件
        String fileName = System.currentTimeMillis() + FOLDER_NAME_SPLIT + mockClazz.getSimpleName() +
            CLASS_METHOD_SPLIT + method.getName() + FILE_SUFFIX;
        MockData data = new MockData();
        data.setMockClazz(mockClazz.getName());
        data.setMethodName(method.getName());
        data.setArgs(new ArrayList<>(Arrays.asList(point.getArgs())));
        data.setResult(result);
        saveData(folderPath + fileName, data);
        // 记录文件
        dataRecord.addFileNames(fileName);
        log.info("保存记录文件" + fileName);
    }

    private static void saveData(String path, MockData data) throws IOException {
        YamlWriter writer = new YamlWriter(new FileWriter(path), CONFIG);
        writer.write(data);
        writer.close();
    }

    private static String getFolderPath(ProceedingJoinPoint joinPoint) {
        String folderName = buildFolderName(joinPoint);
        DataRecord dataRecord = RECORD_DATA.get();
        String folderPath = RECORD_FILE_PATH_PREFIX + FILE_PATH_SPLIT + folderName + FILE_PATH_SPLIT;
        dataRecord.setFolderPath(folderPath);
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdirs();
            log.info("创建记录文件夹" + folderName);
        }
        return folderPath;
    }

    private static String buildFolderName(ProceedingJoinPoint joinPoint) {
        return StringUtils.join(//
            new Object[] {//
                System.currentTimeMillis(),//
                joinPoint.getTarget().getClass().getSimpleName(),//
                AspectJUtils.getMethod(joinPoint).getName()//
            },//
            FOLDER_NAME_SPLIT);
    }

    private DataRecordUtils() { }
}
