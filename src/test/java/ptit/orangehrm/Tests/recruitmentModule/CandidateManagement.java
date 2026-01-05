package ptit.orangehrm.Tests.recruitmentModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.recruitmentObjects.*;
import pageUIs.pageUIsAdmin.recruitmentUIs.AddCandidatePageUI;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CandidateManagement extends BaseTest {
    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObject dashboadPage;
    AddCandidatePageObject addCandidatePage;
    ShortlistPageObject shortlistPage;
    ScheduleInterviewPageObject scheduleInterviewPage;
    MarkInterviewResultPageObject makeInterviewResultPage;
    OfferJobPageObject offerJobPage;
    OfferDeclinedPageObject offerDeclinedPage;
    RejectCandidatePageObject rejectCandidatePage;
    HireCandidatePageObject hireCandidatePage;
    CandidateListPageObject candidateListPage;

    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("CandidateManagement");

        userNameAdmin = excelConfig.getCellData("valid data", 3);
        passwordAdmin = excelConfig.getCellData("valid data", 4);

        firstName = excelConfig.getCellData("valid data", 5);
        middleName = excelConfig.getCellData("valid data", 6);
        lastName = excelConfig.getCellData("valid data", 7);
        vacancy = excelConfig.getCellData("valid data", 8);
        email = excelConfig.getCellData("valid data", 9);
        contactNumber = excelConfig.getCellData("valid data", 10);
        dateOfApplication= excelConfig.getCellData("valid data", 12);
        interviewTitle = excelConfig.getCellData("valid data",18);
        interviewer = excelConfig.getCellData("valid data",19);
        interviewDate = excelConfig.getCellData("valid data",20);
    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userNameAdmin, passwordAdmin);
    }

    // Begin Add Candidate Screen
//    @Test
    public void TC_01_AddCandidateScreen_UI_Verify_name_all_labels(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        ActualListLabelName = addCandidatePage.getListActualLabel();

        for (int i = 0; i < ActualListLabelName.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 3).replace("\n", "<br>"));

            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i + 3);

            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }
//    @Test
    public void TC_02_AddCandidateScreen_UI_Verify_placeholder(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        ActualListPlaceholder = addCandidatePage.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());
        for (int i = 0; i < ActualListPlaceholder.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 12).replace("\n", "<br>"));

            actualPlaceholder = ActualListPlaceholder.get(i);

            expectedPlaceholder = excelConfig.getCellData("Output", i + 12);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
    }
//    @Test
    public void TC_03_AddCandidateScreen_UI_Verify_the_color_of_error_message(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        addCandidatePage.enterToTextboxByName(excelConfig.getCellData("Data", 20), "firstName");
        addCandidatePage.enterToTextboxByName(excelConfig.getCellData("Data", 21), "middleName");
        addCandidatePage.enterToTextboxByName(excelConfig.getCellData("Data", 22), "lastName");

        addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 23), "Email");
        addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 24), "Keywords");
        addCandidatePage.enterToTextareaByLabel(excelConfig.getCellData("Data", 25), "Notes");

        ActualListErrorMessageColor = addCandidatePage.getListListErrorMessageColer();

        for (int i = 0; i < ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 20).replace("\n", "<br>"));

            actualColor = ActualListErrorMessageColor.get(i);

            expectedColor = excelConfig.getCellData("Output", i + 20);
            Assert.assertEquals(actualColor, expectedColor);
        }
    }
//    @Test
    public void TC_04_AddCandidateScreen_UI_Verify_color_of_button_and_textcolor_in_button(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 26).replace("\n", "<br>"));
        Assert.assertEquals(addCandidatePage.colorOfButton(" Cancel "), excelConfig.getCellData("Output", 26));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 27).replace("\n", "<br>"));
        Assert.assertEquals(addCandidatePage.colorTextInButton(" Cancel "), excelConfig.getCellData("Output", 27));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 28).replace("\n", "<br>"));
        Assert.assertEquals(addCandidatePage.colorOfButton(" Save "), excelConfig.getCellData("Output", 28));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 29).replace("\n", "<br>"));
        Assert.assertEquals(addCandidatePage.colorTextInButton(" Save "), excelConfig.getCellData("Output", 29));


    }
//    @Test
    public void TC_05_AddCandidateScreen_UI_Check_Default_Value(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 31).replace("\n", "<br>"));
        ActualValueFirstName = addCandidatePage.getValueInTextBoxByName("firstName");
        Assert.assertEquals(ActualValueFirstName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 32).replace("\n", "<br>"));
        ActualValueMiddleName = addCandidatePage.getValueInTextBoxByName("middleName");
        Assert.assertEquals(ActualValueMiddleName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 33).replace("\n", "<br>"));
        ActualValueLastName = addCandidatePage.getValueInTextBoxByName("lastName");
        Assert.assertEquals(ActualValueLastName, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 34).replace("\n", "<br>"));
        ActualValueEmail = addCandidatePage.getValueInTextBoxByLabel("Email");
        Assert.assertEquals(ActualValueEmail, null);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 29).replace("\n", "<br>"));
        ActualValueContactNumber = addCandidatePage.getValueInTextBoxByLabel("Contact Number");
        Assert.assertEquals(ActualValueContactNumber, null);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 30).replace("\n", "<br>"));
        ActualValueKeyword = addCandidatePage.getValueInTextBoxByLabel("Keywords");
        Assert.assertEquals(ActualValueKeyword, null);

    }
//    @Test
    public void TC_06_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_FirstName(Method method) {
        //Leave the field blank
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        String randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");

        addCandidatePage.clickButtonByJSAndName(" Save ");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 39).replace("\n", "<br>"));
        verifyTrue(addCandidatePage.isErrorMsgDisplayedByName("firstName", excelConfig.getCellData("Output", 39)));

        //Kiem tra DB.....
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 40).replace("\n", "<br>"));
        addCandidatePage.clickButtonByJSAndName(" Save ");
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        verifyEquals(candidateDB.size(), 0);

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 41).replace("\n", "<br>"));
        addCandidatePage.enterToTextboxByName(excelConfig.getCellData("Data", 41), "firstName");
        verifyTrue(addCandidatePage.isErrorMsgDisplayedByName("firstName", excelConfig.getCellData("Output", 41)));

        //Kiem tra DB.....
        addCandidatePage.clickButtonByJSAndName(" Save ");
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 42).replace("\n", "<br>"));
        verifyEquals(candidateDB.size(), 0);

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 43; i <= 57; i += 2) {
//            startCase();
            //Quay lai trang dasboard
            addCandidatePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("Recruitment");
            candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
            candidateListPage.waitForSpinnerIconInvisible();
            candidateListPage.clickToAddButtton();
            candidateListPage.waitForSpinnerIconInvisible();
            addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

            randomPrefix = addCandidatePage.getEmailRadom();
            email1 = randomPrefix + email;
            addCandidatePage.enterToTextboxByName(middleName,"middleName");
            addCandidatePage.enterToTextboxByName(lastName,"lastName");
            addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
            addCandidatePage.enterToTextboxByLabel(email1,"Email");
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            firstName = excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByName(firstName, "firstName");
            ActualValueFirstName = addCandidatePage.getValueInTextBoxByName("firstName");
            //Kiem tra giao dien
            Assert.assertEquals(ActualValueFirstName, firstName);

            //Kiem tra DB.....
            addCandidatePage.clickButtonByJSAndName(" Save ");
            verifyTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
//            errors = endCase();
//            excelConfig.logExcelAndJira("Actual result", "Bug ID",i, this,errors);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
//            startCase();
            candidateDB = addCandidatePage.CandidateFromDB(email1);
            addCandidatePage.sleepInSecond(20);
            verifyEquals(candidateDB.get("first_name"), firstName.trim());
            verifyEquals(candidateDB.get("middle_name"), middleName);
            verifyEquals(candidateDB.get("last_name"), lastName);
            verifyEquals(candidateDB.get("email"), email1);
            verifyEquals(candidateDB.get("contact_number"), contactNumber);
            verifyEquals(candidateDB.get("date_of_application"), dateOfApplication);
            verifyEquals(candidateDB.get("vacancy_name"), vacancy);
            verifyEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
//            errors = endCase();
//            excelConfig.logExcelAndJira("Actual result", "Bug ID",i+1, this,errors);
            addCandidatePage.sleepInSecond(20);
        }
    }
