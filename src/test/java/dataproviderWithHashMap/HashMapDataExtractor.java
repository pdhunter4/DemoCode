package dataproviderWithHashMap;

import java.util.Map;

import org.testng.annotations.Test;

public class HashMapDataExtractor {
	
	@Test(dataProviderClass=HashMapDataProvider.class,dataProvider="getDataFromExcel")
	public void registrationData(Map<String,String> data) {
		System.out.println(data);
	}

}
