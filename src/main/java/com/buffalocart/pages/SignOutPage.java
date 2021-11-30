package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class SignOutPage extends TestHelperUtility {
    WebDriver driver;
    public SignOutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _signOut="//div[@class='pull-right']//a[@class='btn btn-default btn-flat']";
    @FindBy(xpath = _signOut)
    private WebElement signOut;
    public LoginPage clickOnLogOutButton() throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath,_signOut);
        page.clickOnElement(signOut);
        return new LoginPage(driver);
    }
    public LoginPage userAccountSignOutUsingJS() throws IOException {
        page.findElementUsingJavaScript(driver,signOut);
        return new LoginPage(driver);
    }
}
