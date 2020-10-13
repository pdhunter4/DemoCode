package myTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpinnerDemo {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://jqueryui.com/spinner/");
		
		WebElement frameElement = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frameElement);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String value = js.executeScript("return document.getElementById('spinner').value").toString();
		while(!value.equals("10")) {
			driver.findElement(By.xpath("//span[contains(@class,'ui-spinner')]/a[1]")).click();
			value = js.executeScript("return document.getElementById('spinner').value").toString();
		}
		
		

	}

}
