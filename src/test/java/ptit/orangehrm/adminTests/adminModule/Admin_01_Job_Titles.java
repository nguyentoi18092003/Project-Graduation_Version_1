package ptit.orangehrm.adminTests.adminModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.pageObjectsAdmin.adminObjects.AdminPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.*;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Admin_01_Job_Titles extends BaseTest {
    private WebDriver driver;
    private String browserName;//Dung trong cho startTest
    private String userName, password, myAccountName;
    private String jobTitle = "Cong Viec 5";
    private String Anh1 = "Anh1.jpg";
    private String Anh2 = "Anh2.jpg";
    private String Anh3 = "Anh3.jpg";
    private String[] fileNames = {Anh1};

    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    AdminPageObject adminPage;
    JobTitlesPageObject jobTitlesPage;
    JobCategoriesPageObject jobCategoriesPage;
    EmploymentStatusPageObject employmentStatus;

    PersonalDetailsPageObject personalDetailsPage;
    EmployeeListPageObject employeeListPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;
        userName = "automationfc";
        password = "Automationfc@123";
        myAccountName = "Automation FC";

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByName(userName, "username");
        loginPage.enterToTextboxByName(password, "password");
        loginPage.clickToLoginButton();

        loginPage.waitForSpinnerIconInvisible();
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);

        Assert.assertEquals(dashboardPage.getNameMyAccount(), myAccountName);

    }

    @Test
    public void Job_01_Add_Job_Tilte(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Employee_01_Add_New");
        dashboardPage.clickToSidebarLinkByText("Admin");
        dashboardPage.waitForSpinnerIconInvisible();
        adminPage = PageGeneratorManager.getAdminPageObject(driver);
        adminPage.waitForSpinnerIconInvisible();
        adminPage.clickToSubMenuByName("Job ");
        //adminPage.waitForSpinnerIconInvisible();
        adminPage.clickToItemSubMenuByName("Job ", "Job Titles");
        adminPage.waitForSpinnerIconInvisible();

        jobTitlesPage = PageGeneratorManager.getJobTitlesPage(driver);
        jobTitlesPage.waitForSpinnerIconInvisible();
        jobTitlesPage.clickToAddButtonAdminByLabel("Job Titles");
        jobTitlesPage.enterToTextboxByLabel(jobTitle, "Job Title");

        jobTitlesPage.uploadMultipleFiles(driver, fileNames);
        jobTitlesPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, "Add Job Title");
        jobTitlesPage.waitForSpinnerIconInvisible();
        jobTitlesPage.clickToSaveButtonByLabel("Add Job Title");


    }

    @Test
    public void Job_02_Check_Job_Tilte_In_PIM(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Employee_01_Add_New");
        jobTitlesPage.clickToSidebarLinkByText("PIM");
        jobTitlesPage.waitForSpinnerIconInvisible();

        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

        employeeListPage.waitForSpinnerIconInvisible();

        employeeListPage.clickToEditButton("Actions", "1");

        employeeListPage.waitForSpinnerIconInvisible();

        personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetailsPage.waitForSpinnerIconInvisible();
        personalDetailsPage.clickToLinkByName("Job");
        personalDetailsPage.waitForSpinnerIconInvisible();

        jobTitlesPage = PageGeneratorManager.getJobTitlesPage(driver);
        Assert.assertTrue(jobTitlesPage.isItemDropdownDisplayed(jobTitle, "Job Title"));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }

}