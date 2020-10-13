package myTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FileDownloadConcept {
	
	WebDriver driver;
	File folder;
	
	@BeforeMethod
	public void setup() {
		
		//assign name to the folder UUID - Random number - 8888-999-22
		folder = new File(UUID.randomUUID().toString());
		// It is to create the file in the directory and return boolean whether file is created or not
		folder.mkdir();				
		
		//Now in chrome you have to specify the path in the runtime where the downloads are to be stored
		Map<String,Object> chromePrefs = new HashMap<String,Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);	// disable the popups
		chromePrefs.put("download.default_directory", folder.getAbsolutePath());	// set default download path
		
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", chromePrefs);
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
		
		//Now check whether the file is downloaded properly
		File[] listOfFiles = folder.listFiles();
		//make sure directory is not empty
		Assert.assertTrue(listOfFiles.length>0);
		
		//Make sure downloaded files are not empty
		for(File file : listOfFiles) {
			Assert.assertTrue(file.length()>0);
		}
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		/*
		 * now whatever files we have created delete all the files
		 */
//		for(File file : folder.listFiles()) {
//			if(file.exists()) {
//				file.delete();
//			}
//		}
//		if(folder.exists()) {
//			// Now delete the folder
//			folder.delete();
//		}
		
		driver.quit();
	}
	

}
