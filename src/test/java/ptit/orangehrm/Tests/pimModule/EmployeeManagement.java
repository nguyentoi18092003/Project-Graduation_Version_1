package ptit.orangehrm.Tests.pimModule;


import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.AddEmployeePageObject;
import pageObjects.pageObjectsAdmin.pimObjects.EmployeeListPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.JobPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.PersonalDetailsPageObject;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.MyInfoObjectsC.PersonalDetailPageObjectC;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class EmployeeManagement extends BaseTest {
    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObject dashboadPage;
    DashboardPageObjectC dashboardPageClient;
    EmployeeListPageObject employeeListPage;
    AddEmployeePageObject addEmployeePage;
    PersonalDetailsPageObject personalDetails;
    PersonalDetailPageObjectC personalDetailsPageClient;
    JobPageObject jobPage;


    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("employeeManagement");
        userNameAdmin = excelConfig.getCellData("valid data", 2);
        passwordAdmin = excelConfig.getCellData("valid data", 3);
        firstName = excelConfig.getCellData("valid data", 4);
        middleName = excelConfig.getCellData("valid data", 5);
        lastName = excelConfig.getCellData("valid data", 6);

        userName = excelConfig.getCellData("valid data", 8);
        password = excelConfig.getCellData("valid data", 9);

        confirmPassword = excelConfig.getCellData("valid data", 10);
        employeeId = "";

        //Data man Edit
        driverLicenseNumber=excelConfig.getCellData("valid data", 19);
        licenseExpiryDate= excelConfig.getCellData("valid data", 20);
        nationality=excelConfig.getCellData("valid data", 21);
        maritalStatus=excelConfig.getCellData("valid data", 22);
        dateOfBirth=excelConfig.getCellData("valid data", 23);
        gender=excelConfig.getCellData("valid data", 24);


    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userNameAdmin, passwordAdmin);

    }

    //----------Begin Add New Employee Screen
    //@Test
    public void TC_01_AddNewScreen_UI_Verify_name_all_labels(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        ActualListLabelName = addEmployeePage.getListActualLabel();

        for (int i = 0; i <= ActualListLabelName.size() - 1; i++) {

//             ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 3).replace("\n", "<br>"));

            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i + 3);

            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }

    //@Test
    public void TC_02_AddNewScreen_UI_Verify_placeholder(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        ActualListPlaceholder = addEmployeePage.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());
        for (int i = 0; i <= ActualListPlaceholder.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 10).replace("\n", "<br>"));

            actualPlaceholder = ActualListPlaceholder.get(i);

            expectedPlaceholder = excelConfig.getCellData("Output", i + 10);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
    }

    //@Test
    public void TC_03_AddNewScreen_UI_Verify_the_color_of_error_message(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 14), "firstName");
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 15), "middleName");
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 16), "lastName");

        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 17), "Username");
        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 18), "Password");
        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 19), "Confirm Password");

        ActualListErrorMessageColor = addEmployeePage.getListListErrorMessageColer();

        for (int i = 0; i <= ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 14).replace("\n", "<br>"));

            actualColor = ActualListErrorMessageColor.get(i);

            expectedColor = excelConfig.getCellData("Output", i + 14);
            Assert.assertEquals(actualColor, expectedColor);
        }
    }

    //@Test
    public void TC_04_AddNewScreen_UI_Verify_color_of_button_and_textcolor_in_button(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 20).replace("\n", "<br>"));
        Assert.assertEquals(addEmployeePage.colorOfButton(" Cancel "), excelConfig.getCellData("Output", 20));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 21).replace("\n", "<br>"));
        Assert.assertEquals(addEmployeePage.colorTextInButton(" Cancel "), excelConfig.getCellData("Output", 21));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 22).replace("\n", "<br>"));
        Assert.assertEquals(addEmployeePage.colorOfButton(" Save "), excelConfig.getCellData("Output", 22));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 23).replace("\n", "<br>"));
        Assert.assertEquals(addEmployeePage.colorTextInButton(" Save "), excelConfig.getCellData("Output", 23));


    }

    //@Test
    public void TC_05_AddNewScreen_UI_Check_Default_Value(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 25).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("firstName");
        Assert.assertEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 26).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("middleName");
        Assert.assertEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 27).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("lastName");
        Assert.assertEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 28).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        Assert.assertNotEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 29).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Username");
        Assert.assertEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 30).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Password");
        Assert.assertEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 31).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Confirm Password");
        Assert.assertEquals(ActualValueFirstName, "");
    }

    //@Test
    public void TC_06_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_FirstName(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        addEmployeePage.clickToSaveButton();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 33).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("firstName", excelConfig.getCellData("Output", 33)));

        //Kiem tra DB.....
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 34).replace("\n", "<br>"));
        addEmployeePage.clickToSaveButton();
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        Assert.assertEquals(employeeDB.size(), 0);

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 35).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 35), "firstName");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("firstName", excelConfig.getCellData("Output", 35)));

        //Kiem tra DB.....
        addEmployeePage.clickToSaveButton();
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 35).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.size(), 0);

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 37; i <= 49; i += 2) {
            //Quay lai trang dasboard
            addEmployeePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("PIM");
            employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
            employeeListPage.clickToAddButtton();
            addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
            addEmployeePage.clickToggle();

            employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
            addEmployeePage.enterToTextboxByName(middleName, "middleName");
            addEmployeePage.enterToTextboxByName(lastName, "lastName");

            addEmployeePage.enterToTextboxByLabel(userName, "Username");
            addEmployeePage.enterToTextboxByLabel(password, "Password");
            addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            firstName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByName(firstName, "firstName");
            ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("firstName");
            //Kiem tra giao dien
            Assert.assertEquals(ActualValueFirstName, firstName);

            //Kiem tra DB.....
            addEmployeePage.clickToSaveButton();
            employeeDB = addEmployeePage.employeeFromDB(employeeId);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
            Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
            Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);

        }
    }

    //@Test
    public void TC_07_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_MiddleName(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        System.out.println("sssssssssssssssssssssssssssss        " + employeeId);
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 52).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 52), "middleName");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("middleName", excelConfig.getCellData("Output", 52)));

        //Kiem tra DB.....
        addEmployeePage.clickToSaveButton();
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 35).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.size(), 0);

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 54; i <= 66; i += 2) {
            //Quay lai trang dasboard
            addEmployeePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("PIM");
            employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
            employeeListPage.clickToAddButtton();
            addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
            addEmployeePage.clickToggle();

            employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
            addEmployeePage.enterToTextboxByName(firstName, "firstName");
            addEmployeePage.enterToTextboxByName(lastName, "lastName");

            addEmployeePage.enterToTextboxByLabel(userName, "Username");
            addEmployeePage.enterToTextboxByLabel(password, "Password");
            addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            middleName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByName(middleName, "middleName");
            ActualValueMiddleName = addEmployeePage.getValueInTextBoxByName("middleName");
            Assert.assertEquals(ActualValueMiddleName, middleName);

            //Kiem tra DB.....
            addEmployeePage.clickToSaveButton();
            employeeDB = addEmployeePage.employeeFromDB(employeeId);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
            Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
            Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);
        }
    }

    //@Test
    public void TC_08_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_LastName(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 51).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("lastName", excelConfig.getCellData("Output", 51)));

        //Kiem tra DB.....
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 35).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.size(), 0);

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 52).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 52), "lastName");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("lastName", excelConfig.getCellData("Output", 52)));

        //Kiem tra DB.....
        addEmployeePage.clickToSaveButton();
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 35).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.size(), 0);

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 75; i <= 87; i += 2) {
            //Quay lai trang dasboard
            addEmployeePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("PIM");
            employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
            employeeListPage.clickToAddButtton();
            addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
            addEmployeePage.clickToggle();

            employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
            addEmployeePage.enterToTextboxByName(firstName, "firstName");
            addEmployeePage.enterToTextboxByName(middleName, "middleName");

            addEmployeePage.enterToTextboxByLabel(userName, "Username");
            addEmployeePage.enterToTextboxByLabel(password, "Password");
            addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            lastName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByName(lastName, "lastName");
            ActualValuelastName = addEmployeePage.getValueInTextBoxByName("lastName");
            Assert.assertEquals(ActualValuelastName, lastName);

            //Kiem tra DB.....
            addEmployeePage.clickToSaveButton();
            employeeDB = addEmployeePage.employeeFromDB(employeeId);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
            Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
            Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);
        }
    }

    //@Test
    public void TC_09_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_UserName(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 90).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Username", excelConfig.getCellData("Output", 90)));

        //Kiem tra DB.....
        addEmployeePage.clickToSaveButton();
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 91).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.size(), 0);

        //Entering minlength-1, maxlength+ 1
        for (int i = 92; i <= 94; i += 2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "Username");
            addEmployeePage.clickToSaveButton();
            Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Username", excelConfig.getCellData("Output", i)));

            //Kiem tra DB.....
            employeeDB = addEmployeePage.employeeFromDB(employeeId);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 35).replace("\n", "<br>"));
            Assert.assertEquals(employeeDB.size(), 0);
        }
        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 98; i <= 108; i += 2) {
            //Quay lai trang dasboard
            addEmployeePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("PIM");
            employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
            employeeListPage.clickToAddButtton();
            addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
            addEmployeePage.clickToggle();

            employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
            addEmployeePage.enterToTextboxByName(firstName, "firstName");
            addEmployeePage.enterToTextboxByName(middleName, "middleName");
            addEmployeePage.enterToTextboxByName(lastName, "lastName");
            addEmployeePage.enterToTextboxByLabel(password, "Password");
            addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            userName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByLabel(userName, "Username");
            ActualValueUserName = addEmployeePage.getValueInTextBoxByLabel("Username");
            Assert.assertEquals(ActualValueUserName, userName);

            //Kiem tra DB.....
            addEmployeePage.clickToSaveButton();
            employeeDB = addEmployeePage.employeeFromDB(employeeId);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
            Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
            Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);
        }
    }

    //@Test
    //Tam thoi moi sua dc 9 test -> sau con time sua tiep sau
    public void TC_10_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_Password(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(password, "Username");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 74).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Password", excelConfig.getCellData("Output", 74)));

        //Entering minlength-1, maxlength+ 1,...... xem thêm trong file excel
        for (int i = 75; i <= 81; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "Password");
            Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Password", excelConfig.getCellData("Output", i)));
        }
        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 82; i <= 87; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            password = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByLabel(password, "Password");
            ActualValuePassword = addEmployeePage.getValueInTextBoxByLabel("Password");
            Assert.assertEquals(ActualValuePassword, password);
        }
    }

    //@Test
    public void TC_11_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_ConfirmPassword(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(password, "Username");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 89).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Confirm Password", excelConfig.getCellData("Output", 89)));

        //Entering not match password textbox
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 90).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 90), "Confirm Password");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Confirm Password", excelConfig.getCellData("Output", 90)));

    }

    //@Test
    public void TC_12_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_Radio(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        //select radio
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Select radio");
        addEmployeePage.selectRadioButtonByValue("Enabled");
        Assert.assertEquals(addEmployeePage.isRadioButtonSelectedByValue("Enabled"), true);
        //Unselect radio
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "UnSelect radio");
        addEmployeePage.selectRadioButtonByValue("Disabled");
        Assert.assertEquals(addEmployeePage.isRadioButtonSelectedByValue("Enabled"), false);

    }

    //@Test
    public void TC_13_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_Toogle(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        //check ON
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check Toogle ON");
        Assert.assertTrue(addEmployeePage.checkStatusOfToogle());
        //check OFF
        addEmployeePage.clickToggle();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check Toogle OFF");
        Assert.assertFalse(addEmployeePage.checkStatusOfToogle());
    }

    //@Test
    public void TC_14_AddNewScreen_Function_Check_Save_Sucess_And_Fail_With_Avata(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        //Chek default value
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check Default Avata");
        Assert.assertTrue(addEmployeePage.isDefaultAavataDisplay());

        addEmployeePage.uploadMultipleFiles(driver, fileNames);

        //Check upload sucess
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check Avata After Upload");
        Assert.assertTrue(addEmployeePage.isAavataAfterUploadDisplay());
    }

    //@Test
    public void TC_15_AddNewScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Click_Button_Cancel_Check_Save_Unsucessfully");
        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        addEmployeePage.clickCancelButton();

        Map<String, String> employeeDB = addEmployeePage.employeeFromDB(employeeId);
        Assert.assertEquals(employeeDB.size(), 0);


    }

    // @Test
    public void TC_16_AddNewScreen_Click_Button_Save_Check_Save_sucessfully(Method method) {
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Click_Button_Save_Check_Save_sucessfully");
        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName += employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        //vi password o case tren dang bi bien doi theo data test cua case tren, nen k con la gia tri mac dinh set nhu ban dau, nen dien ca password vao confirm password no se tien ho
        addEmployeePage.enterToTextboxByLabel(password, "Confirm Password");

        addEmployeePage.clickToSaveButton();

        Map<String, String> employeeDB = addEmployeePage.employeeFromDB(employeeId);
        Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
        Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
        Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);

    }
