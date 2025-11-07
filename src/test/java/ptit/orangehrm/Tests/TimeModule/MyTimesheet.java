package ptit.orangehrm.Tests.TimeModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.timeObjects.EditTimesheetPageObject;
import pageObjects.pageObjectsAdmin.timeObjects.MyTimesheetPageObject;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.timeObject.EditTimesheetPageObjectC;
import pageObjects.pageObjectsClient.timeObject.MyTimesheetPageObjectC;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class MyTimesheet extends BaseTest {

    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObjectC dashboadPageC;
    DashboardPageObject dashboadPage;
    MyTimesheetPageObjectC myTimesheetPageC;
    MyTimesheetPageObject myTimesheetPage;
    EditTimesheetPageObjectC editTimesheetPageC;
    EditTimesheetPageObject editTimesheetPage;


    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("timeDta");
        //Lay ra data ban dau
        for(int i=1;i<=4;i++){
            projectToSearchs.add(excelConfig.getCellData("projectToSearchs",  i));
            projects.add(excelConfig.getCellData("projects",  i));
            activitys.add(excelConfig.getCellData("activitys",  i));
            Tues.add(excelConfig.getCellData("Tues",  i));
            Weds.add(excelConfig.getCellData("Weds",  i));
            Thus.add(excelConfig.getCellData("Thus",  i));
            Fris.add(excelConfig.getCellData("Fris",  i));
            Sats.add(excelConfig.getCellData("Sats",  i));
            Suns.add(excelConfig.getCellData("Suns",  i));
            Mons.add(excelConfig.getCellData("Mons",  i));
        }
        //Lay ra data de sau nay edit
//        for(int i=6;i<=9;i++){
//            projectToSearchsE.add(excelConfig.getCellData("projectToSearchs",  i));
//            projectsE.add(excelConfig.getCellData("projects",  i));
//            activitysE.add(excelConfig.getCellData("activitys",  i));
//            TuesE.add(excelConfig.getCellData("Tues",  i));
//            WedsE.add(excelConfig.getCellData("Weds",  i));
//            ThusE.add(excelConfig.getCellData("Thus",  i));
//            FrisE.add(excelConfig.getCellData("Fris",  i));
//            SatsE.add(excelConfig.getCellData("Sats",  i));
//            SunsE.add(excelConfig.getCellData("Suns",  i));
//            MonsE.add(excelConfig.getCellData("Mons",  i));
//        }

    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        //Khoi tao trang login
        loginPage = PageGeneratorManager.getLoginPage(driver);
        //Thuc hien login
        loginPage.login("NguyenToiClient", "Toi%03577");

        //Khoi tao trang dashboard
        dashboadPageC = PageGeneratorManager.getDashboardPageClient(driver);
        dashboadPageC.clickToSidebarLinkByText("Time");
        //Khoi tao trang MyTime
        myTimesheetPageC=PageGeneratorManager.getMyTimesheetPageObjectClient(driver);

        //Clean để đảm bảo luôn tạo được dữ liệu
        myTimesheetPageC.PreCleanStep();
        myTimesheetPageC.refreshCurrentPage(driver);
    }

    //@Test
    public void TC_01_UI_Verify_the_color_of_error_message(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.enterToTextboxInTable("Tue","1", excelConfig.getCellData("Data",  5));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", excelConfig.getCellData("Data",  6));
        editTimesheetPageC.enterToTextboxInTable("Thu","1", excelConfig.getCellData("Data",  7));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", excelConfig.getCellData("Data",  8));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", excelConfig.getCellData("Data",  9));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", excelConfig.getCellData("Data",  10));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", excelConfig.getCellData("Data",  11));
        //Click Luu
        editTimesheetPageC.clickButtonByName(" Save ");
        ActualListErrorMessageColor = editTimesheetPageC.getListListErrorMessageColer();

        for (int i = 0; i <= ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i ).replace("\n", "<br>"));

            actualColor = ActualListErrorMessageColor.get(i);

            expectedColor = excelConfig.getCellData("Output", i+3);
            Assert.assertEquals(actualColor, expectedColor);
        }
    }
    //@Test
    public void TC_02_Function_Validate_Tue_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", Weds.get(0));

        for(int i=13;i<=17;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Tue","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }
    //@Test
    public void TC_03_Function_Validate_Wed_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Tue","1", Tues.get(0));
        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
