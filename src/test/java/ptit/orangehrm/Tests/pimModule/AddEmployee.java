package ptit.orangehrm.Tests.pimModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.AddEmployeePageObject;
import pageObjects.pageObjectsAdmin.pimObjects.EmployeeListPageObject;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AddEmployee extends BaseTest {



    @AfterMethod
    public void afterMethod() {
        closeBrowser();
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }

    private String Anh1="Anh1.jpg";
    private String firstName="nguyen", middleName="thi", lastName="toi", employeeId, userName="toi@123",password="automationFc@123", confirmPassword="automationFc@123";

    private ExcelConfig excelConfig;
    private String pre, actualLabelName, expectedLabelName, stepByStep, testCase, expectedResult, otherId, licenseExpiryDate, comment, actualPlaceholder, expectedPlaceholder, actualTextareaPlaceholder, expectedTextareaPlaceholder;
    private String actualColor, expectedColor, ActualValueFirstName,ActualValueMiddleName,ActualValuelastName,ActualValueUserName,ActualValuePassword;
    private List<String> labelOnScreen, ActualListLabelName, ExpectedListLabelName, listDataTest, ActualListPlaceholder, ActualListErrorMessageColor, listExpectedValueDroplist, listActualValueDroplist;
    private String[] fileNames={Anh1};
}
