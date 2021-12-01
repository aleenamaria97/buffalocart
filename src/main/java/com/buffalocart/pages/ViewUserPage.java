package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class ViewUserPage extends TestHelperUtility {
    WebDriver driver;
    public ViewUserPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public final String _viewUserName="//h3[@class='profile-username']";
    @FindBy(xpath = _viewUserName)
    private WebElement viewUserName;

    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_VIEW_USER_PAGE);
    public String get_UserNameToView(){
        return readExcelData.get(0);
    }
    public String getViewUserName(){
        return page.getElementText(viewUserName);
    }
    public String getViewUserExpectedName(){
        return readExcelData.get(1);

    }

}
