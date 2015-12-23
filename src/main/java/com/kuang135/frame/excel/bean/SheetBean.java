package com.kuang135.frame.excel.bean;

import java.util.List;
import java.util.Map;


/**
 * sheet的名称
 * excel表的标题 key: 标题名
 * excel表的内容 key：内容
 */
public class SheetBean {

	private String name;
	private Map<String, String> propertyTitle; 
	private List<Map<String, Object>> records;
	
	public SheetBean(String name, Map<String, String> propertyTitle, List<Map<String, Object>> records) {
		super();
		this.name = name;
		this.propertyTitle = propertyTitle;
		this.records = records;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getPropertyTitle() {
		return propertyTitle;
	}

	public List<Map<String, Object>> getRecords() {
		return records;
	}
	
	
	
}
