package com.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.yaml.snakeyaml.Yaml;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	static WebDriver driver = null;

	public static WebDriver launchBrowser(String browserName) {

		if (browserName != null && !browserName.isEmpty()) {
			if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				System.out.println("BrowserOpen in-------------- " + browserName);
			} else if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				System.out.println("BrowserOpen in-------------- " + browserName);
			} else if (browserName.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new ChromeDriver();
				System.out.println("BrowserOpen in-------------- " + browserName);
			} else {
				System.out.println("Please provide correct browser name");
			}

			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			LogUtils.info("Browser open success in " + browserName);
		}

		else {
			LogUtils.error("correct browser name in config file");
			;
			System.out.println(
					"Please check provided value in config file.==== " + browserName + "does not exist in Yaml file");
		}

		return driver;

	}

	public static WebDriver getDriver() {
		// this.driver=driver;
		return driver;
	}

}
