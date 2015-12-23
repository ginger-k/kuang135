package com.kuang135.frame.excel.handler;

import com.kuang135.frame.excel.bean.ExcelBean;




public interface ExcelHandler {

	//根据ExcelBean中的数据生成一个excel文件
	void handle(ExcelBean bean) throws Exception;
	
}
