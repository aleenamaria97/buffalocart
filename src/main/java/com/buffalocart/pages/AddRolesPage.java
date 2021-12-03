package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class AddRolesPage extends TestHelperUtility {
    WebDriver driver;
    public AddRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public final String _addRoles="name";
    @FindBy(id = _addRoles)
    private WebElement addRoles;
    public final String _saveButton="//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath = _saveButton)
    private WebElement saveButton;


    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_ADD_ROLES_PAGE);
    public String getUserActualPageTitle(){
        return page.getPageTitle(driver);
    }
    public String getUserPageExpectedTitle(){
        return readExcelData.get(0);
    }
    public String getUserRolesToEnter(){
        return readExcelData.get(1);
    }
    public void enterRoles(String eMail) {
        page.enterText(addRoles, eMail);
    }
    public RolesPage clickOnSaveButton() throws IOException {
        page.scrollByJS(driver,saveButton);
        page.clickOnElement(saveButton);
        return new RolesPage(driver);
    }

    }

