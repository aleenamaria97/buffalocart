package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DeleteSaleCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    public DeleteSaleCommissionAgentPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _Delete = "//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _Delete)
    private WebElement delete;
    public SalesCommissionAgentPage clickOnDelete() throws IOException {
        page.clickOnElement(delete);
        return new SalesCommissionAgentPage(driver);
    }
}
