package Mobile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Keywords {
	public AppiumDriver<?> driver;
	driver.rotate(ScreenOrientation.PORTRAIT);
	static Logger logger = Logger.getLogger("devpinoyLogger");
	
	
	public Keywords(AppiumDriver<?> driver) {
		this.driver = driver;
	}
	public boolean waitForElement(String Object, int time){
		boolean elementPresence = false;
		try {

			MobileElement element = driver.findElementByXpath(Object);
			AndroidDriverWait wait= new AndroidDriverWait(driver,time)
			wait.until(ExpectedConditions.visibilityOf(element));
			if (element.isDisplayed()) {
					elementPresence = true;
					return elementPresence;
				}

			} catch (Exception e) {
				System.out.println("Element NOT found");
				elementPresence = false;
				return elementPresence;
			}
			System.out.println("Element Presence is:" +elementPresence);
			return elementPresence;
	}
		public void clickElement(String Object){
			System.out.println("Click Elememt");
			try {
				waitForElement(Object, 15);
				element.click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		public void clearTextField(String Object){
		System.out.println("clearTextField:" +Object);
		try {
			waitForElement(Object, 30);
			if (element.isDisplayed()) {
				element.click();
				Thread.sleep(2000);
				element.clear();
				try {
					driver.hideKeyboard();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				Thread.sleep(2000);

				for (int i = 0; i < 1; i++) {
					String text = element.getAttribute("text");

					if (!text.trim().equalsIgnoreCase("")) {
						element.clear();
						try {
							driver.hideKeyboard();
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}

						Thread.sleep(2000);
					}else {
						System.out.println("Successfully clear the textfield");
						break;
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void typeValue(String Object, String text){
		try {

			boolean elePresence= false;

			for (int i = 0; i < 2; i++) {

				try {
					MobileElement element2 =driver.findElementByXpath(Object);
					elePresence = element2.isDisplayed();
					if (elePresence) {
						element2.click();
					}
				} catch (Exception e) {
					
				}

				if (elePresence) {
					Thread.sleep(2000);
					try {
						MobileElement element2 =driver.findElementByXpath(Object);
						elePresence = element2.isDisplayed();
					} catch (Exception e) {
						elePresence=false;
					}

					try {
						MobileElement element3 =driver.findElementByXpath(Object);
						elePresence = element3.isDisplayed();
					}catch(Exception e){

					}

					if (elePresence) {
						MobileElement element3 =driver.findElementByXpath(Object);
						element3.sendKeys(text);
						try {
							driver.hideKeyboard();
						} catch (Exception e) {
							elePresence=false;
						}

						Thread.sleep(2000);
						break;
					}else {
						elePresence=false;
					}
				}else {
					elePresence=false;
					}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	public void quitDriver(AppiumDriver<?> driverTemp) {

		try {
			if (driverTemp == null) {
				logger.info("There is no session to close...");
			}else {
				logger.info("Closing the driver...");
				driverTemp.quit();
			}

		} catch (Exception E) {
			logger.info("Exception while Quiting driver:"+E);
			
		}

	}
}
	