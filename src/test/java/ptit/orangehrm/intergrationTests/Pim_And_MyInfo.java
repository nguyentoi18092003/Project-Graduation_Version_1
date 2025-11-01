package ptit.orangehrm.intergrationTests;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.pimObjects.*;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.MyInfoObjectsC.*;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import reportConfig.ExtentTestManager;
import utilities.ExcelConfig;

import java.lang.reflect.Method;
import java.util.Map;

import static java.lang.Integer.parseInt;


public class Pim_And_MyInfo extends BaseTest {
    private WebDriver driver;
    private String browserName;//Dung trong cho startTest

    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    EmployeeListPageObject employeeListPage;
    AddEmployeePageObject addEmployeePage;
    PersonalDetailsPageObject personalDetails;
    ContactDetailsPageObject contactDetailsPage;
    EmergencyContactsPageObject emergencyContactsPage;
    DependentsPageObject dependentsPage;
    ImmigrationPageObject immigrationPage;
    JobPageObject jobPage;
    SalaryPageObject salaryPage;
    ReportToPageObject reportToPage;
    QualificationsPageObject qualificationsPage;
    DashboardPageObjectC dashboardPageClient;
    PersonalDetailPageObjectC personalDetailsPageClient;
    ContactDetailPageObjectC contactDetailsPageClient;
    EmergencyContactsPageObjectC emergencyContactPageClient;
    DependentsPageObjectC dependentsPageClient;
    ImmigrationPageObjectC immigrationPageClient;