//    @Test
    public void TC_07_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_MiddleName(Method method) {
        //Leave the field blank
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        String randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(firstName, "firstName");
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 60).replace("\n", "<br>"));
        addCandidatePage.enterToTextboxByName(excelConfig.getCellData("Data", 60), "middleName");
        Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByName("middleName", excelConfig.getCellData("Output", 60)));

        //Kiem tra DB.....
        addCandidatePage.clickButtonByJSAndName(" Save ");
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 61).replace("\n", "<br>"));
        Assert.assertEquals(candidateDB.size(), 0);

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 62; i <= 76; i += 2) {
            //Quay lai trang dasboard
//            startCase();
            addCandidatePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("Recruitment");
            candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
            candidateListPage.waitForSpinnerIconInvisible();
            candidateListPage.clickToAddButtton();
            candidateListPage.waitForSpinnerIconInvisible();
            addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

            randomPrefix = addCandidatePage.getEmailRadom();
            email1 = randomPrefix + email;
            addCandidatePage.enterToTextboxByName(firstName, "firstName");
            addCandidatePage.enterToTextboxByName(lastName,"lastName");
            addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
            addCandidatePage.enterToTextboxByLabel(email1,"Email");
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            middleName = excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByName(middleName,"middleName");
            ActualValueMiddleName = addCandidatePage.getValueInTextBoxByName("middleName");
            //Kiem tra giao dien
            Assert.assertEquals(ActualValueMiddleName, middleName);

            //Kiem tra DB.....
            addCandidatePage.clickButtonByJSAndName(" Save ");
            Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
//            errors = endCase();
//            excelConfig.logExcelAndJira("Actual result", "Bug ID",i, this,errors);

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
//            startCase();
            candidateDB = addCandidatePage.CandidateFromDB(email1);
            addCandidatePage.sleepInSecond(20);
            Assert.assertEquals(candidateDB.get("first_name"), firstName);
            verifyEquals(candidateDB.get("middle_name"), middleName.trim());
            Assert.assertEquals(candidateDB.get("last_name"), lastName);
            Assert.assertEquals(candidateDB.get("email"), email1);
            Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
//            Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
            Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
            Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
//            errors = endCase();
//            excelConfig.logExcelAndJira("Actual result", "Bug ID",i+1, this,errors);
            addCandidatePage.sleepInSecond(10);
        }
    }
//    @Test
    public void TC_08_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_LastName(Method method) {
        //Leave the field blank
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        String randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");

        addCandidatePage.clickButtonByJSAndName(" Save ");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 79).replace("\n", "<br>"));
        Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByName("lastName", excelConfig.getCellData("Output", 79)));

        //Kiem tra DB.....
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 80).replace("\n", "<br>"));
        addCandidatePage.clickButtonByJSAndName(" Save ");
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        Assert.assertEquals(candidateDB.size(), 0);

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 81).replace("\n", "<br>"));
        addCandidatePage.enterToTextboxByName(excelConfig.getCellData("Data", 81), "lastName");
        Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByName("lastName", excelConfig.getCellData("Output", 81)));

        //Kiem tra DB.....
        addCandidatePage.clickButtonByJSAndName(" Save ");
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 82).replace("\n", "<br>"));
        Assert.assertEquals(candidateDB.size(), 0);

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 83; i <= 97; i += 2) {
            //Quay lai trang dasboard
            addCandidatePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("Recruitment");
            candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
            candidateListPage.waitForSpinnerIconInvisible();
            candidateListPage.clickToAddButtton();
            candidateListPage.waitForSpinnerIconInvisible();
            addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

            randomPrefix = addCandidatePage.getEmailRadom();
            email1 = randomPrefix + email;
            addCandidatePage.enterToTextboxByName(middleName,"middleName");
            addCandidatePage.enterToTextboxByName(firstName,"firstName");
            addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
            addCandidatePage.enterToTextboxByLabel(email1,"Email");
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            lastName = excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByName(lastName, "lastName");
            ActualValueLastName = addCandidatePage.getValueInTextBoxByName("lastName");
            //Kiem tra giao dien
            Assert.assertEquals(ActualValueLastName, lastName);

            //Kiem tra DB.....
            addCandidatePage.clickButtonByJSAndName(" Save ");
            Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            candidateDB = addCandidatePage.CandidateFromDB(email1);
            addCandidatePage.sleepInSecond(20);
            Assert.assertEquals(candidateDB.get("first_name"), firstName);
            Assert.assertEquals(candidateDB.get("middle_name"), middleName);
            Assert.assertEquals(candidateDB.get("last_name"), lastName.trim());
            Assert.assertEquals(candidateDB.get("email"), email1);
            Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
//            Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
            Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
            Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
            addCandidatePage.sleepInSecond(10);
        }
    }
//    @Test
    public void TC_09_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_Email(Method method) {
        //Leave the field blank
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        String randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(lastName, "lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");

        addCandidatePage.clickButtonByJSAndName(" Save ");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 100).replace("\n", "<br>"));
        Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByLabel("Email", excelConfig.getCellData("Output", 100)));

        //Kiem tra DB.....
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 101).replace("\n", "<br>"));
        addCandidatePage.clickButtonByJSAndName(" Save ");
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        Assert.assertEquals(candidateDB.size(), 0);

        //Entering valid email
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 102).replace("\n", "<br>"));
        addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 102), "Email");
        email1= excelConfig.getCellData("Data", 102);
        addCandidatePage.enterToTextboxByLabel(email1,"Email");
        ActualValueEmail = addCandidatePage.getValueInTextBoxByLabel("Email");
        //Kiem tra giao dien
        Assert.assertEquals(ActualValueEmail, email1);
        //Kiem tra DB.....
        addCandidatePage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 103).replace("\n", "<br>"));
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email1);
        Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
//            Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");

        //Entering invalid email
        for (int i = 104; i <= 110; i += 2) {
            //Quay lai trang dasboard
            addCandidatePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("Recruitment");
            candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
            candidateListPage.waitForSpinnerIconInvisible();
            candidateListPage.clickToAddButtton();
            candidateListPage.waitForSpinnerIconInvisible();
            addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

            email1= excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByName(lastName, "lastName");
            addCandidatePage.enterToTextboxByName(middleName,"middleName");
            addCandidatePage.enterToTextboxByName(firstName,"firstName");
            addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            email1= excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByLabel(email1,"Email");
            Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByLabel("Email", excelConfig.getCellData("Output", i)));

            //Kiem tra DB.....
            addCandidatePage.clickButtonByJSAndName(" Save ");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            candidateDB = addCandidatePage.CandidateFromDB(email1);
            Assert.assertEquals(candidateDB.size(), 0);
        }
    }


