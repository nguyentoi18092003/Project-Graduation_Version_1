package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectsAdmin.adminObjects.AdminPageObject;
import pageObjects.pageObjectsAdmin.dashboardObjects.DashboardPageObject;
import pageObjects.pageObjectCommon.loginObjects.LoginPageObject;
import pageObjects.pageObjectsAdmin.leaveObjects.*;
import pageObjects.pageObjectsAdmin.pimObjects.*;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageObjects.pageObjectsAdmin.recruitmentObjects.*;
import pageObjects.pageObjectsAdmin.timeObjects.*;
import pageObjects.pageObjectsClient.DashboardObjectC.DashboardPageObjectC;
import pageObjects.pageObjectsClient.LeaveObjectC.*;
import pageObjects.pageObjectsClient.MyInfoObjectsC.*;
import pageObjects.pageObjectsClient.timeObject.EditTimesheetPageObjectC;
import pageObjects.pageObjectsClient.timeObject.MyTimesheetPageObjectC;
import pageObjects.pageObjectsClient.timeObject.PunchInPageObjectC;
import pageObjects.pageObjectsClient.timeObject.PunchOutPageObjectC;

public class PageGeneratorManager {

    public static AddEmployeePageObject getAddEmployeePage(WebDriver driver){
        return new AddEmployeePageObject(driver);
    }

    public static ContactDetailsPageObject getContactDetailsPage(WebDriver driver){
        return new ContactDetailsPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }
    public static DependentsPageObject getDependentsPage(WebDriver driver){
        return new DependentsPageObject(driver);
    }
    public static EmergencyContactsPageObject getEmergencyContactsPage(WebDriver driver){
        return new EmergencyContactsPageObject(driver);
    }
    public static EmergencyContactsPageObject getEmergencyContacts(WebDriver driver){
        return new EmergencyContactsPageObject(driver);
    }
    public static EmployeeListPageObject getEmployeeListPage(WebDriver driver){
        return new EmployeeListPageObject(driver);
    }
    public static ImmigrationPageObject getImmigrationPage(WebDriver driver){
        return new ImmigrationPageObject(driver);
    }
    public static JobPageObject getJobPage(WebDriver driver){
        return new JobPageObject(driver);
    }
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static PersonalDetailsPageObject getPersonalDetailsPage(WebDriver driver){
        return new PersonalDetailsPageObject(driver);
    }
    public static QualificationsPageObject getQualificationsPage(WebDriver driver){
        return new QualificationsPageObject(driver);
    }
    public static ReportToPageObject getReportToPage(WebDriver driver){
        return new ReportToPageObject(driver);
    }
    public static SalaryPageObject getSalaryPage(WebDriver driver){
        return new SalaryPageObject(driver);
    }
    public static SidebarPageObject getSidebarObject(WebDriver driver){
        return new SidebarPageObject(driver);
    }
    public static AdminPageObject getAdminPageObject(WebDriver driver){
        return new AdminPageObject(driver);
    }
    public static EmploymentStatusPageObject getEmploymentStatusPage(WebDriver driver){
        return new EmploymentStatusPageObject(driver);
    }

    public static JobCategoriesPageObject getJobCategoriesPage(WebDriver driver){
        return new JobCategoriesPageObject(driver);
    }
    public static JobTitlesPageObject getJobTitlesPage(WebDriver driver){
        return new  JobTitlesPageObject(driver);
    }
    public static PayGradesPageObject getPayGradesPage (WebDriver driver){
        return new PayGradesPageObject(driver);
    }

