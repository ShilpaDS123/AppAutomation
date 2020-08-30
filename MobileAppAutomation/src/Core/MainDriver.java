package Core;
import java.io.FileNotFoundException;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest; 
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MainDriver {
	public static AppiumDriver<MobileElement> driver;
	public static Properties CONFIG;
	public static DesiredCapabilities capabilities;
	public static URL url;
	public static Properties prop;
	public static String congFile="Configuration\\config.properties";
	public static void main(String[] args)   {
		initAndroidDriver();
	}
	private AppiumDriver<?> initAndroidDriver()
	{
		capabilities = new DesiredCapabilities();
		prop = readPropertiesFile(congFile);
		try {
			capabilities.setCapability("deviceName", prop.getProperty(deviceName));
			capabilities.setCapability("platformVersion", prop.getProperty(AndroidVersion));
			capabilities.setCapability("platformName", prop.getProperty(Platform));
			capabilities.setCapability("udid", prop.getProperty(DeviceId));
			capabilities.setCapability("appPackage", prop.getProperty(AppPackage));
	    	capabilities.setCapability("appActivity", prop.getProperty(AppActivity));
			capabilities.setCapability("noReset","prop.getProperty(FullReset"));
			url = new URL("https://127.0.0.1:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url,capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
			Thread.sleep(10000);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver;
	}
	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try 
		  {
			 fis = new FileInputStream(fileName);
			 prop = new Properties();
			 prop.load(fis);
		  } catch(FileNotFoundException fnfe) {
			 fnfe.printStackTrace();
		  } catch(IOException ioe) {
			 ioe.printStackTrace();
		  } finally {
			 fis.close();
		  }
		  return prop;
	}
}