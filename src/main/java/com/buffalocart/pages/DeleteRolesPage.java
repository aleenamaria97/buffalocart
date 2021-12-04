package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteRolesPage extends TestHelperUtility {
    WebDriver driver;

    public DeleteRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public final String _DeletePopUp="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _DeletePopUp)
    private WebElement deletePopUp;
    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_DELETE_ROLES_PAGE);
    public String get_RolesToDelete(){
        return readExcelData.get(0);
    }

    public RolesPage clickOnDeleteOk() throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _DeletePopUp);
        page.clickOnElement(deletePopUp);
        return new RolesPage(driver);
    }

}