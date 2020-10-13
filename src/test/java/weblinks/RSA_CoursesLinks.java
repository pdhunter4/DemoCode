package weblinks;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.DataProviders;

public class RSA_CoursesLinks {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://courses.rahulshettyacademy.com/courses");
	}
	
	@Test(dataProviderClass=DataProviders.class,dataProvider="getCoursesName")
	public void checkCoursesLink(String courseName) {
		String customizedXPATH = "//div[contains(text(),'"+ courseName + "')]/ancestor::a";
		driver.findElement(By.xpath(customizedXPATH)).click();
		String courseSelected = driver.findElement(By.cssSelector(".course-title")).getText();
		Assert.assertTrue(courseSelected.contains(courseName));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}


}
