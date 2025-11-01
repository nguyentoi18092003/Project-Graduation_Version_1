package ptit.orangehrm.Tests.pimModule;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.AddEmployeePageObject;
import pageObjects.pageObjectsAdmin.pimObjects.EmployeeListPageObject;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class AddEmployee extends BaseTest {

    private WebDriver driver;
    private String browserName;
    LoginPageObject loginPage;

    DashboardPageObject dashboadPage;
    EmployeeListPageObject employeeListPage;
    AddEmployeePageObject addEmployeePage;

    @BeforeClass
    public void beforeClass() {
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("personalDetails");

    }

    @Parameters({"url", "browser"})
    @BeforeMethod
    public void beforeMethod(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver);

        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("addEmployeeDta");

        loginPage.login("automationfc", "Automationfc@123");
        dashboadPage = PageGeneratorManager.getDashboardPage(driver);
        dashboadPage.clickToSidebarLinkByText("PIM");
        employeeListPage = PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);
        addEmployeePage.clickToggle();
    }

    @Test
    public void TC_01_Verify_name_all_labels(Method method) {
        ActualListLabelName = addEmployeePage.getListActualLabel();

        for (int i = 0; i <= ActualListLabelName.size() - 1; i++) {

           // ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 2).replace("\n", "<br>"));

            actualLabelName = ActualListLabelName.get(i);
            expectedLabelName = excelConfig.getCellData("Output", i + 2);

            Assert.assertEquals(actualLabelName, expectedLabelName);
        }
    }
    @Test
    public void TC_02_Verify_placeholder(Method method) {
        ActualListPlaceholder = addEmployeePage.getListActualPlaceholder();
        System.out.println(ActualListPlaceholder.size());
        for (int i = 0; i <= ActualListPlaceholder.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i +9).replace("\n", "<br>"));

            actualPlaceholder = ActualListPlaceholder.get(i);

            expectedPlaceholder = excelConfig.getCellData("Output", i +9);
            Assert.assertEquals(actualPlaceholder, expectedPlaceholder);
        }
    }
    @Test
    public void TC_03_Verify_the_color_of_error_message(Method method) {

        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 13), "firstName");
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 14), "middleName");
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 15), "lastName");

        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 16), "Username");
        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 17), "Password");
        addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", 18), "Confirm Password");

        ActualListErrorMessageColor = addEmployeePage.getListListErrorMessageColer();

        for (int i = 0; i <= ActualListErrorMessageColor.size() - 1; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i + 13).replace("\n", "<br>"));

            actualColor = ActualListErrorMessageColor.get(i);

            expectedColor = excelConfig.getCellData("Output", i + 13);
            Assert.assertEquals(actualColor, expectedColor);
        }
    }
    @Test
    public void TC_04_Verify_color_of_button_and_textcolor_in_button(Method method){
       ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 19).replace("\n", "<br>"));
       Assert.assertEquals(addEmployeePage.colorOfButton(" Cancel "),excelConfig.getCellData("Output", 19));

       ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 20).replace("\n", "<br>"));
       Assert.assertEquals(addEmployeePage.colorTextInButton(" Cancel "),excelConfig.getCellData("Output", 20));

       ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 21).replace("\n", "<br>"));
       Assert.assertEquals(addEmployeePage.colorOfButton(" Save "),excelConfig.getCellData("Output", 21));

       ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 22).replace("\n", "<br>"));
       Assert.assertEquals(addEmployeePage.colorTextInButton(" Save "),excelConfig.getCellData("Output", 22));



   }
    @Test
    public void TC_05_Check_Default_Value(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 24).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("firstName");
        Assert.assertEquals( ActualValueFirstName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 25).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("middleName");
        Assert.assertEquals( ActualValueFirstName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 26).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("lastName");
        Assert.assertEquals( ActualValueFirstName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 27).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        Assert.assertNotEquals( ActualValueFirstName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 28).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Username");
        Assert.assertEquals( ActualValueFirstName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 29).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Password");
        Assert.assertEquals( ActualValueFirstName,"");

        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 30).replace("\n", "<br>"));
        ActualValueFirstName = addEmployeePage.getValueInTextBoxByLabel("Confirm Password");
        Assert.assertEquals( ActualValueFirstName,"");
    }
    @Test
    public void TC_06_Valiate_FirstName(Method method) {
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 32).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("firstName",excelConfig.getCellData("Output", 32)));

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 33).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 33), "firstName");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("firstName",excelConfig.getCellData("Output", 33)));

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 34; i <= 40; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            firstName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByName(firstName, "firstName");
            ActualValueFirstName = addEmployeePage.getValueInTextBoxByName("firstName");
            Assert.assertEquals(ActualValueFirstName, firstName);
        }
    }
    @Test
    public void TC_07_Valiate_MiddleName(Method method) {
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        System.out.println("sssssssssssssssssssssssssssss        "+ employeeId);
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 42).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 42), "middleName");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("middleName",excelConfig.getCellData("Output", 42)));

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 43; i <= 49; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            middleName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByName(middleName, "middleName");
            ActualValueMiddleName = addEmployeePage.getValueInTextBoxByName("middleName");
            Assert.assertEquals(ActualValueMiddleName, middleName);
        }
    }
    @Test
    public void TC_08_Valiate_LastName(Method method) {
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 51).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("lastName",excelConfig.getCellData("Output", 51)));

        //Entering maxlength+ 1
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 52).replace("\n", "<br>"));
        addEmployeePage.enterToTextboxByName(excelConfig.getCellData("Data", 52), "lastName");
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByName("lastName",excelConfig.getCellData("Output", 52)));

        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 53; i <= 60; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            lastName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByName(lastName, "lastName");
            ActualValuelastName = addEmployeePage.getValueInTextBoxByName("lastName");
            Assert.assertEquals(ActualValuelastName, lastName);
        }
    }
    @Test
    public void TC_09_Valiate_UserName(Method method) {
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 62).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Username",excelConfig.getCellData("Output", 62)));

        //Entering minlength-1, maxlength+ 1
        for (int i = 63; i <= 64; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            addEmployeePage.enterToTextboxByLabel(excelConfig.getCellData("Data", i), "Username");
            Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Username", excelConfig.getCellData("Output", i)));
        }
        //Entering minlegth, minlength+1, maxlength-1, maxlength,special characters,Vietnamese characters with diacritics
        for (int i = 65; i <= 72; i++) {
            ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", i).replace("\n", "<br>"));
            userName = excelConfig.getCellData("Data", i);
            addEmployeePage.enterToTextboxByLabel(userName, "Username");
            ActualValueUserName = addEmployeePage.getValueInTextBoxByLabel("Username");
            Assert.assertEquals(ActualValueUserName, userName);
        }
    }
    @Test
    public void TC_10_Valiate_Password(Method method) {
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(password, "Username");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        //Leave the field blank
        addEmployeePage.clickToSaveButton();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description", 74).replace("\n", "<br>"));
        Assert.assertTrue(addEmployeePage.isErrorMsgDisplayedByLabel("Password",excelConfig.getCellData("Output", 74)));

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
    @Test
    public void TC_11_Valiate_ConfirmPassword(Method method) {
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
    @Test
    public void TC_12_Check_Radio(Method method){
        //select radio
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Select radio");
        addEmployeePage.selectRadioButtonByValue("Enabled");
        Assert.assertEquals(addEmployeePage.isRadioButtonSelectedByValue("Enabled"), true);
        //Unselect radio
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"UnSelect radio");
        addEmployeePage.selectRadioButtonByValue("Disabled");
        Assert.assertEquals(addEmployeePage.isRadioButtonSelectedByValue("Enabled"), false);

    }
    @Test
    public void TC_13_Check_Toogle(Method method){
        //check ON
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Check Toogle ON");
        Assert.assertTrue(addEmployeePage.checkStatusOfToogle());
        //check OFF
        addEmployeePage.clickToggle();
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Check Toogle OFF");
        Assert.assertFalse(addEmployeePage.checkStatusOfToogle());
    }
    @Test
    public void TC_14_Check_Avata(Method method){
        //Chek default value
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Check Default Avata");
        Assert.assertTrue(addEmployeePage.isDefaultAavataDisplay());

        addEmployeePage.uploadMultipleFiles(driver,fileNames);

        //Check upload sucess
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Check Avata After Upload");
        Assert.assertTrue(addEmployeePage.isAavataAfterUploadDisplay());
    }
    @Test
    public void TC_15_Click_Button_Cancel_Check_Save_Unsucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Click_Button_Cancel_Check_Save_Unsucessfully");
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");

        addEmployeePage.clickCancelButton();

        Map<String, String> employeeDB =addEmployeePage.employeeFromDB(employeeId);
        Assert.assertEquals(employeeDB.size(),0);


    }
    @Test
    public void TC_16_Click_Button_Save_Check_Save_sucessfully(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(),"Click_Button_Save_Check_Save_sucessfully");
        employeeId= addEmployeePage.getValueInTextBoxByLabel("Employee Id");
        userName+=employeeId;
        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        //vi password o case tren dang bi bien doi theo data test cua case tren, nen k con la gia tri mac dinh set nhu ban dau, nen dien ca password vao confirm password no se tien ho
        addEmployeePage.enterToTextboxByLabel(password, "Confirm Password");

        addEmployeePage.clickToSaveButton();

        Map<String, String> employeeDB =addEmployeePage.employeeFromDB(employeeId);
        Assert.assertEquals(employeeDB.get("emp_firstname"),firstName);
        Assert.assertEquals(employeeDB.get("emp_middle_name"),middleName);
        Assert.assertEquals(employeeDB.get("emp_lastname"),lastName);


    }

    @AfterMethod
    public void afterMethod() {
        closeBrowser();
    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }

    private String Anh1="Anh1.jpg";
    private String firstName="nguyen", middleName="thi", lastName="toi", employeeId, userName="toi@123",password="automationFc@123", confirmPassword="automationFc@123";

    private ExcelConfig excelConfig;
    private String pre, actualLabelName, expectedLabelName, stepByStep, testCase, expectedResult, otherId, licenseExpiryDate, comment, actualPlaceholder, expectedPlaceholder, actualTextareaPlaceholder, expectedTextareaPlaceholder;
    private String actualColor, expectedColor, ActualValueFirstName,ActualValueMiddleName,ActualValuelastName,ActualValueUserName,ActualValuePassword;
    private List<String> labelOnScreen, ActualListLabelName, ExpectedListLabelName, listDataTest, ActualListPlaceholder, ActualListErrorMessageColor, listExpectedValueDroplist, listActualValueDroplist;
    private String[] fileNames={Anh1};
}
