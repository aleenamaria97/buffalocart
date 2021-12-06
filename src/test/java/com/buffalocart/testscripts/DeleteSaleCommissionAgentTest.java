package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteSaleCommissionAgentTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage sign;
    UserManagementPage userManagement;
    SoftAssert soft;
    SalesCommissionAgentPage sales;
    DeleteSaleCommissionAgentPage deleteSales;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 31, enabled = true, description = "TC_031_Verify user can delete a Sales Commission Agents",groups = {"Smoke","Regression"})
    public void verifyUserCanDeleteSalesAgent() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        soft = new SoftAssert();
        login.enterUserName(login.get_UserName());
        extentTest.get().log(Status.PASS, "User name is entered");
        login.enterPassword(login.get_Password());
        extentTest.get().log(Status.PASS, "Password is entered");
        home = login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Clicked on login button");
        home.endTour();
        extentTest.get().log(Status.PASS, "Clicked on end tour");
        userManagement.clickOnUserManagementTab();
        sales = userManagement.clickOnSalesCommissionTab();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to Sales commission page");
        deleteSales=sales.clickOnDeleteButton(sales.getSalesToDelete());
        sales=deleteSales.clickOnDelete();
        extentTest.get().log(Status.PASS, "Deleted a Sales commission agent");
        Thread.sleep(6000);
        List<ArrayList<String>> tableData = sales.getTableData();
        boolean value =sales.getTableDataContains(tableData,sales.getSalesToDelete());
        soft.assertFalse(value,"ERROR : User Deletion Unsuccessful");
        sign = home.clickOnUserName();
        sign.clickOnLogOutButton();
        extentTest.get().log(Status.PASS, "Successfully Signed Out");
        soft.assertAll();
    }
}