//----------End Add New Employee Screen
// ----------Begin Edit Screen
    //@Test
    public void TC_17_EditScreen_UI_Verify_name_all_labels(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        ActualListLabelName = personalDetails.getListActualLabel();
        for (int i = 0; i <= ActualListLabelName.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));
            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i +148);
            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }
    //@Test
    public void TC_18_EditScreen_UI_Verify_placeholder(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);

        ActualListPlaceholder = personalDetails.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());

        for (int i = 0; i <= ActualListPlaceholder.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 18).replace("\n", "<br>"));

            actualPlaceholder = ActualListPlaceholder.get(i);

            expectedPlaceholder = excelConfig.getCellData("Output", i + 160);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
    }
   // @Test
    public void TC_19_EditScreen_UI_Verify_the_color_of_error_message(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);

        personalDetails.waitForSpinnerIconInvisible();
        personalDetails.clickButtonByName(" Add ");
        personalDetails.enterToTextboxByName(excelConfig.getCellData("Data", 167), "firstName");
        personalDetails.enterToTextboxByName(excelConfig.getCellData("Data", 168), "middleName");
        personalDetails.enterToTextboxByName(excelConfig.getCellData("Data", 169), "lastName");

        personalDetails.enterToTextboxByLabel(excelConfig.getCellData("Data", 170), "Other Id");
        personalDetails.enterToTextboxByLabel(excelConfig.getCellData("Data", 171), "License Expiry Date");
        personalDetails.enterToTextareaByLabel(excelConfig.getCellData("Data", 172), "Comment");


        ActualListErrorMessageColor = personalDetails.getListListErrorMessageColer();

        for (int i = 0; i <= ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 28).replace("\n", "<br>"));

            actualColor = ActualListErrorMessageColor.get(i);

            expectedColor = excelConfig.getCellData("Output", i +167);
            Assert.assertEquals(actualColor, expectedColor);
        }


    }
    //@Test
    public void TC_20_EditScreen_Funtion_Valiate_FirstName(Method method) {

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        employeeId = personalDetails.getValueInTextBoxByLabel("Employee Id");

        // Blank
        personalDetails.enterToTextboxByName(middleName, "middleName");
        personalDetails.enterToTextboxByName(lastName, "lastName");
        otherID=employeeId;
        personalDetails.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth,"Date of Birth");
        personalDetails.clickToRadioByValue(gender);

        personalDetails.scrollToTextboxOnDown("firstName");
        personalDetails.enterToTextboxByName("", "firstName");
        personalDetails.waitForSpinnerIconInvisible();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 174).replace("\n", "<br>"));
        personalDetails.isErrorMessageDisplay(excelConfig.getCellData("Output", 174));

        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 175).replace("\n", "<br>"));
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 176).replace("\n", "<br>"));
        personalDetails.enterToTextboxByName(excelConfig.getCellData("Data", 176), "firstName");
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 177).replace("\n", "<br>"));
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //minlegth, minlength+1, maxlength-1, maxlength,ki tu dac biet, tieng viet co dau,
        for (int i = 178; i <= 190; i+=2) {

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            firstName = excelConfig.getCellData("Data", i);
            personalDetails.enterToTextboxByName(firstName, "firstName");
            ActualValueFirstName = personalDetails.getValueInTextBoxByName("firstName");
            Assert.assertEquals(ActualValueFirstName, firstName);

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            //Check DB..
            personalDetails.clickToSaveButtonByLabel("Personal Details");
            Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
            Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
            Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
            Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
            Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
            Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
            Assert.assertEquals(personalDetailsDB.get("name"),nationality);
            Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
            Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        }
    }
    //@Test
    public void TC_21_EditScreen_Funtion_Valiate_MiddleName(Method method) {

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        employeeId = personalDetails.getValueInTextBoxByLabel("Employee Id");

        personalDetails.enterToTextboxByName(firstName, "firstName");
        personalDetails.enterToTextboxByName(lastName, "lastName");
        otherID=employeeId;
        personalDetails.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth,"Date of Birth");
        personalDetails.clickToRadioByValue(gender);
        //maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 193).replace("\n", "<br>"));
        personalDetails.enterToTextboxByName(excelConfig.getCellData("Data", 193), "middleName");
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 194).replace("\n", "<br>"));
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //minlegth, minlength+1, maxlength-1, maxlength,ki tu dac biet, tieng viet co dau,
        for (int i = 195; i <= 207; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            middleName = excelConfig.getCellData("Data", i);
            personalDetails.enterToTextboxByName(middleName, "middleName");
            ActualValueMiddleName = personalDetails.getValueInTextBoxByName("middleName");
            Assert.assertEquals(ActualValueMiddleName, middleName);

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            //Check DB..
            personalDetails.clickToSaveButtonByLabel("Personal Details");
            Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
            Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
            Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
            Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
            Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
            Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
            Assert.assertEquals(personalDetailsDB.get("name"),nationality);
            Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
            Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        }
    }
    //@Test
    public void TC_22_EditScreen_Funtion_Valiate_LastName(Method method) {

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        employeeId = personalDetails.getValueInTextBoxByLabel("Employee Id");

        // Blank
        personalDetails.enterToTextboxByName(firstName, "firstName");
        personalDetails.enterToTextboxByName(middleName, "middleName");
        otherID=employeeId;
        personalDetails.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth,"Date of Birth");
        personalDetails.clickToRadioByValue(gender);

        //blank
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 210).replace("\n", "<br>"));
        personalDetails.scrollToTextboxOnDown("lastName");
        personalDetails.enterToTextboxByName("", "lastName");
        personalDetails.waitForSpinnerIconInvisible();
        personalDetails.isErrorMessageDisplay(excelConfig.getCellData("Output", 210));

        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 211).replace("\n", "<br>"));
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 212).replace("\n", "<br>"));
        personalDetails.enterToTextboxByName(excelConfig.getCellData("Data", 212), "lastName");
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 213).replace("\n", "<br>"));
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //minlegth, minlength+1, maxlength-1, maxlength,ki tu dac biet, tieng viet co dau,
        for (int i = 214; i <= 226; i+=2) {

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            lastName = excelConfig.getCellData("Data", i);
            personalDetails.enterToTextboxByName(lastName, "lastName");
            ActualValueLastName = personalDetails.getValueInTextBoxByName("lastName");
            Assert.assertEquals(ActualValueLastName, lastName);

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            //Check DB..
            personalDetails.clickToSaveButtonByLabel("Personal Details");
            Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
            Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
            Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
            Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
            Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
            Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
            Assert.assertEquals(personalDetailsDB.get("name"),nationality);
            Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
            Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        }
    }
    //@Test
    public void TC_23_EditScreen_Funtion_Valiate_OtherID(Method method) {

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        employeeId = personalDetails.getValueInTextBoxByLabel("Employee Id");

        personalDetails.enterToTextboxByName(firstName, "firstName");
        personalDetails.enterToTextboxByName(lastName, "lastName");
        personalDetails.enterToTextboxByName(middleName, "middleName");
        otherID=employeeId;

        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth,"Date of Birth");
        personalDetails.clickToRadioByValue(gender);
        //maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 229).replace("\n", "<br>"));
        personalDetails.enterToTextboxByLabel(excelConfig.getCellData("Data", 229), "Other Id");
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 230).replace("\n", "<br>"));
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //minlegth, minlength+1, maxlength-1, maxlength,ki tu dac biet, tieng viet co dau,
        for (int i = 231; i <= 243; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            otherID = excelConfig.getCellData("Data", i);
            personalDetails.enterToTextboxByLabel(otherID, "Other Id");
            ActualValueotherID = personalDetails.getValueInTextBoxByLabel("Other Id");
            Assert.assertEquals(ActualValueotherID, otherID);

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            //Check DB..
            personalDetails.clickToSaveButtonByLabel("Personal Details");
            Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
            Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
            Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
            Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
            Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
            Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
            Assert.assertEquals(personalDetailsDB.get("name"),nationality);
            Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
            Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        }
    }
