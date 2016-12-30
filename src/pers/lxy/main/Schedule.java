package pers.lxy.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pers.lxy.util.StringUtil;

/**
 * <p>
 * TODO 主程序
 * </p>
 *
 * @author lxy
 * @date 2016年12月21日
 */
public class Schedule {
	
	private static String infile = "C:\\schedule\\employeesList.xlsx";
	
	private static String outfile = "C:\\schedule\\schedule.xls";
	
	public static void main(String[] args) {
		String inXls = null;
		String outXls = null;
		if (args.length > 0 && args.length == 2) {
			inXls = args[0];
			outXls = args[2];
		}
		if (StringUtil.isEmpty(inXls)) {
			inXls = infile;
		}
		if (StringUtil.isEmpty(outXls)) {
			outXls = outfile;
		}
		Map<String, String> employees = null;
		try {
			employees = getEmployees(inXls);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("发生异常！");
		}
		if (employees.isEmpty()) {
			System.out.println("员工表不能为空！");
		} else {
			Set<Entry<String, String>> entrySet = employees.entrySet();
			for (Entry<String, String> entry : entrySet) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}
	}
	
	@SuppressWarnings("resource")
	private static Map<String, String> getEmployees(String inXls) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtil.isNotEmpty(inXls)) {
			InputStream input = new FileInputStream(inXls);
			if (input != null) {
				Sheet sheet = null;
				if (inXls.endsWith(".xls")) {
					// 创建对Excel工作簿文件的引用
					HSSFWorkbook workbook = new HSSFWorkbook(input);
					// 获取sheet0
					sheet = workbook.getSheetAt(0);
				} else if (inXls.endsWith(".xlsx")) {
					// 创建对Excel工作簿文件的引用
					XSSFWorkbook workbook = new XSSFWorkbook(input);
					// 获取sheet0
					sheet = workbook.getSheetAt(0);
				}
				int rows = sheet.getLastRowNum() + 1;
				for (int i = 1; i < rows; i++) {
					Row row = sheet.getRow(i);
					if (row == null)
						continue;
					Cell cellkey = row.getCell(0);
					Cell cellValue = row.getCell(1);
					if (cellkey != null && cellValue != null) {
						String key = cellkey.getStringCellValue();
						String value = cellValue.getStringCellValue();
						if (StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(value)) {
							map.put(key, value);
						}
					}
				}
			}
		}
		return map;
	}
}
