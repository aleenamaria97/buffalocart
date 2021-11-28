package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManagementPage extends TestHelperUtility {
    WebDriver driver;
    public UserManagementPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _userManagementTab="//span[@class='title']";
    @FindBy(xpath = _userManagementTab)
    private WebElement userManagementTab;
    private final String _userManagementTabs="//ul[@class='treeview-menu menu-open']//span";
    @FindBy(xpath =_userManagementTabs)
    private List<WebElement> userManagementTabs;
    private final String _userTab = "//ul[@class='treeview-menu menu-open']//i[@class='fa fa-user']";
    @FindBy(xpath = _userTab)
    private WebElement userTab;
    public void clickOnUserManagementTab(){
        page.clickOnElement(userManagementTab);

    }
    List<String> readData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_USER_MANAGEMENT_PAGE);
    public List<String> getExpectedUserManagementTabs(){
    List<String>expectedTabs=new ArrayList<>();
        for (int i = 0; i < readData.size(); i++) {
        expectedTabs.add(readData.get(i));
    }
        return expectedTabs;
    }
    public List<String> getActualUserManagementTabs(){
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath,_userManagementTabs);
        List<String> actualData = new ArrayList<>();
        for (int i = 0; i < userManagementTabs.size(); i++) {
            actualData.add(userManagementTabs.get(i).getText());
            }
        return actualData;
    }

    public UserPage clickOnUserTabs() throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _userTab);
        page.clickOnElement(userTab);
        return new UserPage(driver);
    }


}
