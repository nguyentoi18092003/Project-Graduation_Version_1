package ptit.orangehrm.Tests.leaveMoudle;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.leaveObjects.LeaveListPageObject;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.LeaveObjectC.*;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class LeaveManagement extends BaseTest {
    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObject dashboadPage;
    DashboardPageObjectC dashboardPageClient;
    ApplyLeavePageObjectC applyLeavePageClient;
    MyLeaveListPageObjectC myLeaveListPageClient;
    LeaveListPageObject leaveListPage;
    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("leaveManagement");

        userNameAdmin = excelConfig.getCellData("valid data", 15);
        passwordAdmin = excelConfig.getCellData("valid data", 16);

        userName = excelConfig.getCellData("valid data", 3);
        password = excelConfig.getCellData("valid data", 4);
        employeeName= excelConfig.getCellData("valid data", 17);

        leaveType = excelConfig.getCellData("valid data", 5);
        fromDate = excelConfig.getCellData("valid data", 6);
        toDate = excelConfig.getCellData("valid data", 7);
        comments = excelConfig.getCellData("valid data", 8);
        fromTime = excelConfig.getCellData("Data",94);
        toTime = excelConfig.getCellData("Data",109);
    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;
//        isTestFailed = false;
//        subcaseScreenshotPath = null;
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
//        loginPage.login(userNameAdmin,passwordAdmin);

    }
    // Begin Apply Leave Screen
//    @Test
    public void TC_01_ApplyLeaveScreen_UI_Verify_name_all_labels(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        ActualListLabelName = applyLeavePageClient.getListActualLabel();

        for (int i = 0; i < ActualListLabelName.size() ; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i + 3).replace("\n", "<br>"));

            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i + 3);

            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }
//    @Test
    public void TC_02_ApplyLeaveScreen_UI_Verify_placeholder(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        ActualListPlaceholder = applyLeavePageClient.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());
        for (int i = 0; i < ActualListPlaceholder.size(); i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i + 9).replace("\n", "<br>"));

            actualPlaceholder = ActualListPlaceholder.get(i);
            expectedPlaceholder = excelConfig.getCellData("Output", i + 12);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
    }
//    @Test
    public void TC_03_ApplyLeaveScreen_UI_Check_Default_Value(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 12).replace("\n", "<br>"));
        ActualValueFromDate = applyLeavePageClient.getTextboxLocatorByLabel("From Date");
        Assert.assertEquals(ActualValueFromDate, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 13).replace("\n", "<br>"));
        ActualValueToDate = applyLeavePageClient.getTextboxLocatorByLabel("To Date");
        Assert.assertEquals(ActualValueToDate, "");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 14).replace("\n", "<br>"));
        ActualValueComments = applyLeavePageClient.getTextboxLocatorByLabel("Comments");
        Assert.assertEquals(ActualValueComments, "");
    }
//    @Test
    public void TC_04_ApplyLeaveScreen_Function_Check_Save_Sucess_And_Fail_With_FromDate(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

        //Enter invalid from date
        for (int i = 16; i <= 40; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            applyLeavePageClient.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "From Date");
            applyLeavePageClient.clickLabel("From Date");
            applyLeavePageClient.isErrorMessageDisplay(excelConfig.getCellData("Output", i));

        }

//         Enter valid from date
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 42).replace("\n", "<br>"));
        applyLeavePageClient.clickToSidebarLinkByText("Dashboard");
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

        fromDate = excelConfig.getCellData("Data", 42);
        applyLeavePageClient.enterToTextboxByLabel(fromDate, "From Date");
        applyLeavePageClient.clickLabel("From Date");
        Assert.assertEquals(applyLeavePageClient.getValueInTextBoxByLabel("From Date"),fromDate);

        //Kiem tra DB.....
        applyLeavePageClient.clickButtonByJSAndName("Apply");
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 43).replace("\n", "<br>"));
        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
        Assert.assertEquals(leaveDB.get("leave_status_name"), "PENDING APPROVAL");
        Assert.assertEquals(leaveDB.get("leave_comments"), comments);
        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);
        applyLeavePageClient.rollbackApplyLeave("trangnguyentt1@gmail.com",fromDate);

//        Kiem tra hoat dong cua canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 44).replace("\n", "<br>"));
        applyLeavePageClient.clickToSidebarLinkByText("Dashboard");
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

        applyLeavePageClient.scrollCalanderOnDown( "From Date");
        applyLeavePageClient.clickCanlanderIconByLabel("From Date");
        applyLeavePageClient.selectDayInCalender(Integer.toString(parseInt(applyLeavePageClient.getToday().substring(8)) + 1));

        // verify canlender dong lai
        applyLeavePageClient.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 45).replace("\n", "<br>"));

        applyLeavePageClient.clickCanlanderIconByLabel("From Date");
        applyLeavePageClient.clickButtonInCanlender("Close");
        applyLeavePageClient.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 46).replace("\n", "<br>"));

        applyLeavePageClient.clickCanlanderIconByLabel("From Date");
        applyLeavePageClient.clickButtonInCanlender("Today");
        Assert.assertEquals(applyLeavePageClient.getValueInTextBoxByLabel("From Date"), applyLeavePageClient.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 47).replace("\n", "<br>"));

        applyLeavePageClient.enterToTextboxByLabel(excelConfig.getCellData("Data", 47), "From Date");
        applyLeavePageClient.scrollCalanderOnDown( "From Date");
        applyLeavePageClient.clickCanlanderIconByLabel("From Date");
        applyLeavePageClient.clickButtonInCanlender("Clear");
        Assert.assertEquals(applyLeavePageClient.getValueInTextBoxByLabel("From Date"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 48).replace("\n", "<br>"));

        applyLeavePageClient.scrollCalanderOnDown( "From Date");
        applyLeavePageClient.clickCanlanderIconByLabel("From Date");

        // focus vao nggya hien tai
        Assert.assertEquals(applyLeavePageClient.getColorOfTodayDay(Integer.toString(parseInt(applyLeavePageClient.getToday().substring(8)))), excelConfig.getCellData("Output", 48));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 49).replace("\n", "<br>"));

        applyLeavePageClient.enterToTextboxByLabel(excelConfig.getCellData("Data", 49), "From Date");
        applyLeavePageClient.clickLabel("From Date");
        applyLeavePageClient.scrollCalanderOnDown( "From Date");
        applyLeavePageClient.clickCanlanderIconByLabel("From Date");
        applyLeavePageClient.scrollCalanderOnTop( "From Date");

        Assert.assertEquals(applyLeavePageClient.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 49).substring(8)))), excelConfig.getCellData("Output", 49));

    }
