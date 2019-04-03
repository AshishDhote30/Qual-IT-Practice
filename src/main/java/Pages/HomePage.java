package Pages;


import Utils.Log;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

import static Utils.Utils.waitAndClick;
import static Utils.Utils.waitForElementVisibility;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class HomePage extends PageInitializer {

    final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//span[contains(text(),'Sign in')]")
    public static WebElement SignInButton;

    @FindBy(xpath = "//input[@id='email']")
    public static WebElement emailTestBox;

    @FindBy(xpath = "//input[@id='password']")
    public static WebElement passwordTextBox;

    @FindBy(xpath = "//button[@name='submit']")
    public static WebElement SubmitButton;

    @FindBy(xpath = " //span[@class='platformNavigationAccountButtonLabel']")
    public static WebElement getUserName;

    @FindBy(xpath = "//p[contains(text(),'Sorry, we do not have a Strata Club Membership wit')]")
    public static WebElement signInErrorMessage;

    @FindBy(xpath = "//span[@class='platformNavigationAccountButtonLabel']")
    public static WebElement myAccount;

    @FindBy(xpath = "//span[contains(text(),'Sign out')]")
    public static WebElement signOutButton;

    public void doSignIn(java.lang.String _email, String _password) {
        waitAndClick(SignInButton);
        emailTestBox.sendKeys(_email.trim());
        passwordTextBox.sendKeys(_password.trim());
        waitAndClick(SubmitButton);
    }

    public String userGetText(){
        return getUserName.getText();
    }

    public  String getErrorMessage(){
        waitForElementVisibility(signInErrorMessage,7);
        return signInErrorMessage.getText();
    }

    public void clickOnAccount(){
        waitAndClick(myAccount);
    }

    public void clickOnSignOut(){
        waitAndClick(signOutButton);
    }

    public boolean isSignInDisplayed(){
        return SignInButton.isDisplayed();
    }


}
