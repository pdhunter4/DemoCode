package myTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BootStrapDropDown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String courseName = "Angular1";
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_3");
		
		driver.findElement(By.cssSelector("button[class*=btn]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul[class*=multiselect-container]")));
		
		List<WebElement> list = driver.findElements(By.xpath("//ul[contains(@class,'multiselect-container')]/li/a/label"));
		
		//This code is used to click on selected value
	/*	for(int i=0;i<list.size();i++) {
			String course = list.get(i).getText();
			if(course.contains(courseName)) {
				list.get(i).click();
				break;
			}
		}*/
		
		
		//This will select all the courses excluding those which are already selected
		for(int i=0;i<list.size();i++) {
			String course = list.get(i).getText();
			if("Web Development Programming Languages Databases".contains(course) == false) {
				if(list.get(i).isSelected() == false)
					list.get(i).click();
			}
		}
		
		//check the course got selected
		String listOfCourses = driver.findElement(By.className("multiselect-selected-text")).getText();
		System.out.println(listOfCourses);
		//Assert.assertTrue(listOfCourses.contains(courseName));
		
	}
	
	

}