//    @Test
    public void TC_05_ApplyLeaveScreen_Function_Check_Save_Sucess_And_Fail_With_ToDate(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"From Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

        //Enter invalid to date
        for (int i = 51; i <= 77; i+=2) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            applyLeavePageClient.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "To Date");
            applyLeavePageClient.clickLabel("To Date");
            applyLeavePageClient.isErrorMessageDisplay(excelConfig.getCellData("Output", i));

        }

//         Enter valid to date
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 79).replace("\n", "<br>"));
        applyLeavePageClient.clickToSidebarLinkByText("Dashboard");
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"From Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

        toDate = excelConfig.getCellData("Data", 79);
        applyLeavePageClient.enterToTextboxByLabel(fromDate, "To Date");
        applyLeavePageClient.clickLabel("To Date");
        Assert.assertEquals(applyLeavePageClient.getValueInTextBoxByLabel("To Date"),toDate);

        //Kiem tra DB.....
        applyLeavePageClient.clickButtonByJSAndName("Apply");
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 80).replace("\n", "<br>"));
        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
        Assert.assertEquals(leaveDB.get("leave_status_name"), "PENDING APPROVAL");
        Assert.assertEquals(leaveDB.get("leave_comments"), comments);
        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);
        applyLeavePageClient.rollbackApplyLeave("trangnguyentt1@gmail.com",fromDate);

//        Kiem tra hoat dong cua canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 81).replace("\n", "<br>"));
        applyLeavePageClient.clickToSidebarLinkByText("Dashboard");
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"From Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

        applyLeavePageClient.scrollCalanderOnDown( "To Date");
        applyLeavePageClient.clickCanlanderIconByLabel("To Date");
        applyLeavePageClient.selectDayInCalender(Integer.toString(parseInt(applyLeavePageClient.getToday().substring(8)) + 1));

        // verify canlender dong lai
        applyLeavePageClient.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 82).replace("\n", "<br>"));

        applyLeavePageClient.clickCanlanderIconByLabel("To Date");
        applyLeavePageClient.clickButtonInCanlender("Close");
        applyLeavePageClient.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 83).replace("\n", "<br>"));

        applyLeavePageClient.clickCanlanderIconByLabel("To Date");
        applyLeavePageClient.clickButtonInCanlender("Today");
        Assert.assertEquals(applyLeavePageClient.getValueInTextBoxByLabel("To Date"), applyLeavePageClient.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 84).replace("\n", "<br>"));

        applyLeavePageClient.enterToTextboxByLabel(excelConfig.getCellData("Data", 84), "To Date");
        applyLeavePageClient.scrollCalanderOnDown( "To Date");
        applyLeavePageClient.clickCanlanderIconByLabel("To Date");
        applyLeavePageClient.clickButtonInCanlender("Clear");
        Assert.assertEquals(applyLeavePageClient.getValueInTextBoxByLabel("To Date"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 85).replace("\n", "<br>"));

        applyLeavePageClient.scrollCalanderOnDown( "To Date");
        applyLeavePageClient.clickCanlanderIconByLabel("To Date");

        // focus vao nggya hien tai
        Assert.assertEquals(applyLeavePageClient.getColorOfTodayDay(Integer.toString(parseInt(applyLeavePageClient.getToday().substring(8)))), excelConfig.getCellData("Output", 85));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 86).replace("\n", "<br>"));

        applyLeavePageClient.enterToTextboxByLabel(excelConfig.getCellData("Data", 86), "To Date");
        applyLeavePageClient.clickLabel("To Date");
        applyLeavePageClient.scrollCalanderOnDown( "To Date");
        applyLeavePageClient.clickCanlanderIconByLabel("To Date");
        applyLeavePageClient.scrollCalanderOnTop( "To Date");

        Assert.assertEquals(applyLeavePageClient.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 86).substring(8)))), excelConfig.getCellData("Output", 86));

    }
//    @Test
    public void TC_06_Validatation_LeaveType(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 90).replace("\n", "<br>"));
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.clickToVacancyDropList("Leave Type");
        listExpectedValueDroplist = applyLeavePageClient.getListValueLeaveTypeFromDB();
        listActualValueDroplist = applyLeavePageClient.getListActualValueDropList();
        for (int i = 0; i < listExpectedValueDroplist.size(); i++) {
            Assert.assertEquals(listActualValueDroplist.get(i), listExpectedValueDroplist.get(i));
        }
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 88).replace("\n", "<br>"));
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByJSAndName("Apply");
        applyLeavePageClient.isErrorMessageDisplay(excelConfig.getCellData("Output", 88));
    }

//    @Test
    public void TC_07_Validatation_LeaveBalancce(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 92).replace("\n", "<br>"));
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        leaveType = excelConfig.getCellData("Data",92);
        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        Assert.assertEquals(
                String.format("%.2f", applyLeavePageClient.getDisplayedLeaveBalanceValue()),
                String.format("%.2f", applyLeavePageClient.getLeaveBalanceFromDB("trangnguyentt1@gmail.com", leaveType))
        );
    }
