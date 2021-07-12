package stepDef;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.screens.BaseScreen;
import com.screens.HomeScreen;
import com.screens.ShopScreen;
import com.utility.ConfigReaderUtils;
import com.utility.DriverManager;
import com.utility.LogUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stefDefinition {

	Properties props;
	BaseScreen baseScreen = new BaseScreen();

	WebDriver driver = DriverManager.getDriver();

	HomeScreen homeScreen = new HomeScreen();
	ShopScreen shopScreen = new ShopScreen();

	@Given("I launch browser and open application")
	public void i_launch_browser_and_open_application() {
		baseScreen.navigateToApplication();
		LogUtils.info("url open sucess");
	}

	@Given("Verify title of application")
	public void verify_title_of_application() {
		// Write code here that turns the phrase above into concrete actions
		String title = baseScreen.geTitle();
		System.out.println("====title==" + title);
		Assert.assertEquals("Jupiter Toys", baseScreen.geTitle().trim());

	}

	@When("I click on {string} link")
	public void i_click_on_link(String string) {

		baseScreen.clickOnElement("LinkText_" + string);
	}

	@When("user validates errors  for below")
	public void user_validates_errors_for_below(io.cucumber.datatable.DataTable dataTable) {

		homeScreen.validateErrorMessageForMAndatoryfield(dataTable);
	}

	@When("I fill the mandatory field in form")
	public void i_fill_the_mandatory_field_in_form(io.cucumber.datatable.DataTable dataTable) {

		homeScreen.enterMandatoryData(dataTable);
	}

	@Then("user validates all errors are gone")
	public void user_validates_all_errors_are_gone() {
		homeScreen.validateErrorsNoMore();
	}

	@Then("I validates successful submission message for {string}")
	public void user_validates_successful_submission_message_for(String string) {

		homeScreen.verifySuccessMsg(string);

	}

	@Then("I validates error message for invalid for emailid")
	public void i_validates_error_message_for_invalid_for_emailid(io.cucumber.datatable.DataTable dataTable) {
		homeScreen.validateErrorMessageforInvalidEmailId(dataTable);
	}

	@When("I  Added below item  in my cart")
	public void i_added_below_item_in_my_cart(io.cucumber.datatable.DataTable dataTable) {
		shopScreen.addItems(dataTable);
	}

	@When("I  clicks on cart menu")
	public void i_clicks_on_cart_menu() {

		shopScreen.clickOnCart();

	}

	@Then("I validates added item in cart")
	public void i_validates_added_item_in_cart(io.cucumber.datatable.DataTable dataTable) {

		shopScreen.verfiyitemInCartDetail(dataTable);
	}
}