//        editTimesheetPageC.enterToTextboxInTable("Wed","1", Mons.get(0));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));


        for(int i=20;i<=24;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Wed","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }
    //@Test
    public void TC_04_Function_Validate_Thu_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Tue","1", Tues.get(0));
//        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", Mons.get(0));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));


        for(int i=27;i<=31;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Thu","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }
    //@Test
    public void TC_05_Function_Validate_Fri_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Tue","1", Tues.get(0));
        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", Mons.get(0));
//        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));


        for(int i=34;i<=38;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Fri","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }
    //@Test
    public void TC_06_Function_Validate_Sat_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Tue","1", Tues.get(0));
        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", Mons.get(0));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
//        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));


        for(int i=41;i<=45;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Sat","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }
    //@Test
    public void TC_07_Function_Validate_Sun_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Tue","1", Tues.get(0));
        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", Mons.get(0));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
//        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));


        for(int i=48;i<=52;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Sun","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }
    //@Test
    public void TC_08_Function_Validate_Mon_Column(Method method){
        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);
        editTimesheetPageC.waitForSpinnerIconInvisible();
        editTimesheetPageC.searchAndSelectInCombobox("Project","1",projectToSearchs.get(0),projects.get(0));

        editTimesheetPageC.selectItemInDropdownInTable("Activity","1",activitys.get(0) );

        editTimesheetPageC.enterToTextboxInTable("Tue","1", Tues.get(0));
        editTimesheetPageC.enterToTextboxInTable("Thu","1", Thus.get(0));
        editTimesheetPageC.enterToTextboxInTable("Wed","1", Mons.get(0));
        editTimesheetPageC.enterToTextboxInTable("Fri","1", Fris.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sat","1", Sats.get(0));
        editTimesheetPageC.enterToTextboxInTable("Sun","1", Suns.get(0));
//        editTimesheetPageC.enterToTextboxInTable("Mon","1", Mons.get(0));


        for(int i=55;i<=60;i+=2){
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"nnn");
            //3.5. Kiem tra duoi DB
            Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
            editTimesheetPageC.enterToTextboxInTable("Mon","1",  excelConfig.getCellData("Data",  i));
            editTimesheetPageC.clickButtonByName(" Save ");
            Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

            //Check DB so luong ban ghi truoc va sau
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
            Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());
        }

    }

    //@Test
    public void TC_09_Full_Luong_Project_Timesheet(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"rrrr");

        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC=PageGeneratorManager.getEditTimesheetPageObjectC(driver);

        //Nhap tat ca du lieu vao bang
        for(int i=0;i<projectToSearchs.size();i++){
            projectToSearch= projectToSearchs.get(i);
            project = projects.get(i);
            activity = activitys.get(i);
            tue = Tues.get(i);
            wed = Weds.get(i);
            thu =Thus.get(i);
            fri = Fris.get(i);
            sat = Sats.get(i);
            sun = Suns.get(i);
            mon = Mons.get(i);

            //Tam thoi de if o, khi nao xu li duoc cai backkup data thì xoa di
            if(i!=0) {
                editTimesheetPageC.clickAddRowButton();}
                editTimesheetPageC.searchAndSelectInCombobox("Project",Integer.toString(i+1),projectToSearch,project);

            editTimesheetPageC.selectItemInDropdownInTable("Activity",Integer.toString(i+1),activity );
            editTimesheetPageC.enterToTextboxInTable("Tue",Integer.toString(i+1), tue);
            editTimesheetPageC.enterToTextboxInTable("Wed",Integer.toString(i+1), wed);
            editTimesheetPageC.enterToTextboxInTable("Thu",Integer.toString(i+1), thu);
            editTimesheetPageC.enterToTextboxInTable("Fri",Integer.toString(i+1), fri);
            editTimesheetPageC.enterToTextboxInTable("Sat",Integer.toString(i+1), sat);
            editTimesheetPageC.enterToTextboxInTable("Sun",Integer.toString(i+1), sun);
            editTimesheetPageC.enterToTextboxInTable("Mon",Integer.toString(i+1), mon);

            //Kiem tra gia tri trong textbox da dung voi da nhap chua
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Project",Integer.toString(i+1)),project); - dòng nay de fix sau
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Activity",Integer.toString(i+1)),activity);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Tue",Integer.toString(i+1)),tue);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Wed",Integer.toString(i+1)),wed);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Thu",Integer.toString(i+1)),thu);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Fri",Integer.toString(i+1)),fri);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Sat",Integer.toString(i+1)),sat);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Sun",Integer.toString(i+1)),sun);
//            Assert.assertEquals(editTimesheetPageC.getValueInTextboxInTable("Mon",Integer.toString(i+1)),mon);

            //Kiem tra DB...................................

        }
        //Click Luu
        editTimesheetPageC.clickButtonByName(" Save ");

        // Kiem tra hien thi cua toast messge thanh cong
        Assert.assertTrue(editTimesheetPageC.isSuccessMessageDisplayed("Successfully Saved"));

        //Khoi tao trang MyTimesheet
        myTimesheetPageC=PageGeneratorManager.getMyTimesheetPageObjectClient(driver);

        for(int i=0;i<projectToSearchs.size();i++){
            // Lay ra du lieu thuc te tai bang o trang MyTimesheet
            actualProject=myTimesheetPageC.getCellValueInTable("Project",Integer.toString(i+1));
            actualActivity=myTimesheetPageC.getCellValueInTable("Activity",Integer.toString(i+1));
            actualTue=myTimesheetPageC.getCellValueInTable("Tue",Integer.toString(i+1));
            actualWed=myTimesheetPageC.getCellValueInTable("Wed",Integer.toString(i+1));
            actualThu =myTimesheetPageC.getCellValueInTable("Thu",Integer.toString(i+1));
            actualFri=myTimesheetPageC.getCellValueInTable("Fri",Integer.toString(i+1));
            actualSat= myTimesheetPageC.getCellValueInTable("Sat",Integer.toString(i+1));
            actualSun=myTimesheetPageC.getCellValueInTable("Sun",Integer.toString(i+1));
            actualMon=myTimesheetPageC.getCellValueInTable("Mon",Integer.toString(i+1));

            //Gia tri mong muon (duoc lay ra tu excel)
            project = projects.get(i);
            activity = activitys.get(i);
            tue = Tues.get(i);
            wed = Weds.get(i);
            thu = Thus.get(i);
            fri = Fris.get(i);
            sat = Sats.get(i);
            sun = Suns.get(i);
            mon = Mons.get(i);

            //Kiem tra gia tri actual va expected
            Assert.assertEquals(actualProject, project);
            Assert.assertEquals(actualActivity, activity);
            Assert.assertEquals(actualTue, tue);
            Assert.assertEquals(actualWed, wed);
            Assert.assertEquals(actualThu, thu);
            Assert.assertEquals(actualFri, fri);
            Assert.assertEquals(actualSat, sat);
            Assert.assertEquals(actualSun, sun);
            Assert.assertEquals(actualMon, mon);

            //Tinh SUM thuc te va expected theo tung hang
            String sumRowExpected=myTimesheetPageC.sumTimeOfRow(tue, wed, thu,fri, sat, sun, mon);
            String sumRowActual=myTimesheetPageC.sumTimeOfRow(actualTue,actualWed,actualFri, actualThu,actualSat,actualSun,actualMon);

            //Kiem tra gia tri SUM theo hang thuc te va mong doi
            Assert.assertEquals(sumRowActual,sumRowExpected);

            //Tuong tu tinh tong từng cột voi tong tung cot ( actual, expected)
            sumTotalColumExpected=myTimesheetPageC.sumTime(sumTotalColumExpected,sumRowExpected);
            sumTotalColumActual=myTimesheetPageC.sumTime(sumTotalColumActual,sumRowActual);

            sumTueColumExpected=myTimesheetPageC.sumTime(sumTueColumExpected,tue);
            sumTueColumActual=myTimesheetPageC.sumTime(sumTueColumActual,actualTue);

            sumWedColumExpected=myTimesheetPageC.sumTime(sumWedColumExpected,wed);
            sumWedColumActual=myTimesheetPageC.sumTime(sumWedColumActual,actualWed);

            sumThuColumExpected=myTimesheetPageC.sumTime(sumThuColumExpected,thu);
            sumThuColumActual=myTimesheetPageC.sumTime(sumThuColumActual,actualThu);

            sumFriColumExpected=myTimesheetPageC.sumTime(sumFriColumExpected,fri);
            sumFriColumActual=myTimesheetPageC.sumTime( sumFriColumActual,actualFri);

            sumSatColumExpected=myTimesheetPageC.sumTime(sumSatColumExpected,sat);
            sumSatColumActual=myTimesheetPageC.sumTime(sumSatColumActual,actualSat);

            sumSunColumExpected=myTimesheetPageC.sumTime(sumSunColumExpected,sun);
            sumSunColumActual=myTimesheetPageC.sumTime(sumSunColumActual,actualSun);

            sumMonColumExpected=myTimesheetPageC.sumTime(sumMonColumExpected,mon);
            sumMonColumActual=myTimesheetPageC.sumTime(sumMonColumActual,actualMon);


        }
        // Kiểm tra gia tri tổng từng cột (expected, actual)
        Assert.assertEquals(sumTotalColumExpected,sumTotalColumActual);
        Assert.assertEquals(sumTueColumExpected,sumTueColumActual);
        Assert.assertEquals(sumWedColumExpected,sumWedColumActual);
        Assert.assertEquals(sumThuColumExpected,sumThuColumActual);
        Assert.assertEquals(sumThuColumExpected,sumThuColumActual);
        Assert.assertEquals(sumSatColumExpected,sumSatColumActual);
        Assert.assertEquals(sumSunColumExpected,sumSunColumActual);
        Assert.assertEquals(sumMonColumExpected,sumMonColumActual);

        //Click submit nop cho ben admin
        myTimesheetPageC.clickButtonByName(" Submit ");

        //Kiem tra trang thai
        Assert.assertTrue(myTimesheetPageC.isStatusDisplay("Status: Submitted"));
        //Kiem tra o bang danh sạch
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Actions"),"Submitted");
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Performed By"),myTimesheetPageC.getDisplayname());
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Date"),myTimesheetPageC.getToday());
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Comment"),"");

        //Logout
        myTimesheetPageC.logout();

        //Login vao admin
        loginPage=PageGeneratorManager.getLoginPage(driver);
        loginPage.login("automationfc","Automationfc@123");
        //Khoi tao dashboard Page
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("Time");
        //Khoi tao myTimesheetPage
        myTimesheetPage=PageGeneratorManager.getMyTimesheetPageObject(driver);
        //Tim kiem nhan vien muon phe duyẹt
        myTimesheetPage.searchAndSelectInComboboxByLabel("client","Nguyen  Thi Toi client","Employee Name");
        //Click xem
        myTimesheetPage.clickButtonByJSAndName(" View ");
        myTimesheetPage.waitForSpinnerIconInvisible();
        for(int i=0;i<projectToSearchs.size();i++){
            //Lấy ra các giá trị trong bảng
            actualProject=myTimesheetPage.getCellValueInTable("Project",Integer.toString(i+1));
            actualActivity=myTimesheetPage.getCellValueInTable("Activity",Integer.toString(i+1));
            actualTue=myTimesheetPage.getCellValueInTable("Tue",Integer.toString(i+1));
            actualWed=myTimesheetPage.getCellValueInTable("Wed",Integer.toString(i+1));
            actualThu =myTimesheetPage.getCellValueInTable("Thu",Integer.toString(i+1));
            actualFri=myTimesheetPage.getCellValueInTable("Fri",Integer.toString(i+1));
            actualSat= myTimesheetPage.getCellValueInTable("Sat",Integer.toString(i+1));
            actualSun=myTimesheetPage.getCellValueInTable("Sun",Integer.toString(i+1));
            actualMon=myTimesheetPage.getCellValueInTable("Mon",Integer.toString(i+1));

            project = projects.get(i);
            activity = activitys.get(i);
            tue = Tues.get(i);
            wed = Weds.get(i);
            thu = Thus.get(i);
            fri = Fris.get(i);
            sat = Sats.get(i);
            sun = Suns.get(i);
            mon = Mons.get(i);

            //Kiểm tra giá trị thực tế và mong muốn
            //Cac bước kiểm tra giống bên client
            Assert.assertEquals(actualProject, project);
            Assert.assertEquals(actualActivity, activity);
            Assert.assertEquals(actualTue, tue);
            Assert.assertEquals(actualWed, wed);
            Assert.assertEquals(actualThu, thu);
            Assert.assertEquals(actualFri, fri);
            Assert.assertEquals(actualSat, sat);
            Assert.assertEquals(actualSun, sun);
            Assert.assertEquals(actualMon, mon);

            String sumRowExpected=myTimesheetPage.sumTimeOfRow(tue, wed, thu,fri, sat, sun, mon);
            String sumRowActual=myTimesheetPage.sumTimeOfRow(actualTue,actualWed,actualFri, actualThu,actualSat,actualSun,actualMon);
            Assert.assertEquals(sumRowActual,sumRowExpected);

            sumTotalColumExpected=myTimesheetPage.sumTime(sumTotalColumExpected,sumRowExpected);
            sumTotalColumActual=myTimesheetPage.sumTime(sumTotalColumActual,sumRowActual);

            sumTueColumExpected=myTimesheetPage.sumTime(sumTueColumExpected,tue);
            sumTueColumActual=myTimesheetPage.sumTime(sumTueColumActual,actualTue);

            sumWedColumExpected=myTimesheetPage.sumTime(sumWedColumExpected,wed);
            sumWedColumActual=myTimesheetPage.sumTime(sumWedColumActual,actualWed);

            sumThuColumExpected=myTimesheetPage.sumTime(sumThuColumExpected,thu);
            sumThuColumActual=myTimesheetPage.sumTime(sumThuColumActual,actualThu);

            sumFriColumExpected=myTimesheetPage.sumTime(sumFriColumExpected,fri);
            sumFriColumActual=myTimesheetPage.sumTime( sumFriColumActual,actualFri);

            sumSatColumExpected=myTimesheetPage.sumTime(sumSatColumExpected,sat);
            sumSatColumActual=myTimesheetPage.sumTime(sumSatColumActual,actualSat);

            sumSunColumExpected=myTimesheetPage.sumTime(sumSunColumExpected,sun);
            sumSunColumActual=myTimesheetPage.sumTime(sumSunColumActual,actualSun);

            sumMonColumExpected=myTimesheetPage.sumTime(sumMonColumExpected,mon);
            sumMonColumActual=myTimesheetPage.sumTime(sumMonColumActual,actualMon);


        }
        Assert.assertEquals(sumTotalColumExpected,sumTotalColumActual);
        Assert.assertEquals(sumTueColumExpected,sumTueColumActual);
        Assert.assertEquals(sumWedColumExpected,sumWedColumActual);
        Assert.assertEquals(sumThuColumExpected,sumThuColumActual);
        Assert.assertEquals(sumThuColumExpected,sumThuColumActual);
        Assert.assertEquals(sumSatColumExpected,sumSatColumActual);
        Assert.assertEquals(sumSunColumExpected,sumSunColumActual);
        Assert.assertEquals(sumMonColumExpected,sumMonColumActual);

        //Kiem tra trạng thái

        Assert.assertTrue(myTimesheetPageC.isStatusDisplay("Status: Submitted"));

        //Click Chấp nhận bảng thời gian
        myTimesheetPage.scrollButtonOnTopByName(" Reject ");
        myTimesheetPage.clickButtonByName(" Reject ");

        myTimesheetPage.waitForSpinnerIconInvisible();

        Assert.assertTrue(myTimesheetPageC.isStatusDisplay("Status: Rejected"));

        //Click Chấp nhận bảng thời gian
        myTimesheetPage.scrollButtonOnTopByName(" Submit ");
        myTimesheetPage.clickButtonByName(" Submit ");

        myTimesheetPage.waitForSpinnerIconInvisible();


        //Nhap comment
        comment=excelConfig.getCellData("Comment",  1);
        myTimesheetPageC.enterToTextArea(comment);

        //Click Chấp nhận bảng thời gian
        myTimesheetPage.scrollButtonOnTopByName(" Approve ");
        myTimesheetPage.clickButtonByName(" Approve ");

        //Kiem tra them message approva3 v/e thành cong neu có thoi gia /456
        Assert.assertTrue(myTimesheetPageC.isSuccessMessageDisplayed("Timesheet Approved"));
        //Kiem tra trang thaizcpy[p\1y
        Assert.assertTrue(myTimesheetPageC.isStatusDisplay("Status: Approved"));
        //Kiem tra o bang danh sạch
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Actions"),"Approved");
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Performed By"),myTimesheetPageC.getDisplayname());
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Date"),myTimesheetPageC.getToday());
        Assert.assertEquals(myTimesheetPageC.getCellValueInActionsTable("Comment"),comment);



        //Logout
        myTimesheetPageC.logout();

        //Khoi tao trang login
        loginPage = PageGeneratorManager.getLoginPage(driver);
        //Thuc hien login
        loginPage.login("NguyenToiClient", "Toi%03577");

        //Khoi tao trang dashboard
        dashboadPageC = PageGeneratorManager.getDashboardPageClient(driver);
        dashboadPageC.clickToSidebarLinkByText("Time");
        //Khoi tao trang MyTime
        myTimesheetPageC=PageGeneratorManager.getMyTimesheetPageObjectClient(driver);

        //Kiem tra trang thai
        Assert.assertTrue(myTimesheetPageC.isStatusDisplay("Status: Approved"));

