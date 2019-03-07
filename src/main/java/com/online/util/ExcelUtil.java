package com.online.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import com.mysql.fabric.xmlrpc.base.Data;

public class ExcelUtil {

	public static final String EXTENSION_XLS = "xls";
	public static final String EXTENSION_XLSX = "xlsx";
	

	public static class IFERROR implements FreeRefFunction {

		@Override
		public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
			if (args.length < 2) {
				return BlankEval.instance;
			}
			if (args[0] instanceof ErrorEval) {
				return args[1];
			} else {
				return args[0];
			}
		}
	}
	
	public static class IF implements FreeRefFunction {

		@Override
		public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
			if (args.length < 3) {
				return BlankEval.instance;
			}
			if (args[0] instanceof ErrorEval) {
				return args[2];
			} else {
				return args[1];
			}
		}
	}
	
	
	/*static{
		AnalysisToolPak.registerFunction("IFERROR", new IFERROR());
	}*/

	/**
	 * 读取单元格字符串类型值
	 * 
	 * @param row
	 * @param cellIndex
	 * @param errorinfo
	 * @param header
	 * @param enterpriseName
	 * @return
	 */
	@Deprecated
	public static String getStringValue(Row row, int cellIndex) {
		try {
			Cell cell = row.getCell(cellIndex);
			if (cell == null) {
				return "";
			}
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
				return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_ERROR:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() ? "true" : "false";
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			default:
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 读取单元格字符串类型值
	 * 
	 * @param row
	 * @param cellIndex
	 * @param errorinfo
	 * @param header
	 * @param enterpriseName
	 * @return
	 */
	public static String getStringValue(Row row, int cellIndex,FormulaEvaluator evaluator) {
		if(row == null)return "";
		try {
			Cell cell = row.getCell(cellIndex);
			if (cell == null) {
				return "";
			}
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);  
				return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_ERROR:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() ? "true" : "false";
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_FORMULA:
				CellValue evaluate = evaluator.evaluate(cell);
				int cellType = evaluate.getCellType();
				switch (cellType) {
				case  Cell.CELL_TYPE_NUMERIC:
					return String.valueOf(evaluate.getNumberValue());
				case  Cell.CELL_TYPE_BOOLEAN:
					return evaluate.getBooleanValue() ? "true" : "false";
				case  Cell.CELL_TYPE_STRING:
					return evaluate.getStringValue();
				case  Cell.CELL_TYPE_ERROR:
					return String.valueOf(evaluate.getErrorValue());
				default:
					return "";
				}
			default:
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	

	/**
	 * 读取单元格数字类型值
	 * 
	 * @param row
	 * @param cellIndex
	 * @param errorinfo
	 * @param header
	 * @param enterpriseName
	 * @return
	 */
	public static double getDoubleValue(Row row, int cellIndex) {
		try {
			Cell cell = row.getCell(cellIndex);
			if (cell == null) {
				return 0;
			}
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
			case Cell.CELL_TYPE_ERROR:
				return 0;
			case Cell.CELL_TYPE_BOOLEAN:
				return 0;
			case Cell.CELL_TYPE_STRING:
				if (cell.getStringCellValue().trim() != null
						&& !isNumericOrDouble(cell.getStringCellValue().trim())) {
					return 0;
				}
				return Double.parseDouble(cell.getStringCellValue().trim());
			case Cell.CELL_TYPE_BLANK:
				return 0;
			default:
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumericOrDouble(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 读取数字类型
	 * 
	 * @param sheet
	 *            excel Sheet
	 * @param row
	 *            行号
	 * @param column
	 *            列号
	 * @return
	 */
	public static double getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getDoubleValue(fRow, fCell.getColumnIndex());
				}
			}
		}

		return 0.0;
	}

	/**
	 * 读取字符串类型
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static String getMergedRegionStringValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getStringValue(fRow, fCell.getColumnIndex());
				}
			}
		}

		return null;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	public static boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @see 获取工作薄
	 * @see 判断工作薄版本格式
	 * @return Workbook
	 * @author dev3
	 * @throws FileNotFoundException 
	 *
	 */
	public static Workbook getWorkbook(String file) throws FileNotFoundException {
		File f = null;
		Workbook workbook = null;
		try {
			if (file.endsWith(EXTENSION_XLS)) {
				f = ResourceUtils.getFile(file);
				workbook = new HSSFWorkbook(new FileInputStream(f));
			} else if (file.endsWith(EXTENSION_XLSX)) {
				f = ResourceUtils.getFile(file);
				workbook = new XSSFWorkbook(new FileInputStream(f));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException("excel文件失效，请重新上传");
		}
		return workbook;
	}
	
	/**
	 * @see 获取工作薄
	 * @see 判断工作薄版本格式
	 * @return Workbook
	 * @author dev3
	 * @throws FileNotFoundException 
	 *
	 */
	public static Workbook getWorkbook(File file) throws FileNotFoundException {
		Workbook workbook = null;
		try {
			if (file.getName().endsWith(EXTENSION_XLS)) {
				workbook = new HSSFWorkbook(new FileInputStream(file));
			} else if (file.getName().endsWith(EXTENSION_XLSX)) {
				workbook = new XSSFWorkbook(new FileInputStream(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException("excel文件失效，请重新上传");
		}
		return workbook;
	}

	/**
	 * 根据sheet地址和sheet索引
	 * 
	 * @param filePath
	 * @param sheetNum
	 * @return
	 */
	public static Sheet getSheet(String filePath, int sheetNum) {
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook(new FileInputStream(filePath));
			Sheet sheet = wb.getSheetAt(sheetNum);
			return sheet;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 读取Excel单元格内容转成String类型
	 * 
	 * @param row
	 *            行对象
	 * @param colNum
	 *            列号
	 * @return
	 */
	public static String getStringValues(Row row, int colNum) {
		try {
			Cell cell = row.getCell(colNum);
			if (cell == null) {
				return "";
			}
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				return (long) cell.getNumericCellValue() + "";
			case Cell.CELL_TYPE_ERROR:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() ? "true" : "false";
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue().trim();
			case Cell.CELL_TYPE_BLANK:
				return "";
			default:
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("colNum:" + colNum);
			System.out.println("row::" + row.getRowNum());
			return "";
		}
	}
	public static Date getDateValue(Row row, int colNum) {
		try {
			Cell cell = row.getCell(colNum);
			if (cell == null) {
				return null;
			}
			
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getDateCellValue();
			case Cell.CELL_TYPE_STRING:
				return DateUtils.getDateByStr(cell.getStringCellValue());
			default:
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("colNum:" + colNum);
			System.out.println("row::" + row.getRowNum());
			return null;
		}
	}

	/**
	 * 读取Excel单元格内容转成double类型
	 * 
	 * @param row
	 *            行对象
	 * @param colNum
	 *            列号
	 * @return
	 */
	public static double getDoubleValues(Row row, int colNum) {
		try {
			Cell cell = row.getCell(colNum);
			if (cell == null) {
				return 0;
			}
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
			case Cell.CELL_TYPE_ERROR:
				return 0;
			case Cell.CELL_TYPE_BOOLEAN:
				return (cell.getBooleanCellValue() ? 1 : 0);
			case Cell.CELL_TYPE_STRING:
				Boolean isnumber = isNumeric(cell.getStringCellValue().trim());
				if (!isnumber) {
					return 0;
				}
				if (Double.parseDouble(cell.getStringCellValue().trim()) < 0) {
					return 0;
				}
				return Double.parseDouble(cell.getStringCellValue().trim());
			case Cell.CELL_TYPE_BLANK:
				return 0;
			default:
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("colNum:" + colNum);
			System.out.println("row::" + row.getRowNum());
			return 0;
		}
	}

	/**
	 * 读取Excel单元格内容转成int类型
	 * 
	 * @param row
	 *            行对象
	 * @param colNum
	 *            列号
	 * @return
	 */
	public static int getIntegerValue(Row row, int colNum) {
		try {
			Cell cell = row.getCell(colNum);
			if (cell == null) {
				return 0;
			}
			int key = cell.getCellType();
			switch (key) {
			case Cell.CELL_TYPE_NUMERIC:
				return (int) cell.getNumericCellValue();
			case Cell.CELL_TYPE_ERROR:
				return 0;
			case Cell.CELL_TYPE_BOOLEAN:
				return cell.getBooleanCellValue() ? 1 : 0;
			case Cell.CELL_TYPE_STRING:
				Boolean isnumber = isNumeric(cell.getStringCellValue());
				if (!isnumber) {
					return 0;
				}
				if (Double.parseDouble(cell.getStringCellValue().trim()) < 0) {
					return 0;
				}
				return Integer.parseInt(cell.getStringCellValue().trim());
			case Cell.CELL_TYPE_BLANK:
				return 0;
			default:
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("colNum:" + colNum);
			System.out.println("row::" + row.getRowNum());
			return 0;
		}
	}

	public static boolean isNumeric(String str) {
		String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
		if (str.matches(regex)) {
			return true;
		}
		return false;
	}

	public static boolean isEmptyRow(Row row) {
	    if (row==null) {
            return true;
        }
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		String path = "D:/code/mavenspace/sichuan/src/main/resources/data/自定义模板配置/10 农业源scc.xlsx";
		Sheet sheet = getSheet(path, 1);
		Row row = sheet.getRow(5);
		Cell cell = row.getCell(1);
		System.out.println("type:::"+cell.getCellType());
		String stringValue = getStringValue(row, 1);
		System.out.println(stringValue);
	}
}