//    @Test
    public void TC_08_ApplyLeaveScreen_Function_Check_Save_Sucess_And_Fail_With_FromTime(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.selectToDropdownByLabel("Specify Time","Duration");
        applyLeavePageClient.enterTimeByLabel("To",toTime,false);


        //Enter invalid interview date
        for (int i = 96; i <= 99; i += 1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            fromTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("From",fromTime,false);
            applyLeavePageClient.clickLabel("From");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("From"),"");
            applyLeavePageClient.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
        }
        //Enter valid from time
        fromTime = excelConfig.getCellData("Data",94);
        applyLeavePageClient.enterTimeByLabel("From",fromTime,false);
        Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("From"),fromTime);
        applyLeavePageClient.clickButtonByJSAndName("Apply");
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));
        applyLeavePageClient.sleepInSecond(20);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 94).replace("\n", "<br>"));
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
//        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 95).replace("\n", "<br>"));
//        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
//        Assert.assertEquals(leaveDB.get("leave_status_name"), "PENDING APPROVAL");
//        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);
        applyLeavePageClient.rollbackApplyLeave("trangnguyentt1@gmail.com",fromDate);

        for(int i=100;i<=104;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
            dashboardPageClient.clickToSidebarLinkByText("Leave");

            myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
            myLeaveListPageClient.clickToSubMenuByName("Apply");
            applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
            applyLeavePageClient.waitForSpinnerIconInvisible();

            applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
            applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
            applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
            applyLeavePageClient.selectToDropdownByLabel("Specify Time","Duration");

            fromTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("From",fromTime, true);
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.clickTimeButton("From","hour-up");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("From"),excelConfig.getCellData("Output",i));
        }

        for(int i=101;i<=105;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            fromTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("From",fromTime, true);
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.clickTimeButton("From","hour-down");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("From"),excelConfig.getCellData("Output",i));
        }

        for(int i=102;i<=106;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            fromTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("From",fromTime, true);
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.clickTimeButton("From","minute-up");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("From"),excelConfig.getCellData("Output",i));
        }
        for(int i=103;i<=107;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            fromTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("From",fromTime, true);
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.openTimePickerByLabel("From");
            applyLeavePageClient.clickTimeButton("From","minute-down");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("From"),excelConfig.getCellData("Output",i));
        }

    }
//    @Test
    public void TC_09_ApplyLeaveScreen_Function_Check_Save_Sucess_And_Fail_With_ToTime(Method method) {
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.selectToDropdownByLabel("Specify Time","Duration");
        applyLeavePageClient.enterTimeByLabel("From",fromTime,false);


        //Enter invalid to time
        for (int i = 111; i <= 115; i += 1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            toTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("To",toTime,false);
            applyLeavePageClient.clickLabel("To");
            applyLeavePageClient.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
        }
        //Enter valid to time
        toTime = excelConfig.getCellData("Data",109);
        applyLeavePageClient.enterTimeByLabel("To",toTime,false);
        Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("To"),toTime);
        applyLeavePageClient.clickButtonByJSAndName("Apply");
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 94).replace("\n", "<br>"));
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
//        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 95).replace("\n", "<br>"));
//        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
//        Assert.assertEquals(leaveDB.get("leave_status_name"), "PENDING APPROVAL");
//        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);
        applyLeavePageClient.rollbackApplyLeave("trangnguyentt1@gmail.com",fromDate);

        for(int i=116;i<=120;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
            dashboardPageClient.clickToSidebarLinkByText("Leave");

            myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
            myLeaveListPageClient.clickToSubMenuByName("Apply");
            applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
            applyLeavePageClient.waitForSpinnerIconInvisible();

            applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
            applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
            applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
            applyLeavePageClient.selectToDropdownByLabel("Specify Time","Duration");

            toTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("To",toTime, true);
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.clickTimeButton("To","hour-up");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("To"),excelConfig.getCellData("Output",i));
        }

        for(int i=117;i<=121;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            toTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("To",toTime, true);
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.clickTimeButton("To","hour-down");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("To"),excelConfig.getCellData("Output",i));
        }

        for(int i=118;i<=122;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            toTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("To",toTime, true);
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.clickTimeButton("To","minute-up");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("To"),excelConfig.getCellData("Output",i));
        }
        for(int i=103;i<=107;i+=4){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            toTime = excelConfig.getCellData("Data",i);
            applyLeavePageClient.enterTimeByLabel("To",toTime, true);
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.openTimePickerByLabel("To");
            applyLeavePageClient.clickTimeButton("To","minute-down");
            Assert.assertEquals(applyLeavePageClient.getTimeValueByLabel("To"),excelConfig.getCellData("Output",i));
        }

    }
//    @Test
    public void TC_10_ApplyLeaveScreen_Function_Check_Save_Sucess_And_Fail_With_Comments(Method method) {
        //Leave the field blank
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");

        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");


        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 126).replace("\n", "<br>"));
        comments=excelConfig.getCellData("Data",126);
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        Assert.assertTrue(applyLeavePageClient.isErrorMsgDisplayedByLabel("Comments", excelConfig.getCellData("Output", 126)));


        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 128; i <= 140; i += 2) {
            //Quay lai trang dasboard
            dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
            dashboardPageClient.clickToSidebarLinkByText("Leave");

            myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
            myLeaveListPageClient.clickToSubMenuByName("Apply");
            applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
            applyLeavePageClient.waitForSpinnerIconInvisible();

            applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
            applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
            applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            comments = excelConfig.getCellData("Data", i);
            applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");

            applyLeavePageClient.clickButtonByJSAndName("Apply");
            Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i + 1).replace("\n", "<br>"));
            leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
//        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 95).replace("\n", "<br>"));
//        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
//        Assert.assertEquals(leaveDB.get("leave_status_name"), "PENDING APPROVAL");
//        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);
            applyLeavePageClient.rollbackApplyLeave("trangnguyentt1@gmail.com",fromDate);
        }
    }
    // End Apply Leave Screen
    // Begin Leave List-Search
//    @Test
    public void TC_11_SearchLeaveScreen_Validation_FromDate(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        //Enter invalid from date
        for (int i = 144; i <= 156; i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            leaveListPage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "From Date");
            leaveListPage.clickLabel("From Date");
            leaveListPage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));

        }

//         Enter valid from date
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 157).replace("\n", "<br>"));

        fromDate = excelConfig.getCellData("Data", 157);
        leaveListPage.enterToTextboxByLabel(fromDate, "From Date");
        leaveListPage.clickLabel("From Date");
        Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("From Date"),fromDate);

