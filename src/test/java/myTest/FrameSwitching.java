package myTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameSwitching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://jqueryui.com/draggable/");
		
		//switch to frame to access the webelements
		//WebElement frameElement = driver.findElement(By.className("demo-frame"));
		WebElement frameElement = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(frameElement);
		
		System.out.println(driver.findElement(By.id("draggable")).getText());

	}

}
