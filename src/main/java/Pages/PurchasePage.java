package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static Utils.Utils.waitAndClick;
import static Utils.Utils.waitForElementVisibility;

public class PurchasePage extends PageInitializer {

    final WebDriver driver;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@class='navigationButton navigationPrimaryButton hasDropdown'][contains(text(),'Spirits & Wine')]")
    public static WebElement SpiritsAndWine;

    @FindBy(xpath = "//a[@title='White Walker 1L']")
    public static WebElement bottle;

    @FindBy(xpath = "//div[@class='addToCart addToCartStackedLayout']//button[@type='button'][contains(text(),'Add to Cart')]")
    public static WebElement AddtoCart;

    @FindBy(xpath = "//div[@class='productSidebarStickyContent']//a[@class='button buttonSizeRegular buttonStylePrimary buttonColorBlack deliveryIntentSelectionButton deliveryIntentSelectionButtonDeparture'][contains(text(),'at Departure')]")
    public static WebElement departure;

    @FindBy(xpath = "//div[@class='productSidebarStickyContent']//a[@class='button buttonSizeRegular buttonStylePrimary buttonColorBlack deliveryIntentSelectionButton deliveryIntentSelectionButtonArrival'][contains(text(),'on Arrival')]")
    public static WebElement arrival;

    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/div[1]/div[2]/div/div[4]/h5[1]")
    public static WebElement purchaseText;

    @FindBy(xpath = "//div[@class='productSidebarStickyContent']//a[@class='button buttonSizeRegular buttonStylePrimary buttonColorPrimary productAddToCartSuccessGoToCartButton'][contains(text(),'Go to cart')]")
    public static WebElement gotoCart;

    @FindBy(xpath = "//div[@class='cartItemProductTitle cartItemProductTitleEditable']")
    ////a[contains(text(),'White Walker 1L')]
    public static WebElement cartProduct;

    @FindBy(xpath = "//input[@placeholder='Search products, brands & more']")
    public static WebElement searchBox;

    @FindBy(xpath = " //a[@title='Johnnie Walker Black 200ml']")
    public static WebElement searchBottle;

    @FindBy(xpath = "//div[@id='cmsAklTileGroup0']//a[3]")   //div[@id='cmsAklTileGroup0']//a[3]//div[1]
    public static WebElement technology;

    @FindBy(xpath = "//a[@title='Apple iPhone XS 64GB Space Grey']")
    public static WebElement phone;

    @FindBy(xpath = "//div[@class='cartItemPrice']")
    public static WebElement cartItemPrice;

    @FindBy(xpath = "//a[@title='Apple iPhone XS 64GB Space Grey']//strong[@class='priceValue'][contains(text(),'$1,696.00')]")
    public static WebElement phonePrice;


    public void clickOnTech(){
        waitForElementVisibility(technology,5);
        waitAndClick(technology);
    }

    public void clickOnSpirit() {
        waitAndClick(SpiritsAndWine);
    }

    public void selectBottle() {
        waitAndClick(bottle);
    }

    public void selectPhone(){
        waitAndClick(phone);
    }

    public void addToCart() {
        waitAndClick(AddtoCart);
    }

    public void clickOnDeparture() {
        waitAndClick(departure);
    }

    public void clickOnArrival() {
        waitAndClick(arrival);
    }

    public String getPurchaseText() {
        waitForElementVisibility(purchaseText,7);
        return purchaseText.getText();
    }

    public String getCartItemPrice(){
        return cartItemPrice.getText();
    }

    public String getPhonePrice(){
        return phonePrice.getText();
    }

    public void goToCart() {
        waitAndClick(gotoCart);
    }

    public String getCartProductName() {
        return cartProduct.getText();
    }



    public void searchProduct(String product){
        waitAndClick(searchBox);
        searchBox.sendKeys(product);
        waitAndClick(searchBottle);
    }


}
