package com.kuang135.test;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesTest {
	
	
	@Test
	public void test1() throws IOException {
		Properties properties = PropertiesLoaderUtils.loadAllProperties("env.properties");
		String value = properties.getProperty("testKey");
		System.out.println(value);
	}

}
