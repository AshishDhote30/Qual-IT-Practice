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

    @Given("^user open Auckland Airport website$")
    public void user_open_Auckland_Airport_website() {
        getPage("https://themall.aucklandairport.co.nz");
    }

    @Then("^user validate title and URL$")
    public void user_validate_title_and_URL() {
        assertTrue("AKL Airport home page title is not correct", getPageTitle().contains("Shop duty free and tax free online | The Mall | Auckland Airport"));
        assertTrue("AKL Airport URL returned is correct", getPageURL().contains("https://themall.aucklandairport.co.nz/"));
    }

    @And("^user try to login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userTryToLogin(String username, String password) {
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

    @Then("^user clicks on my account$")
    public void user_clicks_on_my_account() {
        homePage.clickOnAccount();
    }

    @Then("^user sign out$")
    public void user_sign_out()  {
        homePage.clickOnSignOut();
        assertTrue("Sign In button is not displayed", homePage.isSignInDisplayed());
    }


}