//        Kiem tra hoat dong cua canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 158).replace("\n", "<br>"));

        leaveListPage.scrollCalanderOnDown( "From Date");
        leaveListPage.clickCanlanderIconByLabel("From Date");
        leaveListPage.selectDayInCalender(Integer.toString(parseInt(leaveListPage.getToday().substring(8)) + 1));

        // verify canlender dong lai
//        leaveListPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 159).replace("\n", "<br>"));

        leaveListPage.clickCanlanderIconByLabel("From Date");
        leaveListPage.clickButtonInCanlender("Close");
//        leaveListPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 160).replace("\n", "<br>"));

        leaveListPage.clickCanlanderIconByLabel("From Date");
        leaveListPage.clickButtonInCanlender("Today");
        Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("From Date"), leaveListPage.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 161).replace("\n", "<br>"));

        leaveListPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 161), "From Date");
        leaveListPage.scrollCalanderOnDown( "From Date");
        leaveListPage.clickCanlanderIconByLabel("From Date");
        leaveListPage.clickButtonInCanlender("Clear");
        Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("From Date"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 162).replace("\n", "<br>"));

        leaveListPage.scrollCalanderOnDown( "From Date");
        leaveListPage.clickCanlanderIconByLabel("From Date");

        // focus vao nggya hien tai
        Assert.assertEquals(leaveListPage.getColorOfTodayDay(Integer.toString(parseInt(leaveListPage.getToday().substring(8)))), excelConfig.getCellData("Output", 162));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 162).replace("\n", "<br>"));

        leaveListPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 163), "From Date");
        leaveListPage.clickLabel("From Date");
        leaveListPage.scrollCalanderOnDown( "From Date");
        leaveListPage.clickCanlanderIconByLabel("From Date");
        leaveListPage.scrollCalanderOnTop( "From Date");

        Assert.assertEquals(leaveListPage.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 163).substring(8)))), excelConfig.getCellData("Output", 163));

    }
//    @Test
    public void TC_12_SearchLeaveScreen_Validation_ToDate(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        //Enter invalid from date
        for (int i = 165; i <=178 ; i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            leaveListPage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "To Date");
            leaveListPage.clickLabel("To Date");
            leaveListPage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));

        }

//         Enter valid from date
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 179).replace("\n", "<br>"));

        fromDate = excelConfig.getCellData("Data", 179);
        leaveListPage.enterToTextboxByLabel(fromDate, "To Date");
        leaveListPage.clickLabel("To Date");
        Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("To Date"),excelConfig.getCellData("Output",179) );

//        Kiem tra hoat dong cua canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 180).replace("\n", "<br>"));

        leaveListPage.scrollCalanderOnDown( "To Date");
        leaveListPage.clickCanlanderIconByLabel("To Date");
        leaveListPage.selectDayInCalender(Integer.toString(parseInt(leaveListPage.getToday().substring(8)) + 1));

        // verify canlender dong lai
//        leaveListPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 181).replace("\n", "<br>"));

        leaveListPage.clickCanlanderIconByLabel("To Date");
        leaveListPage.clickButtonInCanlender("Close");
//        leaveListPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 182).replace("\n", "<br>"));

        leaveListPage.clickCanlanderIconByLabel("To Date");
        leaveListPage.clickButtonInCanlender("Today");
        Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("To Date"), leaveListPage.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 183).replace("\n", "<br>"));

        leaveListPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 183), "To Date");
        leaveListPage.scrollCalanderOnDown( "To Date");
        leaveListPage.clickCanlanderIconByLabel("To Date");
        leaveListPage.clickButtonInCanlender("Clear");
        Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("To Date"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 184).replace("\n", "<br>"));

        leaveListPage.scrollCalanderOnDown( "To Date");
        leaveListPage.clickCanlanderIconByLabel("To Date");

        // focus vao nggya hien tai
        Assert.assertEquals(leaveListPage.getColorOfTodayDay(Integer.toString(parseInt(leaveListPage.getToday().substring(8)))), excelConfig.getCellData("Output", 185));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 185).replace("\n", "<br>"));

        leaveListPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 185), "To Date");
        leaveListPage.clickLabel("To Date");
        leaveListPage.scrollCalanderOnDown( "To Date");
        leaveListPage.clickCanlanderIconByLabel("To Date");
        leaveListPage.scrollCalanderOnTop( "To Date");

        Assert.assertEquals(leaveListPage.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 185).substring(8)))), excelConfig.getCellData("Output", 185));

    }
//    @Test
    public void TC_13_SearchLeaveScreen_Validation_LeaveType(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 187).replace("\n", "<br>"));

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        leaveListPage.clickToDropList("Leave Type");
        listExpectedValueDroplist = leaveListPage.getListValueLeaveTypeFromDB();
        listActualValueDroplist = leaveListPage.getListActualValueDropList();
        for (int i = 0; i < listExpectedValueDroplist.size(); i++) {
            Assert.assertEquals(listActualValueDroplist.get(i), listExpectedValueDroplist.get(i));
        }

    }
//    @Test
    public void TC_13_SearchLeaveScreen_Validation_ShowLeaveWithStatus(Method method){
        // Kiem tra cac gia tri trong multicombobox
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 190).replace("\n", "<br>"));

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        leaveListPage.clickToDropList("Show Leave with Status");
        listExpectedValueDroplist = leaveListPage.getListValueShowLeaveWithStatusFromDB();
        listActualValueDroplist = leaveListPage.getListActualValueDropList();
        for (int i = 0; i < listExpectedValueDroplist.size()-2; i++) {
            Assert.assertEquals(listActualValueDroplist.get(i).toUpperCase(), listExpectedValueDroplist.get(i));
        }
        leaveListPage.clickLabel("Show Leave with Status");

        // Kiểm tra giá trị bắt buộc
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 189).replace("\n", "<br>"));
        leaveListPage.clearAllSelectedOptionsInCombobox("Show Leave with Status");
        leaveListPage.isErrorMessageDisplay(excelConfig.getCellData("Output", 189));

        // Kiểm tra chọn 1, nhieu gia tri
        for(int i=191;i<=192;++i){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            leaveStatus= excelConfig.getCellData("Data", i).split(",");
            leaveListPage.selectMultiDropdownByLabel("Show Leave with Status", leaveStatus);
            Assert.assertEquals(leaveListPage.getDisplayedTagsInCombobox("Show Leave with Status"),excelConfig.getCellData("Output", i));

        }
    }