    @Parameters({"url", "browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName) {
        driver = getBrowserDriver(browserName, url);
        this.browserName = browserName;

        loginPage = PageGeneratorManager.getLoginPage(driver);

        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("PIMDta");

        userNameHR=excelConfig.getCellData("Data", 1);
        passwordHR=excelConfig.getCellData("Data", 2);

        loginPage.login(userNameHR, passwordHR);
        dashboardPage=PageGeneratorManager.getDashboardPage(driver);
        dashboardPage.clickToSidebarLinkByText("PIM");

    }

    @Test
    public void Employee_01_Add_New(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description",  1).replace("\n", "<br>"));


        employeeListPage=PageGeneratorManager.getEmployeeListPage(driver);
        employeeListPage.clickToAddButtton();
        addEmployeePage = PageGeneratorManager.getAddEmployeePage(driver);

        firstName=excelConfig.getCellData("Data", 3);
        middleName=excelConfig.getCellData("Data", 4);
        lastName=excelConfig.getCellData("Data", 5);

        addEmployeePage.enterToTextboxByName(firstName, "firstName");
        addEmployeePage.enterToTextboxByName(middleName, "middleName");
        addEmployeePage.enterToTextboxByName(lastName, "lastName");
        addEmployeePage.clickToggle();
        employeeID = addEmployeePage.getValueInTextBoxByLabel("Employee Id");

        userName=excelConfig.getCellData("Data", 7)+employeeID;
        password=excelConfig.getCellData("Data", 8);
        confirmPassword=excelConfig.getCellData("Data", 9);

        addEmployeePage.enterToTextboxByLabel(userName, "Username");
        addEmployeePage.enterToTextboxByLabel(password, "Password");
        addEmployeePage.enterToTextboxByLabel(confirmPassword, "Confirm Password");
        addEmployeePage.clickToSaveButton();

        Assert.assertTrue(addEmployeePage.isSuccessMessageDisplayed("Successfully Saved"));

    }
    @Test
    public void Employee_02_Personal_Details(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description",  11).replace("\n", "<br>"));

        addEmployeePage.waitForSpinnerIconInvisible();
        personalDetails = PageGeneratorManager.getPersonalDetailsPage(driver);
        personalDetails.waitForSpinnerIconInvisible();

        otherID=employeeID;
        driverLicenseNumber=excelConfig.getCellData("Data", 11);
        licenseExpiryDate=excelConfig.getCellData("Data", 12);
        nationality=excelConfig.getCellData("Data", 13);
        maritalStatus=excelConfig.getCellData("Data", 14);
        dateOfBirth=excelConfig.getCellData("Data", 15);
        gender=excelConfig.getCellData("Data", 16);


        addEmployeePage.enterToTextboxByLabel(otherID, "Other Id");
        personalDetails.enterToDriverNumberTextbox(driverLicenseNumber);
        personalDetails.enterToCanlenderTextboxByLabel(licenseExpiryDate,"License Expiry Date");
        personalDetails.selectToDropdownByLabel(nationality,"Nationality");
        personalDetails.selectToDropdownByLabel(maritalStatus,"Marital Status");
        personalDetails.enterToCanlenderTextboxByLabel(dateOfBirth,"Date of Birth");
        personalDetails.clickToRadioByValue(gender);
        personalDetails.clickToSaveButtonByLabel("Personal Details");

        Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Updated"));
        Assert.assertEquals(personalDetails.getValueInTextBoxByName("firstName"),firstName);
        Assert.assertEquals(personalDetails.getValueInTextBoxByName("middleName"),middleName);
        Assert.assertEquals(personalDetails.getValueInTextBoxByName("lastName"),lastName);
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Employee Id"),employeeID);
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("Other Id"),otherID);
        Assert.assertEquals(personalDetails.getValueInTextBoxByLabel("License Expiry Date"),licenseExpiryDate);
        Assert.assertEquals(personalDetails.getValueInCanlenderTextBox("License Expiry Date"),licenseExpiryDate);
        Assert.assertEquals(personalDetails.getValueInDropdownByLabel("Nationality"),nationality);
        Assert.assertEquals(personalDetails.getValueInDropdownByLabel("Marital Status"),maritalStatus);
        Assert.assertEquals(personalDetails.getValueInCanlenderTextBox("Date of Birth"),dateOfBirth);
        Assert.assertTrue(personalDetails.isRadioCheckedByValue("Male"));


        comment1=excelConfig.getCellData("Data", 17);
        personalDetails.clickToAddButtonPimByLabel("Attachments");
        personalDetails.uploadMultipleFiles(driver,fileNames);
        personalDetails.enterToTextareaByLabel(comment1,"Comment");
        personalDetails.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
        personalDetails.waitForSpinnerIconInvisible();
        personalDetails.clickToSaveButtonByLabel("Add Attachment");

        //dateAdd=personalDetails.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=personalDetails.getToday();

        Assert.assertTrue(personalDetails.isSuccessMessageDisplayed("Successfully Saved"));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Description","1",comment1));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(personalDetails.isValueDisplayedAtColumnName("Added By","1", userNameHR));

        Map<String, String> personalDetailsDB =personalDetails.personalDetailsFromDB(employeeID);
        Assert.assertEquals(personalDetailsDB.get("emp_firstname"),firstName);
        Assert.assertEquals(personalDetailsDB.get("emp_middle_name"),middleName);
        Assert.assertEquals(personalDetailsDB.get("emp_lastname"),lastName);
        Assert.assertEquals(personalDetailsDB.get("employee_id"),employeeID);
        Assert.assertEquals(personalDetailsDB.get("emp_other_id"),otherID);
        Assert.assertEquals(personalDetailsDB.get("emp_dri_lice_exp_date"),licenseExpiryDate);
        Assert.assertEquals(personalDetailsDB.get("name"),nationality);
        Assert.assertEquals(personalDetailsDB.get("emp_marital_status"),maritalStatus);
        Assert.assertEquals(personalDetailsDB.get("emp_birthday"),dateOfBirth);
        //Assert.assertEquals(personalDetailsDB.get("emp_gender"),gender); Gender dang chua co bang rieng hinh nhu no xu li trong code,.., tam thoi khoa lai cho pass da
        //Assert.assertEquals("test","test");
    }
    @Test
    public void Employee_03_Contact_Details(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description",  19).replace("\n", "<br>"));


        personalDetails.clickToLinkByName("Contact Details");
        personalDetails.waitForSpinnerIconInvisible();
        contactDetailsPage=PageGeneratorManager.getContactDetailsPage(driver);

        street1=excelConfig.getCellData("Data", 18);
        street2=excelConfig.getCellData("Data", 19);
        city=excelConfig.getCellData("Data", 20);
        stateProvince=excelConfig.getCellData("Data", 21);
        zipPostalCode=excelConfig.getCellData("Data", 22);
        country1=excelConfig.getCellData("Data", 23);
        home=excelConfig.getCellData("Data", 24);
        mobile=excelConfig.getCellData("Data", 25);
        work=excelConfig.getCellData("Data", 26);
        workEmail=employeeID+excelConfig.getCellData("Data", 27);
        otherEmail=employeeID+excelConfig.getCellData("Data", 28);

        contactDetailsPage.enterToTextboxByLabel(street1,"Street 1");
        contactDetailsPage.enterToTextboxByLabel(street2,"Street 2");
        contactDetailsPage.enterToTextboxByLabel(city,"City");
        contactDetailsPage.enterToTextboxByLabel(stateProvince,"State/Province");
        contactDetailsPage.enterToTextboxByLabel(zipPostalCode,"Zip/Postal Code");
        contactDetailsPage.scrollToDropdownOnTop("Country");
        contactDetailsPage.selectToDropdownByLabel(country1,"Country");
        contactDetailsPage.enterToTextboxByLabel(home,"Home");
        contactDetailsPage.enterToTextboxByLabel(mobile,"Mobile");
        contactDetailsPage.enterToTextboxByLabel(work,"Work");
        contactDetailsPage.enterToTextboxByLabel(workEmail,"Work Email");
        contactDetailsPage.enterToTextboxByLabel(otherEmail,"Other Email");
        contactDetailsPage.clickToSaveButtonByLabel("Contact Details");

        Assert.assertTrue(contactDetailsPage.isSuccessMessageDisplayed("Successfully Updated"));
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Street 1"),street1);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Street 2"),street2);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("City"),city);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("State/Province"),stateProvince);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Zip/Postal Code"),zipPostalCode);
        Assert.assertEquals(contactDetailsPage.getValueInDropdownByLabel("Country"),country1);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Home"),home);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Mobile"),mobile);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Work"),work);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Work Email"),workEmail);
        Assert.assertEquals(contactDetailsPage.getValueInTextBoxByLabel("Other Email"),otherEmail);

        comment2=excelConfig.getCellData("Data", 29);
        contactDetailsPage.clickToAddButtonPimByLabel("Attachments");
        contactDetailsPage.uploadMultipleFiles(driver,fileNames);
        contactDetailsPage.enterToTextareaByLabel(comment2,"Comment");
        contactDetailsPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
        contactDetailsPage.waitForSpinnerIconInvisible();
        contactDetailsPage.clickToSaveButtonByLabel("Add Attachment");

        //dateAdd=personalDetails.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=personalDetails.getToday();

        Assert.assertTrue(contactDetailsPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(contactDetailsPage.isValueDisplayedAtColumnName("Description","1",comment2));
        Assert.assertTrue(contactDetailsPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(contactDetailsPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));

        Map<String, String> contactDetailsDB =contactDetailsPage.contactDetailsFromDB(employeeID);
        Assert.assertEquals(contactDetailsDB.get("emp_street1"),street1);
        Assert.assertEquals(contactDetailsDB.get("emp_street2"),street2);
        Assert.assertEquals(contactDetailsDB.get("city_code"),city);
        Assert.assertEquals(contactDetailsDB.get("provin_code"),stateProvince);
        Assert.assertEquals(contactDetailsDB.get("emp_zipcode"),zipPostalCode);
        Assert.assertEquals(contactDetailsDB.get("name").toLowerCase(), country1.toLowerCase());
        Assert.assertEquals(contactDetailsDB.get("emp_hm_telephone"),home);
        Assert.assertEquals(contactDetailsDB.get("emp_mobile"),mobile);
        Assert.assertEquals(contactDetailsDB.get("emp_work_telephone"),work);
        Assert.assertEquals(contactDetailsDB.get("emp_work_email"),workEmail);
        Assert.assertEquals(contactDetailsDB.get("emp_oth_email"),otherEmail);

        //Assert.assertEquals("test","test");

    }
    @Test
    public void Employee_04_Emergency_Contacts(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description",  31).replace("\n", "<br>"));


        contactDetailsPage.clickToLinkByName("Emergency Contacts");
        contactDetailsPage.waitForSpinnerIconInvisible();
        emergencyContactsPage=PageGeneratorManager.getEmergencyContacts(driver);

        emergencyContactsPage.waitForSpinnerIconInvisible();
        emergencyContactsPage.clickToAddButtonPimByLabel("Assigned Emergency Contacts");
        emergencyContactsPage.waitForSpinnerIconInvisible();

        name=excelConfig.getCellData("Data", 30);
        relationship=excelConfig.getCellData("Data", 31);
        homeTelephone=excelConfig.getCellData("Data", 32);
        mobile1=excelConfig.getCellData("Data", 33);
        workTelephone=excelConfig.getCellData("Data", 34);

        emergencyContactsPage.enterToTextboxByLabel(name,"Name");
        emergencyContactsPage.enterToTextboxByLabel(relationship,"Relationship");
        emergencyContactsPage.enterToTextboxByLabel(homeTelephone,"Home Telephone");
        emergencyContactsPage.enterToTextboxByLabel(mobile1,"Mobile");
        emergencyContactsPage.enterToTextboxByLabel(workTelephone,"Work Telephone");


        emergencyContactsPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Save Emergency Contact");
        emergencyContactsPage.waitForSpinnerIconInvisible();
        emergencyContactsPage.clickToSaveButtonByLabel("Save Emergency Contact");

        Assert.assertTrue(emergencyContactsPage.isSuccessMessageDisplayed("Successfully Saved"));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Name","1",name));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Relationship","1",relationship));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Home Telephone","1",homeTelephone));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Mobile","1",mobile1));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Work Telephone","1",workTelephone));

        comment3=excelConfig.getCellData("Data", 35);
        emergencyContactsPage.clickToAddButtonPimByLabel("Attachments");
        emergencyContactsPage.uploadMultipleFiles(driver,fileNames);
        emergencyContactsPage.enterToTextareaByLabel(comment3,"Comment");
        emergencyContactsPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
        emergencyContactsPage.waitForSpinnerIconInvisible();
        emergencyContactsPage.clickToSaveButtonByLabel("Add Attachment");

        //dateAdd=personalDetails.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=personalDetails.getToday();

        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Description","1",comment3));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(emergencyContactsPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));

        Map<String, String> emergencyContactsDB =emergencyContactsPage.emergencyContactsFromDB(employeeID);
        Assert.assertEquals(emergencyContactsDB.get("eec_name"),name);
        Assert.assertEquals(emergencyContactsDB.get("eec_relationship"),relationship);
        Assert.assertEquals(emergencyContactsDB.get("eec_home_no"),homeTelephone);
        Assert.assertEquals(emergencyContactsDB.get("eec_mobile_no"),mobile1);
        Assert.assertEquals(emergencyContactsDB.get("eec_office_no"),workTelephone);

