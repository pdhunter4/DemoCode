package weblinks;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RSACoursesLinkTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://courses.rahulshettyacademy.com/courses");
		
		List<WebElement> coursesList = driver.findElements(By.xpath("//div[@class='course-listing-title']"));
		
		coursesList.stream().filter(ele -> ele.getText().contains("Selenium Webdriver"))
		.forEach(WebElement::click);

		coursesList.stream().filter(ele -> ele.getText().contains("Selenium Webdriver"))
		.forEach(ele -> clickOnCourse(ele));

	}
	
	public static void clickOnCourse(WebElement element) {
		element.findElement(By.xpath("ancestor::a")).click();
	}
	
	
}
