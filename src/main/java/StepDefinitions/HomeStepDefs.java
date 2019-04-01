package StepDefinitions;

//import helpers.Log;

import Pages.PageInitializer;
import Utils.Utils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;


public class HomeStepDefs extends PageInitializer {

    @Then("^I save the screenshot$")
    public void iSaveTheScreenshot() {
        getScreenShot();
    }

    @Given("^I open Auckland Airport website$")
    public void i_open_Auckland_Airport_website() {
        getPage("https://themall.aucklandairport.co.nz");
    }

    @Then("^I validate title and URL$")
    public void i_validate_title_and_URL() {
        assertTrue("AKL Airport home page title is not correct", getPageTitle().contains("Shop duty free and tax free online | The Mall | Auckland Airport"));
        assertTrue("AKL Airport URL returned is correct", getPageURL().contains("https://themall.aucklandairport.co.nz/"));
    }

    @And("^I try to login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iTryToLogin(String username, String password) {
        homePage.doSignIn(username, password);
    }

    @And("^I check login username$")
    public void iCheckUserName() {
        homePage.userGetText();
    }

    @And("^I should see following alert$")
    public void iShouldSeeFollowingAlert(String errorExpected) {
        System.out.println(homePage.getErrorMessage());
        //assertTrue(homePage.getErrorMessage().contains(errorExpected));
    }
}
