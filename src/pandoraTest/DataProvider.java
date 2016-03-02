package pandoraTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class DataProvider {
	
	private static final String DATA_SOURCE = "/resources/locators.xls";
	
	public static String getLocator(String key){
		String value = null;
		HSSFWorkbook workbook = null;
		
		InputStream iStream = DataProvider.class.getResourceAsStream(DATA_SOURCE);
		if (iStream == null){
			String msg = "File not found " + DATA_SOURCE;
			System.out.println(msg);
			throw new RuntimeException(msg);
		}
		
		try {
			workbook = new HSSFWorkbook(iStream);
		} catch ( IOException e) {
			String msg = "Error oppening workbook " + DATA_SOURCE;
			System.out.println(msg);
			throw new RuntimeException(msg);
		}
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowItr = sheet.rowIterator();
		
		while (rowItr.hasNext()){
			Row row = rowItr.next();
			if (row.getCell(0).getStringCellValue().equals(key)) {
				value = row.getCell(1).getStringCellValue();
				System.out.println(">>> DataProvider.getLocator('" + key + "')" + " - " + value);
				break;
			}
		}
		
		try {
			workbook.close();
			iStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (value == null){
			System.out.println("Locator has not been found for - " + key);
		}
		
		return value;
		
	}

}
