package dataproviderWithHashMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class HashMapDataProvider {
	
	/*
	 * 1. Use FileInputStream to locate the file
	 * 2. Now use XSSFWorkbook and give the file instance to the XSSFWorkbook
	 * 3. from the XSSFWorkbook get the desired sheet object 
	 * 4. using sheet::rowIterator store all the rows in the Iterable class Iterable<Row>
	 * 5. 
	 */
	
	@DataProvider
	public static Object[][] getDataFromExcel() throws IOException {
		
		FileInputStream rsaDataFile = new FileInputStream(System.getProperty("user.dir")
										+ "\\src\\main\\java\\testData\\RSAData.xlsx");
		
		XSSFWorkbook book = new XSSFWorkbook(rsaDataFile);
		XSSFSheet sheet = book.getSheet("Registration_Data");
		Iterable<Row> rows = sheet::rowIterator;
		List<Map<String,String>> results = new ArrayList<>();
		boolean header = true;
		List<String> keys = null;
		for(Row row : rows) {
			List<String> values = getValuesFromRow(row);
			if(header == true) {
				header = false;
				keys = values;
				continue;
			}
			results.add(transformIntoMap(keys,values));
		}
		return convertToTwoDimesionalArray(results);
	}
	
	private static Object[][] convertToTwoDimesionalArray(List<Map<String,String>> finalData) {
		Object[][] results = new Object[finalData.size()][1];
		int index = 0;
		for(Map<String,String> data : finalData) {
			results[index++] = new Object[] {data};
		}
		return results;
	}
	
	private static Map<String, String> transformIntoMap(List<String> keys, List<String> values) {
		Map<String,String> results = new HashMap<>();
		for(int i=0;i<keys.size();i++) {
			String key = keys.get(i);
			String value = values.get(i);
			results.put(key, value);
		}
		return results;
	}
	
	private static List<String> getValuesFromRow(Row row) {
		DataFormatter formatter = new DataFormatter();
		List<String> data = new ArrayList<>();
		Iterable<Cell> cells = row::cellIterator;
		for(Cell cell : cells) {
			data.add(formatter.formatCellValue(cell));
		}
		return data;
	}

}