//    @Test
    public void TC_10_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_ContactNumber(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        String randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(lastName, "lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");

        //Entering valid contactNumber
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 113).replace("\n", "<br>"));
        addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 113), "Contact Number");
        contactNumber= excelConfig.getCellData("Data", 113);
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
        ActualValueContactNumber = addCandidatePage.getValueInTextBoxByLabel("Contact Number");
        //Kiem tra giao dien
        Assert.assertEquals(ActualValueContactNumber, contactNumber);
        //Kiem tra DB.....
        addCandidatePage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 114).replace("\n", "<br>"));
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email1);
        Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
//            Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");

        //Entering invalid email
        for (int i = 115; i <= 119; i += 2) {
            addCandidatePage.sleepInSecond(10);
            //Quay lai trang dasboard
            addCandidatePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("Recruitment");
            candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
            candidateListPage.waitForSpinnerIconInvisible();
            candidateListPage.clickToAddButtton();
            candidateListPage.waitForSpinnerIconInvisible();
            addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

            randomPrefix = addCandidatePage.getEmailRadom();
            email1 = randomPrefix + email;
            addCandidatePage.enterToTextboxByName(lastName, "lastName");
            addCandidatePage.enterToTextboxByName(middleName,"middleName");
            addCandidatePage.enterToTextboxByName(firstName,"firstName");
            addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
            addCandidatePage.enterToTextboxByLabel(email1,"Email");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            contactNumber= excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
            Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByLabel("Contact Number", excelConfig.getCellData("Output", i)));

            //Kiem tra DB.....
            addCandidatePage.clickButtonByJSAndName(" Save ");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 1).replace("\n", "<br>"));
            candidateDB = addCandidatePage.CandidateFromDB(email1);
            Assert.assertEquals(candidateDB.size(), 0);

        }
    }
//    @Test
    public void TC_11_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_DateOfApplication(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(firstName, "firstName");
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");


        //Enter invalid date of application
        for (int i = 122; i <= 146; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "Date of Application");
            addCandidatePage.clickLabel("Date of Application");
            addCandidatePage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));

        }

        // Enter valid date of application
        for (int i = 148; i <= 170; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            addCandidatePage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.clickToSidebarLinkByText("Recruitment");
            candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
            candidateListPage.waitForSpinnerIconInvisible();
            candidateListPage.clickToAddButtton();
            addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

            randomPrefix = addCandidatePage.getEmailRadom();
            email1 = randomPrefix + email;
            addCandidatePage.enterToTextboxByName(firstName, "firstName");
            addCandidatePage.enterToTextboxByName(lastName,"lastName");
            addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
            addCandidatePage.enterToTextboxByLabel(email1,"Email");
            addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");

            dateOfApplication = excelConfig.getCellData("Data", i);
            addCandidatePage.enterToTextboxByLabel(dateOfApplication, "Date of Application");
            addCandidatePage.clickLabel("Date of Application");
            Assert.assertEquals(addCandidatePage.getValueInTextBoxByLabel("Date of Application"),dateOfApplication);

            //Kiem tra DB.....
            addCandidatePage.clickButtonByJSAndName(" Save ");
            Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
            candidateDB = addCandidatePage.CandidateFromDB(email1);
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            Assert.assertEquals(candidateDB.get("first_name"), firstName);
            Assert.assertEquals(candidateDB.get("last_name"), lastName);
            Assert.assertEquals(candidateDB.get("email"), email1);
            Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
            Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
            Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
            Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
            addCandidatePage.sleepInSecond(20);
        }

//        Kiem tra hoat dong cua canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 176).replace("\n", "<br>"));
        addCandidatePage.clickToSidebarLinkByText("Dashboard");
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        addCandidatePage.scrollCalanderOnDown( "Date of Application");
        addCandidatePage.clickCanlanderIconByLabel("Date of Application");
        addCandidatePage.selectDayInCalender(Integer.toString(parseInt(addCandidatePage.getToday().substring(8)) + 1));

        // verify canlender dong lai
        addCandidatePage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 177).replace("\n", "<br>"));

        addCandidatePage.clickCanlanderIconByLabel("Date of Application");
        addCandidatePage.clickButtonInCanlender("Close");
        addCandidatePage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 178).replace("\n", "<br>"));

        addCandidatePage.clickCanlanderIconByLabel("Date of Application");
        addCandidatePage.clickButtonInCanlender("Today");
        Assert.assertEquals(addCandidatePage.getValueInTextBoxByLabel("Date of Application"), addCandidatePage.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 179).replace("\n", "<br>"));

        addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 179), "Date of Application");
        addCandidatePage.scrollCalanderOnDown( "Date of Application");
        addCandidatePage.clickCanlanderIconByLabel("Date of Application");
        addCandidatePage.clickButtonInCanlender("Clear");
        Assert.assertEquals(addCandidatePage.getValueInTextBoxByLabel("Date of Application"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 176).replace("\n", "<br>"));

        addCandidatePage.scrollCalanderOnDown( "Date of Application");
        addCandidatePage.clickCanlanderIconByLabel("Date of Application");

        // focus vao nggya hien tai
        Assert.assertEquals(addCandidatePage.getColorOfTodayDay(Integer.toString(parseInt(addCandidatePage.getToday().substring(8)))), excelConfig.getCellData("Output", 176));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 177).replace("\n", "<br>"));

        addCandidatePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 177), "Date of Application");
        addCandidatePage.clickLabel("Date of Application");
        addCandidatePage.scrollCalanderOnDown( "Date of Application");
        addCandidatePage.clickCanlanderIconByLabel("Date of Application");
        addCandidatePage.scrollCalanderOnTop( "Date of Application");

        Assert.assertEquals(addCandidatePage.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 177).substring(8)))), excelConfig.getCellData("Output", 177));

    }
//    @Test
    public void TC_12_AddCandidateScreen_Function_Check_Save_Sucess_And_Fail_With_Resume(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(lastName, "lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");

        //Entering invalid resume
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 179).replace("\n", "<br>"));
        resume = excelConfig.getCellData("Data", 179);
        fileNames = new String[]{resume};
        addCandidatePage.uploadMultipleFiles(driver,fileNames);
        Assert.assertTrue(addCandidatePage.isErrorMsgDisplayedByLabel("Resume", excelConfig.getCellData("Output", 179)));

        //Entering valid resume
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 181).replace("\n", "<br>"));
        resume = excelConfig.getCellData("Data", 181);
        fileNames = new String[]{resume};
        addCandidatePage.uploadMultipleFiles(driver,fileNames);
        //Kiem tra DB.....
        addCandidatePage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));
        candidateDB = addCandidatePage.CandidateFromDB(email1);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 181).replace("\n", "<br>"));
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email1);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
        Assert.assertEquals(candidateDB.get("resume_file"), resume);

    }
//    @Test
    public void TC_13_Validatation_Vacancy(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 186).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);
        addCandidatePage.clickToVacancyDropList("Vacancy");
        listExpectedValueDroplist = addCandidatePage.getListValueVacancyFromDB();
        listActualValueDroplist = addCandidatePage.getListActualValueDropList();
        for (int i = 0; i < listExpectedValueDroplist.size(); i++) {
            Assert.assertEquals(listActualValueDroplist.get(i), listExpectedValueDroplist.get(i));
        }
    }

