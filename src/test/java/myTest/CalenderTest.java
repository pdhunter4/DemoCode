package myTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalenderTest {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		
		String expectedSource = "Nauru Island (INU)";
		String expectedDestination = "Paris (CDG)";
		//Departure city
		WebElement sourceElement = driver.findElement(By.id("gosuggest_inputSrc"));
		sourceElement.click();
		sourceElement.clear();
		sourceElement.sendKeys("in");
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//Click on defined source 
		String receivedSource = null;
		do {
			
			sourceElement.sendKeys(Keys.ARROW_DOWN);
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receivedSource = js.executeScript("return document.getElementById('gosuggest_inputSrc').value").toString();
		}while(!receivedSource.equalsIgnoreCase(expectedSource));
		
		//Click enter to select the source 
		sourceElement.sendKeys(Keys.ENTER);
		
		
		/*
		 * Destination
		 */
		
		
		
		
		String date = "20-March 2021";
		
		String expectedDate = date.split("-")[0].trim();
		String expectedMonth = date.split("-")[1].trim();
		
		//Click on Departure date box
		driver.findElement(By.id("departureCalendar")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='application']")));
		
		//get which month is present
		String month = driver.findElement(By.xpath("//div[@role='heading']")).getText();
		
		while(!month.contentEquals(expectedMonth)) {
			//click on next button
			driver.findElement(By.xpath("//span[contains(@class,'DayPicker-NavButton--next')]")).click();
			//update the month
			month = driver.findElement(By.xpath("//div[@role='heading']")).getText();
		}
		
		//Now Select Date dynamically
		String dateSelector = "fare_202103" +  expectedDate;
		driver.findElement(By.id(dateSelector)).click();
		
		
	}

}
