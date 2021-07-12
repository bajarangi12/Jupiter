package com.screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utility.DriverManager;

import io.cucumber.datatable.DataTable;

public class HomeScreen {

	// @FindBy(id="forename")
	// WebElement ForeNameElement;

	BaseScreen baseScreen = new BaseScreen();

	public static final String idforName = "ID_forename";
	public static final String forNameErroe = "ID_forename-err";
	public static final String emailIdError = "ID_email-err";

	public static final String messagedError = "ID_message-err";
	public static final String forName = "ID_forename";
	public static final String email = "ID_email";
	public static final String messageLocator = "ID_message";
	public static final String alertSuccess = "Xpath_//*[@class='alert alert-success']";
	WebDriver driver = DriverManager.getDriver();

	public void validateErrorMessageForMAndatoryfield(DataTable dataTable) {

		List<Map<String, String>> allData = dataTable.asMaps(String.class, String.class);
		Map<String, String> map = new HashMap<>();
		int ExpextedRowCount = allData.size();
		int ActualCount = 0;

		baseScreen.waitForElementToPresent(idforName);

		if (ExpextedRowCount > 0) {
			for (int i = 0; i < ExpextedRowCount; i++) {
				map = allData.get(i);
				if (map.get("FieldName").equalsIgnoreCase("Forename") || map.get("FieldName").equalsIgnoreCase("Email")
						|| map.get("FieldName").equalsIgnoreCase("Message")) {
					if (map.get("FieldName").equalsIgnoreCase("Forename"))

					{

						WebElement foreNameErrorMesage = baseScreen.waitForElementToPresent(forNameErroe);
						String forenameMsg = foreNameErrorMesage.getText();

						if (forenameMsg.trim().equals(map.get("ErrorMessage").trim())) {
							ActualCount++;
						}
					}

					if (map.get("FieldName").equalsIgnoreCase("Email"))

					{

						WebElement emailError = baseScreen.waitForElementToPresent(emailIdError);
						String emailErrorMesage = emailError.getText();

						if (emailErrorMesage.trim().equals(map.get("ErrorMessage").trim())) {
							ActualCount++;
						}
					}
					if (map.get("FieldName").equalsIgnoreCase("Message"))

					{

						WebElement foreErrorMesage = baseScreen.waitForElementToPresent(messagedError);
						String forenameMsg = foreErrorMesage.getText();

						if (forenameMsg.trim().equals(map.get("ErrorMessage").trim())) {
							ActualCount++;
						}
					}
				}
			}
		}

		System.out.println("Total number of error messages validated: " + ActualCount);
		Assert.assertEquals(ExpextedRowCount, ActualCount);
	}

	public void enterMandatoryData(DataTable dataTable) {

		Map<String, String> map = baseScreen.getAllTest(dataTable);
		baseScreen.waitForElementToPresent(idforName);
		baseScreen.enterValueinTextFiled(forName, map.get("Forename"));
		baseScreen.enterValueinTextFiled(email, map.get("Email"));
		baseScreen.enterValueinTextFiled(messageLocator, map.get("Message"));

	}

	public void validateErrorsNoMore() {

		baseScreen.CheckElementNotExist("ID_forename-err");
		baseScreen.CheckElementNotExist("ID_email-err");
		baseScreen.CheckElementNotExist("ID_message-err");

	}

	public void verifySuccessMsg(String name) {

		String successMsg;
		baseScreen.waitForElementToPresent(alertSuccess);
		successMsg = baseScreen.getText(alertSuccess);
		Assert.assertEquals("Thanks " + name + ", we appreciate your feedback.", successMsg);

	}

	public void validateErrorMessageforInvalidEmailId(DataTable dataTable) {
		Map<String, String> map = new HashMap<>();
		map = baseScreen.getAllTest(dataTable);

		baseScreen.waitForElementToPresent(idforName);

		String expectedErrorMsg = map.get("ErrorMessage");
		System.out.println("Expected error message  for invalid email==" + expectedErrorMsg);
		baseScreen.waitForElementToPresent(emailIdError);
		baseScreen.getText(emailIdError);
		System.out.println("Actual error message  for invalid email==" + baseScreen.getText(emailIdError));
		Assert.assertEquals(expectedErrorMsg.trim(), baseScreen.getText(emailIdError).trim());
	}

}
