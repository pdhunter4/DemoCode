package advancedcalenderui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class CalenderAutomation {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://jqueryui.com/datepicker/#date-range");
		
		WebElement frameElement = driver.findElement(By.tagName("iframe"));
		//Switch to frame to access the calender
		driver.switchTo().frame(frameElement);
		//Click on from date picker
		driver.findElement(By.id("from")).click();
		
		//select month
		WebElement monthElement = driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div[1]/div/div/select"));
		Select monthOptions = new Select(monthElement);
		monthOptions.selectByVisibleText("Dec");
		
		//select date
		WebElement firstGroup = driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-group-first')]"));
		List<WebElement> dateElements = firstGroup.findElements(By.xpath("//a[@class='ui-state-default']"));
		
		for(int i=0;i<dateElements.size();i++) {
			if(dateElements.get(i).getText().contains("23")) {
				dateElements.get(i).click();
				break;
			}
		}
		driver.close();

		
		
	}

}