//    @Test
    public void TC_14_AddCandidateScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 188).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");
        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        randomPrefix = addCandidatePage.getEmailRadom();
        email1 = randomPrefix + email;
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(lastName, "lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email1,"Email");

        addCandidatePage.clickCancelButton();

        candidateDB = addCandidatePage.CandidateFromDB(email1);
        Assert.assertEquals(candidateDB.size(), 0);
    }

//    @Test
    public void TC_15_ShortlistScreen_Function_CheckValueDefault(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 191).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/208");

        shortlistPage = PageGeneratorManager.getShortlistPageObject(driver);
        shortlistPage.waitForSpinnerIconInvisible();
        shortlistPage.clickButtonByJSAndName(" Shortlist ");
        shortlistPage.waitForSpinnerIconInvisible();
        candidateDB = shortlistPage.ShortlistCandidateFromDB("auto3349minh@gmail.com");

        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Vacancy"),candidateDB.get("vacancy_name"));
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Candidate"),candidateDB.get("full_name"));
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("status"));
    }

//    @Test
    public void TC_16_ShortlistScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 191).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/208");

        shortlistPage = PageGeneratorManager.getShortlistPageObject(driver);
        shortlistPage.waitForSpinnerIconInvisible();
        shortlistPage.clickButtonByJSAndName(" Shortlist ");
        shortlistPage.waitForSpinnerIconInvisible();

        shortlistPage.clickCancelButton();
        candidateDB = shortlistPage.ShortlistCandidateFromDB("auto3349minh@gmail.com");
        Assert.assertEquals(candidateDB.get("status"),"APPLICATION INITIATED");
    }
    //sua email va duong dan
//    @Test
    public void TC_17_ScheduleInterviewScreen_Function_Check_Save_Fail_With_InterviewTitle(Method method){
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/133");

        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
        scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");

        for(int i=202; i<=204;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            interviewTitle = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterToTextboxByLabel(interviewTitle,"Interview Title");
            scheduleInterviewPage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
        }
        for(int i=206;i<=218;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            scheduleInterviewPage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/133");
            scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
            scheduleInterviewPage.waitForSpinnerIconInvisible();
            scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
            scheduleInterviewPage.waitForSpinnerIconInvisible();

            interviewTitle = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
            scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
            scheduleInterviewPage.enterToTextboxByLabel(interviewTitle,"Interview Title");
            scheduleInterviewPage.clickButtonByJSAndName(" Save ");

            // Verify sau khi save
            Assert.assertTrue(scheduleInterviewPage.isSuccessMessageDisplayed("Successfully Updated"));
            // Check DB
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            candidateDB = scheduleInterviewPage.CandidateFromDB("auto32193minh@gmail.com");
            Assert.assertEquals(candidateDB.get("status"), "INTERVIEW SCHEDULED");

            interviewDB = scheduleInterviewPage.InterviewFromDB("auto32193minh@gmail.com");
            Assert.assertEquals(interviewDB.get("interview_name"), interviewTitle);
            Assert.assertEquals(interviewDB.get("interview_date"), interviewDate);
            Assert.assertEquals(interviewDB.get("interviewer_name"), interviewer);
            scheduleInterviewPage.rollbackScheduleInterview("auto32193minh@gmail.com");
        }

    }
    //sua email va duong dan
//    @Test
    public void TC_18_ScheduleInterviewScreen_Function_Check_Save_Sucess_And_Fail_With_InterviewDate(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/133");

        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
        scheduleInterviewPage.enterToTextboxByLabel(interviewTitle,"Interview Title");

        //Enter invalid interview date
        for (int i = 221; i <=243 ; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            interviewDate = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
            scheduleInterviewPage.clickLabel("Date");
            scheduleInterviewPage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
        }

        // Enter valid interview date
        for (int i = 245; i <= 267; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            scheduleInterviewPage.clickToSidebarLinkByText("Dashboard");
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/133");
            scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
            scheduleInterviewPage.waitForSpinnerIconInvisible();
            scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
            scheduleInterviewPage.waitForSpinnerIconInvisible();

            interviewDate = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterToTextboxByLabel(interviewTitle,"Interview Title");
            scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
            scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
            scheduleInterviewPage.clickLabel("Date");

            Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Date"),interviewDate);

            //Kiem tra DB.....
            scheduleInterviewPage.clickButtonByJSAndName(" Save ");
            Assert.assertTrue(scheduleInterviewPage.isSuccessMessageDisplayed("Successfully Updated"));
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i+1).replace("\n", "<br>"));
            candidateDB = scheduleInterviewPage.CandidateFromDB("auto32193minh@gmail.com");
            Assert.assertEquals(candidateDB.get("status"), "INTERVIEW SCHEDULED");

            interviewDB = scheduleInterviewPage.InterviewFromDB("auto32193minh@gmail.com");
            Assert.assertEquals(interviewDB.get("interview_name"), interviewTitle);
            Assert.assertEquals(interviewDB.get("interview_date"), interviewDate);
            Assert.assertEquals(interviewDB.get("interviewer_name"), interviewer);
            scheduleInterviewPage.rollbackScheduleInterview("auto32193minh@gmail.com");
        }

//        Kiem tra hoat dong cua canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 269).replace("\n", "<br>"));
        scheduleInterviewPage.clickToSidebarLinkByText("Dashboard");
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/133");
        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        scheduleInterviewPage.scrollCalanderOnDown( "Date");
        scheduleInterviewPage.clickCanlanderIconByLabel("Date");
        scheduleInterviewPage.selectDayInCalender(Integer.toString(parseInt(scheduleInterviewPage.getToday().substring(8)) + 1));

        // verify canlender dong lai
        scheduleInterviewPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 270).replace("\n", "<br>"));

        scheduleInterviewPage.clickCanlanderIconByLabel("Date");
        scheduleInterviewPage.clickButtonInCanlender("Close");
        scheduleInterviewPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 271).replace("\n", "<br>"));

        scheduleInterviewPage.clickCanlanderIconByLabel("Date");
        scheduleInterviewPage.clickButtonInCanlender("Today");
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Date"), scheduleInterviewPage.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 272).replace("\n", "<br>"));

        scheduleInterviewPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 272), "Date");
        scheduleInterviewPage.scrollCalanderOnDown( "Date");
        scheduleInterviewPage.clickCanlanderIconByLabel("Date");
        scheduleInterviewPage.clickButtonInCanlender("Clear");
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Date"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 273).replace("\n", "<br>"));

        scheduleInterviewPage.scrollCalanderOnDown( "Date");
        scheduleInterviewPage.clickCanlanderIconByLabel("Date");

        // focus vao nggya hien tai
        Assert.assertEquals(scheduleInterviewPage.getColorOfTodayDay(Integer.toString(parseInt(scheduleInterviewPage.getToday().substring(8)))), excelConfig.getCellData("Output", 273));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 274).replace("\n", "<br>"));

        scheduleInterviewPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 274), "Date");
        scheduleInterviewPage.clickLabel("Date");
        scheduleInterviewPage.scrollCalanderOnDown( "Date");
        scheduleInterviewPage.clickCanlanderIconByLabel("Date");
        scheduleInterviewPage.scrollCalanderOnTop( "Date");

        Assert.assertEquals(scheduleInterviewPage.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 274).substring(8)))), excelConfig.getCellData("Output", 274));

    }
