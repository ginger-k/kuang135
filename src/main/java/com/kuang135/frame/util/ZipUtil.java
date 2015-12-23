package com.kuang135.frame.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtil {
	
	private ZipUtil() {
		
	}
	
	/**
	 * @param outputZipFilePath 输出文件的路径，
	 * 			可以是多层目录，也可以是文件
	 * 			2015\用户信息.zip 或 用户信息.zip,必须用系统的文件分隔符
	 * @param inputDir 输入文件的路径
	 * 			可用是一个目录，也可用是一个文件
	 * 			2015 或 2015\1223\用户信息.xls
	 * @throws Exception
	 */
	public static void zipFile(String outputZipFilePath, File inputDir) throws Exception { 
        Map<String, File> map = new HashMap<String, File>();
        putNameAndFile2Map(inputDir, map);
        if (map.size() != 0) {
        	File file = new File(StringUtils.substringBeforeLast(outputZipFilePath, File.separator));
    		if (!file.exists())
    			file.mkdirs();
        	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputZipFilePath));
        	out.setEncoding("UTF-8");//解决linux乱码
        	String parent = inputDir.getParent(); 
        	for (Entry<String, File> entry : map.entrySet()) {
        		if (parent == null) {
        			zip(out, entry.getKey(), entry.getValue());
            	} else {
	            	if (!parent.endsWith(File.separator)) {
	            		parent = parent + File.separator; 
	            	}
	        		String zipName = StringUtils.substringAfter(entry.getKey(), parent); 
	        		zip(out, zipName, entry.getValue()); 
            	}
			}
        	out.close();
        }
    }    
    
    
    
    private static void zip(ZipOutputStream out, String zipName, File file) throws Exception {
        if (file.isFile()) {
        	ZipEntry zipEntry = new org.apache.tools.zip.ZipEntry(zipName);
        	zipEntry.setUnixMode(664);//解决linux乱码
        	out.putNextEntry(zipEntry);
        	FileInputStream in = new FileInputStream(file);
        	byte[] buf = new byte[1024];
        	while (in.read(buf) != -1) {
        		out.write(buf);
        	}
        	in.close();
       }
    }

    
    private static void putNameAndFile2Map(File file,Map<String,File> map){
    	if (file.isDirectory()) {
    		File[] files = file.listFiles();
    		if (files != null && files.length != 0) {
    			for (File f : files) {
    				putNameAndFile2Map(f,map);
				}
    		}
    	} else {
    		map.put(file.getPath(),file);
    	}
    }
	
}
