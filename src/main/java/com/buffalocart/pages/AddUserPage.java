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

public class AddUserPage extends TestHelperUtility {
    WebDriver driver;

    public AddUserPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
    private final String _role = "role";
    @FindBy(id = _role)
    private WebElement role;
    private final String _UserName="username";
    @FindBy(id =_UserName)
    private WebElement userName;
    private final String _PWord = "password";
    @FindBy(id = _PWord)
    private WebElement pWord;
    private final String _CPWord = "confirm_password";
    @FindBy(id = _CPWord)
    private WebElement CpWord;
    private final String _save = "//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath = _save)
    private WebElement saveButton;
    private final String _toastMessage = "//div[@id='toast-container']";
    @FindBy(xpath = _toastMessage)
    WebElement toastMessage;
    private final String _errMsg = "password-error";
    @FindBy(id = _errMsg)
    private WebElement errorMessage;
    private  String newUserName;
    public  String getNewUserName() {
        return newUserName;
    }
    public  void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    List<String> readData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_ADD_USER_PAGE);

    public String get_prefix() {
        return readData.get(0);
    }

    public void enterPrefix(String pFix) {
        page.enterText(prefix, pFix);
    }

    public String get_FirstName() {
        return readData.get(1);
    }

    public void enterFirstName(String fName) {
        page.enterText(firstName, fName);
    }

    public String get_LastName() {
        return readData.get(2);
    }

    public void enterLastName(String lName) {
        page.enterText(lastName, lName);
    }

    public String get_Id() {
        return randomString.getEmailUtility();
    }

    public void enterId(String eMail) {
        page.enterText(e_mail, eMail);
    }

    public void selectRole() {
        wait.waitForVisibilityOfElements(driver, WaitUtility.LocatorType.Id, _role);
        page.selectDropdownByValue(role, readData.get(3));
    }

    public String get_UserName(){
        return readData.get(8);
    }
    public String getExpectedUserName(){
        return readData.get(9);
    }
    public void enterUserName(String uName){
        page.enterText(userName,uName);
    }

    public String get_PWord() {
        return readData.get(4);
    }

    public void enterPassWord(String passWord) {
        page.enterText(pWord, passWord);
    }

    public String get_CPWord() {
        return readData.get(5);
    }

    public void enterConfirmPassWord(String confirmPassWord) {
        page.enterText(CpWord, confirmPassWord);
    }

    public UserPage clickOnSaveButton() throws IOException {
        page.scrollByJS(driver,saveButton);
        page.clickOnElement(saveButton);
        return new UserPage(driver);
    }

    public String getActualErrorMsg() {
        return page.getElementText(errorMessage);
    }

    public String getExpectedErrorMsg() {
        return readData.get(6);
    }


    public String getPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getPageExpectedTitle() {
        return readData.get(7);
    }
    public String getRandomUserName(){
        setNewUserName(randomString.getUserNameUtility("uname"));
        return getNewUserName();
    }



}