//    @Test
    public void TC_19_ScheduleInterviewScreen_Function_Check_Save_Sucess_And_Fail_With_InterviewTime(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver, "http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/132");

        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer, interviewer, "Interviewer");
        scheduleInterviewPage.enterToTextboxByLabel(interviewTitle, "Interview Title");
        scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");

        //Enter invalid interview date
        for (int i = 278; i <= 280; i += 1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            time = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterTimeByLabel("Time",time,false);
            scheduleInterviewPage.clickLabel("Time");
            Assert.assertEquals(scheduleInterviewPage.getTimeValueByLabel("Time"),"");
        }
        //Enter valid interview date
        time = excelConfig.getCellData("Data",276);
        scheduleInterviewPage.enterTimeByLabel("Time",time, true);
        Assert.assertEquals(scheduleInterviewPage.getTimeValueByLabel("Time"),time);
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(scheduleInterviewPage.isSuccessMessageDisplayed("Successfully Updated"));
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 276).replace("\n", "<br>"));
        candidateDB = scheduleInterviewPage.CandidateFromDB("auto32293minh@gmail.com");
        Assert.assertEquals(candidateDB.get("status"), "INTERVIEW SCHEDULED");

        interviewDB = scheduleInterviewPage.InterviewFromDB("auto32293minh@gmail.com");
        Assert.assertEquals(interviewDB.get("interview_name"), interviewTitle);
        Assert.assertEquals(interviewDB.get("interview_date"), interviewDate);
        Assert.assertEquals(interviewDB.get("interviewer_name"), interviewer);
        Assert.assertEquals(interviewDB.get("interview_time"), scheduleInterviewPage.convertTo24HourFormat(time));
        scheduleInterviewPage.rollbackScheduleInterview("auto32293minh@gmail.com");

        for(int i=281;i<=285;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            dashboadPage = PageGeneratorManager.getDashboardPage(driver);
            dashboadPage.openPageUrl(driver, "http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/132");

            scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
            scheduleInterviewPage.waitForSpinnerIconInvisible();
            scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
            scheduleInterviewPage.waitForSpinnerIconInvisible();

            scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer, interviewer, "Interviewer");
            scheduleInterviewPage.enterToTextboxByLabel(interviewTitle, "Interview Title");
            scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
            time = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterTimeByLabel("Time",time, true);
            scheduleInterviewPage.openTimePickerByLabel("Time");
            scheduleInterviewPage.clickTimeButton("Time","hour-up");
            Assert.assertEquals(scheduleInterviewPage.getTimeValueByLabel("Time"),excelConfig.getCellData("Output",i));
        }

        for(int i=282;i<=286;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            time = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterTimeByLabel("Time",time, true);
            scheduleInterviewPage.openTimePickerByLabel("Time");
            scheduleInterviewPage.clickTimeButton("Time","hour-down");
            Assert.assertEquals(scheduleInterviewPage.getTimeValueByLabel("Time"),excelConfig.getCellData("Output",i));
        }

        for(int i=283;i<=287;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            time = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterTimeByLabel("Time",time, true);
            scheduleInterviewPage.openTimePickerByLabel("Time");
            scheduleInterviewPage.clickTimeButton("Time","minute-up");
            Assert.assertEquals(scheduleInterviewPage.getTimeValueByLabel("Time"),excelConfig.getCellData("Output",i));
        }
        for(int i=284;i<=288;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            time = excelConfig.getCellData("Data",i);
            scheduleInterviewPage.enterTimeByLabel("Time",time, true);
            scheduleInterviewPage.openTimePickerByLabel("Time");
            scheduleInterviewPage.clickTimeButton("Time","minute-down");
            Assert.assertEquals(scheduleInterviewPage.getTimeValueByLabel("Time"),excelConfig.getCellData("Output",i));
        }


    }
//    @Test
    public void TC_20_ShortlistScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 191).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/132");
        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer, interviewer, "Interviewer");
        scheduleInterviewPage.enterToTextboxByLabel(interviewTitle, "Interview Title");
        scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");

        scheduleInterviewPage.clickCancelButton();
    }

//    @Test
    public void TC_21_MarkInterviewResultScreen_Function_CheckValueDefault(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 191).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/125");

        makeInterviewResultPage = PageGeneratorManager.getMarkInterviewResultPageObject(driver);
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        makeInterviewResultPage.clickButtonByJSAndName(" Mark Interview Passed ");
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        candidateDB = makeInterviewResultPage.MarkInterviewResultCandidateFromDB("auto10938minh@gmail.com");

        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Vacancy"),candidateDB.get("vacancy_name"));
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Candidate"),candidateDB.get("full_name"));
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("status"));
    }
//  @Test
    public void TC_22_MarkInterviewResultScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 191).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/125");

        makeInterviewResultPage = PageGeneratorManager.getMarkInterviewResultPageObject(driver);
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        makeInterviewResultPage.clickButtonByJSAndName(" Mark Interview Passed ");
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        makeInterviewResultPage.clickCancelButton();
        candidateDB = makeInterviewResultPage.MarkInterviewResultCandidateFromDB("auto10938minh@gmail.com");
        Assert.assertEquals(candidateDB.get("status"),"INTERVIEW SCHEDULED");
    }

//    @Test
    public void TC_23_OfferJobScreen_Function_CheckValueDefault(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 300).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/137");

        offerJobPage = PageGeneratorManager.getOfferJobPageObject(driver);
        offerJobPage.waitForSpinnerIconInvisible();
        offerJobPage.clickButtonByJSAndName(" Offer Job ");
        offerJobPage.waitForSpinnerIconInvisible();
        candidateDB = offerJobPage.OfferJobCandidateFromDB("auto75013minh@gmail.com");

        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Vacancy"),candidateDB.get("vacancy_name"));
        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Candidate"),candidateDB.get("full_name"));
        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("status"));
    }
//    @Test
    public void TC_24_OfferJobScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 304).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/137");

        offerJobPage = PageGeneratorManager.getOfferJobPageObject(driver);
        offerJobPage.waitForSpinnerIconInvisible();
        offerJobPage.clickButtonByJSAndName(" Offer Job ");
        offerJobPage.waitForSpinnerIconInvisible();
        offerJobPage.clickCancelButton();
        candidateDB = offerJobPage.OfferJobCandidateFromDB("auto75013minh@gmail.com");
        Assert.assertEquals(candidateDB.get("status"),"INTERVIEW PASSED");
    }

//    @Test
    public void TC_25_HireCandidateScreen_Function_CheckValueDefault(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 307).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/17");

        hireCandidatePage = PageGeneratorManager.getHireCandidatePageObject(driver);
        hireCandidatePage.waitForSpinnerIconInvisible();
        hireCandidatePage.clickButtonByJSAndName(" Hire ");
        hireCandidatePage.waitForSpinnerIconInvisible();
        candidateDB = hireCandidatePage.HireCandidateFromDB("minh5748@gmail.com");

        Assert.assertEquals(hireCandidatePage.getValueInTextBoxByLabel("Vacancy"),candidateDB.get("vacancy_name"));
        Assert.assertEquals(hireCandidatePage.getValueInTextBoxByLabel("Candidate"),candidateDB.get("full_name"));
        Assert.assertEquals(hireCandidatePage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("status"));
    }
//    @Test
    public void TC_26_OfferJobScreen_Click_Button_Cancel_Check_Save_Unsucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 311).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.openPageUrl(driver,"http://localhost:90/orangehrm5/web/index.php/recruitment/addCandidate/17");

        hireCandidatePage = PageGeneratorManager.getHireCandidatePageObject(driver);
        hireCandidatePage.waitForSpinnerIconInvisible();
        hireCandidatePage.clickButtonByJSAndName(" Hire ");
        hireCandidatePage.waitForSpinnerIconInvisible();
        hireCandidatePage.clickCancelButton();
        candidateDB = hireCandidatePage.HireCandidateFromDB("minh5748@gmail.com");
        Assert.assertEquals(candidateDB.get("status"),"JOB OFFERED");
    }
    // Begin Search Candidate