//    @Test
    public void TC_13_SearchLeaveScreen_Validation_EmployeeName(Method method) {
       // Kiem tra ket qua search khi nhap gia tri vao comboboxsearch
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();
        leaveListPage.disableAutoFocus();;

        for(int i=194;i<=198;i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            employeeName = excelConfig.getCellData("Data", i);
            leaveListPage.enterToTextboxByLabel(employeeName, "Employee Name");
            leaveListPage.sleepInSecond(40);
            Assert.assertEquals(leaveListPage.getEmployeeNamesFromDB(employeeName), leaveListPage.getSuggestionValuesInCombobox("Employee Name"));
        }
        // Kiem tra khi nhap gia tri nhưng khong chon suggestlist
        for(int i=199;i<=200;i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            employeeName = excelConfig.getCellData("Data", i);
            leaveListPage.enterToTextboxByLabel(employeeName, "Employee Name");
            leaveListPage.clickLabel("Employee Name");
            leaveListPage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
        }
        // Kiem tra khi nhap gia tri va chon suggestlist
        for(int i=201;i<=202;i+=1) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));
            data = excelConfig.getCellData("Data", i).split(",");
            leaveListPage.searchAndSelectInComboboxByLabel(data[0],data[1],"Employee Name");
            Assert.assertEquals(leaveListPage.getValueInTextBoxByLabel("Employee Name"),excelConfig.getCellData("Output", i));
        }
    }

//    @Test
    public void TC_14_SearchLeaveScreen_Validation_CheckAll(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 204).replace("\n", "<br>"));

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        // Kiem tra tich chọn Check All
        leaveListPage.clickCheckAll();
        leaveListPage.sleepInSecond(30);
        Assert.assertTrue(leaveListPage.areAllRowCheckboxesChecked());

        // Kiem tra bo chon check All
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 205).replace("\n", "<br>"));
        leaveListPage.clickCheckAll();
        leaveListPage.sleepInSecond(50);
        Assert.assertFalse(leaveListPage.areAllRowCheckboxesChecked());

        // Kiem tra check all sau khi check tat ca cac checkbox
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 206).replace("\n", "<br>"));
        leaveListPage.checkAllRowCheckboxesIndividually();
        leaveListPage.sleepInSecond(30);
        Assert.assertTrue(leaveListPage.isCheckAllChecked());

        // Kiem tra check all sau khi bo check 1 checkbox
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 207).replace("\n", "<br>"));
        leaveListPage.clickRowCheckboxByIndex(3);
        leaveListPage.sleepInSecond(30);
        Assert.assertFalse(leaveListPage.isCheckAllChecked());

        // Kiem tra tich chon/bo chon cac checkbox
        leaveListPage.clickCheckAll();

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 208).replace("\n", "<br>"));
        leaveListPage.clickRowCheckboxByIndex(3);
        leaveListPage.sleepInSecond(30);
        Assert.assertTrue(leaveListPage.isCheckboxCheckedByIndex(3));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 209).replace("\n", "<br>"));
        leaveListPage.clickRowCheckboxByIndex(3);
        leaveListPage.sleepInSecond(30);
        Assert.assertFalse(leaveListPage.isCheckboxCheckedByIndex(3));

    }
//    @Test
    public void TC_15_SearchLeaveScreen_Function_DateRange(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 212).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();
        leaveListPage.disableAutoFocus();;

        // search by valid daterange
        for(int i=212;i<213;i+=1) {
            data = excelConfig.getCellData("Data", i).split(",");
            leaveListPage.enterToCanlenderTextboxByLabel(data[0], "From Date");
            leaveListPage.enterToCanlenderTextboxByLabel(data[1], "To Date");

            leaveListPage.clickButtonByName(" Search ");
            leaveListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = leaveListPage.getLeaveListByDateRange(data[0], data[1]);
            Assert.assertEquals(leaveListPage.getTotalRecordOnUI(), dbList.size());

            // Verify tung ban ghi
            for (int j = 0; j < dbList.size(); j++) {
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", String.valueOf(j + 1), dbList.get(j).get("date_applied")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", String.valueOf(j + 1), dbList.get(j).get("fullname")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", String.valueOf(j + 1), dbList.get(j).get("leave_type")));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Number of Days", String.valueOf(j + 1)), String.format("%.2f", Double.parseDouble(dbList.get(j).get("length_days"))));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status", String.valueOf(j + 1)).split("\\(")[0].trim().toUpperCase(), dbList.get(j).get("status_name"));
            }
        }
    }
//    @Test
    public void TC_16_SearchLeaveScreen_Function_NoResultsExist(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 214).replace("\n", "<br>"));
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        // search by valid daterange
        data = excelConfig.getCellData("Data", 214).split(",");
        leaveListPage.enterToCanlenderTextboxByLabel(data[0],"From Date");
        leaveListPage.enterToCanlenderTextboxByLabel(data[1],"To Date");

        leaveListPage.clickButtonByName(" Search ");
        leaveListPage.waitForSpinnerIconInvisible();
        // Verify so luong ban ghi tren ui

        Assert.assertTrue(leaveListPage.isSuccessMessageDisplayed("No Records Found"));
        Assert.assertEquals( leaveListPage.getTotalRecordOnUI(),0);

        //Verify so luong ban ghi db tra ve
        dbList = leaveListPage.getLeaveListByDateRange(data[0], data[1]);
        Assert.assertEquals( dbList.size(),0);
    }

