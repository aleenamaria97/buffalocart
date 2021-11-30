package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteUserPage extends TestHelperUtility {
    WebDriver driver;
    public DeleteUserPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
