package myTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DatePicker2 {

	public static void main(String[] args) {
		
		String currentDate = "21-Feb-2021";
		String givenAirline = "Air India";

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get("https://www.path2usa.com/travel-companions");
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//click to open the date table
		driver.findElement(By.id("travel_date")).click();
		
		//First click on month to go for year selection
		driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[1]/th[2]")).click();
		
		//Now check if year show is as per given year
		WebElement yearPicker = driver.findElement(By.xpath("//div[@class='datepicker-months']/table/thead/tr[1]/th[2]"));
		String year = yearPicker.getText();
		String givenYear = currentDate.split("-")[2].trim();
		while(!year.equalsIgnoreCase(givenYear)) {
			//click on next button to move to next year
			driver.findElement(By.xpath("//div[@class='datepicker-months']/table/thead/tr[1]/th[3]")).click();
			//update the year
			year = yearPicker.getText();
		}
		
		//now evaluate the month we have to select
		List<WebElement> monthPicker = driver.findElements(By.xpath("//div[@class='datepicker-months']/table/tbody/tr/td/span"));
		String givenMonth = currentDate.split("-")[1].trim();
		for(int i=0;i<monthPicker.size();i++) {
			String month = monthPicker.get(i).getText();
			if(givenMonth.equalsIgnoreCase(month)) {
				monthPicker.get(i).click();
				break;
			}
		}
		
		
		//now evaluate the date and select the date
		List<WebElement> datePicker = driver.findElements(By.xpath("//div[@class='datepicker-days']/table/tbody/tr//td[@class='day']"));
		String givenDate = currentDate.split("-")[0].trim();
		for(int i=0;i<datePicker.size();i++) {
			String date = datePicker.get(i).getText();
			if(givenDate.equalsIgnoreCase(date)) {
				datePicker.get(i).click();
				break;
			}
		}
		
		//Select Airline from the select dropdown
		WebElement airlineOptions = driver.findElement(By.id("travel_airline"));
		Select selectAirline = new Select(airlineOptions);
		List<WebElement> airlineList = selectAirline.getOptions();
		for(int i=0;i<airlineList.size();i++) {
			String airline = airlineList.get(i).getText();
			if(airline.contains(givenAirline)) {
				airlineList.get(i).click();
				break;
			}
		}
		
		
		
	}

}
