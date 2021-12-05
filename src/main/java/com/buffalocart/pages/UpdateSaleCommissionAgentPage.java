package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateSaleCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    public UpdateSaleCommissionAgentPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _email = "email";
    @FindBy(id = _email)
    private WebElement e_mail;
    private final String _Save = "//button[@class='btn btn-primary']";
    @FindBy(xpath = _Save)
    private WebElement save;
    List<String> readData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_SALES_PAGE);
    public void clickOnEmail(){
        page.doubleClickOnElement(driver,e_mail);
    }
    public String getEmailToEdit(){
        return readData.get(10);
    }
    public void enterEmail(String eMail) {
        page.enterText(e_mail, eMail);
    }
    public SalesCommissionAgentPage clickOnUpdate() throws IOException {
        page.clickOnElement(save);
        return new SalesCommissionAgentPage(driver);
    }
}
