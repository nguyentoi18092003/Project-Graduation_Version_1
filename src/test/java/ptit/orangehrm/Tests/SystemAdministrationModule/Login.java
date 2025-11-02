package ptit.orangehrm.Tests.SystemAdministrationModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.List;

public class Login extends BaseTest {
    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("login");
    }
    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver);
//        loginPage.enterToTextboxByName(userName, "username");
//        loginPage.enterToTextboxByName(password, "password");
    }

    //Verify name all labels
   // @Test
    public void TC_01_Verify_name_all_labels(Method method) {
        ActualListLabelName = loginPage.getListActualLabel();
        for (int i = 0; i <= ActualListLabelName.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));
            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i + 2);
            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }
    //@Test
    public void TC_02_Verify_placeholder(Method method) {
        ActualListPlaceholder = loginPage.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());
        for (int i = 0; i <= ActualListPlaceholder.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i +5).replace("\n", "<br>"));
            actualPlaceholder = ActualListPlaceholder.get(i);
            expectedPlaceholder = excelConfig.getCellData("Output", i +5);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
    }
    //@Test
    public void TC_03_Verify_the_color_of_error_message(Method method) {
        loginPage.clickToLoginButton();
        ActualListErrorMessageColor = loginPage.getListListErrorMessageColer();

        for (int i = 0; i <= ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 8).replace("\n", "<br>"));
            actualColor = ActualListErrorMessageColor.get(i);
            expectedColor = excelConfig.getCellData("Output", i + 8);
            Assert.assertEquals(actualColor, expectedColor);
        }
    }
    //@Test
    public void TC_04_Verify_color_of_button_and_textcolor_in_button(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 11).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfButton(" Login "),excelConfig.getCellData("Output", 11));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 12).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorTextInButton(" Login "),excelConfig.getCellData("Output", 12));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 13).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfTitlePage(),excelConfig.getCellData("Output", 13));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 14).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfForgotPasswordLink(),excelConfig.getCellData("Output", 14));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 15).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfIconSocialPage("linkedin.com"),excelConfig.getCellData("Output", 15));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 16).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfIconSocialPage("facebook.com"),excelConfig.getCellData("Output", 16));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 17).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfIconSocialPage("twitter.com"),excelConfig.getCellData("Output", 17));

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 18).replace("\n", "<br>"));
        Assert.assertEquals(loginPage.colorOfIconSocialPage("youtube.com"),excelConfig.getCellData("Output", 18));
    }
    //@Test
    public void TC_05_Check_Default_Value(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 20).replace("\n", "<br>"));
        ActualValueUserName = loginPage.getValueInTextBoxByName("username");
        Assert.assertEquals(ActualValueUserName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 21).replace("\n", "<br>"));
        ActualValueUserName = loginPage.getValueInTextBoxByName("password");
        Assert.assertEquals(ActualValueUserName,"");
    }
    //@Test
    public void TC_06_Valiate_UserName(Method method) {

        loginPage.enterToTextboxByName(password, "password");
        loginPage.clickToLoginButton();

        //Leave the field blank
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 23).replace("\n", "<br>"));
        Assert.assertTrue(loginPage.isErrorMsgDisplayedByName("username",excelConfig.getCellData("Output", 23)));

        //special characters,Vietnamese characters with diacritics
        for (int i = 24; i <= 25; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            userName = excelConfig.getCellData("Data", i);
            loginPage.enterToTextboxByName(userName, "username");
            ActualValueUserName = loginPage.getValueInTextBoxByName("username");
            Assert.assertEquals(ActualValueUserName, userName);
        }
    }
    //@Test
    public void TC_07_Valiate_Password(Method method) {

        loginPage.enterToTextboxByName(userName, "username");
        loginPage.clickToLoginButton();

        //Leave the field blank
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 27).replace("\n", "<br>"));
        Assert.assertTrue(loginPage.isErrorMsgDisplayedByName("password",excelConfig.getCellData("Output", 27)));

        //special characters,Vietnamese characters with diacritics
        for (int i = 28; i <= 29; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            password = excelConfig.getCellData("Data", i);
            loginPage.enterToTextboxByName(password, "password");
            ActualValuePassword = loginPage.getValueInTextBoxByName("password");
            Assert.assertEquals(ActualValuePassword, password);
        }
    }
    //@Test
    public void TC_08_Click_To_Link(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check display after cliking fogot password link");
        loginPage.clickToForgotPasswordLink();
        Assert.assertTrue(loginPage.resetPasswordDisplay());
    }
    @Test
    public void TC_09_Click_To_Icon_Social_Page(Method method) throws InterruptedException {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check display after click icon Linkedin Page");
        String orangeIDPage=loginPage.getOrangeIDPage();

        loginPage.clickToOrangeSocialPage("linkedin.com");
        loginPage.swithTab("OrangeHRM | LinkedIn");
        Thread.sleep(4000);
        loginPage.closePopupInLinkedinPage();
        Assert.assertTrue(loginPage.isLinkedPageDisplay());

        loginPage.closeTabExOrangePage(orangeIDPage);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check display after click icon Facebook Page");
        loginPage.clickToOrangeSocialPage("facebook.com");
        loginPage.swithTab("OrangeHRM - World's Most Popular Opensource HRIS | Secaucus NJ | Facebook");
        Thread.sleep(4000);
        loginPage.closePopupInFacebookPage();
        Assert.assertTrue(loginPage.isFacebookPageDisplay());

        loginPage.closeTabExOrangePage(orangeIDPage);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check display after click icon Tiwster Page");
        loginPage.clickToOrangeSocialPage("twitter.com");
        loginPage.swithTab("OrangeHRM (@orangehrm) / X");
        Thread.sleep(4000);
        Assert.assertTrue(loginPage.isTiwsterPageDisplay());

        loginPage.closeTabExOrangePage(orangeIDPage);

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Check display after click icon Youtube Page");
        loginPage.clickToOrangeSocialPage("youtube.com");
        Thread.sleep(4000);
        loginPage.swithTab("OrangeHRM Inc - YouTube");
        Assert.assertTrue(loginPage.isYoutubePageDisplay());

//        loginPage.clickToForgotPasswordLink();
//        Assert.assertTrue(loginPage.resetPasswordDisplay());
        ////img/parent::div/parent::div/preceding-sibling::button
        //icon[@data-test-id='nav-logo'] - logo Linked

        //Face
        //div[@aria-label='Close'] - popup
        //title[contains(.,'Facebook')]

        //youtube
       //
    }
    @AfterMethod
    public void afterMethod() {
        closeBrowser();
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();
    }
    private String userName="automationfc", password="Automationfc@123";
    private ExcelConfig excelConfig;
    private String  actualLabelName, expectedLabelName, actualPlaceholder, expectedPlaceholder;
    private String actualColor, expectedColor, ActualValueUserName,ActualValuePassword;
    private List<String>  ActualListLabelName, ActualListPlaceholder, ActualListErrorMessageColor;

}