//    @Test
    public void TC_27_SearchCandidateScreen_Function_DateRange(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.disableAutoFocus();;

        // search by valid daterange
        for(int i=315;i<316;i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));

            data = excelConfig.getCellData("Data", i).split(",");
            candidateListPage.enterToCanlenderTextboxByLabel(data[0], "Date of Application");
            candidateListPage.enterToCanlenderTextbox(data[1], "To");

            candidateListPage.clickButtonByName(" Search ");
            candidateListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = candidateListPage. getCandidatesByDateRange(data[0], data[1]);
            Assert.assertEquals(candidateListPage.getTotalRecordOnUI(), dbList.size());

            // Verify tung ban ghi
            for (int j = 0; j < dbList.size(); j++) {
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Vacancy", String.valueOf(j + 1), dbList.get(j).get("vacancy")));
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Candidate", String.valueOf(j + 1), dbList.get(j).get("candidate_name")));
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Hiring Manager", String.valueOf(j + 1), dbList.get(j).get("hiring_manager")));
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Date of Application", String.valueOf(j + 1), dbList.get(j).get("date_of_application")));
                Assert.assertEquals(candidateListPage.getValueInTextboxInTable("Status", String.valueOf(j + 1)).toUpperCase(), dbList.get(j).get("application_status"));
            }
        }
    }

//    @Test
    public void TC_28_SearchCandidateScreen_Function_CandidateName(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.disableAutoFocus();;

        for(int i=318;i<=321;i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));

            data = excelConfig.getCellData("Data", i).split(",");
            candidateListPage.searchAndSelectInComboboxByLabel(data[0],data[1],"Candidate Name");

            candidateListPage.clickButtonByName(" Search ");
            candidateListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = candidateListPage. getCandidatesByName(data[1]);
            Assert.assertEquals(candidateListPage.getTotalRecordOnUI(), dbList.size());

            // Verify tung ban ghi
            for (int j = 0; j < dbList.size(); j++) {
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Vacancy", String.valueOf(j + 1), dbList.get(j).get("vacancy")));
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Candidate", String.valueOf(j + 1), dbList.get(j).get("candidate_name")));
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Hiring Manager", String.valueOf(j + 1), dbList.get(j).get("hiring_manager")));
                Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Date of Application", String.valueOf(j + 1), dbList.get(j).get("date_of_application")));
                Assert.assertEquals(candidateListPage.getValueInTextboxInTable("Status", String.valueOf(j + 1)).toUpperCase(), dbList.get(j).get("application_status"));
            }
        }
    }

//    @Test
    public void TC_29_SearchCandidateScreen_Function_Vacancy(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.disableAutoFocus();;

        for(int i=323;i<=324;i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));

            vacancy = excelConfig.getCellData("Data", i);
            candidateListPage.selectToDropdownByLabel("Vacancy",vacancy);

            candidateListPage.clickButtonByName(" Search ");
            candidateListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = candidateListPage. getCandidateListByVacancy(vacancy);
            Assert.assertEquals(candidateListPage.getTotalRecordOnUI(), dbList.size());

        }
    }

//    @Test
    public void TC_30_SearchCandidateScreen_Function_CombinedFields(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.disableAutoFocus();;


        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 326).replace("\n", "<br>"));

        data = excelConfig.getCellData("Data", 326).split(",");
        candidateListPage.searchAndSelectInComboboxByLabel(data[0],data[1],"Candidate Name");
        candidateListPage.selectToDropdownByLabel("Vacancy",data[2]);

        candidateListPage.clickButtonByName(" Search ");
        candidateListPage.waitForSpinnerIconInvisible();

        // Verify so luong ban ghi tren ui voi db
        dbList = candidateListPage. getCandidateListByNameAndVacancy(data[1],data[2]);
        Assert.assertEquals(candidateListPage.getTotalRecordOnUI(), dbList.size());

        // Verify tung ban ghi
        for (int j = 0; j < dbList.size(); j++) {
            Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Vacancy", String.valueOf(j + 1), dbList.get(j).get("vacancy")));
            Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Candidate", String.valueOf(j + 1), dbList.get(j).get("candidate_name")));
            Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Hiring Manager", String.valueOf(j + 1), dbList.get(j).get("hiring_manager")));
            Assert.assertTrue(candidateListPage.isValueDisplayedAtColumnName("Date of Application", String.valueOf(j + 1), dbList.get(j).get("date_of_application")));
            Assert.assertEquals(candidateListPage.getValueInTextboxInTable("Status", String.valueOf(j + 1)).toUpperCase(), dbList.get(j).get("application_status"));
        }

    }

    // End Search Candidate
    // Delete Candidate
//    @Test
    public void TC_31_Delete_Candidate(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.disableAutoFocus();;

        data = excelConfig.getCellData("Data", 328).split(",");
        candidateListPage.searchAndSelectInComboboxByLabel(data[0],data[1],"Candidate Name");
        candidateListPage.clickButtonByName(" Search ");
        candidateListPage.waitForSpinnerIconInvisible();

        // Kiem tra xoa khong thanh cong
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 328).replace("\n", "<br>"));

        candidateListPage.clickDeleteIcon();
        candidateListPage.clickButtonByName(" No, Cancel ");

        Assert.assertEquals(candidateListPage.getTotalRecordOnUI(),1);

        // Check DB
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 329).replace("\n", "<br>"));
        Assert.assertFalse(candidateListPage.isCandidateDeletedFromDB(data[1]));

        // Kiem tra xoa thanh cong
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 330).replace("\n", "<br>"));

        candidateListPage.clickDeleteIcon();
        candidateListPage.clickButtonByName(" Yes, Delete ");
        Assert.assertEquals(candidateListPage.getTotalRecordOnUI(),0);

        // Check DB
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 331).replace("\n", "<br>"));
        Assert.assertTrue(candidateListPage.isCandidateDeletedFromDB(data[1]));


    }
    // End Delete Candidate


    @Test
    public void Vacancy_01_Flow_VacancyManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 13).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        firstName = excelConfig.getCellData("valid data", 5);
        middleName = excelConfig.getCellData("valid data", 6);
        lastName = excelConfig.getCellData("valid data", 7);
        vacancy = excelConfig.getCellData("valid data", 8);
        email = excelConfig.getCellData("valid data", 9);
        contactNumber = excelConfig.getCellData("valid data", 10);
        resume = excelConfig.getCellData("valid data", 11);
        dateOfApplication= excelConfig.getCellData("valid data", 12);
        fullName =  firstName + " " + middleName + " " + lastName;
        fileNames = new String[]{resume};

        addCandidatePage.scrollCalanderOnDown("Date of Application");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.sleepInSecond(10);
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.sleepInSecond(10);
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.sleepInSecond(10);
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.sleepInSecond(10);
        addCandidatePage.enterToTextboxByLabel(email,"Email");
        addCandidatePage.sleepInSecond(10);
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
        addCandidatePage.sleepInSecond(10);
        addCandidatePage.uploadMultipleFiles(driver,fileNames);
        addCandidatePage.clickButtonByJSAndName(" Save ");

        // Check toast messeage thanh cong
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email);
        Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