//    @Test
    public void TC_24_EditScreen_Funtion_Valiate_Driver_License_Number(Method method) {

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        employeeId = personalDetails.getValueInTextBoxByLabel("Employee Id");

        personalDetails.enterToTextboxByName(firstName, "firstName");
        personalDetails.enterToTextboxByName(lastName, "lastName");
        personalDetails.enterToTextboxByName(middleName, "middleName");
        otherID=employeeId;
        personalDetails.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth,"Date of Birth");
        personalDetails.clickToRadioByValue(gender);
        //maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 246).replace("\n", "<br>"));
        personalDetails.enterToDriverNumberTextbox(excelConfig.getCellData("Data", 246));
        personalDetails.clickToSaveButtonByLabel("Personal Details");
        //Check save fail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 247).replace("\n", "<br>"));
        Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));

        //minlegth, minlength+1, maxlength-1, maxlength,ki tu dac biet, tieng viet co dau,
        for (int i = 248; i <= 260; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            driverLicenseNumber = excelConfig.getCellData("Data", i);
            personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
            ActualValuedriverLicenseNumber = personalDetails.getValueInTextBoxByLabel("Other Id");
            Assert.assertEquals(ActualValuedriverLicenseNumber, ActualValuedriverLicenseNumber);

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            //Check DB..
            personalDetails.clickToSaveButtonByLabel("Personal Details");
            Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
            Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
            Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
            Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
            Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
            Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
            Assert.assertEquals(personalDetailsDB.get("name"),nationality);
            Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
            Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        }
    }

    //@Test
    // TC 25: trên web đang gap bug
    public void TC_25_Combobox_Valiate_Nationality(Method method) {
        // Check cac gia tri va thư tu các gia tri
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();

        personalDetails.clickToDropList("Nationality");

        listExpectedValueDroplist = personalDetails.getListValueDroplistFromDB();
        listActualValueDroplist = personalDetails.getListActualValueDropList();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 263).replace("\n", "<br>"));
        for (int i = 0; i < listExpectedValueDroplist.size(); i++) {
            Assert.assertEquals(listActualValueDroplist.get(i), listExpectedValueDroplist.get(i));
        }
    }

   // @Test - Case nay dang khong fix dc, thoi de sau
    public void TC_26_Combobox_Date(Method method) throws InterruptedException {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        employeeId = personalDetails.getValueInTextBoxByLabel("Employee Id");
        otherID=employeeId;
        personalDetails.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.clickToRadioByValue(gender);

        personalDetails.enterToTextboxByName(firstName, "firstName");
        personalDetails.enterToTextboxByName(middleName, "middleName");
        personalDetails.enterToTextboxByName(lastName, "lastName");



        //Cac case invalid
//        for (int i = 265; i <= 293; i+=2) {
//            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
//            personalDetails.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "Date of Birth");
//            personalDetails.scrollCalanderOnDown( "Date of Birth");
//            personalDetails.clickIconCanlanderByLabel("Date of Birth");
//            personalDetails.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
//
//            //Check save fail
//            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+2).replace("\n", "<br>"));
//            personalDetails.clickToSaveButtonByLabel("Personal Details");
//            Assert.assertTrue(personalDetails.isSuccessMessageNotDisplayed("Successfully Updated"));
//        }

        // Cac case valid
        for (int i = 295; i <= 325; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            dateOfBirth=excelConfig.getCellData("Data", i);
            personalDetails.enterToTextboxByLabel(dateOfBirth, "Date of Birth");
            personalDetails.clickLabel("Date of Birth");
            personalDetails.scrollButtonOnTopByName(" Save ");
            personalDetails.clickButtonByJSAndName(" Save ");
            Thread.sleep(5000);
            System.out.println("ssssss"+ i);
            System.out.println("rrr" +personalDetails.getValueInTextBoxByLabel("Date of Birth"));
            System.out.println("hhhh  "+dateOfBirth);
            Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Date of Birth"), dateOfBirth);

            //Check DB..
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));

            Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
            Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
            Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
            Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
            Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeId);
            Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
            Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
            Assert.assertEquals(personalDetailsDB.get("name"),nationality);
            Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
            Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        }

        //Kiem tra hoat dong cuar canlender, khi chon calender se dong lai
        personalDetails.scrollCalanderOnDown( "Date of Birth");
        personalDetails.clickCanlanderIconByLabel("Date of Birth");
        personalDetails.selectDayInCalender(Integer.toString(parseInt(personalDetails.getToday().substring(8)) + 1));

        // verify canlender dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 327).replace("\n", "<br>"));
        personalDetails.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cuua button close
        personalDetails.clickCanlanderIconByLabel("Date of Birth");
        personalDetails.clickButtonInCanlender("Close");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 328).replace("\n", "<br>"));
        personalDetails.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 329).replace("\n", "<br>"));

        personalDetails.clickCanlanderIconByLabel("Date of Birth");
        personalDetails.clickButtonInCanlender("Today");
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Date of Birth"), personalDetails.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 330).replace("\n", "<br>"));

        personalDetails.enterToTextboxByLabel("asdfasdfds", "Date of Birth");
        personalDetails.scrollCalanderOnTop( "Date of Birth");
        personalDetails.clickCanlanderIconByLabel("Date of Birth");
        personalDetails.clickButtonInCanlender("Clear");
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Date of Birth"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 331).replace("\n", "<br>"));

        personalDetails.scrollCalanderOnDown( "Date of Birth");
        personalDetails.clickCanlanderIconByLabel("Date of Birth");

        // focus vao nggya hien tai
        Assert.assertEquals(personalDetails.getColorOfTodayDay(Integer.toString(parseInt(personalDetails.getToday().substring(8)))), excelConfig.getCellData("Output", 331));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 332).replace("\n", "<br>"));

        personalDetails.enterToTextboxByLabel(excelConfig.getCellData("Data", 332), "Date of Birth");
        personalDetails.scrollCalanderOnDown( "Date of Birth");
        personalDetails.clickIconCanlanderByLabel("Date of Birth");
        personalDetails.scrollCalanderOnDown( "Date of Birth");

        Assert.assertEquals(personalDetails.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 332).substring(8)))), excelConfig.getCellData("Output", 332));


    }
    //@Test
    public void TC_26_RadioButton(Method method){
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, GlobalConstants.getGlobalConstants().geteditURl());

        personalDetails=PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();

        //kiem tra chon radio
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 334).replace("\n", "<br>"));
        personalDetails.selectRadioButtonByValue("Male");
        Assert.assertEquals(personalDetails.isRadioButtonSelectedByValue("Male"), true);
        //kiem tra bo chon
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 335).replace("\n", "<br>"));
        personalDetails.selectRadioButtonByValue("Female");
        Assert.assertEquals(personalDetails.isRadioButtonSelectedByValue("Male"), false);

    }
    // ..........End Edit Screen






