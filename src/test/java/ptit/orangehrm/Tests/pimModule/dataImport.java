package ptit.orangehrm.Tests.pimModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.*;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.MyInfoObjectsC.*;
import pageUIs.pageUIsAdmin.pimUIs.DataImportPageUI;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;

public class dataImport extends BaseTest {
    private WebDriver driver;
    private String browserName;//Dung trong cho startTest

    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    EmployeeListPageObject employeeListPage;
    DataImportPageObject dataImportPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver);

        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("PIMDta");

        userNameHR = "automationfc";
        passwordHR = "Automationfc@123";

        loginPage.login(userNameHR, passwordHR);
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);
        dashboardPage.clickToSidebarLinkByText("PIM");

    }

    @Test
    public void Employee_01_Add_New(Method method) {
        //Moi lan chay phari vao sua ma va email ms chay pass dc, dang lam do chuc nang nay,de lam sau
        //ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        ExtentTestManager.startTest(method.getName(),"lll");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        //employeeListPage.waitForSpinnerIconInvisible();
        employeeListPage.clickToConfigurationSubmenu();
        employeeListPage.clickToItemConfigurationSubmenu("Data Import");
        dataImportPage=PageGeneratorManager.getDataImportPageObject(driver);
        dataImportPage.uploadMultipleFiles(driver,fileCSV);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dataImportPage.clickToUploadButton();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        dataImportPage.isPopUpSucessDisplay();


    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }

    private String Anh1 = "Anh1.jpg";
    private String Anh2 = "Anh2.jpg";
    private String Anh3 = "Anh3.jpg";
    private String importListEmployee="sample_employees.csv";
    private String[] fileNames = {Anh1};
    private String[] fileCSV={importListEmployee};

    //private String comment ;
    private String dateAdd, displayName;

    private String userNameHR, passwordHR, userNameClient, passwordClient;
    private String userName, password, confirmPassword;
    private ExcelConfig excelConfig;
}
