package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignOutPage extends TestHelperUtility {
    WebDriver driver;
    public SignOutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _signOut="//div[@class='pull-right']//a[@class='btn btn-default btn-flat']";
    @FindBy(xpath = _signOut)
    private WebElement signOut;
    public LoginPage logOut(){
        page.clickOnElement(signOut);
        return new LoginPage(driver);
    }
}
