package com.djk.web.util.poi;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelUtil {

	private void setHouseSheetContent(XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
		CellStyle numberCs = xWorkbook.createCellStyle();
		CellStyle cs = xWorkbook.createCellStyle();
		// 设置水平垂直居中
		numberCs.setAlignment(CellStyle.ALIGN_CENTER);
		numberCs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		numberCs.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		cs.setWrapText(true);

		for (int i = 0; i < 1000; i++) {
			XSSFRow xRow = xSheet.createRow(i + 1);
			for (int j = 0; j < 9; j++) {
				XSSFCell xCell = xRow.createCell(j);
				xCell.setCellStyle(cs);
				switch (j) {
				case 5:
					xCell.setCellStyle(numberCs);
					break;
				default:
					break;
				}
			}
		}

	}

	public void medicineExport(HttpServletResponse response) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		OutputStream os = null;
		XSSFWorkbook xWorkbook = null;
		try {
			String fileName = "药品模版" + df.format(new Date()) + ".xlsx";

			os = response.getOutputStream();
			response.reset();

			response.setHeader("Content-disposition", "attachment; filename = " + URLEncoder.encode(fileName, "UTF-8"));
			response.setContentType("application/octet-streem");

			xWorkbook = new XSSFWorkbook();
			XSSFSheet xSheet = xWorkbook.createSheet("sheet1");

			// set Sheet页头部
			setSheetHeader(xWorkbook, xSheet);

			// set Sheet页内容
			setHouseSheetContent(xWorkbook, xSheet);

			xWorkbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (null != xWorkbook) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setSheetHeader(XSSFWorkbook xWorkbook, XSSFSheet xSheet) {
		xSheet.setColumnWidth(0, 20 * 256);
		xSheet.setColumnWidth(1, 20 * 256);
		xSheet.setColumnWidth(2, 20 * 256);
		xSheet.setColumnWidth(3, 20 * 256);
		xSheet.setColumnWidth(4, 20 * 256);
		xSheet.setColumnWidth(5, 20 * 256);
		xSheet.setColumnWidth(6, 20 * 256);
		xSheet.setColumnWidth(7, 20 * 256);
		xSheet.setColumnWidth(8, 20 * 256);

		CellStyle cs = xWorkbook.createCellStyle();
		// 设置水平垂直居中
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 设置字体
		Font headerFont = xWorkbook.createFont();
		headerFont.setFontHeightInPoints((short) 12);
		headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontName("宋体");
		cs.setFont(headerFont);
		cs.setWrapText(true);// 是否自动换行

		XSSFRow xRow0 = xSheet.createRow(0);

		XSSFCell xCell0 = xRow0.createCell(0);
		xCell0.setCellStyle(cs);
		xCell0.setCellValue("药品分类");

		XSSFCell xCell1 = xRow0.createCell(1);
		xCell1.setCellStyle(cs);
		xCell1.setCellValue("药品名称");

		XSSFCell xCell2 = xRow0.createCell(2);
		xCell2.setCellStyle(cs);
		xCell2.setCellValue("供应商");

		XSSFCell xCell3 = xRow0.createCell(3);
		xCell3.setCellStyle(cs);
		xCell3.setCellValue("单位");

		XSSFCell xCell4 = xRow0.createCell(4);
		xCell4.setCellStyle(cs);
		xCell4.setCellValue("规格");

		XSSFCell xCell5 = xRow0.createCell(5);
		xCell5.setCellStyle(cs);
		xCell5.setCellValue("销售单价");

		XSSFCell xCell6 = xRow0.createCell(6);
		xCell6.setCellStyle(cs);
		xCell6.setCellValue("常用药品");

		XSSFCell xCell7 = xRow0.createCell(7);
		xCell7.setCellStyle(cs);
		xCell7.setCellValue("备注");

		XSSFCell xCell8 = xRow0.createCell(8);
		xCell8.setCellStyle(cs);
		xCell8.setCellValue("批次号");

	}
}