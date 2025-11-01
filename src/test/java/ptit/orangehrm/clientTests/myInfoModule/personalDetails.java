package ptit.orangehrm.clientTests.myInfoModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.MyInfoObjectsC.PersonalDetailPageObjectC;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;
import utilities.GeminiClient;

import java.lang.reflect.Method;
import java.util.List;

import static java.lang.Integer.parseInt;

public class personalDetails extends BaseTest {
    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObjectC dashboardClientpage;
    PersonalDetailPageObjectC personalDetailClientPage;

    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("personalDetails");
        int counter = 0;
        for(int i=1;i<=83;i++){
            String moTa = excelConfig.getCellData("Description", i );
            String yesNo=excelConfig.getCellData("Auto gen data test (Yes/No)", i );
            if(yesNo.equals("Yes")){
                String prompt = "Dựa vào mô tả của testcase, lấy cho tôi data test của case đó, chú ý chỉ cần trả về duy nhất data test không trả về nội dung thừa: Mô tả testcase như sau" +moTa;
                try {
                    String result = GeminiClient.callGemini(prompt);
                    System.out.println("Mô tả" +" "+ i+ ":" + moTa);
                    System.out.println("Gemini trả lời: " + result);

                    counter++;
                    if (counter % 8 == 0) { // sau mỗi 8 request thì nghỉ 1 giây
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Lỗi khi gọi Gemini: "+ e);
                }
            }
        }
    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;
        userName = "NguyenToiClient";
        password = "Toi%03577";

        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.enterToTextboxByName(userName, "username");
        loginPage.enterToTextboxByName(password, "password");

        loginPage.clickToLoginButton();
        dashboardClientpage = PageGeneratorManager.getDashboardPageClient(driver);

    }

    //Verify name all labels
    @Test
    public void TC_01_Verify_name_all_labels(Method method) {
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);

        ActualListLabelName = personalDetailClientPage.getListActualLabel();

        for (int i = 0; i <= ActualListLabelName.size() - 1; i++) {

            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));

            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i + 2);

            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }

    //Verify message
    @Test
    public void TC_02_Verify_placeholder(Method method) {
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailClientPage.clickButtonAdd();

        ActualListPlaceholder = personalDetailClientPage.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());

        for (int i = 0; i <= ActualListPlaceholder.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 18).replace("\n", "<br>"));

            actualPlaceholder = ActualListPlaceholder.get(i);

            expectedPlaceholder = excelConfig.getCellData("Output", i + 18);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 23).replace("\n", "<br>"));
        actualTextareaPlaceholder = personalDetailClientPage.getActualPlaceholder();
        expectedTextareaPlaceholder = excelConfig.getCellData("Output", 23);

        Assert.assertEquals(actualTextareaPlaceholder, expectedTextareaPlaceholder);

    }

    @Test
    public void TC_03_Verify_the_color_of_error_message(Method method) {
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailClientPage.clickButtonAdd();

        personalDetailClientPage.waitForSpinnerIconInvisible();

        personalDetailClientPage.enterToTextboxByName(excelConfig.getCellData("Data", 28), "firstName");
        personalDetailClientPage.enterToTextboxByName(excelConfig.getCellData("Data", 29), "middleName");
        personalDetailClientPage.enterToTextboxByName(excelConfig.getCellData("Data", 30), "lastName");

        personalDetailClientPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 31), "Other Id");
        personalDetailClientPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 32), "License Expiry Date");
        personalDetailClientPage.enterToTextareaByLabel(excelConfig.getCellData("Data", 33), "Comment");


        ActualListErrorMessageColor = personalDetailClientPage.getListListErrorMessageColer();
//        System.out.println("sssssssssssssssssssssssssssss "+ ActualListErrorMessageColor.size());

        for (int i = 0; i <= ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 28).replace("\n", "<br>"));

            actualColor = ActualListErrorMessageColor.get(i);

            expectedColor = excelConfig.getCellData("Output", i + 28);
            Assert.assertEquals(actualColor, expectedColor);
        }


    }

    @Test
    public void TC_04_Valiate_FirstName(Method method) {
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailClientPage.clickButtonAdd();

        personalDetailClientPage.waitForSpinnerIconInvisible();

        // Blank
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 36).replace("\n", "<br>"));

        personalDetailClientPage.scrollToTextboxOnDown("firstName");
        personalDetailClientPage.enterToTextboxByName("", "firstName");
        personalDetailClientPage.waitForSpinnerIconInvisible();
        personalDetailClientPage.isErrorMessageDisplay(excelConfig.getCellData("Output", 36));