//





















//        editTimesheetPageC.enterToTextboxInTable("Wed","2","5");
//        editTimesheetPageC.enterToTextboxInTable("Thu","2","5");
//        editTimesheetPageC.enterToTextboxInTable("Fri","2","5");

        //tr[1]/th[contains(string(),'Project')]-- Lay cot
        //tr[1]/td[2] -- Lay hang
        //tr[1]/td[2]//i -- click vao mui ten
        //tr[1]/td[2]//preceding-sibling::div//div//span[text()='Hanh dong 1']
        //tr[1]/td[10]//button/i -- button xoa



    }

    @Test
    public void TC_10_Edit_TimeSheet_Fail_When_Dupicate_Activity(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "rrrr");

        myTimesheetPageC.clickButtonByName(" Edit ");
        //Khoi tao trang Edit Time Sheet Page
        editTimesheetPageC = PageGeneratorManager.getEditTimesheetPageObjectC(driver);

        //Nhap tat ca du lieu vao bang
        for (int i = 0; i < 2; i++) {
            projectToSearch = projectToSearchs.get(0);
            project = projects.get(0);
            activity = activitys.get(0);
            tue = Tues.get(0);
            wed = Weds.get(0);
            thu = Thus.get(0);
            fri = Fris.get(0);
            sat = Sats.get(0);
            sun = Suns.get(0);
            mon = Mons.get(0);

            //Tam thoi de if o, khi nao xu li duoc cai backkup data thì xoa di
            if (i != 0) {
                editTimesheetPageC.clickAddRowButton();
            }
            editTimesheetPageC.searchAndSelectInCombobox("Project", Integer.toString(i + 1), projectToSearch, project);

            editTimesheetPageC.selectItemInDropdownInTable("Activity", Integer.toString(i + 1), activity);
            editTimesheetPageC.enterToTextboxInTable("Tue", Integer.toString(i + 1), tue);
            editTimesheetPageC.enterToTextboxInTable("Wed", Integer.toString(i + 1), wed);
            editTimesheetPageC.enterToTextboxInTable("Thu", Integer.toString(i + 1), thu);
            editTimesheetPageC.enterToTextboxInTable("Fri", Integer.toString(i + 1), fri);
            editTimesheetPageC.enterToTextboxInTable("Sat", Integer.toString(i + 1), sat);
            editTimesheetPageC.enterToTextboxInTable("Sun", Integer.toString(i + 1), sun);
            editTimesheetPageC.enterToTextboxInTable("Mon", Integer.toString(i + 1), mon);

        }
        Map<String, String> recordDBTruoc = editTimesheetPageC.recordFromDB("0347");
        editTimesheetPageC.clickButtonByName(" Save ");
        Map<String, String> recordDBSau = editTimesheetPageC.recordFromDB("0347");

        //Check DB so luong ban ghi truoc va sau
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "dd");
        Assert.assertEquals(recordDBTruoc.size(),recordDBSau.size());

        //Check not display toast message
        editTimesheetPageC.isSuccessMessageNotDisplayed("Saved Succesfully");
    }
