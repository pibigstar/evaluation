package com.pibigstar.evaluation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 读取自定义的配置信息
 */
public class ConfigUtils {

    private static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
    /**
     * 配置文件路径
     */
    private static final String CONFIG_NAME = "conf/config.properties";
    private static Properties ps;

    public static int getInt(String key) {
        String value = getValue(key);
        return Integer.parseInt(value);
    }

    public static String getString(String key) {
        String value = getValue(key);
        return value;
    }

    private static String getValue(String key) {
        if (ps == null) {
            init();
        }
        String value = ps.getProperty(key);
        if (StringUtils.isEmpty(value)) {
            value = "";
        }
        logger.info("key:"+key+"----value:"+value);
        return value;
    }

    private static synchronized void init() {
        try {
            ps = new Properties();
            ps.load(new InputStreamReader(ConfigUtils.class.getClassLoader().getResourceAsStream(CONFIG_NAME), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}