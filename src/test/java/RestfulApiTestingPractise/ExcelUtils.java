package RestfulApiTestingPractise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] getTableArray(String filePath, String SheetName) throws Exception {
		Object[][] tabArray = null;

		try {
			
			File file = new File(filePath);
			
			System.out.print("Is Exists? " + file.exists());
			System.out.print("Can Readable? " +file.canRead());
			
			FileInputStream excelFile = new FileInputStream(filePath);
			Workbook excelWBook = new XSSFWorkbook(excelFile);
			Sheet excelWSheet = excelWBook.getSheet(SheetName);

			int totalRows = excelWSheet.getLastRowNum() + 1;
			int totalCols = excelWSheet.getRow(0).getLastCellNum();

			tabArray = new Object[totalRows][totalCols];

			for (int i = 0; i < totalRows; i++) {
				
				Row row = excelWSheet.getRow(i);
				
				for (int j = 0; j < totalCols; j++) {
					
					Cell cell = row.getCell(j);
					
					if(CellType.NUMERIC == cell.getCellType()) {
						tabArray[i][j] = cell.getNumericCellValue();
					} else if(CellType.STRING == cell.getCellType()) {
						tabArray[i][j] = cell.getStringCellValue();
					} else if(CellType.BOOLEAN == cell.getCellType()) {
						tabArray[i][j] = cell.getBooleanCellValue();
					} else {
						tabArray[i][j] = "";
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}

		return tabArray;
	}
}
