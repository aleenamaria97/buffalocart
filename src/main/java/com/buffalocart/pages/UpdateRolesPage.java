package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateRolesPage extends TestHelperUtility {
    WebDriver driver;
    public UpdateRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public final String _selectUser="//div[@class='icheckbox_square-blue hover']";
    @FindBy(xpath = _selectUser)
    private WebElement selectUser;
    public final String _UpdateButton="//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath = _UpdateButton)
    private WebElement updateButton;


    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_UPDATE_ROLES_PAGE);
    public String getUpdateRolesPageActualTitle(){
        return page.getPageTitle(driver);
    }
    public String getUpdateRolesPageExpectedTitle(){
        return readExcelData.get(0);
    }
    public void selectAllRoles(){
        page.clickOnElement(selectUser);
    }
    public boolean isElementIsSelected(){
        return page.isElementSelected(selectUser);
    }
    public RolesPage clickOnUpdateButton() throws IOException {
        page.scrollByJS(driver,updateButton);
        page.clickOnElement(updateButton);
        return new RolesPage(driver);
    }
}