    public static DashboardPageObjectC getDashboardPageClient(WebDriver driver){
        return new DashboardPageObjectC(driver);
    }
    public static PersonalDetailPageObjectC getPersonalDetailsPageClient(WebDriver driver){
        return new PersonalDetailPageObjectC(driver);
    }
    public static ContactDetailPageObjectC getContactDetailPageClient(WebDriver driver){
        return new ContactDetailPageObjectC(driver);

    }
    public static EmergencyContactsPageObjectC getEmergencyContactPageClient(WebDriver driver){
        return new EmergencyContactsPageObjectC(driver);
    }
    public static DependentsPageObjectC getDependentsPageClient(WebDriver driver){
        return new DependentsPageObjectC(driver);
    }
    public static ImmigrationPageObjectC getImmigrationPageClient(WebDriver driver){
        return new ImmigrationPageObjectC(driver);
    }
    public static DataImportPageObject getDataImportPageObject(WebDriver driver){
        return new DataImportPageObject(driver);
    }
    public static MyTimesheetPageObjectC getMyTimesheetPageObjectClient(WebDriver driver){
        return new MyTimesheetPageObjectC(driver);
    }
    public static MyTimesheetPageObject getMyTimesheetPageObject(WebDriver driver){
        return new MyTimesheetPageObject(driver);
    }
    public static EditTimesheetPageObjectC getEditTimesheetPageObjectC(WebDriver driver){
        return new EditTimesheetPageObjectC(driver);
    }
    public static PunchInPageObject getPunchInPageObject(WebDriver driver){
        return new PunchInPageObject(driver);
    }
    public static PunchOutPageObject getPunchOutPageObject(WebDriver driver){
        return new PunchOutPageObject(driver);
    }
    public static EditPunchInOutPageObject getEditPunchInOutPageObject(WebDriver driver){
        return new EditPunchInOutPageObject(driver);
    }
    public static EditTimesheetPageObject getEditTimesheetPageObject(WebDriver driver){
        return new EditTimesheetPageObject(driver);
    }
    public static PunchInPageObjectC getPunchInPageObjectClient(WebDriver driver){
        return new PunchInPageObjectC(driver);
    }
    public static PunchOutPageObjectC getPunchOutPageObjectClient(WebDriver driver){
        return new PunchOutPageObjectC(driver);
    }
    public static ApplyLeavePageObjectC getApplyLeavePageObjectClient(WebDriver driver){
        return new ApplyLeavePageObjectC(driver);
    }

    public static MyLeaveListPageObjectC getMyLeaveListPageObjectClient(WebDriver driver){
        return new MyLeaveListPageObjectC(driver);
    }

    public static LeaveListPageObject getLeaveListPageObject(WebDriver driver){
        return new LeaveListPageObject(driver);
    }

    public static EditVacancyPageObject getEditVacancyPageObject(WebDriver driver){
        return new EditVacancyPageObject(driver);
    }

    public static VacancyListPageObject getVacancyListPageObject(WebDriver driver){
        return new VacancyListPageObject(driver);
    }

    public static AddCandidatePageObject getAddCandidatePageObject(WebDriver driver){
        return new AddCandidatePageObject(driver);
    }
    public static HireCandidatePageObject getHireCandidatePageObject(WebDriver driver){
        return new HireCandidatePageObject(driver);
    }
    public static MarkInterviewResultPageObject getMarkInterviewResultPageObject(WebDriver driver){
        return new MarkInterviewResultPageObject(driver);
    }
    public static OfferDeclinedPageObject getOfferDeclinedPageObject(WebDriver driver){
        return new OfferDeclinedPageObject(driver);
    }
    public static OfferJobPageObject getOfferJobPageObject(WebDriver driver){
        return new OfferJobPageObject(driver);
    }
    public static RejectCandidatePageObject getRejectCandidatePageObject(WebDriver driver){
        return new RejectCandidatePageObject(driver);
    }
    public static ScheduleInterviewPageObject getScheduleInterviewPageObject(WebDriver driver){
        return new ScheduleInterviewPageObject(driver);
    }
    public static ShortlistPageObject getShortlistPageObject(WebDriver driver){
        return new ShortlistPageObject(driver);
    }
    public static CandidateListPageObject getCandidateListPageObject(WebDriver driver){
        return new CandidateListPageObject(driver);
    }
}
