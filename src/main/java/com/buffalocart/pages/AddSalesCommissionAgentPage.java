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

public class AddSalesCommissionAgentPage extends TestHelperUtility {
    WebDriver driver;
    public AddSalesCommissionAgentPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _Prefix = "surname";
    @FindBy(id = _Prefix)
    private WebElement prefix;
    private final String _FName = "first_name";
    @FindBy(id = _FName)
    private WebElement firstName;
    private final String _LName = "last_name";
    @FindBy(id = _LName)
    private WebElement lastName;
    private final String _email = "email";
    @FindBy(id = _email)
    private WebElement e_mail;
    private final String _cNumber = "contact_no";
    @FindBy(id = _cNumber)
    private WebElement conNumuber;
    private final String _Address = "address";
    @FindBy(id = _Address)
    private WebElement address;
    private final String _salesPer = "cmmsn_percent";
    @FindBy(id = _salesPer)
    private WebElement salesPer;
    private final String _Save = "//button[@class='btn btn-primary']";
    @FindBy(xpath = _Save)
    private WebElement save;


    List<String> readData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_SALES_PAGE);

    public String get_prefix() {
        return readData.get(1);
    }

    public void enterPrefix(String pFix) {
        page.enterText(prefix, pFix);
    }

    public String get_FirstName() {
        return readData.get(2);
    }

    public void enterFirstName(String fName) {
        page.enterText(firstName, fName);
    }

    public String get_LastName() {
        return readData.get(3);
    }

    public void enterLastName(String lName) {
        page.enterText(lastName, lName);
    }
    public String get_Email() {
        return readData.get(4);
    }

    public void enterEmail(String eMail) {
        page.enterText(e_mail, eMail);
    }
    public String get_cNumber() {
        return readData.get(5);
    }

    public void enterConNumber(String cNum) {
        page.enterText(conNumuber, cNum);
    }
    public String get_Address() {
        return readData.get(6);
    }

    public void enterAddress(String add) {
        page.enterText(address, add);
    }
    public String get_salesPer() {
        return readData.get(7);
    }

    public void enterSalesPercentage(String sP) {
        page.enterText(salesPer, sP);
    }
    public SalesCommissionAgentPage clickOnSaveButton() throws IOException {
        page.clickOnElement(save);
        return new SalesCommissionAgentPage(driver);
    }
}
