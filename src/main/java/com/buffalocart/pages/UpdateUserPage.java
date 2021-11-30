package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateUserPage extends TestHelperUtility {
    WebDriver driver;
    public UpdateUserPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    List<String> readExcelData = excel.readDataFromExcel(Constants.EXCEL_FILE_PATH, Constants.EXCEL_SHEET_EDIt_USER_PAGE);
    public String getEditUserPageTitle() throws InterruptedException {
        Thread.sleep(3000);
        return driver.getTitle();
    }
    public String getEditUserPageExpectedTitle(){
        return readExcelData.get(0);
    }

}
