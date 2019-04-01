package StepDefinitions;

import Pages.PageInitializer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PurchaseStepDefs extends PageInitializer {

    @Given("^the user clicks the wine and spirit link$")
    public void the_user_clicks_the_wine_and_spirit_link() {
        purchasePage.clickOnSpirit();
    }

    @Given("^clicks on the spirit$")
    public void clicks_on_the_spirit() {
        purchasePage.selectBottle();
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
        //assertTrue(purchasePage.getPurchaseText().contains(arg1));
    }

    @When("^the user clicks his cart$")
    public void the_user_clicks_his_cart() {
        purchasePage.goToCart();
    }

    @Then("^the item \"([^\"]*)\" should be listed in the cart$")
    public void the_item_should_be_listed_in_the_cart(String arg1) {
        System.out.println(purchasePage.getCartBottleName());
        assertTrue(purchasePage.getCartBottleName().contains(arg1));
    }

    @Given("^the user input \"([^\"]*)\" and click search button$")
    public void the_user_input_and_click_search_button(String arg1)  {
        purchasePage.searchProduct(arg1);
    }

    @Given("^looks for the item \"([^\"]*)\" then selects it$")
    public void looks_for_the_item_then_selects_it(String arg1)  {

    }



}