//        Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
        Assert.assertEquals(candidateDB.get("resume_file"), resume);


        // Shortlist Candidate
        shortlistPage = PageGeneratorManager.getShortlistPageObject(driver);
        shortlistPage.waitForSpinnerIconInvisible();
        shortlistPage.clickButtonByJSAndName(" Shortlist ");
        shortlistPage.waitForSpinnerIconInvisible();
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        addCandidatePage.sleepInSecond(10);
        shortlistPage.clickButtonByJSAndName(" Save ");


        Assert.assertTrue(shortlistPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "SHORTLISTED");

        // Schedule Interview
        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));

        interviewTitle = excelConfig.getCellData("valid data",18);
        interviewer = excelConfig.getCellData("valid data",19);
        interviewDate = excelConfig.getCellData("valid data",20);
        time = excelConfig.getCellData("valid data",21);

        scheduleInterviewPage.enterToTextboxByLabel(interviewTitle, "Interview Title");
//        addCandidatePage.sleepInSecond(10);
        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
//        addCandidatePage.sleepInSecond(10);
        scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
//        addCandidatePage.sleepInSecond(10);
//        scheduleInterviewPage.enterTimeByLabel("Time",time);
//        addCandidatePage.sleepInSecond(10);
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");

        // Verify sau khi save
        Assert.assertTrue(scheduleInterviewPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "INTERVIEW SCHEDULED");

        interviewDB = scheduleInterviewPage.InterviewFromDB(email);
        Assert.assertEquals(interviewDB.get("interview_name"), interviewTitle);
        Assert.assertEquals(interviewDB.get("interview_date"), interviewDate);
        Assert.assertEquals(interviewDB.get("interviewer_name"), interviewer);

        // Mark Interview Pass
        makeInterviewResultPage = PageGeneratorManager.getMarkInterviewResultPageObject(driver);
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        makeInterviewResultPage.clickButtonByJSAndName(" Mark Interview Passed ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        addCandidatePage.sleepInSecond(20);
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(makeInterviewResultPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "INTERVIEW PASSED");

        // Offer Job
        offerJobPage = PageGeneratorManager.getOfferJobPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        offerJobPage.clickButtonByJSAndName(" Offer Job ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        addCandidatePage.sleepInSecond(20);
        offerJobPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(offerJobPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "JOB OFFERED");

        // Hire
        hireCandidatePage = PageGeneratorManager.getHireCandidatePageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        hireCandidatePage.clickButtonByJSAndName(" Hire ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(hireCandidatePage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(hireCandidatePage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(hireCandidatePage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        addCandidatePage.sleepInSecond(20);
        hireCandidatePage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(hireCandidatePage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "HIRED");
    }

//    @Test
    public void Vacancy_02_Flow_VacancyManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 13).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        firstName = excelConfig.getCellData("valid data", 30);
        middleName = excelConfig.getCellData("valid data", 31);
        lastName = excelConfig.getCellData("valid data", 32);
        vacancy = excelConfig.getCellData("valid data", 33);
        email = excelConfig.getCellData("valid data", 34);
        contactNumber = excelConfig.getCellData("valid data", 35);
        resume = excelConfig.getCellData("valid data", 36);
        dateOfApplication= excelConfig.getCellData("valid data", 37);
        fullName =  firstName + " " + middleName + " " + lastName;
        fileNames = new String[]{resume};

        addCandidatePage.scrollCalanderOnDown("Date of Application");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
        addCandidatePage.uploadMultipleFiles(driver,fileNames);
        addCandidatePage.clickButtonByJSAndName(" Save ");

        // Check toast messeage thanh cong
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email);
        Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
        Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
        Assert.assertEquals(candidateDB.get("resume_file"), resume);

        // Shortlist Candidate
        shortlistPage = PageGeneratorManager.getShortlistPageObject(driver);
        shortlistPage.waitForSpinnerIconInvisible();
        shortlistPage.clickButtonByJSAndName(" Shortlist ");
        shortlistPage.waitForSpinnerIconInvisible();
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        shortlistPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(shortlistPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "SHORTLISTED");

        // Reject Candidate
        rejectCandidatePage = PageGeneratorManager.getRejectCandidatePageObject(driver);
        rejectCandidatePage.waitForSpinnerIconInvisible();
        rejectCandidatePage.clickButtonByJSAndName(" Reject ");
        rejectCandidatePage.waitForSpinnerIconInvisible();

        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));

        // Verify sau khi save
        rejectCandidatePage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(rejectCandidatePage.isSuccessMessageDisplayed("Successfully Updated"));

        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "REJECTED");

    }

//    @Test
    public void Vacancy_03_Flow_VacancyManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 13).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        firstName = excelConfig.getCellData("valid data", 46);
        middleName = excelConfig.getCellData("valid data", 47);
        lastName = excelConfig.getCellData("valid data", 48);
        vacancy = excelConfig.getCellData("valid data", 49);
        email = excelConfig.getCellData("valid data", 50);
        contactNumber = excelConfig.getCellData("valid data", 51);
        resume = excelConfig.getCellData("valid data", 52);
        dateOfApplication= excelConfig.getCellData("valid data", 53);
        fullName =  firstName + " " + middleName + " " + lastName;
        fileNames = new String[]{resume};

        addCandidatePage.scrollCalanderOnDown("Date of Application");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
        addCandidatePage.uploadMultipleFiles(driver,fileNames);
        addCandidatePage.clickButtonByJSAndName(" Save ");

        // Check toast messeage thanh cong
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email);
        Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
        Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
        Assert.assertEquals(candidateDB.get("resume_file"), resume);

        // Shortlist Candidate
        shortlistPage = PageGeneratorManager.getShortlistPageObject(driver);
        shortlistPage.waitForSpinnerIconInvisible();
        shortlistPage.clickButtonByJSAndName(" Shortlist ");
        shortlistPage.waitForSpinnerIconInvisible();
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        shortlistPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(shortlistPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "SHORTLISTED");

        // Schedule Interview
        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));

        interviewTitle = excelConfig.getCellData("valid data",59);
        interviewer = excelConfig.getCellData("valid data",60);
        interviewDate = excelConfig.getCellData("valid data",61);
        time = excelConfig.getCellData("valid data",62);

        scheduleInterviewPage.enterToTextboxByLabel(interviewTitle, "Interview Title");
        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
        scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
//        scheduleInterviewPage.enterTimeByLabel("Time",time);
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");

        // Verify sau khi save
        Assert.assertTrue(scheduleInterviewPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "INTERVIEW SCHEDULED");

        interviewDB = scheduleInterviewPage.InterviewFromDB(email);
        Assert.assertEquals(interviewDB.get("interview_name"), interviewTitle);
        Assert.assertEquals(interviewDB.get("interview_date"), interviewDate);
        Assert.assertEquals(interviewDB.get("interviewer_name"), interviewer);

        // Mark Interview Fail
        makeInterviewResultPage = PageGeneratorManager.getMarkInterviewResultPageObject(driver);
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        makeInterviewResultPage.clickButtonByJSAndName(" Mark Interview Failed ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(makeInterviewResultPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "INTERVIEW FAILED");

        // Reject Candidate
        rejectCandidatePage = PageGeneratorManager.getRejectCandidatePageObject(driver);
        rejectCandidatePage.waitForSpinnerIconInvisible();
        rejectCandidatePage.clickButtonByJSAndName(" Reject ");
        rejectCandidatePage.waitForSpinnerIconInvisible();

        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));

        // Verify sau khi save
        rejectCandidatePage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(rejectCandidatePage.isSuccessMessageDisplayed("Successfully Updated"));

        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "REJECTED");
    }

