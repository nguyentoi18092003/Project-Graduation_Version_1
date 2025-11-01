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
import pageObjects.pageObjectsAdmin.pimObjects.PayGradesPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.PersonalDetailsPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.SalaryPageObject;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class Admin_02_Pay_Grades extends BaseTest {
    private WebDriver driver;
    private String browserName;
    private String userName, password, myAccountName;
    private String payGradeName;

    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    AdminPageObject adminPage;
    PayGradesPageObject payGradesPage;
    EmployeeListPageObject employeeListPage;
    PersonalDetailsPageObject personalDetailsPage;
    SalaryPageObject salaryPage;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;
        userName = "automationfc";
        password = "Automationfc@123";
        myAccountName = "Automation FC";
        payGradeName = "Intern" + getEmailRadom();

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

        adminPage.clickToItemSubMenuByName("Job ", "Pay Grades");
        adminPage.waitForSpinnerIconInvisible();

        payGradesPage = PageGeneratorManager.getPayGradesPage(driver);
        payGradesPage.waitForSpinnerIconInvisible();
        payGradesPage.clickToAddButtonAdminByLabel("Pay Grades");
        payGradesPage.enterToTextboxByLabel(payGradeName, "Name");

        payGradesPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, "Add Pay Grade");
        payGradesPage.waitForSpinnerIconInvisible();
        payGradesPage.clickToSaveButtonByLabel("Add Pay Grade");


    }

    @Test
    public void Pay_Grade_02_Check_Pay_Grades_In_PIM(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Pay_Grade_02_Check_Pay_Grades_In_PIM");
        payGradesPage.clickToSidebarLinkByText("PIM");
        payGradesPage.waitForSpinnerIconInvisible();

        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

        employeeListPage.waitForSpinnerIconInvisible();

        employeeListPage.clickToEditButton("Actions", "1");

        employeeListPage.waitForSpinnerIconInvisible();

        personalDetailsPage = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetailsPage.waitForSpinnerIconInvisible();
        personalDetailsPage.clickToLinkByName("Salary");
        personalDetailsPage.waitForSpinnerIconInvisible();

        salaryPage = PageGeneratorManager.getSalaryPage(driver);
        payGradesPage.clickToAddButtonPimByLabel("Assigned Salary Components");
        payGradesPage.waitForSpinnerIconInvisible();

        Assert.assertTrue(payGradesPage.isItemDropdownDisplayed(payGradeName, "Pay Grade"));
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }

}
