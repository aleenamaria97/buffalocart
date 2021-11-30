package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UpdateUserPage extends TestHelperUtility {
    WebDriver driver;
    public UpdateUserPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
//    private final String _EmailAddress="email";
//    @FindBy(id = _EmailAddress)
//    private WebElement emailAddress;
}
