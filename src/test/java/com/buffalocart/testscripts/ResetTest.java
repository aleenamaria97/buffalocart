package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.ResetPage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ResetTest extends Base {
    LoginPage login;
    ExcelUtility excel;
    ResetPage reset;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 5,enabled = true,description = "TC_005_Verify error message displayed on  Reset Password page with invalid email id")
    public void verifyErrorMessageDisplayedOnResetPasswordPageWithInvalidEmailId() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        reset =new ResetPage(driver);
        reset =login.clickOnForgotPassword();
        extentTest.get().log(Status.PASS, "Click on forgot password");
        excel=new ExcelUtility();
        reset =new ResetPage(driver);
        reset.setEmailAddress();
        extentTest.get().log(Status.PASS, "Invalid email is entered");
        reset.pWResetButton();
        extentTest.get().log(Status.PASS, "Clicked on reset button");
        String actualMessage= reset.getErrorMessage();
        extentTest.get().log(Status.PASS, "Actual message is generated");
        List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_FORGOT_PASSWORD_PAGE);
        String expectedMessage= readExcelData.get(1);
        extentTest.get().log(Status.PASS, "Expected message is generated");
        String errorMessage=readExcelData.get(2);
        extentTest.get().log(Status.PASS, "Error message is generated");
        Assert.assertEquals(actualMessage,expectedMessage,errorMessage);
        extentTest.get().log(Status.PASS, "Verify error message displayed on  Reset Password page with invalid email id test passed");
    }
}
