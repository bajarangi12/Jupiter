package com.screens;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.utility.ConfigReaderUtils;
import com.utility.DriverManager;
import com.utility.LogUtils;

import io.cucumber.datatable.DataTable;

public class BaseScreen {

	WebDriver driver = DriverManager.getDriver();

	WebDriverWait wait = new WebDriverWait(driver, 30);
	ExtentReports report = new ExtentReports();

	public void navigateToApplication() {
		driver = DriverManager.getDriver();
		ConfigReaderUtils confiUtils = new ConfigReaderUtils();
		String webURL = confiUtils.propload().getProperty("url");
		driver.get(webURL);
		LogUtils.info("url open sucess" + webURL);

	}

	public WebElement waitForElementToPresent(String locatorTypeAnValue) {
		WebElement webElement = null;

		FluentWait<WebDriver> fluentWait;
		String[] splited = locatorTypeAnValue.split("_");
		String locatorType = splited[0];
		String locatorValue = splited[1];

		fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(3000))
				.ignoring(NoSuchElementException.class);
		if (locatorType.equalsIgnoreCase("id"))
			webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
		else if (locatorType.equalsIgnoreCase("xpath"))
			webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
		else if (locatorType.equalsIgnoreCase("LinkText"))
			webElement = fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
		if (webElement != null) {
			System.out.println("Element found");

			LogUtils.info("Element found for given locator value---" + locatorValue);
			return webElement;
		} else {
			System.out.println("Element not found.Please provide correct value ");
			LogUtils.info("Element found for given locator value---" + locatorValue);
			LogUtils.error("Element not found for  given locator value---" + locatorValue);

		}
		return webElement;
	}

	public void clickOnElement(String locatorTypeAndVAlue) {
		WebElement webElement = null;
		webElement = waitForElementToPresent(locatorTypeAndVAlue);
		webElement.click();
		LogUtils.info("clicked on element---" + locatorTypeAndVAlue);

	}

	public String getText(String locatorTypeAndVAlue) {
		WebElement webElement = null;
		webElement = waitForElementToPresent(locatorTypeAndVAlue);

		LogUtils.info("get text value for ---" + locatorTypeAndVAlue + "is---" + webElement.getText());
		return webElement.getText();
	}

	public String geTitle() {

		LogUtils.info("Title ---" + driver.getTitle().toString());

		return driver.getTitle().toString();
	}

	public void enterValueinTextFiled(String locatorTypeAndVAlue, String valueToEnter) {
		WebElement webElement = null;
		webElement = waitForElementToPresent(locatorTypeAndVAlue);
		webElement.clear();
		webElement.sendKeys(valueToEnter);
		LogUtils.info("value entered in text field success for " + valueToEnter);
	}

	public WebElement waitForElement(String path) {
		FluentWait<WebDriver> fluentWait;
		WebElement webElement;
		fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(3000))
				.ignoring(NoSuchElementException.class);

		webElement = fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
		return webElement;
	}

	public void CheckElementNotExist(String locatorTypeAnValue) {
		String[] splited = locatorTypeAnValue.split("_");
		String locatorType = splited[0];
		String locatorValue = splited[1];
		int size = 0;
		if (locatorType.equalsIgnoreCase("id"))
			size = driver.findElements(By.id(locatorValue)).size();
		if (locatorType.equalsIgnoreCase("xpath"))
			size = driver.findElements(By.xpath(locatorValue)).size();
		System.out.println("size---" + size);

		Assert.assertEquals(0, size);

	}

	public void clickOnLink(String linkText) {
		WebElement webElement;
		FluentWait<WebDriver> fluentWait;
		fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(3000))
				.ignoring(NoSuchElementException.class);
		webElement = fluentWait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		webElement.click();
	}

	public Map<String, String> getAllTest(DataTable dataTable) {

		Map<String, String> map = new HashMap<>();

		try {
			List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
			if (list.size() > 0) {
				map = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	public WebDriver getWebDriver() {
		return getWebDriver();
	}

}
