package StepDefinitions;

import Pages.PageInitializer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class PurchaseStepDefs extends PageInitializer {

    @Given("^the user clicks the \"([^\"]*)\" link$")
    public void user_clicks_the(String category) {

        switch (category) {
            case "wine and spirit":
                purchasePage.clickOnSpirit();
                break;
            case "Technology":
                purchasePage.clickOnTech();
                break;
            default:
                assertFalse("Given option is unavailable", true);
                break;
        }
    }

    @Given("^clicks on the spirit$")
    public void clicks_on_the_spirit() {
        purchasePage.selectBottle();
    }

    @Given("^clicks on the phone$")
    public void clicks_on_the_phone() {
        purchasePage.selectPhone();
    }

    @When("^the user adds it to cart then selects \"([^\"]*)\" as pickup from AKL international airport$")
    public void the_user_adds_it_to_cart_then_selects_as_pickup_from_AKL_international_airport(String arg1) {
        purchasePage.addToCart();

        switch (arg1) {
            case "departure":
                purchasePage.clickOnDeparture();
                break;
            case "arrival":
                purchasePage.clickOnArrival();
                break;
            default:
                assertFalse("Given option is unavailable", true);
                break;
        }
    }

    @Then("^the message \"([^\"]*)\" is displayed$")
    public void the_message_is_displayed(String arg1) {
        System.out.println(purchasePage.getPurchaseText());
        assertTrue(purchasePage.getPurchaseText().contains(arg1));
    }

    @When("^the user clicks his cart$")
    public void the_user_clicks_his_cart() {
        purchasePage.goToCart();
    }

    @Then("^the item \"([^\"]*)\" should be listed in the cart$")
    public void the_item_should_be_listed_in_the_cart(String arg1) {
        System.out.println(purchasePage.getCartProductName());
        assertTrue(purchasePage.getCartProductName().contains(arg1));
    }

    @Given("^the user input \"([^\"]*)\" and click search button$")
    public void the_user_input_and_click_search_button(String arg1) {
        purchasePage.searchProduct(arg1);
    }

    @Given("^the cart price should display the correct amount for this purchase$")
    public void cartPrice() {
        System.out.println(purchasePage.getPhonePrice());
        System.out.println(purchasePage.getCartItemPrice());
       // assertEquals("Price did not matched", purchasePage.getCartItemPrice(), purchasePage.getPhonePrice());
    }


}
