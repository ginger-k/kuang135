package com.kuang135.frame.excel.bean;


/**
 * excle的名称，全路径，比如 2015-10-15\嘉兴\物业维护表\嘉兴站物业维护表.xls，必须使用系统的文件分隔符
 * 多张sheet
 */
public class ExcelBean {

	private String name;
	private SheetBean[] sheets;
	
	public ExcelBean(String name, SheetBean[] sheets) {
		this.name = name;
		this.sheets = sheets;
	}
	
	public String getName() {
		return name;
	}
	
	public SheetBean[] getSheets() {
		return sheets;
	}
}
