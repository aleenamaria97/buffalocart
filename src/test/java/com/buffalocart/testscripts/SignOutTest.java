package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.ResetPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SignOutTest extends Base {
    LoginPage login;
    HomePage home;
    ExcelUtility excel;
    SignOutPage sign;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 8, enabled = true, description = "TC_008_Verify whether user is navigating to login page by clicking on Sign out button")
    public void verifyUserIsNavigatingToLoginPageByClickingOnSignOutButton() throws IOException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");
        login = new LoginPage(driver);
        home=new HomePage(driver);
        excel=new ExcelUtility();
        sign=new SignOutPage(driver);
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_HOME_PAGE);
        login.enterUserName(readExcelData.get(0));
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(readExcelData.get(1));
        extentTest.get().log(Status.PASS, "Password is entered");
        home=login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        sign=home.clickOnUserName();
        login=sign.logOut();
        extentTest.get().log(Status.PASS, "Clicked on logout button");
        String actualTitle= login.get_LoginPageActualTitle();
        String expectedTitle=readExcelData.get(5);
        String errorMsg=readExcelData.get(6);
        Assert.assertEquals(actualTitle,expectedTitle,errorMsg);
        extentTest.get().log(Status.PASS, "User is successfully navigated to LoginPage");
    }


}
