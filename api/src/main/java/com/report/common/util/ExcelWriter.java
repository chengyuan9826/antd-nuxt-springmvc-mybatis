package com.report.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	public static void main(String[] args) {
		try {
			List<Map<Integer, String>> data = new ExcelReader()
					.readExcel("E:\\zks\\文档\\相似性分析\\0901全量7-91.xls");
			new ExcelWriter().write("E:\\DD.xls", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * writeWorkbook:把excel对象输出到指定的路径. <br/>
	 * 
	 * @author zuokangsheng
	 * @param filePath
	 * @param workbook
	 * @since JDK 1.6
	 */
	public void writeWorkbook(String filePath, Workbook workbook) {
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			writeExcel(out, workbook);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把workbook对象写入到工作薄
	 */
	public static void writeExcel(File file, Workbook workbook)
			throws FileNotFoundException, IOException {
		FileOutputStream out = new FileOutputStream(file);
		writeExcel(out, workbook);
	}

	/**
	 * 用特定的输出流，把workbook对象写入到工作薄
	 */
	public static void writeExcel(OutputStream out, Workbook workbook)
			throws FileNotFoundException, IOException {
		workbook.write(out);
		out.flush();
		out.flush();
	}

	/**
	 * 用键为Integer类型的map创建excel
	 * 
	 * @param filePath
	 * @param data
	 */
	public void write(String filePath, List<Map<Integer, String>> data) {
		Workbook workbook = createWorkbook(filePath);
		Sheet sheet = workbook.createSheet("sheet1");
		buildSheet(data, sheet);
		writeWorkbook(filePath, workbook);
	}

	/**
	 * 
	 * write:创建一个excel，并指定其标题. <br/>
	 * 
	 * @author zuokangsheng
	 * @param filePath
	 * @param list
	 * @param titles
	 * @since JDK 1.6
	 */
	public void write(String filePath, List<Map<String, Object>> list,
			String[] titles) {
		Workbook workbook = createWorkbook(filePath);
		Sheet sheet = workbook.createSheet("sheet1");
		buildSheet(list, sheet, titles);
		writeWorkbook(filePath, workbook);
	}

	public void write(OutputStream out, List<Map<String, Object>> data,
			String[] titles) {
		
	}

	/*
	 * 用指定的标题创建excel。这里的标题同时也是data的键
	 */
	private void buildSheet(List<Map<String, Object>> data, Sheet sheet,
			String[] titles) {
		Map<String, Object> att = null;
		Row row = null;
		Cell cell = null;
		Object obj = null;
		DateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");;
		// 线创建标题行
		row = sheet.createRow(0);
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(titles[i].toUpperCase());
		}
		for (int i = 0; i < data.size();) {
			att = data.get(i);
			row = sheet.createRow(++i);
			// 这里的map的键是以Excel中列序号排列的，故可以用for循环取出每列的值写入Excel
			for (int j = 0; j < titles.length; j++) {
				cell = row.createCell(j);
				obj = att.get(titles[j]);
				if (obj instanceof Timestamp) {
					obj = df.format(obj);
				}
				cell.setCellValue((String) obj);
			}
		}
	}

	/*
	 * 用指定的顺序为key创建sheet
	 */
	private void buildSheet(List<Map<Integer, String>> data, Sheet sheet) {
		Map<Integer, String> att = null;
		Row row = null;
		Cell cell = null;
		for (int i = 0; i < data.size(); i++) {
			att = data.get(i);
			row = sheet.createRow(i);
			// 这里的map的键是以Excel中列序号排列的，故可以用for循环取出每列的值写入Excel
			for (Integer j = 0; j < att.size();) {
				cell = row.createCell(j);
				cell.setCellValue(att.get(++j));
			}
		}
	}

	private Workbook createWorkbook(String filePath) {
		Workbook workbook = null;
		// 判断生成什么格式的工作薄
		if (filePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (filePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			System.out.println("请输入正确的文件名称");
		}
		return workbook;
	}
}
