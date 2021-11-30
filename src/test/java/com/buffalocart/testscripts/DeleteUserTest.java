package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.ResetPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteUserTest extends Base {
    LoginPage login;
    ResetPage reset;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 19, enabled = true, description = "TC_019_Verify user can delete a user")
    public void verifyUserCanDeleteAUser() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        reset = login.clickOnForgotPassword();
    }
}