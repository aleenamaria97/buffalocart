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
    private  WebElement tableCellElement;
    private final String _InvalidUserMsg="//td[@class='dataTables_empty']";
    @FindBy(xpath = _InvalidUserMsg)
    private WebElement invalidUserMsg;
    public final String _addUserTab="//div[@class='box-tools']";
    @FindBy(xpath = _addUserTab)
    private WebElement addUsertab;
    public final String _EditButton="//a[@class='btn btn-xs btn-primary']";
    @FindBy(xpath = _EditButton)
    private WebElement editButton;
    private final String _rElement = "//table[@id='users_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;
    private final String _cElement = "//table[@id='users_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;
    public final String _DeleteButton="//button[@class='btn btn-xs btn-danger delete_user_button']";
    @FindBy(xpath = _DeleteButton)
    private WebElement deleteButton;
    public final String _ViewUserButton="//a[@class='btn btn-xs btn-info']";
    @FindBy(xpath = _ViewUserButton)
    private WebElement viewUserButton;
    boolean values;


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
    public String getActualUserIdAfterSearch() {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _TableCellElement);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _TableCellElement);
        String actualUserValue = page.getElementText(usersListWebElement.get(0));
        if (actualUserValue != " ") {
            return actualUserValue;
        } else {
            return " ";
        }
    }

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
    public List<ArrayList<String>> getTableData() {
        wait.IMPLICIT_WAIT(6000);
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _cElement);
        return table.getGridData(rowElement, colElement);
    }

    public boolean getTableDataContains(List<ArrayList<String>> tableData, String expectedUserName){

        boolean value = false;
        for(int i=0;i<tableData.size();i++){
            if(tableData.get(i).contains(expectedUserName)){
                value= true;
            }
        }
        return value;
    }
    public UpdateUserPage clickOnEditButton(String userName) throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _EditButton);
        List<ArrayList<WebElement>> actionData = table.actionData(rowElement, colElement);
        if (values == false) {
        for (int i = 0; i < actionData.size(); i++) {
            for (int j = 0; j < actionData.get(0).size(); j++) {
                if (values == false) {

                    WebElement data = actionData.get(i).get(j);
                    String tData = data.getText();

                    if (tData.contains(userName)) {
                        page.clickOnElement(editButton);
                        values = true;
                    }
                }
            }

                }
            }

        return new UpdateUserPage(driver);
    }


    public DeleteUserPage clickOnDeleteButton(String userName) throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _DeleteButton);
        List<ArrayList<WebElement>> actionData = table.actionData(rowElement, colElement);
        if (values == false) {
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    if (values == false) {

                        WebElement data = actionData.get(i).get(j);
                        String tData = data.getText();

                        if (tData.equals(userName)) {
                            page.clickOnElement(deleteButton);
                            values = true;
                        }
                    }
                }

            }
        }

        return new DeleteUserPage(driver);
    }
    public ViewUserPage clickOnViewButton(String userName) throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _ViewUserButton);
        List<ArrayList<WebElement>> actionData = table.actionData(rowElement, colElement);
        if (values == false) {
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    if (values == false) {

                        WebElement data = actionData.get(i).get(j);
                        String tData = data.getText();

                        if (tData.equals(userName)) {
                            page.clickOnElement(viewUserButton);
                            values = true;
                        }
                    }
                }

            }
        }

        return new ViewUserPage(driver);
    }
}