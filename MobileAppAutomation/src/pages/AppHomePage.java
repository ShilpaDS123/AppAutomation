package pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import Mobile.Keywords;

import Core.MainDriver;
import Core.Utils;
public class AppHomePage {
	static Logger logger = Logger.getLogger("devpinoyLogger");
	public AppiumDriver<MobileElement> driver;
	Keywords keyword;
		
	public AppHomePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}
	public boolean launchApp(HashMap<String, String> map) throws InterruptedException{
		keyword = new Keywords(driver);
		Thread.sleep(2000);
		boolean lanchSts=false;
		String appTitle = (String)map.get("appInitialTitle");
		String actTitle = (String)map.get("appTtle");
		
		if(keyword.waitForElement(appTitle,"10") || keyword.waitForElement(actTitle,"10")) {
			lanchSts=true;
		}
		else{
			logger.info("fail to launch app");
			lanchSts=false;
		}
		return lanchSts;
	}
}