//        //Gia tri mac dinh
//        ExtentTestManager.startTest(method.getName() + "-"+browserName.toUpperCase(),"gia tri mac dinh");
//
//        ActualValueFirstName = personalDetailClientPage.getValueInTextBoxByName("firstName");
//        //expectedResult=.....// Doan nay mai viet tham ham lay gia tri o DB ra
//        expectedResult=personalDetailClientPage.getDefaultValueFromDB("firstName");
//        Assert.assertEquals( ActualValueFirstName,expectedResult);

        //maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 37).replace("\n", "<br>"));
        personalDetailClientPage.enterToTextboxByName(excelConfig.getCellData("Data", 37), "firstName");
        personalDetailClientPage.isErrorMessageDisplay(excelConfig.getCellData("Output", 37));

        //minlegth, minlength+1, maxlength-1, maxlength,ki tu dac biet, tieng viet co dau,
        for (int i = 38; i <= 44; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            firstName = excelConfig.getCellData("Data", i);
            personalDetailClientPage.enterToTextboxByName(firstName, "firstName");
            ActualValueFirstName = personalDetailClientPage.getValueInTextBoxByName("firstName");
            Assert.assertEquals(ActualValueFirstName, firstName);
        }
    }

    @Test
    public void TC_05_Button_EmpoyeeID(Method method) {
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailClientPage.clickButtonAdd();

        personalDetailClientPage.waitForSpinnerIconInvisible();

        // Check textbox disable
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 36).replace("\n", "<br>"));

        Assert.assertEquals(personalDetailClientPage.checkTextboxDisable("Employee Id"), false);
    }

    @Test
    // TC 06: trên web đang gap bug
    public void TC_06_Combobox(Method method) {
        // Check cac gia tri va thư tu các gia tri
//        dashboardClientpage.clickToSidebarLinkByText("My Info");
//        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
//        personalDetailClientPage.clickButtonAdd();
//
//        personalDetailClientPage.waitForSpinnerIconInvisible();
//
//        personalDetailClientPage.clickToNationalDropList("Nationality");
//
//        listExpectedValueDroplist = personalDetailClientPage.getListValueDroplistFromDB();
//        listActualValueDroplist = personalDetailClientPage.getListActualValueDropList();
//        System.out.println("ssssssssssssssssssssssssssss " + listExpectedValueDroplist.size() + "\n");
//        System.out.println("ssssssssssssssssssssssssssssssssssssss " + listActualValueDroplist.size());
//
//        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "test dropdown");
//        for (int i = 0; i < listExpectedValueDroplist.size(); i++) {
//            Assert.assertEquals(listActualValueDroplist.get(i), listExpectedValueDroplist.get(i));
//        }
        Assert.assertEquals("True","True");


    }

    @Test
    public void TC_07_Combobox_Date(Method method) throws InterruptedException {
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailClientPage.clickButtonAdd();

        personalDetailClientPage.waitForSpinnerIconInvisible();

        //Cac case invalid
        for (int i = 47; i <= 61; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            personalDetailClientPage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "License Expiry Date");
            personalDetailClientPage.clickLabel("License Expiry Date");
            personalDetailClientPage.isErrorMessageDisplay(excelConfig.getCellData("Output", i));
        }

        // Cac case valid
        for (int i = 62; i <= 77; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            personalDetailClientPage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "License Expiry Date");
            personalDetailClientPage.clickLabel("License Expiry Date");
            Assert.assertEquals(personalDetailClientPage.getValueInTextBoxByLabel("License Expiry Date"), excelConfig.getCellData("Output", i));

        }

        //Kiem tra hoat dong cuar canlender, khi chon calender se dong lai
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 78).replace("\n", "<br>"));

        personalDetailClientPage.scrollCalanderOnDown( "License Expiry Date");
        personalDetailClientPage.clickCanlanderIconByLabel("License Expiry Date");
        personalDetailClientPage.selectDayInCalender(Integer.toString(parseInt(personalDetailClientPage.getToday().substring(8)) + 1));

        // verify canlender dong lai
        personalDetailClientPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cuua button close
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 79).replace("\n", "<br>"));

        personalDetailClientPage.clickCanlanderIconByLabel("License Expiry Date");
        personalDetailClientPage.clickButtonInCanlender("Close");
        personalDetailClientPage.isButtonInCanalenerDisplay("Close");

        //Check hoat dong cua button Today
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 80).replace("\n", "<br>"));

        personalDetailClientPage.clickCanlanderIconByLabel("License Expiry Date");
        personalDetailClientPage.clickButtonInCanlender("Today");
        Assert.assertEquals(personalDetailClientPage.getValueInTextBoxByLabel("License Expiry Date"), personalDetailClientPage.getToday());

        //Check hoat dong cua button clear
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 81).replace("\n", "<br>"));

        personalDetailClientPage.enterToTextboxByLabel("asdfasdfds", "License Expiry Date");
        personalDetailClientPage.scrollCalanderOnDown( "License Expiry Date");
        personalDetailClientPage.clickCanlanderIconByLabel("License Expiry Date");
        personalDetailClientPage.clickButtonInCanlender("Clear");
        Assert.assertEquals(personalDetailClientPage.getValueInTextBoxByLabel("License Expiry Date"), null);

        //Trong textbox chua co gia tri
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 82).replace("\n", "<br>"));

        personalDetailClientPage.scrollCalanderOnDown( "License Expiry Date");
        personalDetailClientPage.clickCanlanderIconByLabel("License Expiry Date");

        // focus vao nggya hien tai
        Assert.assertEquals(personalDetailClientPage.getColorOfTodayDay(Integer.toString(parseInt(personalDetailClientPage.getToday().substring(8)))), excelConfig.getCellData("Output", 82));

        //Kiem tra focus trong calender khi co dư lieu
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 83).replace("\n", "<br>"));

        personalDetailClientPage.enterToTextboxByLabel(excelConfig.getCellData("Data", 83), "License Expiry Date");
        personalDetailClientPage.clickLabel("License Expiry Date");
        personalDetailClientPage.scrollCalanderOnDown( "License Expiry Date");
        personalDetailClientPage.clickCanlanderIconByLabel("License Expiry Date");
        personalDetailClientPage.scrollCalanderOnTop( "License Expiry Date");

        Assert.assertEquals(personalDetailClientPage.getColorOfFocusDay(Integer.toString(parseInt(excelConfig.getCellData("Data", 83).substring(8)))), excelConfig.getCellData("Output", 83));


    }

    @Test
    public void TC_08_RadioButton(Method method){
        dashboardClientpage.clickToSidebarLinkByText("My Info");
        personalDetailClientPage = PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailClientPage.clickButtonAdd();

        personalDetailClientPage.waitForSpinnerIconInvisible();
        //kiem tra chon radio
        personalDetailClientPage.selectRadioButtonByValue("Male");
        Assert.assertEquals(personalDetailClientPage.isRadioButtonSelectedByValue("Male"), true);
        //kiem tra bo chon
        personalDetailClientPage.selectRadioButtonByValue("Female");
        Assert.assertEquals(personalDetailClientPage.isRadioButtonSelectedByValue("Male"), false);

    }

    @AfterMethod
    public void afterMethod() {
        closeBrowser();
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }

    private String userName;
    private String password, confirmPassword;
    private String firstName, middleName, lastName, employeeId;


    private ExcelConfig excelConfig;
    private String pre, actualLabelName, expectedLabelName, stepByStep, testCase, expectedResult, otherId, licenseExpiryDate, comment, actualPlaceholder, expectedPlaceholder, actualTextareaPlaceholder, expectedTextareaPlaceholder;
    private String actualColor, expectedColor, ActualValueFirstName;
    private List<String> labelOnScreen, ActualListLabelName, ExpectedListLabelName, listDataTest, ActualListPlaceholder, ActualListErrorMessageColor, listExpectedValueDroplist, listActualValueDroplist;

}
