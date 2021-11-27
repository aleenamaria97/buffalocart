package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.ResetPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResetTest extends Base {
    LoginPage login;
    ResetPage reset;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 5,enabled = true,description = "TC_005_Verify error message displayed on  Reset Password page with invalid email id")
    public void verifyErrorMessageDisplayedOnResetPasswordPageWithInvalidEmailId() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        reset =login.clickOnForgotPassword();
        extentTest.get().log(Status.PASS, "Click on forgot password");
        reset =new ResetPage(driver);
        reset.setEmailAddress();
        extentTest.get().log(Status.PASS, "Invalid email is entered");
        reset.pWResetButton();
        extentTest.get().log(Status.PASS, "Clicked on reset button");
        String actualMessage= reset.getActualErrorMessage();
        extentTest.get().log(Status.PASS, "Actual message is generated");
        String expectedMessage= reset.getExpectedErrorMessage();
        extentTest.get().log(Status.PASS, "Expected message is generated");
        String errorMessage=reset.get_ErrorMsg();
        extentTest.get().log(Status.PASS, "Error message is generated");
        Assert.assertEquals(actualMessage,expectedMessage,errorMessage);
        extentTest.get().log(Status.PASS, "Verify error message displayed on  Reset Password page with invalid email id test passed");
    }
}
