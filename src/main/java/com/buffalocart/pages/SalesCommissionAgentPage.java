package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TableUtility;
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

public class SalesCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    public SalesCommissionAgentPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _Add="//div[@class='box-tools']";
    @FindBy(xpath = _Add)
    private WebElement add;
    private final String _rElement = "//table[@id='sales_commission_agent_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;
    private final String _cElement = "//table[@id='sales_commission_agent_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;
    public final String _EditButton="//i[@class='glyphicon glyphicon-edit']";
    @FindBy(xpath = _EditButton)
    private WebElement editButton;
    public final String _DeleteButton="//button[@class='btn btn-xs btn-danger delete_commsn_agnt_button']";
    @FindBy(xpath = _DeleteButton)
    private WebElement deleteButton;
    boolean values;
    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_SALES_PAGE);
    public String getUserActualPageTitle(){
        return page.getPageTitle(driver);
    }
    public String getUserPageExpectedTitle(){
        return readExcelData.get(0);
    }
    public AddSalesCommissionAgentPage clickOnAddButton() throws IOException {
        page.clickOnElement(add);
        return new AddSalesCommissionAgentPage(driver);
    }
    public String getAddedSalesCommissionAgent(){
        return readExcelData.get(8);
    }
    public List<ArrayList<String>> getTableData() {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Xpath, _cElement);
        return TableUtility.getGridData(rowElement, colElement);
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
    public UpdateSaleCommissionAgentPage clickOnEditButton(String userName) throws IOException {
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
                                    By.xpath("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) +"]//td[6]//i[@class='glyphicon glyphicon-edit']"));
                            page.clickOnElement(editButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        }

        return new UpdateSaleCommissionAgentPage(driver);
    }
    public String getSalesToEdit(){
        return readExcelData.get(9);
    }
    public DeleteSaleCommissionAgentPage clickOnDeleteButton(String role) throws IOException {
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
                                    By.xpath(("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) + "]//td[6]//button[2]")));
                            page.clickOnElement(deleteButton);
                            values = true;
                            break;
                        }
                    }
                }

            }

        return new DeleteSaleCommissionAgentPage(driver);
    }
    public String getSalesToDelete(){
        return readExcelData.get(11);
    }

}
