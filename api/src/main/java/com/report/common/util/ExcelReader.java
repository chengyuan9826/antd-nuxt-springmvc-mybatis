package com.report.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	/**
	 * 读取一个Excel第一个工作表的所有行和列， 默认跳过表头
	 * 
	 * @param path
	 * @return  [{1=1, 2=客户说, 3=7}, {1=2, 2=客户说}]
	 * @throws IOException
	 */
	public List<Map<Integer, String>> readExcel(String path) throws IOException {
		return readExcel(path, true, 0);
	}

	/**
	 * 读取一个工作薄指定工作表的所有行和列,skipTitle = true时跳过表头
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public List<Map<Integer, String>> readExcel(String path, boolean skipTitle,
			int sheetIndex) throws IOException {
		Workbook workbook = getWorkBook(path);
		List<Map<Integer, String>> list = readsheetOfWorkBook(workbook,
				skipTitle, sheetIndex);
		return list;
	}

	/**
	 * 读取一个Excel指定sheet的指定列的数据，cellNum从0开始。skipTitle = true时跳过表头
	 * cellNum为要读取的列序号，从0开始
	 * 
	 * @param path
	 * @param sheetIndex
	 * @param CellNum
	 * @return
	 * @throws IOException
	 */
	public List<Map<Integer, String>> readExcel(String path, boolean skipTitle,
			int sheetIndex, int... cellNum) throws IOException {
		Workbook workbook = getWorkBook(path);
		List<Map<Integer, String>> list = readsheetOfWorkBook(workbook,
				skipTitle, sheetIndex, cellNum);
		return list;
	}

	/**
	 * 
	 * getWorkBook:以指定路径生成WorkBook对象. <br/>
	 * @author zuokangsheng
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public Workbook getWorkBook(String path) throws FileNotFoundException,
			IOException {
		InputStream in = new FileInputStream(path);
		Workbook workbook = null;
		if (path.endsWith("xls")) {
			workbook = new HSSFWorkbook(in);
		} else {
			workbook = new XSSFWorkbook(in);// xlsx
		}
		return workbook;
	}
	
	/**
	 * 读取sheet并放入list
	 */
	public List<Map<Integer, String>> readsheetOfWorkBook(Workbook workBook,
			boolean skipTitle, int sheetIndex) {
		List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> att = null;
		Integer key = 0;
		Row row = null;
		Sheet sheet = workBook.getSheetAt(sheetIndex);
		int i = skipTitle ? 1 : 0;// 如果跳过表头，从第二行开始遍历
		for (; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			att = new HashMap<Integer, String>();
			key = 0;
			for (Cell cell : row) {
				att.put(++key, getValue(cell));
			}
			list.add(att);
		}
		return list;
	}

	/**
	 * 读取sheet中指定列的数据，并放入list，参数为列号，从0开始
	 */
	public List<Map<Integer, String>> readsheetOfWorkBook(Workbook workBook,
			boolean skipTitle, int sheetIndex, int... cellNums) {
		List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> att = null;
		Row row = null;
		Sheet sheet = workBook.getSheetAt(sheetIndex);
		int i = skipTitle ? 1 : 0;// 如果跳过标题，从第二行开始遍历
		for (; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			att = new HashMap<Integer, String>();
			for (Integer j = 0; j < cellNums.length; j++) {
				att.put(j, getValue(row.getCell(cellNums[j])));
			}
			list.add(att);
		}
		return list;
	}

	public String getValue(Cell cell) {
		return getValue(cell, null);
	}

	public String getValue(Cell cell, DateFormat formater) {
		formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String result = new String();
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				// SimpleDateFormat sdf = null;
				// if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
				// .getBuiltinFormat("h:mm")) {
				// sdf = new SimpleDateFormat("HH:mm");
				// } else {// 日期
				// sdf = new SimpleDateFormat("yyyy-MM-dd");
				// }
				// Date date = cell.getDateCellValue();
				// result = sdf.format(date);
				// } else if (cell.getCellStyle().getDataFormat() == 58) {
				// // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// double value = cell.getNumericCellValue();
				// Date date = org.apache.poi.ss.usermodel.DateUtil
				// .getJavaDate(value);
				// result = sdf.format(date);
				Date date = cell.getDateCellValue();
				return formater.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat df = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					df.applyPattern("#");
				}
				result = df.format(value);
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = "";
			break;
		}
		return result;
	}

	public static void main(String[] args) {
		ExcelReader reader = new ExcelReader();
		// C:\Users\zuokangsheng\Desktop
		// E:\\文档\\POI\\友商.xlsx
		try {
			System.out.println(reader.readExcel(
					"C:\\Users\\zuokangsheng\\Desktop\\测试.xls", false, 0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
