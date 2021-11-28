package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class LoginPage extends TestHelperUtility {
    WebDriver driver;
    public  LoginPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _LoginPageTitle="//h1[@class='text-center page-header']";
    @FindBy(xpath =_LoginPageTitle)
    private WebElement loginPageTitle;
    private final String _UserName="username";
    @FindBy(id =_UserName)
    private WebElement userName;
    private final String _Password="password";
    @FindBy(id =_Password)
    private WebElement password;
    private final String _LoginButton="//button[@class='btn btn-primary']";
    @FindBy(xpath =_LoginButton)
    private WebElement loginButton;
    private final String _ErrorMsg="//span[@class='help-block']";
    @FindBy(xpath =_ErrorMsg)
    private WebElement errorMsg;
    private final String _RememberMe="//input[@type='checkbox']";
    @FindBy(xpath =_RememberMe)
    private WebElement rememberMe;
    private final String _ForgotPassWord="//a[@class='btn btn-link']";
    @FindBy(xpath =_ForgotPassWord)
    private WebElement forgotPassword;
    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_LOGIN_PAGE);
    public String get_LoginPageActualTitle(){
        return page.getPageTitle(driver);
    }
    public String get_LoginPageExpectedTitle() throws IOException {
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_LOGIN_PAGE);
        return readExcelData.get(0);
    }
    public String get_UserName(){
        return readExcelData.get(1);
    }
    public void enterUserName(String uName){
         page.enterText(userName,uName);
    }
    public String get_Password(){
        return readExcelData.get(2);
    }
    public void enterPassword(String pWord){
        page.enterText(password,pWord);
    }
    public String getExpectedUserName(){
        return readExcelData.get(3);
    }
    public HomePage clickOnLoginButton() throws IOException {
         page.clickOnElement(loginButton);
         return new HomePage(driver);
    }
    public String getInvalidPWord(){
        return readExcelData.get(4);
    }
   public String getInvalidCredentialsErrorMessage(){
        return page.getElementText(errorMsg);
   }
   public String getInvalidCredentialsExpectedErrorMsg(){
        return readExcelData.get(5);
   }
     public boolean rememberMeCheckBox(){
        page.clickOnElement(rememberMe);
        return page.isElementSelected(rememberMe);
   }
  public ResetPage clickOnForgotPassword() throws IOException {
        page.clickOnElement(forgotPassword);
        return new ResetPage(driver);
  }
}

