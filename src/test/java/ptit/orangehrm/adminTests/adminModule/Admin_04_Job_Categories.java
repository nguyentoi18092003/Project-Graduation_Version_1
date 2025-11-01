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
import pageObjects.pageObjectsAdmin.pimObjects.EmployeeListPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.JobCategoriesPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.JobPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.PersonalDetailsPageObject;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Admin_04_Job_Categories extends BaseTest {
    private WebDriver driver;
    private String browserName;
    private String userName, password, myAccountName;
    private String jobCategoryName;

    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    AdminPageObject adminPage;
    JobCategoriesPageObject jobCategoriesPage;
    EmployeeListPageObject employeeListPage;
    PersonalDetailsPageObject personalDetailsPage;
    JobPageObject jobPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;
        userName = "automationfc";
        password = "Automationfc@123";
        myAccountName = "Automation FC";
        jobCategoryName = "IT" + getEmailRadom();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByName(userName, "username");
        loginPage.enterToTextboxByName(password, "password");
        loginPage.clickToLoginButton();

        loginPage.waitForSpinnerIconInvisible();
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);

        Assert.assertEquals(dashboardPage.getNameMyAccount(), myAccountName);
    }

    @Test
    public void Pay_Grade_01_Add_Pay_Grades(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Pay_Grade_01_Add_Pay_Grades");
        dashboardPage.clickToSidebarLinkByText("Admin");
        dashboardPage.waitForSpinnerIconInvisible();
        adminPage = PageGeneratorManager.getAdminPageObject(driver);
        adminPage.waitForSpinnerIconInvisible();
        adminPage.clickToSubMenuByName("Job ");

        adminPage.clickToItemSubMenuByName("Job ", "Job Categories");
        adminPage.waitForSpinnerIconInvisible();

        jobCategoriesPage = PageGeneratorManager.getJobCategoriesPage(driver);
        jobCategoriesPage.waitForSpinnerIconInvisible();
        jobCategoriesPage.clickToAddButtonAdminByLabel("Job Categories");
        jobCategoriesPage.enterToTextboxByLabel(jobCategoryName, "Name");

        jobCategoriesPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, "Add Job Category");
        jobCategoriesPage.waitForSpinnerIconInvisible();
        jobCategoriesPage.clickToSaveButtonByLabel("Add Job Category");


    }

    @Test
    public void Pay_Grade_02_Check_Pay_Grades_In_PIM(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Pay_Grade_02_Check_Pay_Grades_In_PIM");
        jobCategoriesPage.clickToSidebarLinkByText("PIM");
        jobCategoriesPage.waitForSpinnerIconInvisible();

        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

        employeeListPage.waitForSpinnerIconInvisible();

        employeeListPage.clickToEditButton("Actions", "1");

        employeeListPage.waitForSpinnerIconInvisible();

        personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetailsPage.waitForSpinnerIconInvisible();
        personalDetailsPage.clickToLinkByName("Job");
        personalDetailsPage.waitForSpinnerIconInvisible();

        jobPage = PageGeneratorManager.getJobPage(driver);
        jobPage.waitForSpinnerIconInvisible();

        Assert.assertTrue(jobPage.isItemDropdownDisplayed(jobCategoryName, "Job Category"));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }
}