//@Test
    public void Employee_01_Full_luong_EmployeeManagement(Method method) throws InterruptedException {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
//1. Create an employee and an account for the employee.
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        firstName = excelConfig.getCellData("valid data", 4) + employeeId;
        middleName = excelConfig.getCellData("valid data", 5);
        lastName = excelConfig.getCellData("valid data", 6);

        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");
        addEmployeePage.clickToggle();

        userName = excelConfig.getCellData("valid data", 8) + employeeId;
        password = excelConfig.getCellData("valid data", 9);
        confirmPassword = excelConfig.getCellData("valid data", 10);

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
        addEmployeePage.clickToSaveButton();

        //Check toast messeage thanh cong
        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
        Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
        Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);
// 2. Search employee
        // 2.1. Tim kiem
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        addEmployeePage.waitForSpinnerIconInvisible();
        personalDetails = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        personalDetails.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.searchAndSelectInComboboxByLabel(employeeId, firstName + " " + middleName + " " + lastName, "Employee Name");
        employeeListPage.clickButtonByName(" Search ");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        //2.2 Kiem tra noi dung hien thi trong bang voi DB
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("First (& Middle) Name", "1", employeeDB.get("emp_firstname") + " " + employeeDB.get("emp_middle_name")));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Last Name", "1", employeeDB.get("emp_lastname")));

        employeeListPage.clickEditIcon();
