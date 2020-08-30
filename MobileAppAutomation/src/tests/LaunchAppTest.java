package tests;

import Core.MainDriver;
import Core.Utils;
import Mobile.Keywords;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
@SuppressWarnings("rawtypes")
public class LaunchAppTest extends MainDriver{
	static Logger logger = Logger.getLogger("devpinoyLogger");
	
	@SuppressWarnings("unchecked")
	@Test(description="Verify that user is able to Launch Application")
	
	public void launchApp()throws Exception {
		AppiumDriver driver = initAndroidDriver();
		Keywords kw= new Keywords(driver);
		Utils util = new Utils(driver);
		
		AppHomePage appHomePage = new AppHomePage(driver);
		logger.info("Executing launch to application scenario.");
		
		HashMap<String,String> map= new HashMap<String, String>();
		map=util.readXML("CropHme");
	
		Assert.assertTrue(appHomePage.launchApp(map),"FAILED: App launch not successfull");
		logger.info("App launch Scenarios ends.");
		kw.quitDriver(driver);
	}
}	
