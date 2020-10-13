package utilities.datepicker;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectDate {
	
	public static void dateSelector(WebDriver driver,String dateToBeSelected) {
		String[] startDateSpliter = dateToBeSelected.split("/");
		String expectedMonthYear = startDateSpliter[1].trim() + " " + startDateSpliter[2].trim();
		String receivedMonthYear =driver.findElement(By.cssSelector("div[class='uitk-new-date-picker-menu-months-container'] div:nth-child(1) h2")).getText();
		WebElement nextButton = driver.findElement(By.cssSelector("div[class*='uitk-new-date-picker-menu-pagination-container'] button:nth-child(2)"));
		
		
		// traversing to the desired Month and Year
		while(!receivedMonthYear.contains(expectedMonthYear)) {
			nextButton.click();
			receivedMonthYear = driver.findElement(By.cssSelector("div[class='uitk-new-date-picker-menu-months-container'] div:nth-child(1) h2")).getText();
		}
		
		//now traverse to the desired date
		List<WebElement> dayList = driver.findElements(By.xpath("//div[@class='uitk-new-date-picker-menu-months-container']/div[1]//button[@class='uitk-new-date-picker-day']"));
		String extractedDate = null;
		for(int i=0;i<dayList.size();i++) {
			extractedDate = dayList.get(i).getAttribute("aria-label").toString().split(" ")[0].trim();
			if(extractedDate.equals(startDateSpliter[0].trim())) {
				dayList.get(i).click();
				break;
			}
		}
	}

}
