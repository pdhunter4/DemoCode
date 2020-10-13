package datepicker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utilities.datepicker.SelectDate;

public class Exercise1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String startDate = "23/December/2020";
		String endDate	= "24/March/2021";
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.expedia.co.in/");
		
		driver.findElement(By.cssSelector("#d1-btn")).click();
		
		// Check-in Date
		SelectDate.dateSelector(driver, startDate);
		
		//Checkout
		SelectDate.dateSelector(driver, endDate);
		
		//now click done to confirm the dates
		driver.findElement(By.cssSelector("div[class*='uitk-new-date-picker-menu-footer'] button")).click();
		
		driver.quit();
		
	}

}
