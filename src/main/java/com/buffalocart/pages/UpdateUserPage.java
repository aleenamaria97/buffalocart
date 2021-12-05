package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateUserPage extends TestHelperUtility {
    WebDriver driver;
    public UpdateUserPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _Email="email";
    @FindBy(id = _Email)
    private WebElement email;
    private final String _Update="//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath = _Update)
    private WebElement update;


    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_UPDATE_USER_PAGE);
    public String getEditUserPageTitle() throws InterruptedException {
        Thread.sleep(3000);
        return driver.getTitle();
    }
    public String getEditUserPageExpectedTitle(){
        return readExcelData.get(0);
    }
    public String get_Email(){
        return  readExcelData.get(1);
    }
    public void clickOnEmail(){
        page.clickOnElement(email);
    }
    public void set_Email(String eMail){
        page.enterText(email,eMail);
    }
    public UserPage clickOnUpdate() throws IOException {
        page.scrollByJS(driver,update);
        page.clickOnElement(update);
        return new UserPage(driver);
    }

}
