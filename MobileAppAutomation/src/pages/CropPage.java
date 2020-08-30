package pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import Mobile.Keywords;

import Core.MainDriver;

public class CropPage {
	static Logger logger = Logger.getLogger("devpinoyLogger");
	public AppiumDriver<MobileElement> driver;
	Keywords keyword;
		
	public CropPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}
	public boolean addCrop(HashMap<String, String> tstDataMap,HashMap<String, String> objLocatorMap) throws InterruptedException{
		keyword = new Keywords(driver);
		Thread.sleep(2000);
		boolean cropSts=false;
		boolean eleSts=fasle;
		boolean navigateCropSts=false;
		String title = (String)objLocatorMap.get("cropTitle");
		String frmFld=(String)objLocatorMap.get("farmNme");
		String riceFld=(String)objLocatorMap.get("riceVariety");
		String prdFld=(String)objLocatorMap.get("period");
		String frmSizeFld=(String)objLocatorMap.get("frmSize");
		String addFstCrop = (String) objLocatorMap.get("addFstCrop");
		String newSample = (String) objLocatorMap.get("newSampleBtn");
		String newSampleTtle = (String) objLocatorMap.get("newsamplePgeTitle");
		String adNxtCrop=(String) objLocatorMap.get("newSampleBtn");
		String saveBtn=(String) objLocatorMap.get("saveBtn");
		String instn=(String) objLocatorMap.get("instn");
		
		String frmName=(String) tstDataMap.get("FarmName");
		String riceVariety=(String) tstDataMap.get("RiceVariety:");
		String growingPeriod=(String) tstDataMap.get("GrowingPeriod:");
		String farmSize=(String) tstDataMap.get("FarmSize:");
		navigateCropSts=navigateToCropPage(addFstCrop,newSample,adNxtCrop,newSampleTtle);
		if(navigateCropSts) {
			cropSts=keyword.waitForElement(title,"10");
			if(cropSts) {
				keyword.clearTextField(frmFld);
				keyword.typeValue(frmFld,frmName);
				String riceObj="//*[@text=riceVariety]";
				keyword.clickElement(riceObj);
				String prdObj="//*[@text=growingPeriod]";
				keyword.clickElement(prdObj);
				String frmObj="//*[@text=farmSize]";
				keyword.clickElement(frmObj);
				Thread.sleep(1000);
				keyword.clickElement(saveBtn
				if(keyword.waitForElement(instn,"10")) {
					cropSts=true;
					logger.info("successfully added crop details");
				}
				else{
					cropSts=false;
					logger.info("fail to add crop details");
				}
			}
			else {
				logger.info("add crop page not found");
				cropSts=false;
			}
		}
		else {
			logger.info("Add Crop button not found");
			cropSts=false;
		}
		return cropSts;
	}
	public boolean navigateToCropPage(String adFstCrop,String newSamples,String adNextCrop,String newSampleTtle) {
		Boolean eleFnd=false;
		if(keyword.waitForElement(adFstCrop,"10")) {
			keyword.clickElement(adFstCrop);
			eleFnd=true;
		}
		else if(keyword.waitForElement(newSamples,"10")) {
			keyword.clickElement(newSamples);
			if(keyword.waitForElement(newSampleTtle,"10")) {
				keyword.clickElement(adNextCrop);
				eleFnd=true;
			}
			else {
				eleFnd=false;
			}
		}
		else {
			eleFnd=false;
		}
		return eleFnd;
	}
}
