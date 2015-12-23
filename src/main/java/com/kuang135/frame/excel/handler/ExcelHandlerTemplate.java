package com.kuang135.frame.excel.handler;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.CellValue;

import org.apache.commons.lang3.StringUtils;

import com.kuang135.frame.excel.bean.ExcelBean;
import com.kuang135.frame.excel.bean.SheetBean;



public abstract class ExcelHandlerTemplate implements ExcelHandler {

	@Override
	public void handle(ExcelBean bean) throws Exception {
		if (bean == null) {
			throw new IllegalArgumentException("-------> 参数异常 <-------");
		}
		File file = new File(StringUtils.substringBeforeLast(bean.getName(), File.separator));
		if (!file.exists())
			file.mkdirs();
		WritableWorkbook book = Workbook.createWorkbook(new File(bean.getName()));
		SheetBean[] sheets = bean.getSheets();
		if (sheets != null && sheets.length > 0) {
			for(int i = 0; i<sheets.length; i++) {
				WritableSheet sheet = book.createSheet(sheets[i].getName(), i);
				Map<String, String> propertyTitle = sheets[i].getPropertyTitle();
				List<Map<String, Object>> records = sheets[i].getRecords();
				//制作表头
				int column = 0;
				int row = 0;
				for (Entry<String, String> entry : propertyTitle.entrySet()) {
					Label label = new Label(column, row , entry.getValue());
					this.setTitleFormat(label, entry.getKey(), entry.getValue(), i);
					sheet.addCell(label);
					column++;
				}
				row++;
				column = 0;
				//构建表内容
				if (records != null && records.size() > 0) {
					for (int j = 0; j < records.size(); j++) {
						for (Entry<String, String> entry : propertyTitle.entrySet()) {
							String property = entry.getKey();
							Object recordValue = records.get(j).get(property);
							CellValue cellValue = this.getDefaultCell(column, row + j, recordValue);
							cellValue = this.overWriteCell(cellValue, property, recordValue, i);
							sheet.addCell(cellValue);
							column++;
						}
						column = 0;
					}
				}
				row = 0;
			}
		}
		book.write();
		book.close();
	}
	
	/**
	 * 设置标题单元格的格式
	 * @param lable 单元格对象，
	 * 			int row = label.getRow(); //获取行号
	 * 			int column = label.getColumn(); //获取列号
	 * @param key 键名
	 * @param value 键值
	 * @param sheetNumber sheet的number
	 * @throws Exception
	 */
	protected abstract void setTitleFormat(Label lable, String key, Object value, int sheetNumber) throws Exception;
	
	private CellValue getDefaultCell(int column, int row, Object value) throws Exception {
		if (value == null) {
			return new jxl.write.Blank(column, row);
		}
		Class<?> type = value.getClass();
		if ( type == String.class) {
			return new Label(column, row, (String) value);
		} else if ( type == Integer.class) {
			return new jxl.write.Number(column, row,(Integer) value);
		} else if ( type == Float.class) {
			return new jxl.write.Number(column, row,(Float) value);
		} else if ( type == Double.class) {
			return new jxl.write.Number(column, row,(Double) value);
		} else if ( type == Long.class) {
			return new jxl.write.Number(column, row,(Long) value);
		} else if ( type == Date.class) {
			return new jxl.write.DateTime(column, row,(Date) value);
		} else {
			return new jxl.write.Blank(column, row);
		}
	}
	
	//设置非标题单元格的格式，和值
	protected abstract CellValue overWriteCell(CellValue cellValue, String key, Object value, int sheetNumber) throws Exception;

}
