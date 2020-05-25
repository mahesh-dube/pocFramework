package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;

			int startCol = 1;

			int ci, cj;

			int totalRows = ExcelWSheet.getLastRowNum();

			System.out.println("Number of rows in excel : " + totalRows);

			// you can write a function as well to get Column count

			int totalCols = 12;

			System.out.println("total number of colums for row : " + totalCols);

			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

					System.out.println(tabArray[ci][cj]);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String dataType = Cell.getCellType().toString();

			if (dataType == "3") {

				return "";

			} else {

				String CellData = Cell.getStringCellValue();

				return CellData;

			}
		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}

	}

	public static Map<String, Map<String, String>> getLocators() throws IOException {

		String FilePath = "/Users/jb/workspace/pocFramework/src/test/java/Utility/TestData.xlsx";
		String sheetName = "RegistrationPage";
		FileInputStream ExcelFile = new FileInputStream(FilePath);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(sheetName);

		int lastRow = ExcelWSheet.getLastRowNum();

		int startRow = 1;

		Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();

		Map<String, String> dataMap = new HashMap<String, String>();

		// Looping over entire row
		for (int i = startRow; i <= lastRow; i++) {

			XSSFRow row = ExcelWSheet.getRow(i);

			// 0th Cell as Key
			XSSFCell keyCell = row.getCell(0);

			// 1st Cell as Value
			XSSFCell valueCell = row.getCell(1);
			String key = keyCell.getStringCellValue().trim();
			String value = valueCell.getStringCellValue().trim();

			// Putting key & value in dataMap
			dataMap.put(key, value);

			// Putting dataMap to excelFileMap
			excelFileMap.put("DataSheet", dataMap);
		}

		// Returning excelFileMap
		return excelFileMap;

	}

	// Method to retrieve value
	public static String getMapData(String key) throws IOException {

		Map<String, String> m = getLocators().get("DataSheet");
		String value = m.get(key);
		//System.out.println(value);
		return value;

	}

}