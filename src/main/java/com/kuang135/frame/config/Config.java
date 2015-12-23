package com.kuang135.frame.config;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.kuang135.frame.exception.ConfigIllegalTypeException;


/**
 *	从配置文件读取配置信息，赋值给字段
 *	字段的类型只支持Boolean，String，String[]
 *		如果是String[]，分隔符为逗号
 */
public class Config {

	private static Logger log = Logger.getLogger(Config.class);
	

	@PropertiesKey("testKey")
	public static String CONFIG_TEST;
	
	@PropertiesKey("booleanKey")
	public static Boolean CONFIG_BOOLEAN;
	
	@PropertiesKey("stringKey")
	public static String CONFIG_STRING;
	
	@PropertiesKey("stringArrayKey")
	public static String[] CONFIG_STRING_ARRAY;
	
	
	
	static {
		try {
			InputStream in = Config.class.getResourceAsStream("/config.properties");
			Properties properties = new Properties();
			properties.load(in);
			Field[] fields = Config.class.getDeclaredFields();
			if (fields != null) {
				for (Field field : fields) {
					field.setAccessible(true);
					PropertiesKey annot = field.getAnnotation(PropertiesKey.class);
					if (annot != null) {
						String key = annot.value();
						String value = properties.getProperty(key).trim();
						Class<?> type = field.getType();
						if (type == Boolean.class) {
							field.set(null, Boolean.valueOf(value));
						} else if (type == String[].class) {
							field.set(null,value.split(","));
						}  else if (type == String.class) {
							field.set(null, value);
						} else {
							throw new ConfigIllegalTypeException("-------> Config类型只能为Boolean,String,String[] <-------");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
}
