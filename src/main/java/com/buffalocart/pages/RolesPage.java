package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class RolesPage extends TestHelperUtility {
    WebDriver driver;
    public RolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public final String _AddRoles="//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath = _AddRoles)
    private WebElement addRoles;

    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_ROLES_PAGE);
    public String getUserActualPageTitle(){
        return page.getPageTitle(driver);
    }
    public String getUserPageExpectedTitle(){
        return readExcelData.get(0);
    }
    public AddRolesPage clickAddRolesTab() throws IOException {
        page.clickOnElement(addRoles);
        return new AddRolesPage(driver);
    }
}