//    @Test
    public void Vacancy_04_Flow_VacancyManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 13).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Recruitment");

        candidateListPage = PageGeneratorManager.getCandidateListPageObject(driver);
        candidateListPage.waitForSpinnerIconInvisible();
        candidateListPage.clickToAddButtton();
        candidateListPage.waitForSpinnerIconInvisible();
        addCandidatePage = PageGeneratorManager.getAddCandidatePageObject(driver);

        firstName = excelConfig.getCellData("valid data", 70);
        middleName = excelConfig.getCellData("valid data", 71);
        lastName = excelConfig.getCellData("valid data", 72);
        vacancy = excelConfig.getCellData("valid data", 73);
        email = excelConfig.getCellData("valid data", 74);
        contactNumber = excelConfig.getCellData("valid data", 75);
        resume = excelConfig.getCellData("valid data", 76);
        dateOfApplication= excelConfig.getCellData("valid data", 77);
        fullName =  firstName + " " + middleName + " " + lastName;
        fileNames = new String[]{resume};

        addCandidatePage.scrollCalanderOnDown("Date of Application");
        addCandidatePage.enterToTextboxByName(firstName,"firstName");
        addCandidatePage.enterToTextboxByName(middleName,"middleName");
        addCandidatePage.enterToTextboxByName(lastName,"lastName");
        addCandidatePage.selectToDropdownByLabel(vacancy,"Vacancy");
        addCandidatePage.enterToTextboxByLabel(email,"Email");
        addCandidatePage.enterToTextboxByLabel(contactNumber,"Contact Number");
        addCandidatePage.uploadMultipleFiles(driver,fileNames);
        addCandidatePage.clickButtonByJSAndName(" Save ");

        // Check toast messeage thanh cong
        Assert.assertTrue(addCandidatePage.isSuccessMessageDisplayed("Successfully Saved"));

        //Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("first_name"), firstName);
        Assert.assertEquals(candidateDB.get("middle_name"), middleName);
        Assert.assertEquals(candidateDB.get("last_name"), lastName);
        Assert.assertEquals(candidateDB.get("email"), email);
        Assert.assertEquals(candidateDB.get("contact_number"), contactNumber);
        Assert.assertEquals(candidateDB.get("date_of_application"), dateOfApplication);
        Assert.assertEquals(candidateDB.get("vacancy_name"), vacancy);
        Assert.assertEquals(candidateDB.get("application_status"), "APPLICATION INITIATED");
        Assert.assertEquals(candidateDB.get("resume_file"), resume);

        // Shortlist Candidate
        shortlistPage = PageGeneratorManager.getShortlistPageObject(driver);
        shortlistPage.waitForSpinnerIconInvisible();
        shortlistPage.clickButtonByJSAndName(" Shortlist ");
        shortlistPage.waitForSpinnerIconInvisible();
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(shortlistPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        shortlistPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(shortlistPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "SHORTLISTED");

        // Schedule Interview
        scheduleInterviewPage = PageGeneratorManager.getScheduleInterviewPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        scheduleInterviewPage.clickButtonByJSAndName(" Schedule Interview ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(scheduleInterviewPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));

        interviewTitle = excelConfig.getCellData("valid data",83);
        interviewer = excelConfig.getCellData("valid data",84);
        interviewDate = excelConfig.getCellData("valid data",85);
        time = excelConfig.getCellData("valid data",86);

        scheduleInterviewPage.enterToTextboxByLabel(interviewTitle, "Interview Title");
        scheduleInterviewPage.searchAndSelectInComboboxByLabel(interviewer,interviewer,"Interviewer");
        scheduleInterviewPage.enterToTextboxByLabel(interviewDate, "Date");
//        scheduleInterviewPage.enterTimeByLabel("Time",time);
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");

        // Verify sau khi save
        Assert.assertTrue(scheduleInterviewPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "INTERVIEW SCHEDULED");

        interviewDB = scheduleInterviewPage.InterviewFromDB(email);
        Assert.assertEquals(interviewDB.get("interview_name"), interviewTitle);
        Assert.assertEquals(interviewDB.get("interview_date"), interviewDate);
        Assert.assertEquals(interviewDB.get("interviewer_name"), interviewer);

        // Mark Interview Pass
        makeInterviewResultPage = PageGeneratorManager.getMarkInterviewResultPageObject(driver);
        makeInterviewResultPage.waitForSpinnerIconInvisible();
        makeInterviewResultPage.clickButtonByJSAndName(" Mark Interview Passed ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(makeInterviewResultPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        scheduleInterviewPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(makeInterviewResultPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "INTERVIEW PASSED");

        // Offer Job
        offerJobPage = PageGeneratorManager.getOfferJobPageObject(driver);
        scheduleInterviewPage.waitForSpinnerIconInvisible();
        offerJobPage.clickButtonByJSAndName(" Offer Job ");
        scheduleInterviewPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(offerJobPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        offerJobPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(offerJobPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "JOB OFFERED");

        // Offer Declined
        offerDeclinedPage = PageGeneratorManager.getOfferDeclinedPageObject(driver);
        offerDeclinedPage.waitForSpinnerIconInvisible();
        offerDeclinedPage.clickButtonByJSAndName(" Offer Declined ");
        offerDeclinedPage.waitForSpinnerIconInvisible();

        Assert.assertEquals(offerDeclinedPage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(offerDeclinedPage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(offerDeclinedPage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));
        offerDeclinedPage.clickButtonByJSAndName(" Save ");

        Assert.assertTrue(offerDeclinedPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "OFFER DECLINED");

        // Reject Candidate
        rejectCandidatePage = PageGeneratorManager.getRejectCandidatePageObject(driver);
        rejectCandidatePage.waitForSpinnerIconInvisible();
        rejectCandidatePage.clickButtonByJSAndName(" Reject ");
        rejectCandidatePage.waitForSpinnerIconInvisible();

        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Vacancy"),vacancy);
        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Candidate"),fullName);
        Assert.assertEquals(rejectCandidatePage.getValueInTextBoxByLabel("Current Status").toUpperCase(),candidateDB.get("application_status"));

        // Verify sau khi save
        rejectCandidatePage.clickButtonByJSAndName(" Save ");
        Assert.assertTrue(rejectCandidatePage.isSuccessMessageDisplayed("Successfully Updated"));

        // Check DB
        candidateDB = addCandidatePage.CandidateFromDB(email);
        Assert.assertEquals(candidateDB.get("application_status"), "REJECTED");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
//        excelConfig.saveAndClose();
        closeBrowser();
    }
    private String userNameAdmin , passwordAdmin;
    private String firstName, lastName, middleName, vacancy, email, email1, contactNumber, dateOfApplication, fullName,randomPrefix, errors;
    private String resume ;
    private String[] fileNames, data;
    private List<Map<String,String>> dbList;
    private String interviewTitle, interviewer, interviewDate, time;
    private Map<String, String> candidateDB, interviewDB;
    private ExcelConfig excelConfig;
    private String actualLabelName, expectedLabelName,actualPlaceholder, expectedPlaceholder,actualColor, expectedColor,ActualValueFirstName, ActualValueMiddleName, ActualValueLastName, ActualValueEmail, ActualValueContactNumber, ActualValueKeyword, ActualValueNotes;
    private List<String> ActualListLabelName, ActualListPlaceholder, ActualListErrorMessageColor, listExpectedValueDroplist, listActualValueDroplist;
    private Map<String, Object> backup;
}