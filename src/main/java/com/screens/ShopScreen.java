package com.screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utility.DriverManager;
import com.utility.LogUtils;

import io.cucumber.datatable.DataTable;

public class ShopScreen {

	BaseScreen baseScreen = new BaseScreen();
	WebDriver driver = DriverManager.getDriver();
	public static int totalQty = 0;
	public static final String cartXpath = "xpath_//*[@id='nav-cart']/a";
	public static final String cartTableXpath = "xpath_//table[@class='table table-striped cart-items']";

	public void addItems(DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		Map<String, String> map = new HashMap<>();
		int rowCount = list.size();
		int countItems = 0;
		String xpathBuy = "";

		if (rowCount > 0) {
			for (int count = 0; count < rowCount; count++) {
				map = list.get(count);
				int Quantitycount = Integer.parseInt(map.get("Count"));
				totalQty = totalQty + Quantitycount;
				String itemName = map.get("ItemName");
				System.out.println("Item Name: " + itemName + "\nItem Quantity: " + Quantitycount);
				xpathBuy = "//*[text()='" + itemName + "']" + "/..//a";
				baseScreen.waitForElement(xpathBuy);
				for (int num = 0; num < Quantitycount; num++) {
					driver.findElement(By.xpath(xpathBuy)).click();
				}
				xpathBuy = "";
				countItems++;
			}
			System.out.println("Total number of items validated: " + countItems);
			Assert.assertEquals(rowCount, countItems);
		}

	}

	public void clickOnCart() {

		baseScreen.clickOnElement(cartXpath);

	}

	public void verfiyitemInCartDetail(DataTable datatable) {

		List<Map<String, String>> list = datatable.asMaps(String.class, String.class);
		Map<String, String> map = new HashMap<>();
		int listsize = list.size();
		int quantityValidatedCounter = 0;

		
		System.out.println("List size: "+ listsize);
		if (listsize > 0) {
			for (int count = 0; count < listsize; count++) {
				map = list.get(count);

				String ExpectedItemName = map.get("ItemName");
				System.out.println("ExpectedItemName item manme--" + ExpectedItemName);
				String ExpectedQuantityName = map.get("Count");
				System.out.println("ExpectedQuantityName--" + ExpectedQuantityName);
				WebElement BooksTable = baseScreen.waitForElementToPresent(cartTableXpath);

				List<WebElement> rowVals = BooksTable.findElements(By.tagName("tr"));
				System.out.println("row count==" + rowVals.size());
				List<WebElement> colHeader = rowVals.get(0).findElements(By.tagName("th"));
				System.out.println("column count==" + colHeader.size());
				for (int i = 1; i < rowVals.size() - 2; i++) {

					List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));
					colVals.get(0).getText();
					System.out.println(" new value----" + colVals.get(0).getText());
					if (colVals.get(0).getText().equalsIgnoreCase(ExpectedItemName)) {
						for (int j = 0; j < colVals.size(); j++) {
							// Print the coulumn values to console
							System.out.println(colVals.get(j).getText());
							if (colVals.get(j).getText().equalsIgnoreCase(ExpectedItemName)) {
								LogUtils.info("quantity-matched Expected iteam" + ExpectedItemName
										+ " and   actual Expected iteam" + colVals.get(j).getText());
								rowVals.get(i).findElements(By.xpath(".//input"));

								if (rowVals.get(i).findElements(By.xpath(".//input")).size() > 0) {
									String actualQuantity = rowVals.get(i).findElement(By.xpath(".//input"))
											.getAttribute("value");
									System.out.println("quantity-----" + actualQuantity);
									if (ExpectedQuantityName.equalsIgnoreCase(actualQuantity)) {
										System.out.println("quantity-matched ----");
										LogUtils.info("quantity-matched Expected QuantityName" + ExpectedQuantityName
												+ "   actualQuantity" + actualQuantity);
										quantityValidatedCounter++;
										j = colVals.size() + 1;
										i = rowVals.size() + 2;
										break;
									}
								}
							}

						}
					}
				}

			}
		}
		Assert.assertEquals(quantityValidatedCounter, listsize);

	}

}
