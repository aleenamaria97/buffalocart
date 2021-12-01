package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class DeleteUserPage extends TestHelperUtility {
    WebDriver driver;
    public DeleteUserPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public final String _DeletePopUp="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _DeletePopUp)
    private WebElement deletePopUp;
    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_DELETE_USER_PAGE);
     public String get_UserNameToDelete(){
         return readExcelData.get(0);
     }
     public void clickOnDeleteOk(){
         page.clickOnElement(deletePopUp);
     }
}
