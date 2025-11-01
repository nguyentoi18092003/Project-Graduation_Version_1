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
import pageObjects.pageObjectsAdmin.pimObjects.EmploymentStatusPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.JobPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.PersonalDetailsPageObject;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Admin_03_Employment_Status extends BaseTest {
    private WebDriver driver;
    private String browserName;
    private String userName, password, myAccountName;
    private String employmentStatusName;

    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    AdminPageObject adminPage;
    EmploymentStatusPageObject employmentStatusPage;
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
        employmentStatusName = "Woking" + getEmailRadom();
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
        adminPage.clickToItemSubMenuByName("Job ", "Employment Status");
        adminPage.waitForSpinnerIconInvisible();

        employmentStatusPage = PageGeneratorManager.getEmploymentStatusPage(driver);
        employmentStatusPage.waitForSpinnerIconInvisible();
        employmentStatusPage.clickToAddButtonAdminByLabel("Employment Status");
        employmentStatusPage.enterToTextboxByLabel(employmentStatusName, "Name");

        employmentStatusPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, "Add Employment Status");
        employmentStatusPage.waitForSpinnerIconInvisible();
        employmentStatusPage.clickToSaveButtonByLabel("Add Employment Status");


    }

    @Test
    public void Pay_Grade_02_Check_Pay_Grades_In_PIM(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Pay_Grade_02_Check_Pay_Grades_In_PIM");
        employmentStatusPage.clickToSidebarLinkByText("PIM");
        employmentStatusPage.waitForSpinnerIconInvisible();

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

        Assert.assertTrue(jobPage.isItemDropdownDisplayed(employmentStatusName, "Employment Status"));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }
}
