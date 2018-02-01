package com.xsx.util;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;
import com.xsx.domain.Orders;

/**
 * 
 * @Title: ExcelUtils.java 
 * @Package com.xsx.util 
 * @Description: Excel文件操作工具类
 * @author xsx
 * @date 2018年1月21日 下午1:07:00 
 * @version V1.0
 */
public class ExcelUtils {

	private ExcelUtils(){}
	
	/**
	 * 导出订单数据
	 * @param list
	 */
	public static void writeData(List<Orders> list){
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("订单号");
		cell = row.createCell(1);
		cell.setCellValue("款式");
		cell = row.createCell(2);
		cell.setCellValue("收货人");
		cell = row.createCell(3);
		cell.setCellValue("电话");
		cell = row.createCell(4);
		cell.setCellValue("收获地址");
		cell = row.createCell(5);
		cell.setCellValue("下单时间");
		for(int i = 0;i<list.size();i++){
			Orders o = list.get(i);
			HSSFRow row1 = sheet.createRow(i+1);
			row1.createCell(0).setCellValue(o.getId());
			row1.createCell(1).setCellValue(o.getShopname());
			row1.createCell(2).setCellValue(o.getReceiptname());
			row1.createCell(3).setCellValue(o.getReceiptphone());
			row1.createCell(4).setCellValue(o.getReceiptaddress());
			row1.createCell(5).setCellValue(o.getCreatedate());
		}
	}
}
