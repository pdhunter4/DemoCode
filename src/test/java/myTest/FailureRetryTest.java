package myTest;

import org.testng.Assert;
import org.testng.annotations.Test;


public class FailureRetryTest {
	
	@Test(retryAnalyzer = analyzer.RetryAnalyzer.class)
	public void test1() {
		Assert.assertEquals(true,false);
	}
	
	@Test(retryAnalyzer = analyzer.RetryAnalyzer.class)
	public void test2() {
		Assert.assertEquals(true,false);
	}
	
	

}
