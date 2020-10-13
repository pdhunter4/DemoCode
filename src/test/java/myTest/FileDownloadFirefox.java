package myTest;

import java.io.File;
import java.util.UUID;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileDownloadFirefox {

	WebDriver driver;
	File folder;
	
	@BeforeMethod
	public void setup() {
		
		folder = new File(UUID.randomUUID().toString());
		folder.mkdir();
		
		/*
		 * Firefox Options
		 */
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);	// To save the downloaded file in a specified location 2 is passed
		option.addPreference("browser.download.dir", folder.getAbsolutePath());	// give the path for download
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream,application/x-winzip");
		option.addPreference("pdfjs.disabled", true);
		
		
		/*
		 * Launching Firefox Browser
		 */
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver(option);

		
	}
	
	
	@Test
	public void downloadFileTest() {
		
		driver.get("https://the-internet.herokuapp.com/download");
		
		// this will download the file in the directory which is set
		driver.findElement(By.xpath("//a[contains(@href,'earth.jpg')]")).click();	//file 1
		
		driver.findElement(By.xpath("//a[contains(@href,'hello_world.txt')]")).click(); // file 2
		
		
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		/*
		 * Logic to check whether the folder is create and it consist of files in it
		 */
		File[] listOfFiles = folder.listFiles();
		Assert.assertTrue(listOfFiles.length>0);
		
		/*
		 * Logic to check the folder consisting of files are having content in it
		 */
		for(File file : listOfFiles) {
			Assert.assertTrue(file.length()>0);
		}
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
		/*
		 * Delete the files after the test
		 */
		for(File file : folder.listFiles()) {
			file.delete();
		}
		//Delete the folder
		folder.delete();
		
	}

}