//    @Test
    public void TC_17_SearchLeaveScreen_Function_EmployeeName(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        for(int i= 216;i<= 220;++i){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));

            data = excelConfig.getCellData("Data", i).split(",");
            employeeName=data[1];
            leaveListPage.searchAndSelectInComboboxByLabel(data[0],employeeName,"Employee Name");

            leaveListPage.clickButtonByJSAndName(" Search ");
            leaveListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = leaveListPage.getLeaveListByEmployeeName(employeeName);
            Assert.assertEquals(leaveListPage.getTotalRecordOnUI(), dbList.size());

            // Verify tung ban ghi
            for (int j = 0; j < dbList.size(); j++) {
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", String.valueOf(j + 1), dbList.get(j).get("date_applied")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", String.valueOf(j + 1), dbList.get(j).get("fullname")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", String.valueOf(j + 1), dbList.get(j).get("leave_type")));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Number of Days",String.valueOf(j + 1)),String.format("%.2f", Double.parseDouble(dbList.get(j).get("length_days"))));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status",String.valueOf(j + 1)).split("\\(")[0].trim().toUpperCase(),dbList.get(j).get("status_name"));
            }
            leaveListPage.sleepInSecond(20);
        }
    }

//    @Test
    public void TC_19_SearchLeaveScreen_Function_LeaveStatus(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        for(int i= 222;i<= 223;++i){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));


            leaveStatus= excelConfig.getCellData("Data", i).split(",");
            leaveListPage.selectMultiDropdownByLabel("Show Leave with Status", leaveStatus);
            leaveListPage.clickButtonByJSAndName(" Search ");
            leaveListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = leaveListPage.getLeaveListByStatuses(leaveStatus);
            Assert.assertEquals(leaveListPage.getTotalRecordOnUI(), dbList.size());

            // Verify tung ban ghi
            for (int j = 0; j < dbList.size(); j++) {
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", String.valueOf(j + 1), dbList.get(j).get("date_applied")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", String.valueOf(j + 1), dbList.get(j).get("fullname")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", String.valueOf(j + 1), dbList.get(j).get("leave_type")));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Number of Days",String.valueOf(j + 1)),String.format("%.2f", Double.parseDouble(dbList.get(j).get("length_days"))));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status",String.valueOf(j + 1)).split("\\(")[0].trim().toUpperCase(),dbList.get(j).get("status_name"));
            }
            leaveListPage.sleepInSecond(20);
        }
    }

//    @Test
    public void TC_19_SearchLeaveScreen_Function_LeaveType(Method method) {
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        for(int i= 225;i<= 226;++i){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", i).replace("\n", "<br>"));

            leaveType = excelConfig.getCellData("Data", i);
            leaveListPage.selectToDropdownByLabel("Leave Type",leaveType);
            leaveListPage.clickButtonByJSAndName(" Search ");
            leaveListPage.waitForSpinnerIconInvisible();

            // Verify so luong ban ghi tren ui voi db
            dbList = leaveListPage.getLeaveListByLeaveType(leaveType);
            Assert.assertEquals(leaveListPage.getTotalRecordOnUI(), dbList.size());

            // Verify tung ban ghi
            for (int j = 0; j < dbList.size(); j++) {
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", String.valueOf(j + 1), dbList.get(j).get("date_applied")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", String.valueOf(j + 1), dbList.get(j).get("fullname")));
                Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", String.valueOf(j + 1), dbList.get(j).get("leave_type")));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Number of Days",String.valueOf(j + 1)),String.format("%.2f", Double.parseDouble(dbList.get(j).get("length_days"))));
                Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status",String.valueOf(j + 1)).split("\\(")[0].trim().toUpperCase(),dbList.get(j).get("status_name"));
            }
            leaveListPage.sleepInSecond(20);
        }
    }

//    @Test
    public void TC_20_SearchLeaveScreen_Function_CombinedFields(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description 1", 228).replace("\n", "<br>"));

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");

        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.waitForSpinnerIconInvisible();

        data = excelConfig.getCellData("Data", 228).split(",");
        employeeName = data[0];
        leaveType = data[1];

        leaveListPage.searchAndSelectInComboboxByLabel(employeeName,employeeName,"Employee Name");
        leaveListPage.selectToDropdownByLabel("Leave Type",leaveType);
        leaveListPage.clickButtonByJSAndName(" Search ");
        leaveListPage.waitForSpinnerIconInvisible();

        // Verify so luong ban ghi tren ui voi db
        dbList = leaveListPage.getLeaveListByEmployeeAndLeaveType(employeeName,leaveType);
        Assert.assertEquals(leaveListPage.getTotalRecordOnUI(), dbList.size());

        // Verify tung ban ghi
        for (int j = 0; j < dbList.size(); j++) {
            Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", String.valueOf(j + 1), dbList.get(j).get("date_applied")));
            Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", String.valueOf(j + 1), dbList.get(j).get("fullname")));
            Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", String.valueOf(j + 1), dbList.get(j).get("leave_type")));
            Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Number of Days",String.valueOf(j + 1)),String.format("%.2f", Double.parseDouble(dbList.get(j).get("length_days"))));
            Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status",String.valueOf(j + 1)).split("\\(")[0].trim().toUpperCase(),dbList.get(j).get("status_name"));
        }
    }
        //End Leave List-Search

//    @Test
    public void Leave_01_Flow_Approve_LeaveManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 9).replace("\n", "<br>"));

        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        //1. Thuc hien apply leave
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        leaveType = excelConfig.getCellData("valid data", 5);
        fromDate = excelConfig.getCellData("valid data", 6);
        toDate = excelConfig.getCellData("valid data", 7);
        comments = excelConfig.getCellData("valid data", 8);

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByJSAndName("Apply");

        // Check toast message thanh cong
        verifyTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));

        // Check DB
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 10).replace("\n", "<br>"));
        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
        Assert.assertEquals(leaveDB.get("leave_status_name"), "PENDING APPROVAL");
        Assert.assertEquals(leaveDB.get("leave_comments"), comments);
        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);

        //2. Verify on My Leave list
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 13).replace("\n", "<br>"));
//        startCase();
        applyLeavePageClient.clickToSubMenuByName("My Leave");
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        status = excelConfig.getCellData("valid data", 12);
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        myLeaveListPageClient.clearAllSelectedOptionsInCombobox("Show Leave with Status");
        myLeaveListPageClient.selectToDropdownByLabel("Show Leave with Status",status);
        myLeaveListPageClient.clickButtonByName(" Search ");

        // Kiem tra noi dung hien tai trong bang
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Leave Type", "1",leaveType));
//        Assert.assertEquals(myLeaveListPageClient.getValueInTextboxInTable("Status", "1").replaceAll("\\s*\\(.*\\)", "").toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Comments", "1", comments));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Date", "1", fromDate));
//        errors = endCase();
//        excelConfig.logExcelAndJira("Actual result 2", "Bug ID 2",12, this,errors);

        // 3. Approve leave
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 19).replace("\n", "<br>"));
        myLeaveListPageClient.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userNameAdmin, passwordAdmin);

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");
        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);

        leaveListPage.enterToCanlenderTextboxByLabel(fromDate, "From Date");
        leaveListPage.enterToCanlenderTextboxByLabel(toDate, "To Date");
        leaveListPage.searchAndSelectInComboboxByLabel(employeeName, employeeName, "Employee Name");
        leaveListPage.clickButtonByName(" Search ");

        // Verify UI hiển thị đúng
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", "1", leaveDB.get("emp_full_name")));
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", "1", leaveDB.get("leave_type_name")));
//        Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status", "1").replaceAll("\\s*\\(.*\\)", "").toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Comments", "1", leaveDB.get("leave_comments")));
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", "1", leaveDB.get("date_applied")));

        leaveListPage.clickIconByName(" Approve ");
        Assert.assertTrue(leaveListPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 20).replace("\n", "<br>"));
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        Assert.assertEquals(leaveDB.get("leave_status_name"), "SCHEDULED");
        //4. Check my leave after approve
        leaveListPage.logout();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 22).replace("\n", "<br>"));