//3. Edit Personal Detail
        //3.1. Nhap thong tin Personal Detail
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));

        employeeListPage.waitForSpinnerIconInvisible();
        personalDetails = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();

        otherID = employeeId;
        driverLicenseNumber = excelConfig.getCellData("valid data", 19);
        licenseExpiryDate = excelConfig.getCellData("valid data", 20);
        nationality = excelConfig.getCellData("valid data", 21);
        maritalStatus = excelConfig.getCellData("valid data", 22);
        dateOfBirth = excelConfig.getCellData("valid data", 23);
        gender = excelConfig.getCellData("valid data", 24);


        addEmployeePage.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate, "License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality, "Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus, "Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth, "Date of Birth");
        personalDetails.clickToRadioByValue(gender);
        personalDetails.clickToSaveButtonByLabel("Personal Details");

        //3.2. Check tren giao dien gia tri duoc nhap hien thi dung trong textbox
        Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
        Assert.assertEquals(personalDetails.getValueInTextBoxByName("firstName"), firstName);
        Assert.assertEquals(personalDetails.getValueInTextBoxByName("middleName"), middleName);
        Assert.assertEquals(personalDetails.getValueInTextBoxByName("lastName"), lastName);
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Employee Id"), employeeId);
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Other Id"), otherID);
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("License Expiry Date"), licenseExpiryDate);
        Assert.assertEquals(personalDetails.getValueInCanlenderTextBox("License Expiry Date"), licenseExpiryDate);
        Assert.assertEquals(personalDetails.getValueInDropdownByLabel("Nationality"), nationality);
        Assert.assertEquals(personalDetails.getValueInDropdownByLabel("Marital Status"), maritalStatus);
        Assert.assertEquals(personalDetails.getValueInCanlenderTextBox("Date of Birth"), dateOfBirth);
        Assert.assertTrue(personalDetails.isRadioCheckedByValue("Male"));

        //3.4. Them moi attachement
        comment1 = excelConfig.getCellData("valid data", 30);
        personalDetails.clickToAddButtonPimByLabel("Attachments");
        personalDetails.uploadMultipleFiles(driver, fileNames);
        personalDetails.enterToTextareaByLabel(comment1, "Comment");
        personalDetails.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, "Add Attachment");
        personalDetails.waitForSpinnerIconInvisible();
        personalDetails.clickToSaveButtonByLabel("Add Attachment");

        //dateAdd=EditPersonalDetails.getToday().substring(0,8)+Integer.toString(parseInt(EditPersonalDetails.getToday().substring(8)) -1);
        dateAdd = personalDetails.getToday();
        //Check toast message
        Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Saved"));

        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("File Name", "1", Anh1));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Description", "1", comment1));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Date Added", "1", dateAdd));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Added By", "1", userNameAdmin));

        //3.5. Kiem tra duoi DB
        Map<String, String> personalDetailsDB = personalDetails.personalDetailsFromDB(employeeId);
        Assert.assertEquals(personalDetailsDB.get("emp_firstname"), firstName);
        Assert.assertEquals(personalDetailsDB.get("emp_middle_name"), middleName);
        Assert.assertEquals(personalDetailsDB.get("emp_lastname"), lastName);
        Assert.assertEquals(personalDetailsDB.get("employee_id"), employeeId);
        Assert.assertEquals(personalDetailsDB.get("emp_other_id"), otherID);
        Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"), licenseExpiryDate);
        Assert.assertEquals(personalDetailsDB.get("name"), nationality);
        Assert.assertEquals(personalDetailsDB.get("emp_marital_status"), maritalStatus);
        Assert.assertEquals(personalDetailsDB.get("emp_birthday"), dateOfBirth);
        //Assert.assertEquals(personalDetailsDB.get("emp_gender"),gender); Gender dang chua co bang rieng hinh nhu no xu li trong code,.., tam thoi khoa lai cho pass da
