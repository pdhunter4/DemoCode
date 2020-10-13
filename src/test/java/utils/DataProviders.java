package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider
	public String[] getCoursesName() {
		String[] courseNames = {"Web Security","Selenium Webdriver","Learn SQL in Practical",
								"Appium","WebServices/REST"};
		return courseNames;
	}

}
