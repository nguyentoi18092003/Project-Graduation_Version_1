package pageUIs.pageUIsAdmin.recruitmentUIs;

public class ScheduleInterviewPageUI {
    public static final String APPLICATION_STATUS = "xpath=//p[contains(.,'Status:')]";
    public static final String TIME_INPUT_BY_LABEL ="xpath=//label[text()='̀%s']/parent::div/following-sibling::div//input[contains(@placeholder,'hh:mm')]";
    public static final String DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL = "xpath=//label[text()='Date']/parent::div/following-sibling::div//i";
    public static final String DYNAMIC_LABEL="xpath=//label[text()='%s']";
    public static final String TIME_ICON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i[contains(@class,'oxd-time-input--clock')]";
    public static final String HOUR_UP_BUTTON_BY_LABEL ="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-hour-input-up')]";
    public static final String HOUR_DOWN_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-hour-input-down')]";
    public static final String MINUTE_UP_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-minute-input-up')]";
    public static final String MINUTE_DOWN_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-minute-input-down')]";

}
