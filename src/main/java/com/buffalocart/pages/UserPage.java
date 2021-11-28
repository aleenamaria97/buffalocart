package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UserPage extends TestHelperUtility {
    WebDriver driver;

    public UserPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String _SearchBar="//input[@class='form-control input-sm']";
    @FindBy(xpath = _SearchBar)
    private WebElement searchBar;
    private final String _TableCellElement="//table[@id='users_table']//tr//td[@class='sorting_1']";
    @FindBy(xpath = _TableCellElement)
    private  List<WebElement> tableCellElement;
    private final String _InvalidUserMsg="//td[@class='dataTables_empty']";
    @FindBy(xpath = _InvalidUserMsg)
    private WebElement invalidUserMsg;
    public final String _addUserTab="//div[@class='box-tools']";
    @FindBy(xpath = _addUserTab)
    private WebElement addUsertab;

    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_USER_PAGE);
    public String getUserActualPageTitle(){
       return page.getPageTitle(driver);
    }
    public String getUserPageExpectedTitle(){
        return readExcelData.get(0);
    }
    public String getUserNameToSearch(){
        return readExcelData.get(1);
    }
    public void searchUser(String uName){
        page.enterText(searchBar,uName);
    }
//    public String validateUserName(){
//        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _ValidateUser);
//        return page.getElementText(validateUser);
//    }
//
    public String getInvalidUserName(){
        return readExcelData.get(2);
    }

    public String getNoUserFoundActualMsg(){
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath,_InvalidUserMsg);
        return page.getElementText(invalidUserMsg);
    }
    public String getNoUserFoundExpectedMsg(){
        return readExcelData.get(3);
    }
    public AddUserPage clickAddUserTab() throws IOException {
        page.clickOnElement(addUsertab);
       return new AddUserPage(driver);
    }
}