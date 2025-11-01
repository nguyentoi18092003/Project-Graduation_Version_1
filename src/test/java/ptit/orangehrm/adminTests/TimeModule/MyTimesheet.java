package ptit.orangehrm.adminTests.TimeModule;

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

public class MyTimesheet extends BaseTest {

    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObject dashboadPage;
    EmployeeListPageObject employeeListPage;
    AddEmployeePageObject addEmployeePage;

    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("personalDetails");

    }

//    @Parameters({"url", "browser"})
//    @BeforeMethod
//    public void beforeMethod(String url, String browserName) {
//        driver = getBrowserDriver(browserName, url);
//        this.browserName = browserName;
//
//        loginPage = PageGeneratorManager.getLoginPage(driver);
//
//        excelConfig = ExcelConfig.getExcelData();
//        excelConfig.switchToSheet("addEmployeeDta");
//
//        loginPage.login("automationfc", "Automationfc@123");
//        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
//        dashboadPage.clickToSidebarLinkByText("Time");
//        myTimesheetPage=PageGeneratorManager.getMyTimesheetPage(driver);
//
//    }
//
//    @Test
//    public void TC_01_Verify_name_all_labels(Method method) {
//        myTimesheetPage.clickToButtonByName("Edit");
//        editTimesheetPage=PageGeneratorManager.getEditTimesheetPage(driver);
//
//        editTimesheetPage.clickAddRowButton();
//        editTimesheetPage.selectValueInDropdown(columnName, rowIndex,value);
//        editTimesheetPage.selectValueInDropdown(columnName, rowIndex,value);
//
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        editTimesheetPage.enterValueAtColumnName (columnName, rowIndex, value);
//        //tr[1]/th[contains(string(),'Project')]-- Lay cot
//        //tr[1]/td[2] -- Lay hang
//        //tr[1]/td[2]//i -- click vao mui ten
//        //tr[1]/td[10]//button/i -- button xoa
//
//
//
//    }
////        ActualListLabelName = addEmployeePage.getListActualLabel();
////
////        for (int i = 0; i <= ActualListLabelName.size() - 1; i++) {
////
////            // ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));
////            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));
////
////            actualLabelName = ActualListLabelName.get(i);
////            expectedLabelName = excelConfig.getCellData("Output", i + 2);
////
////            Assert.assertEquals(actualLabelName, expectedLabelName);
////        }
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