//        startCase();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        myLeaveListPageClient.clickToSubMenuByName("My Leave");
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        myLeaveListPageClient.clickButtonByName(" Search ");

        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Leave Type", "1", leaveDB.get("leave_type_name")));
        Assert.assertEquals(myLeaveListPageClient.getValueInTextboxInTable("Status", "1").replaceAll("\\s*\\(.*\\)", "").toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Comments", "1", leaveDB.get("leave_comments")));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Date", "1", leaveDB.get("date_applied")));
    }

//    @Test
    public void Leave_02_Flow_Reject_LeaveManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 29).replace("\n", "<br>"));

        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        //1. Thuc hien apply leave
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        leaveType = excelConfig.getCellData("valid data", 25);
        fromDate = excelConfig.getCellData("valid data", 26);
        toDate = excelConfig.getCellData("valid data", 27);
        comments = excelConfig.getCellData("valid data", 28);
        status = excelConfig.getCellData("Output", 30);

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByJSAndName("Apply");

        // Check toast message thanh cong
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));

        // Check DB
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 30).replace("\n", "<br>"));
        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
        Assert.assertEquals(leaveDB.get("leave_status_name"), status);
        Assert.assertEquals(leaveDB.get("leave_comments"), comments);
        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);

        //2. Verify on My Leave list
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 33).replace("\n", "<br>"));
        applyLeavePageClient.clickToSubMenuByName("My Leave");
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.disableAutoFocus();
        status = excelConfig.getCellData("valid data",32 );
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        myLeaveListPageClient.clearAllSelectedOptionsInCombobox("Show Leave with Status");
        myLeaveListPageClient.selectToDropdownByLabel("Show Leave with Status",status);
        myLeaveListPageClient.clickButtonByName(" Search ");

        // Kiem tra noi dung hien tai trong bang
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Leave Type", "1",leaveType));
        Assert.assertEquals(myLeaveListPageClient.getValueInTextboxInTable("Status","1").split("\\(")[0].trim().toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Comments", "1", comments));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Date", "1", fromDate));

        // 3. Reject leave
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 36).replace("\n", "<br>"));
        myLeaveListPageClient.logout();
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userNameAdmin, passwordAdmin);

        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Leave");
        leaveListPage = PageGeneratorManager.getLeaveListPageObject(driver);
        leaveListPage.disableAutoFocus();

        leaveListPage.enterToCanlenderTextboxByLabel(fromDate, "From Date");
        leaveListPage.enterToCanlenderTextboxByLabel(toDate, "To Date");
        leaveListPage.searchAndSelectInComboboxByLabel(employeeName, employeeName, "Employee Name");
        leaveListPage.clickButtonByName(" Search ");

        // Verify UI hiển thị đúng
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Employee Name", "1", leaveDB.get("emp_full_name")));
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Leave Type", "1", leaveDB.get("leave_type_name")));
        Assert.assertEquals(leaveListPage.getValueInTextboxInTable("Status",String.valueOf( "1")).split("\\(")[0].trim().toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Comments", "1", leaveDB.get("leave_comments")));
        Assert.assertTrue(leaveListPage.isValueDisplayedAtColumnName("Date", "1", leaveDB.get("date_applied")));

        leaveListPage.clickIconByName(" Reject ");
        Assert.assertTrue(leaveListPage.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 37).replace("\n", "<br>"));
        status = excelConfig.getCellData("Output", 37);
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        Assert.assertEquals(leaveDB.get("leave_status_name"), status);
        //4. Check my leave after reject
        leaveListPage.logout();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 22).replace("\n", "<br>"));
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        myLeaveListPageClient.clickToSubMenuByName("My Leave");
        myLeaveListPageClient.disableAutoFocus();


        myLeaveListPageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        myLeaveListPageClient.clickButtonByName(" Search ");

        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Leave Type", "1", leaveDB.get("leave_type_name")));
        Assert.assertEquals(myLeaveListPageClient.getValueInTextboxInTable("Status","1").split("\\(")[0].trim().toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Comments", "1", leaveDB.get("leave_comments")));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Date", "1", leaveDB.get("date_applied")));
    }

