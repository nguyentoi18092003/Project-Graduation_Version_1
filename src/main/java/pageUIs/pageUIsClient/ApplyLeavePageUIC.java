package pageUIs.pageUIsClient;

public class ApplyLeavePageUIC {
    public static final String BUTTON_BY_NAME = "xpath=//button[contains(.,'%s')]";
    public static final String DYNAMIC_LABEL="xpath=//label[text()='%s']";
    public static final String DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String LEAVE_BALANCE_TEXT ="xpath=//label[text()='Leave Balance']/parent::div/following-sibling::div/p";
    public static final String TIME_INPUT_BY_LABEL ="xpath=//label[text()='%s']/parent::div/following-sibling::div//input[contains(@placeholder,'hh:mm')]";
    public static final String TIME_ICON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i[contains(@class,'oxd-time-input--clock')]";
    public static final String HOUR_UP_BUTTON_BY_LABEL ="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-hour-input-up')]";
    public static final String HOUR_DOWN_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-hour-input-down')]";
    public static final String MINUTE_UP_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-minute-input-up')]";
    public static final String MINUTE_DOWN_BUTTON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='alert']//i[contains(@class,'oxd-time-minute-input-down')]";
    public static final String DYNAMIC_TEXTAREA = "xpath=//textarea";
    public static final String OVERLAP_TITLE = "xpath=//h6[contains(string(),'Overlapping Leave Request(s) Found')]";
}