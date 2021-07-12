package hook;

import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.utility.ConfigReaderUtils;
import com.utility.DriverManager;
import com.utility.LogUtils;

import freemarker.log.Logger;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hook {

	Properties properties;
	private DriverManager driverManager;
	private WebDriver driver;
	private ConfigReaderUtils configReader;
	//private Scenario sceanrio;
	
	

	
	@Before(order=0)
	public void launchBrowser(){
		configReader= new ConfigReaderUtils();
		properties=configReader.propload();
		String browserName=properties.getProperty("BrowserName");
		DOMConfigurator.configure("log4j.xml");
		//Scenario sn
		LogUtils.info("browser value  fetch"+browserName);
		System.out.println("browe---"+browserName);
		
		driverManager=new DriverManager();
		DriverManager.launchBrowser(browserName);
	}
	
	
	@AfterStep()
	public void takeScreenShot(Scenario scenario){
		String screenshotName=scenario.getName().replaceAll(" ", "_");
		
		byte[] src=((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
		
		scenario.attach(src, "image/png", screenshotName);
	}
	
	 @After(order = 1)
	    public void tearDown(Scenario scenario) {
	        if (scenario.isFailed()) {
	            // take screenshot:
	            String screenshotName = scenario.getName().replaceAll(" ", "_");
	            byte[] src=((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	    		
	    		scenario.attach(src, "image/png", screenshotName);

	        }
	    }
	
	
	@After(order = 0)
	public void tearDown(){	
		DriverManager.getDriver().close();
		DriverManager.getDriver().quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	
	
}
