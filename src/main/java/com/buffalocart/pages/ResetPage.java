package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class ResetPage extends TestHelperUtility {
    WebDriver driver;
    public ResetPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _EmailAddress="email";
    @FindBy(id = _EmailAddress)
    private WebElement emailAddress;
    private final String _PWordResetButton="//button[@class='btn btn-primary']";
    @FindBy(xpath = _PWordResetButton)
    private WebElement pWResetButton;
    private final String _ErrorMsg="//span[@class='help-block']";
    @FindBy(xpath = _ErrorMsg)
    private WebElement errorMessage;

    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_FORGOT_PASSWORD_PAGE);
    public void setEmailAddress() {

        page.enterText(emailAddress,readExcelData.get(0));
    }
    public void pWResetButton() {
      page.clickOnElement(pWResetButton);
    }
    public String getActualErrorMessage(){
      return page.getElementText(errorMessage);
    }
    public String getExpectedErrorMessage(){
        return readExcelData.get(1);
    }
    public String get_ErrorMsg(){
        return readExcelData.get(2);
    }
    }

