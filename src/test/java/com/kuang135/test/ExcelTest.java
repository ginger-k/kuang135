package com.kuang135.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kuang135.frame.dao.DbUtil;
import com.kuang135.frame.excel.bean.ExcelBean;
import com.kuang135.frame.excel.bean.SheetBean;
import com.kuang135.frame.excel.handler.DefaultExcelHandler;
import com.kuang135.frame.excel.handler.ExcelHandler;

public class ExcelTest {
	
	@Test
	public void test1() throws Exception {
		//标题
		Map<String, String> propertyTitle = new LinkedHashMap<String, String>();
		propertyTitle.put("id", "id号");
		propertyTitle.put("name", "姓名");
		propertyTitle.put("password", "密码");
		//内容
		String sql = "SELECT id id,username name,password password FROM user";
		@SuppressWarnings({ "resource", "unused" })
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring*.xml");
		List<Map<String,Object>> records = DbUtil.getMapList(sql);
		SheetBean sheetBean = new SheetBean("表1", propertyTitle, records);
		//excel文件名，一定要用系统分隔符
		String excelName = "2015" + File.separator + "1223" + File.separator + "用户信息.xls";
		ExcelBean excelBean = new ExcelBean(excelName, new SheetBean[]{sheetBean});
		ExcelHandler excelHandler = new DefaultExcelHandler();
		excelHandler.handle(excelBean);
	}

}