//@AfterClass
//public void afterClass() {
//    closeBrowser();
//
//}
    private ExcelConfig excelConfig;
    private String disPlayName,comment;

    private ArrayList<String> projectToSearchs = new ArrayList<>();
    private ArrayList<String> projectToSearchsE=new ArrayList<>();
    private ArrayList<String> projects = new ArrayList<>();
    private ArrayList<String> projectsE = new ArrayList<>();

    private ArrayList<String> activitys = new ArrayList<>();
    private ArrayList<String> activitysE = new ArrayList<>();

    private ArrayList<String> Tues = new ArrayList<>();
    private ArrayList<String> TuesE = new ArrayList<>();


    private ArrayList<String> Weds = new ArrayList<>();
    private ArrayList<String> WedsE = new ArrayList<>();

    private ArrayList<String> Thus = new ArrayList<>();
    private ArrayList<String> ThusE = new ArrayList<>();

    private ArrayList<String> Fris = new ArrayList<>();
    private ArrayList<String> FrisE = new ArrayList<>();

    private ArrayList<String> Sats = new ArrayList<>();
    private ArrayList<String> SatsE = new ArrayList<>();

    private ArrayList<String> Suns = new ArrayList<>();
    private ArrayList<String> SunsE = new ArrayList<>();

    private ArrayList<String> Mons = new ArrayList<>();
    private ArrayList<String> MonsE = new ArrayList<>();
    private String actualColor, expectedColor;
    private List<String> ActualListErrorMessageColor;
    private String actualProject,actualActivity,actualTue,actualWed, actualThu,actualFri,actualSat,actualSun,actualMon;
    private String projectToSearch,project, activity, tue, wed, thu, fri, sat, sun, mon;
    private  String sumTotalColumActual="00:00",sumTotalColumExpected="00:00", sumTueColumExpected="00:00",sumTueColumActual="00:00";
    private String  sumWedColumExpected="00:00",sumWedColumActual="00:00",sumThuColumExpected="00:00",sumThuColumActual="00:00",sumFriColumExpected="00:00",sumFriColumActual="00:00";
    private String sumSatColumExpected="00:00",sumSatColumActual="00:00",sumSunColumExpected="00:00",sumSunColumActual="00:00",sumMonColumExpected="00:00",sumMonColumActual="00:00";
}