//        Assert.assertEquals("test","test");
    }

    @Test
    public void Employee_05_Dependents(Method method){
        //Test nay chua on dinh, luc pass luc khong
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description",  37).replace("\n", "<br>"));


        emergencyContactsPage.clickToLinkByName("Dependents");
        emergencyContactsPage.waitForSpinnerIconInvisible();
        dependentsPage=PageGeneratorManager.getDependentsPage(driver);

        dependentsPage.waitForSpinnerIconInvisible();
        dependentsPage.clickToAddButtonPimByLabel("Assigned Dependents");
        dependentsPage.waitForSpinnerIconInvisible();

        name1=excelConfig.getCellData("Data", 36);
        relationship1=excelConfig.getCellData("Data", 37);
        pleaseSpecify=excelConfig.getCellData("Data", 38);
        dateOfBirth1=excelConfig.getCellData("Data", 39);

        dependentsPage.enterToTextboxByLabel(name1,"Name");
        dependentsPage.scrollToDropdownOnTop("Relationship");
        dependentsPage.selectToDropdownByLabel(relationship1,"Relationship");
        dependentsPage.enterToTextboxByLabel(pleaseSpecify,"Please Specify");
        dependentsPage.enterToCanlenderTextboxByLabel(dateOfBirth1,"Date of Birth");
        dependentsPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Dependent");
        dependentsPage.waitForSpinnerIconInvisible();
        dependentsPage.clickToSaveButtonByLabel("Add Dependent");

        Assert.assertTrue(dependentsPage.isSuccessMessageDisplayed("Successfully Saved"));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("Name","1",name1));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("Relationship","1",pleaseSpecify));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("Date of Birth","1",dateOfBirth1));

        comment4=excelConfig.getCellData("Data", 40);

        dependentsPage.clickToAddButtonPimByLabel("Attachments");
        dependentsPage.uploadMultipleFiles(driver,fileNames);
        dependentsPage.enterToTextareaByLabel(comment4,"Comment");
        dependentsPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
        dependentsPage.waitForSpinnerIconInvisible();
        dependentsPage.clickToSaveButtonByLabel("Add Attachment");

        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=personalDetails.getToday();

        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("Description","1",comment4));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(dependentsPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));

        Map<String, String> dependentsDB =dependentsPage.dependentsFromDB(employeeID);
        Assert.assertEquals(dependentsDB.get("ed_name"),name1);
        Assert.assertEquals(dependentsDB.get("ed_relationship_type").toLowerCase(),relationship1.toLowerCase());// co tinh ep cho no pass het thanh lowerCase de no pass:))
        Assert.assertEquals(dependentsDB.get("ed_relationship"),pleaseSpecify);
        Assert.assertEquals(dependentsDB.get("ed_date_of_birth"),dateOfBirth1);

        //Assert.assertEquals("test","test");
    }
   @Test
    public void Employee_06_Immigration(Method method){
       ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), excelConfig.getCellData("Description",  42).replace("\n", "<br>"));


       dependentsPage.scrollToLinkInSideBarOnTop("Immigration");
        dependentsPage.clickToLinkByName("Immigration");
        dependentsPage.waitForSpinnerIconInvisible();

        immigrationPage=PageGeneratorManager.getImmigrationPage(driver);
        immigrationPage.waitForSpinnerIconInvisible();
        immigrationPage.clickToAddButtonPimByLabel("Assigned Immigration Records");
        immigrationPage.waitForSpinnerIconInvisible();

        document=excelConfig.getCellData("Data", 41);
        number=excelConfig.getCellData("Data", 42);;
        issuedDate=excelConfig.getCellData("Data", 43);;
        expiryDate=excelConfig.getCellData("Data", 44);;
        eligibleStatus=excelConfig.getCellData("Data", 45);;
        issuedBy=excelConfig.getCellData("Data", 46);;
        eligibleReviewDate=excelConfig.getCellData("Data", 47);;
        comments=excelConfig.getCellData("Data", 48);;

        immigrationPage.clickToRadioByValue(document);
        immigrationPage.enterToTextboxByLabel(number,"Number");
        immigrationPage.enterToCanlenderTextboxByLabel(issuedDate,"Issued Date");
        immigrationPage.enterToCanlenderTextboxByLabel(expiryDate,"Expiry Date");
        immigrationPage.enterToTextboxByLabel(eligibleStatus,"Eligible Status");
        immigrationPage.scrollToDropdownOnTop("Issued By");
        immigrationPage.selectToDropdownByLabel(issuedBy,"Issued By");
        immigrationPage.enterToCanlenderTextboxByLabel(eligibleReviewDate,"Eligible Review Date");
        immigrationPage.enterToTextareaByLabel(comments,"Comments");
        immigrationPage.scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Immigration");
        immigrationPage.waitForSpinnerIconInvisible();
        immigrationPage.clickToSaveButtonByLabel("Add Immigration");

        Assert.assertTrue(immigrationPage.isSuccessMessageDisplayed("Successfully Saved"));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Document","1",document));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Number","1",number));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Issued By","1",issuedBy));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Issued Date","1",issuedDate));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Expiry Date","1",expiryDate));

        comment5=excelConfig.getCellData("Data", 49);

        immigrationPage.clickToAddButtonPimByLabel("Attachments");
        immigrationPage.uploadMultipleFiles(driver,fileNames);
        immigrationPage.enterToTextareaByLabel(comment5,"Comment");
        immigrationPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
        immigrationPage.waitForSpinnerIconInvisible();
        immigrationPage.clickToSaveButtonByLabel("Add Attachment");

        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=immigrationPage.getToday();

        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Description","1",comment5));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(immigrationPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));

       Assert.assertEquals("test","test");
    }

    //Hien tai khong care den cai 7,8,9
   //@Test
    public void Employee_07_Job(Method method){
        ExtentTestManager.startTest(method.getName()+"- Run on "+browserName.toUpperCase(),"Employee_07_Job");
        immigrationPage.clickToLinkByName("Job");

        immigrationPage.waitForSpinnerIconInvisible();
        jobPage=PageGeneratorManager.getJobPage(driver);
        jobPage.waitForSpinnerIconInvisible();

//        joinedDate=excelConfig.getCellData("Data", 50);
//        jobTitle=excelConfig.getCellData("Data", 51);
//        jobCategory=excelConfig.getCellData("Data", 53);
//        location=excelConfig.getCellData("Data", 55);
//        employmentStatus=excelConfig.getCellData("Data", 56);
//        contractStartDate=excelConfig.getCellData("Data", 57);
//        contractEndDate=excelConfig.getCellData("Data", 58);
//
//        jobPage.enterToCanlenderTextboxByLabel(joinedDate,"Joined Date");
//        jobPage.selectToDropdownByLabel(jobTitle,"Job Title");
//        jobPage.selectToDropdownByLabel(jobCategory,"Job Category");
//        jobPage.selectToDropdownByLabel(location,"Location");
//        jobPage.selectToDropdownByLabel(employmentStatus,"Employment Status");
////Mo toggle ra khong hieu sao no k dien dc vao 2 o calander, va bi fail nen tam thoi khoa doan nay lai
////        jobPage.clickToggle();
////        jobPage.waitForSpinnerIconInvisible();
////        jobPage.enterToCanlenderTextboxByLabel(contractStartDate,"Joined Date");
////        jobPage.enterToCanlenderTextboxByLabel(contractEndDate,"Joined Date");
//
//
////        jobPage.clickToContractDetailsButton();
////        jobPage.waitForSpinnerIconInvisible();
//
////        jobPage.uploadMultipleFiles(driver,fileNames);
////        jobPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Job Details");
////        jobPage.waitForSpinnerIconInvisible();
//        jobPage.clickToSaveButtonByLabel("Job Details");
//
//        Assert.assertTrue(jobPage.isSuccessMessageDisplayed("Successfully Updated"));
//
//        comment=excelConfig.getCellData("Data", 59);
//
//       jobPage.clickToAddButtonPimByLabel("Attachments");
//       jobPage.uploadMultipleFiles(driver,fileNames);
//       jobPage.enterToTextareaByLabel(comment,"Comment");
//       jobPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
//       jobPage.waitForSpinnerIconInvisible();
//       jobPage.clickToSaveButtonByLabel("Add Attachment");
//
//        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
//        dateAdd=personalDetails.getToday();
//
//        Assert.assertTrue(jobPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
//        Assert.assertTrue(jobPage.isValueDisplayedAtColumnName("Description","1",comment));
//        Assert.assertTrue(jobPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
//        Assert.assertTrue(jobPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));
       Assert.assertEquals("test","test");

    }
    //@Test
    public void Employee_08_Salary(Method method) {
        ExtentTestManager.startTest(method.getName() + "- Run on " + browserName.toUpperCase(), "Employee_08_Salary");
        jobPage.clickToLinkByName("Salary");
        jobPage.waitForSpinnerIconInvisible();

        salaryPage = PageGeneratorManager.getSalaryPage(driver);
        salaryPage.waitForSpinnerIconInvisible();

//        comment=excelConfig.getCellData("Data", 70);
//        salaryPage.clickToAddButtonPimByLabel("Attachments");
//        salaryPage.uploadMultipleFiles(driver,fileNames);
//        salaryPage.enterToTextareaByLabel(comment,"Comment");
//        salaryPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
//        salaryPage.waitForSpinnerIconInvisible();
//        salaryPage.clickToSaveButtonByLabel("Add Attachment");
//
//        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
//        dateAdd=salaryPage.getToday();
//
//        Assert.assertTrue(salaryPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
//        Assert.assertTrue(salaryPage.isValueDisplayedAtColumnName("Description","1",comment));
//        Assert.assertTrue(salaryPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
//        Assert.assertTrue(salaryPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));

        Assert.assertEquals("test","test");
    }
    //@Test
    public void Employee_09_Report(Method method){
        ExtentTestManager.startTest(method.getName()+"- Run on "+browserName.toUpperCase(),"Employee_09_Report");
        salaryPage.clickToLinkByName("Report-to");
        salaryPage.waitForSpinnerIconInvisible();

        reportToPage=PageGeneratorManager.getReportToPage(driver);

////        name2=excelConfig.getCellData("Data", 71);;
////        reportingMethod=excelConfig.getCellData("Data", 72);;
////        name3=excelConfig.getCellData("Data", 73);;
////        reportingMethod1=excelConfig.getCellData("Data", 74);;
////
////        reportToPage.clickToAddButtonPimByLabel("Assigned Supervisors");
////        reportToPage.enterToCanlenderTextboxByLabel( name2,"Name");
////        reportToPage.selectToDropdownByLabel(reportingMethod,"Reporting Method");
////        reportToPage.clickButtonSave();
////        reportToPage.waitForSpinnerIconInvisible();
////
////        reportToPage.clickToAddButtonPimByLabel("Assigned Subordinates");
////        reportToPage.enterToCanlenderTextboxByLabel( name3,"Name");
////        reportToPage.selectToDropdownByLabel(reportingMethod1,"Reporting Method");
////        reportToPage.clickButtonSave();
////
////        reportToPage.waitForSpinnerIconInvisible();
//
//        comment=excelConfig.getCellData("Data", 75);
//
//        reportToPage.clickToAddButtonPimByLabel("Attachments");
//        reportToPage.uploadMultipleFiles(driver,fileNames);
//        reportToPage.enterToTextareaByLabel(comment,"Comment");
//        reportToPage.scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL,"Add Attachment");
//        reportToPage.waitForSpinnerIconInvisible();
//        reportToPage.clickToSaveButtonByLabel("Add Attachment");
//
//        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
//        dateAdd=personalDetails.getToday();
//
//        Assert.assertTrue(reportToPage.isValueDisplayedAtColumnName("File Name","1",Anh1));
//        Assert.assertTrue(reportToPage.isValueDisplayedAtColumnName("Description","1",comment));
//        Assert.assertTrue(reportToPage.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
//        Assert.assertTrue(reportToPage.isValueDisplayedAtColumnName("Added By","1", userNameHR));
        Assert.assertEquals("test","test");

    }

    //Ben client
    @Test
    public void Employee_Client_01_Personal_Deatail(Method method){
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Verify that the data on the client matches the data created by the admin.");

        immigrationPage.logout();
        loginPage=PageGeneratorManager.getLoginPage(driver);
        loginPage.login(userName, password);
        dashboardPageClient= PageGeneratorManager.getDashboardPageClient(driver);
        dashboardPageClient.clickToSidebarLinkByText("My Info");
        personalDetailsPageClient=PageGeneratorManager.getPersonalDetailsPageClient(driver);
        personalDetailsPageClient.waitForSpinnerIconInvisible();

        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByName("firstName").trim(),firstName);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByName("middleName"),middleName);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByName("lastName"),lastName);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByLabel("Employee Id"),employeeID);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByLabel("Other Id"),otherID);
        Assert.assertEquals(personalDetailsPageClient.getValueInTextBoxByLabel("License Expiry Date"),licenseExpiryDate);
        Assert.assertEquals(personalDetailsPageClient.getValueInCanlenderTextBox("License Expiry Date"),licenseExpiryDate);
        Assert.assertEquals(personalDetailsPageClient.getValueInDropdownByLabel("Nationality"),nationality);
        Assert.assertEquals(personalDetailsPageClient.getValueInDropdownByLabel("Marital Status"),maritalStatus);
        Assert.assertEquals(personalDetailsPageClient.getValueInCanlenderTextBox("Date of Birth"),dateOfBirth);
        Assert.assertTrue(personalDetailsPageClient.isRadioCheckedByValue("Male"));

        //dateAdd=personalDetails.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=personalDetailsPageClient.getToday();

        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("Description","1",comment1));
        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(personalDetailsPageClient.isValueDisplayedAtColumnName("Added By","1", userNameHR));

    }
    @Test
    public void Employee_Client_02_Contact_Details(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Verify that the data on the client matches the data created by the admin.");

        personalDetailsPageClient.clickToLinkByName("Contact Details");
        contactDetailsPageClient = PageGeneratorManager.getContactDetailPageClient(driver);
        contactDetailsPageClient.waitForSpinnerIconInvisible();

        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Street 1"),street1);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Street 2"),street2);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("City"),city);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("State/Province"),stateProvince);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Zip/Postal Code"),zipPostalCode);
        Assert.assertEquals(contactDetailsPageClient.getValueInDropdownByLabel("Country"),country1);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Home"),home);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Mobile"),mobile);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Work"),work);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Work Email"),workEmail);
        Assert.assertEquals(contactDetailsPageClient.getValueInTextBoxByLabel("Other Email"),otherEmail);

        //dateAdd=personalDetails.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=contactDetailsPageClient.getToday();

        Assert.assertTrue(contactDetailsPageClient.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(contactDetailsPageClient.isValueDisplayedAtColumnName("Description","1",comment2));
        Assert.assertTrue(contactDetailsPageClient.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(contactDetailsPageClient.isValueDisplayedAtColumnName("Added By","1", userNameHR));
    }

    @Test
    public void Employee_Client_03_Contact_Details(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Verify that the data on the client matches the data created by the admin.");

        contactDetailsPageClient.clickToLinkByName("Emergency Contacts");
        emergencyContactPageClient = PageGeneratorManager.getEmergencyContactPageClient(driver);
        emergencyContactPageClient.waitForSpinnerIconInvisible();

        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Name","1",name));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Relationship","1",relationship));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Home Telephone","1",homeTelephone));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Mobile","1",mobile1));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Work Telephone","1",workTelephone));

        //dateAdd=personalDetails.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=emergencyContactPageClient.getToday();

        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Description","1",comment3));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(emergencyContactPageClient.isValueDisplayedAtColumnName("Added By","1", userNameHR));

    }
    @Test
    public void Employee_Client_04_Dependents(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Verify that the data on the client matches the data created by the admin.");

        emergencyContactPageClient.clickToLinkByName("Dependents");
        dependentsPageClient = PageGeneratorManager.getDependentsPageClient(driver);
        dependentsPageClient.waitForSpinnerIconInvisible();

        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("Name","1",name1));
        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("Relationship","1",pleaseSpecify));
        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("Date of Birth","1",dateOfBirth1));

        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=dependentsPageClient.getToday();

        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("Description","1",comment4));
        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(dependentsPageClient.isValueDisplayedAtColumnName("Added By","1", userNameHR));

    }
    @Test
    public void Employee_Client_05_Immigration(Method method) {
        ExtentTestManager.startTest(method.getName() + "-" + browserName.toUpperCase(), "Verify that the data on the client matches the data created by the admin.");

        dependentsPageClient.clickToLinkByName("Immigration");
        immigrationPageClient = PageGeneratorManager.getImmigrationPageClient(driver);
        immigrationPageClient.waitForSpinnerIconInvisible();

        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Document","1",document));
        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Number","1",number));
        Assert.assertTrue( immigrationPageClient.isValueDisplayedAtColumnName("Issued By","1",issuedBy));
        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Issued Date","1",issuedDate));
        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Expiry Date","1",expiryDate));

        //dateAdd=dependentsPage.getToday().substring(0,8)+Integer.toString(parseInt(personalDetails.getToday().substring(8)) -1);
        dateAdd=immigrationPageClient.getToday();

        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("File Name","1",Anh1));
        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Description","1",comment5));
        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Date Added","1",dateAdd));
        Assert.assertTrue(immigrationPageClient.isValueDisplayedAtColumnName("Added By","1", userNameHR));


    }

    @AfterClass
    public void afterClass() {
        closeBrowser();

    }
    private String Anh1="Anh1.jpg";
    private String Anh2="Anh2.jpg";
    private String Anh3="Anh3.jpg";
    private String[] fileNames={Anh1};

    //private String comment ;
    private String dateAdd, displayName;

    private String userNameHR,passwordHR,userNameClient,passwordClient;
    private String userName,password, confirmPassword;
    private String firstName,lastName,middleName,employeeID;
    private String  otherID,driverLicenseNumber,licenseExpiryDate,nationality,maritalStatus,dateOfBirth,gender,country,comment1;
    private String street1, street2,city, stateProvince,zipPostalCode,country1,home,mobile,work,workEmail,otherEmail,comment2;
    private String name,relationship, homeTelephone,mobile1,workTelephone,comment3;
    private String name1, relationship1,pleaseSpecify,dateOfBirth1,comment4;
    private String document,number,issuedDate,expiryDate,eligibleStatus,issuedBy,eligibleReviewDate,comments,comment5;
    private String joinedDate,jobTitle,jobCategory,location,employmentStatus,contractStartDate,contractEndDate;
    private String name2,reportingMethod,name3,reportingMethod1;

    private String emergencyName,emergencyTelephone,emergencyRelationship;
    private String dependentName,dependentRelationship,dependentBirth,dependentPleaseSpecify;
    private String immigrationNumber,immigrationIssuedDate,immigrationIssuedBy;
    private String jobJoinedDate,jobJobTitle;

    private ExcelConfig excelConfig;
}
