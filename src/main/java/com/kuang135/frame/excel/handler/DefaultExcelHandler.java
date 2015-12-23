package com.kuang135.frame.excel.handler;



import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.biff.CellValue;



public class DefaultExcelHandler extends ExcelHandlerTemplate {

	@Override
	protected void setTitleFormat(Label lable, String key, Object value, int sheetNumber) throws Exception{
		FontName fontName = WritableFont.createFont("新宋体");
		WritableFont totileFont = new WritableFont(fontName,12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK); 
		WritableCellFormat titleFormat = new WritableCellFormat(totileFont); 
		lable.setCellFormat(titleFormat);
	}

	@Override
	protected CellValue overWriteCell(CellValue cellValue, String key, Object value, int sheetNumber) throws Exception{
		FontName fontName = WritableFont.createFont("新宋体");
		WritableFont cellFont = new WritableFont(fontName,12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK); 
		WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		cellValue.setCellFormat(cellFormat);
		return cellValue;
	}

}