//    @Test
    public void Leave_03_Flow_Cancel_LeaveManagement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 46).replace("\n", "<br>"));

        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        //1. Thuc hien apply leave
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();

        leaveType = excelConfig.getCellData("valid data", 42);
        fromDate = excelConfig.getCellData("valid data", 43);
        toDate = excelConfig.getCellData("valid data", 44);
        comments = excelConfig.getCellData("valid data", 45);
        status = excelConfig.getCellData("Output", 47);

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByJSAndName("Apply");

        // Check toast message thanh cong
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed("Successfully Saved"));

        // Check DB
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 47).replace("\n", "<br>"));
        Assert.assertEquals(leaveDB.get("leave_type_name"), leaveType);
        Assert.assertEquals(leaveDB.get("leave_status_name"), status);
        Assert.assertEquals(leaveDB.get("leave_comments"), comments);
        Assert.assertEquals(leaveDB.get("date_applied"), fromDate);

        //2. Verify on My Leave list
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 50).replace("\n", "<br>"));
        applyLeavePageClient.clickToSubMenuByName("My Leave");
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.disableAutoFocus();
        status = excelConfig.getCellData("valid data",49 );
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        myLeaveListPageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        myLeaveListPageClient.clearAllSelectedOptionsInCombobox("Show Leave with Status");
        myLeaveListPageClient.selectToDropdownByLabel("Show Leave with Status",status);
        myLeaveListPageClient.clickButtonByName(" Search ");

        // Kiem tra noi dung hien tai trong bang
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Leave Type", "1",leaveType));
        Assert.assertEquals(myLeaveListPageClient.getValueInTextboxInTable("Status","1").split("\\(")[0].trim().toUpperCase(), leaveDB.get("leave_status_name"));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Comments", "1", comments));
        Assert.assertTrue(myLeaveListPageClient.isValueDisplayedAtColumnName("Date", "1", fromDate));

        // 3. Cancel leave
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 52).replace("\n", "<br>"));

        myLeaveListPageClient.clickIconByName(" Cancel ");
        Assert.assertTrue(myLeaveListPageClient.isSuccessMessageDisplayed("Successfully Updated"));
        // Check DB
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 53).replace("\n", "<br>"));
        status = excelConfig.getCellData("Output", 53);
        leaveDB = applyLeavePageClient.leaveFromDB(employeeName, fromDate, toDate);
        Assert.assertEquals(leaveDB.get("leave_status_name"), status);
    }

//    @Test
    public void Leave_04_Apply_Overlap(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 60).replace("\n", "<br>"));

        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        //1. Thuc hien apply leave
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();
        applyLeavePageClient.disableAutoFocus();

        leaveType = excelConfig.getCellData("valid data", 56);
        fromDate = excelConfig.getCellData("valid data", 57);
        toDate = excelConfig.getCellData("valid data", 58);
        comments = excelConfig.getCellData("valid data", 59);

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByName(" Apply ");
        applyLeavePageClient.sleepInSecond(30);

        // Check toast message hien thi va ban ghi trung ngay
        Assert.assertTrue(applyLeavePageClient.isOverlapPopupDisplayed());
        Assert.assertTrue(applyLeavePageClient.isValueDisplayedAtColumnName("Date", "1", fromDate));
    }

//    @Test
    public void Leave_05_Apply_Leave_On_Holiday(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 67).replace("\n", "<br>"));

        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        //1. Thuc hien apply leave
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();
        applyLeavePageClient.disableAutoFocus();

        leaveType = excelConfig.getCellData("valid data", 63);
        fromDate = excelConfig.getCellData("valid data", 64);
        toDate = excelConfig.getCellData("valid data", 65);
        comments = excelConfig.getCellData("valid data", 66);

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByName(" Apply ");

        // Check toast message hien thi va ban ghi trung ngay
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed(excelConfig.getCellData("Output", 67)));
    }
    @Test
    public void Leave_06_Apply_Leave_Exceed_Entitlement(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 74).replace("\n", "<br>"));

        dashboardPageClient = PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("Leave");
        //1. Thuc hien apply leave
        myLeaveListPageClient = PageGeneratorManager.getMyLeaveListPageObjectClient(driver);
        myLeaveListPageClient.clickToSubMenuByName("Apply");
        applyLeavePageClient = PageGeneratorManager.getApplyLeavePageObjectClient(driver);
        applyLeavePageClient.waitForSpinnerIconInvisible();
        applyLeavePageClient.disableAutoFocus();

        leaveType = excelConfig.getCellData("valid data", 70);
        fromDate = excelConfig.getCellData("valid data", 71);
        toDate = excelConfig.getCellData("valid data", 72);
        comments = excelConfig.getCellData("valid data", 73);

        applyLeavePageClient.selectToDropdownByLabel(leaveType,"Leave Type");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(fromDate,"From Date");
        applyLeavePageClient.enterToCanlenderTextboxByLabel(toDate,"To Date");
        applyLeavePageClient.enterToTextareaByLabel(comments,"Comments");
        applyLeavePageClient.clickButtonByName(" Apply ");

        // Check toast message hien thi va ban ghi trung ngay
        Assert.assertTrue(applyLeavePageClient.isSuccessMessageDisplayed(excelConfig.getCellData("Output", 74)));
    }




    @AfterClass(alwaysRun = true)
    public void afterClass() {
//        excelConfig.saveAndClose();
        closeBrowser();
    }
    private String userNameAdmin , passwordAdmin, userName, password, employeeName,fromTime,toTime ;
    private String leaveType, fromDate, toDate, comments, status, errors;
    private String actualLabelName, expectedLabelName,actualPlaceholder, expectedPlaceholder,actualColor, expectedColor, ActualValueFromDate, ActualValueToDate, ActualValueComments;
    private List<String> ActualListLabelName, ActualListPlaceholder, ActualListErrorMessageColor, listExpectedValueDroplist, listActualValueDroplist;
    private String[] data,leaveStatus;
    private List<Map<String,String>> dbList;
    private Map<String, String> leaveDB;
    private ExcelConfig excelConfig;
}
