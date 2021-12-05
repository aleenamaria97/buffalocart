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

public class RolesPage extends TestHelperUtility {
    WebDriver driver;
    public RolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public final String _AddRoles="//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath = _AddRoles)
    private WebElement addRoles;
    private final String _SearchBar="//input[@class='form-control input-sm']";
    @FindBy(xpath = _SearchBar)
    private WebElement searchBar;
    private final String _TableCellElement="//table[@id='roles_table']//tr//td[@class='sorting_1']";
    @FindBy(xpath = _TableCellElement)
    private  WebElement tableCellElement;
    public final String _EditButton="//a[@class='btn btn-xs btn-primary']";
    @FindBy(xpath = _EditButton)
    private WebElement editButton;
    private final String _rElement = "//table[@id='roles_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;
    private final String _cElement = "//table[@id='roles_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;
    public final String _DeleteButton="//button[@class='btn btn-xs btn-danger delete_role_button']";
    @FindBy(xpath = _DeleteButton)
    private WebElement deleteButton;
    boolean values;


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
    public String getRolesToSearch(){
        return readExcelData.get(1);
    }
    public void searchRoles(String uName){
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _SearchBar);
        page.enterText(searchBar,uName);
    }
    public String getActualRoleAfterSearch() {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _TableCellElement);
        List<WebElement> usersListWebElement = page.getWebElementList(driver, _TableCellElement);
        String actualUserValue = page.getElementText(usersListWebElement.get(0));
        if (actualUserValue != " ") {
            return actualUserValue;
        } else {
            return " ";
        }
    }
    public String getRolesToEdit(){
        return readExcelData.get(2);
    }
    public UpdateRolesPage clickOnEditButton(String userName) throws IOException {
        wait.IMPLICIT_WAIT(6000);
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _EditButton);
        List<ArrayList<WebElement>> actionData = table.actionData(rowElement, colElement);
        if (values == false) {
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);
                    if (values == false) {

                        String tData = data.getText();

                        if (tData.contains(userName)) {
                            editButton = driver.findElement(
                                    By.xpath("//table[@id='roles_table']//tbody//tr[" + (i + 1) +"]//td[2]//a[1]"));
                            page.clickOnElement(editButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        }

        return new UpdateRolesPage(driver);
    }
    public DeleteRolesPage clickOnDeleteButton(String role) throws IOException {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _DeleteButton);
        List<ArrayList<WebElement>> actionData = table.actionData(rowElement, colElement);
        if (values == false)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);
                    if (values == false) {
                        String tData = data.getText();
                        if (tData.contains(role)) {
                            deleteButton = driver.findElement(
                                    By.xpath(("//table[@id='roles_table']//tbody//tr[" + (i + 1) + "]//td[2]//button")));
                            page.clickOnElement(deleteButton);
                            values = true;
                            break;
                        }
                    }
                }

            }

        return new DeleteRolesPage(driver);
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
    public String getUserRolesToEnter(){
        return readExcelData.get(3);
    }
    }
