package ptit.orangehrm.Tests.TimeModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.timeObjects.MyTimesheetPageObject;
import pageObjects.pageObjectsAdmin.timeObjects.PunchInPageObject;
import pageObjects.pageObjectsAdmin.timeObjects.PunchOutPageObject;
import pageObjects.pageObjectsClient.timeObject.MyTimesheetPageObjectC;
import pageObjects.pageObjectsClient.timeObject.PunchInPageObjectC;
import pageObjects.pageObjectsClient.timeObject.PunchOutPageObjectC;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;

public class PunchIn_PunchOut extends BaseTest {
    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObject dashboadPage;
    MyTimesheetPageObjectC myTimesheetPageC;
    MyTimesheetPageObject myTimesheetPage;
    PunchInPageObjectC punchInPageC;
    PunchOutPageObjectC punchOutPageC;


    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("personalDetails");
    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver);

        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("addEmployeeDta");

        loginPage.login("NguyenToiClient", "Toi%03577");
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Time");
        myTimesheetPageC = PageGeneratorManager.getMyTimesheetPageObjectClient(driver);

    }

    @Test
    public void TC_01_Full_luong_PunchIn_PunOut(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "rrrr");

        myTimesheetPageC.clickToSubMenuByName("Attendance ");
        myTimesheetPageC.clickToItemSubMenuByName("Punch In/Out");
        punchInPageC=PageGeneratorManager.getPunchInPageObjectClient(driver);

        punchInPageC.waitForSpinnerIconInvisible();
        Assert.assertEquals(punchInPageC.getValueInCanlenderTextBoxByJS(),punchInPageC.getToday());
        Assert.assertEquals(punchInPageC.getValueTimeTextBoxByJS(),punchInPageC.getTimeNow());

        punchInPageC.enterToTextArea("Note...................");
        punchInPageC.scrollButtonOnTopByName(" In ");
        punchInPageC.clickButtonByName(" In ");

        punchOutPageC=PageGeneratorManager.getPunchOutPageObjectClient(driver);

        punchOutPageC.waitForSpinnerIconInvisible();

        punchOutPageC.enterToTextArea("Note...................");
//        Assert.assertEquals(punchOutPageC.getValueInCanlenderTextBoxByJS(),punchOutPageC.getToday());
//        Assert.assertEquals(punchOutPageC.getValueTimeTextBoxByJS(),punchOutPageC.getTimeNow());
        punchOutPageC.scrollButtonOnTopByName(" Out ");
        punchOutPageC.waitForSpinnerIconInvisible();
        punchOutPageC.clickButtonByName(" Out ");

        punchInPageC=PageGeneratorManager.getPunchInPageObjectClient(driver);
        punchInPageC.waitForSpinnerIconInvisible();
        //Logout
        punchInPageC.logout();

        //Khoi tao trang login
        loginPage = PageGeneratorManager.getLoginPage(driver);
        //Thuc hien login
        loginPage.login("automationfc", "Automationfc@123");

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Time");
        myTimesheetPage = PageGeneratorManager.getMyTimesheetPageObject(driver);
        myTimesheetPage.clickToSubMenuByName("Attendance ");
        myTimesheetPage.clickToItemSubMenuByName("Employee Records");

        //Tim kiem nhan vien muon phe duyẹt
        myTimesheetPage.searchAndSelectInComboboxByLabel("client","Nguyen  Thi Toi client","Employee Name");
        //Click xem
        myTimesheetPage.clickButtonByJSAndName(" View ");
        myTimesheetPage.clickEditIcon();




    }

//    @AfterClass
//    public void afterClass() {
//        closeBrowser();
//
//    }
    private String Anh1 = "Anh1.jpg";
    private ExcelConfig excelConfig;
}
