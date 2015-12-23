package com.kuang135.test;

import java.io.File;

import org.junit.Test;

import com.kuang135.frame.util.ZipUtil;

public class ZipTest {
	
	@Test
	public void test1() throws Exception {
		String outputZipFilePath = "2015zip" + File.separator + "用户信息.zip";
		File inputDir = new File("2015");
		//把目录2015压缩为 用户信息.zip的文件，放到2015zip的目录中
		ZipUtil.zipFile(outputZipFilePath, inputDir);
	}

}