//4. Sang client kiem tra cac thong tin Personal Detail
        //Sang client de kiem tra
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Verify that the data on the client matches the data created by the admin.");

        personalDetails.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("My Info");
        personalDetailsPageClient = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailsPageClient.waitForSpinnerIconInvisible();

        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByName("firstName").trim(), firstName);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByName("middleName"), middleName);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByName("lastName"), lastName);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByLabel("Employee Id"), employeeId);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByLabel("Other Id"), otherID);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByLabel("License Expiry Date"), licenseExpiryDate);
        Assert.assertEquals(personalDetailsPageClient.getValueInCanlenderTextBox("License Expiry Date"), licenseExpiryDate);
        Assert.assertEquals(personalDetailsPageClient.getValueInDropdownByLabel("Nationality"), nationality);
        Assert.assertEquals(personalDetailsPageClient.getValueInDropdownByLabel("Marital Status"), maritalStatus);
        Assert.assertEquals(personalDetailsPageClient.getValueInCanlenderTextBox("Date of Birth"), dateOfBirth);
        Assert.assertTrue(personalDetailsPageClient.isRadioCheckedByValue("Male"));

        //dateAdd=EditPersonalDetails.getToday().substring(0,8)+Integer.toString(parseInt(EditPersonalDetails.getToday().substring(8)) -1);
        dateAdd = personalDetailsPageClient.getToday();

        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("File Name", "1", Anh1));
        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("Description", "1", comment1));
        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("Date Added", "1", dateAdd));
        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("Added By", "1", userNameAdmin));

        personalDetailsPageClient.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userNameAdmin, passwordAdmin);
        dashboadPage.clickToSidebarLinkByText("PIM");
        personalDetails = PageGeneratorManager.getPersonalDetailsPage(driver);

