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
public class AddCropTest extends MainDriver{
	static Logger logger = Logger.getLogger("devpinoyLogger");
	
	@SuppressWarnings("unchecked")
	@Test(description="Verify that user is able to Launch Application")
	
	public void launchApp()throws Exception {
		
		AppiumDriver driver = initAndroidDriver();
		
		Keywords kw= new Keywords(driver);
		AppHomePage appHomePage = new AppHomePage(driver);
		Utils util=new Utils(driver);
		CropPage cropPage=new CropPage(driver);
		logger.info("Executing launch to application scenario.");
		HashMap<String,String> map= new HashMap<String, String>();
		map=util.readXML("CropHme");
	
		Assert.assertTrue(appHomePage.launchApp(map),"FAILED: App launch not successfull");
		logger.info("App launch Scenarios ends.");
		
		HashMap<String,String> tstData= new HashMap<String, String>();
		tstData=util.readExcel()
		map=util.readXML("AddCrop");
		logger.info("Executing Add crop scenario.");
		Assert.assertTrue(cropPage.addCrop(tstData,map),"FAILED: Add crop functionality not successfull");

		
		kw.quitDriver(driver);
	}
}	
