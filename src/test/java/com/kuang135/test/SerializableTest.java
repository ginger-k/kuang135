package com.kuang135.test;

import org.junit.Test;

import com.kuang135.frame.util.SerializableUtils;

public class SerializableTest {
	
	@Test
	public void test1() {
		String serialize = SerializableUtils.serialize("a");
		System.out.println(serialize);
	}
	
	//测试未通过
	@Test
	public void test2() {
		String a = (String) SerializableUtils.deserialize(SerializableUtils.serialize("a"));
		System.out.println(a);
	}

}