//5. Login lai ben admin va Terminate_Employment đi
//        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
// 5.1. Tim kiem va cho nhan vien muon Terminate_Employment
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        addEmployeePage.waitForSpinnerIconInvisible();
        personalDetails = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
        personalDetails.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.searchAndSelectInComboboxByLabel(employeeId, firstName + " " + middleName + " " + lastName, "Employee Name");
        employeeListPage.clickButtonByName(" Search ");
        employeeListPage.clickEditIcon();

        personalDetails = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();
//5.2. Terminate_Employment
        personalDetails.clickToLinkByName("Job");
        jobPage = PageGeneratorManager.getJobPage(driver);
        jobPage.waitForSpinnerIconInvisible();
        jobPage.clickButtonByName(" Terminate Employment ");
        jobPage.enterToCanlenderTextboxByLabel(excelConfig.getCellData("valid data", 44), "Termination Date");
        jobPage.selectToDropdownByLabel(excelConfig.getCellData("valid data", 45), "Termination Reason");
        jobPage.enterToTextArea(excelConfig.getCellData("valid data", 46));
        jobPage.clickToSaveButtonInPopup();

        Assert.assertTrue(jobPage.isSuccessMessageDisplayed("Successfully Updated"));
//6. chuyen sang tab mới va sang ben nhan vien kiem tra dang nhap khong thanh cong
//  6.1. Mở tab mới
        String parentID = loginPage.getOrangeIDPage();
        driver.switchTo().newWindow(WindowType.TAB);
//6.2.  Điều hướng đến URL mong muốn
        jobPage.openPageUrl(driver, "http://localhost:90/orangehrm5/orangehrm5/web/index.php/auth/login");

        loginPage = PageGeneratorManager.getLoginPage(driver);
        jobPage.logout();
        loginPage.login(userName, password);
        //Verify login that bai
        Assert.assertTrue(loginPage.isDisplayLoginFailTerminated());

        Thread.sleep(5000);
        //Dang nhap lai voi tai khoan admin, de giu trang thai
        loginPage.login(userNameAdmin, passwordAdmin);
        loginPage.closeTabExOrangePage(parentID);

//Kich hoat tai khoan de cho tai khoan active lai
//7. kich hoat lai tai khoan
        jobPage.clickButtonByName(" Activate Employment ");
        Thread.sleep(5000);
        //    8. Logout va sang ben nhan vien kiem tra dang nhap  thanh cong
        jobPage.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
//        //Them buoc kiem tra dang nhap thanh cong;

        //8. Dang nhap lai tim kiem va xoa employee
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userNameAdmin, passwordAdmin);

        jobPage.clickToSidebarLinkByTextAndJS("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);

        // Tim kiem
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        employeeListPage.searchAndSelectInComboboxByLabel(employeeId, firstName + " " + middleName + " " + lastName, "Employee Name");
        employeeListPage.clickButtonByName(" Search ");

        employeeListPage.clickDeleteIcon();
        employeeListPage.clickButtonByName(" Yes, Delete ");
        Thread.sleep(5000);
        //logout,va kiem tra employee vua xoa dang nhap khong thanh cong
        personalDetailsPageClient.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
        //Kiem tra login that bai
        Assert.assertTrue(loginPage.isDisplayLoginFail());
    }

   // @Test
    public void Full_luong_EmployeeManagement_Dupicated_Username(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
//1. Create an employee and an account for the employee.
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        firstName = excelConfig.getCellData("valid data", 4) + employeeId;
        middleName = excelConfig.getCellData("valid data", 5);
        lastName = excelConfig.getCellData("valid data", 6);

        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");
        addEmployeePage.clickToggle();

        userName = excelConfig.getCellData("valid data", 8) + employeeId;
        password = excelConfig.getCellData("valid data", 9);
        confirmPassword = excelConfig.getCellData("valid data", 10);

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
        addEmployeePage.clickToSaveButton();

        //Check toast messeage thanh cong
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
        Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
        Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);

        //Tao them mot tai khoan co user dupicate voi username da ton tai trong he thong
        addEmployeePage.openPageUrl(driver, GlobalConstants.getGlobalConstants().getAddEmployeeUrl());
        firstName = excelConfig.getCellData("valid data", 4) + employeeId;
        middleName = excelConfig.getCellData("valid data", 5);
        lastName = excelConfig.getCellData("valid data", 6);

        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");
        addEmployeePage.clickToggle();

        userName = excelConfig.getCellData("valid data", 8) + employeeId;
        password = excelConfig.getCellData("valid data", 9);
        confirmPassword = excelConfig.getCellData("valid data", 10);

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
        Map<String,String> employeeDBTruoc = addEmployeePage.employeeFromDB(employeeId);
        addEmployeePage.clickToSaveButton();

        Assert.assertTrue(addEmployeePage.isSuccessMessageNotDisplayed("Successfully Saved"));

        //Check DB
        Map<String,String> employeeDBSau = addEmployeePage.employeeFromDB(employeeId);
        employeeDB = addEmployeePage.employeeFromDB(employeeId);

        //Check Khong luu them vao DB
        Assert.assertEquals(employeeDBSau.size(),employeeDBTruoc.size());

    }
    @Test
    //Testcase nay he thong dang loi
    public void Full_luong_EmployeeManagement_Dupicated_EmployeeID(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 1).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
//1. Create an employee and an account for the employee.
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);

        employeeId = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        firstName = excelConfig.getCellData("valid data", 4) + employeeId;
        middleName = excelConfig.getCellData("valid data", 5);
        lastName = excelConfig.getCellData("valid data", 6);

        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");
        addEmployeePage.clickToggle();

        userName = excelConfig.getCellData("valid data", 8) + employeeId;
        password = excelConfig.getCellData("valid data", 9);
        confirmPassword = excelConfig.getCellData("valid data", 10);

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
        //Nhap emloyee trung voi cai da co
        addEmployeePage.enterToTextboxByLabel(employeeId, "Employee Id");
        addEmployeePage.clickToSaveButton();

        //Check toast messeage thanh cong
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        employeeDB = addEmployeePage.employeeFromDB(employeeId);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        Assert.assertEquals(employeeDB.get("emp_firstname"), firstName);
        Assert.assertEquals(employeeDB.get("emp_middle_name"), middleName);
        Assert.assertEquals(employeeDB.get("emp_lastname"), lastName);

//        addEmployeePage.openPageUrl(driver, GlobalConstants.getGlobalConstants().getAddEmployeeUrl());
        addEmployeePage.backToPage(driver);

        //1. Create an employee and other account for the employee and dupicate employeeID
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);


        firstName = excelConfig.getCellData("valid data", 4) + employeeId;
        middleName = excelConfig.getCellData("valid data", 5);
        lastName = excelConfig.getCellData("valid data", 6);

        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");
        addEmployeePage.clickToggle();

        userName = excelConfig.getCellData("valid data", 8) + employeeId+"sss";
        password = excelConfig.getCellData("valid data", 9);
        confirmPassword = excelConfig.getCellData("valid data", 10);

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
        Map<String,String> employeeDBTruoc = addEmployeePage.employeeFromDB(employeeId);
        addEmployeePage.enterToTextboxByLabel(employeeId, "Employee Id");
        addEmployeePage.clickToSaveButton();

        Assert.assertTrue(addEmployeePage.isSuccessMessageNotDisplayed("Successfully Saved"));

        //Check DB
        Map<String,String> employeeDBSau = addEmployeePage.employeeFromDB(employeeId);
        employeeDB = addEmployeePage.employeeFromDB(employeeId);

        //Check Khong luu them vao DB
        Assert.assertEquals(employeeDBSau.size(),employeeDBTruoc.size());

    }

    //    @AfterClass
//    public void afterClass() {
//        closeBrowser();
//
//    }
    private String userNameAdmin = "automationfc", passwordAdmin = "Automationfc@123";
    private String firstName, middleName, lastName, employeeId, userName, password, confirmPassword;
    private Map<String, String> employeeDB;
    private ExcelConfig excelConfig;
    private String actualLabelName, expectedLabelName, licenseExpiryDate, comment, actualPlaceholder, expectedPlaceholder;
    private String actualColor, expectedColor, ActualValueFirstName, ActualValueMiddleName, ActualValuelastName, ActualValueUserName, ActualValuePassword,ActualValueLastName,ActualValueotherID,ActualValuedriverLicenseNumber;
    private List<String> ActualListLabelName, ActualListPlaceholder, ActualListErrorMessageColor,listExpectedValueDroplist,listActualValueDroplist;

    private String Anh1 = "Anh1.jpg";
    private String[] fileNames = {Anh1};

    //private String comment ;
    private String dateAdd;


    private String otherID, driverLicenseNumber, nationality, maritalStatus, dateOfBirth, gender, country, comment1;


}
