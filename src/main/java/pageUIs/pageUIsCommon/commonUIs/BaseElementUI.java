package pageUIs.pageUIsCommon.commonUIs;

public class BaseElementUI {
    public static String DYNAMIC_NAME_LABEL ="xpath=//label[contains(@class, 'oxd-label')]";
    public static String TEXTBOX_PLACEHOLDER ="xpath=//input[@placeholder]";
    public static String TEXTAREA_PLACEHOLDER="xpath=//textarea[@placeholder]";
    public static final String ERROR_MESSAGE ="css=.oxd-input-field-error-message";
    public static String ADD_BUTTON="xpath=//button[text()=' Add ']";
    public static final String DYNAMIC_ERROR_MESSAGE="xpath=//span[contains(@class,'oxd-input-field-error-message') and text()='%s']";
    public static final String DYNAMIC_VALUE_IN_DROPLIST ="xpath=//div[@role='option']/span";
    public static final String DYNAMIC_LABEL="xpath=//label[text()='License Expiry Date']";
    public static final String DYNAMIC_DAY_IN_CALENDER="xpath=//div[@class='oxd-calendar-date' and text()='%s']";

    public static final String AVATA="xpath=//img[@alt='profile picture']";
    public static final String LOGOUT="xpath=//a[@role='menuitem' and text()='Logout']";
    public static final String DYNAMIC_TEXTBOX_BY_NAME = "css=input[name='%s']";
    public static final String DYNAMIC_TEXTBOX_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DYNAMIC_TEXTAREA_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//textarea";
    public static final String DYNAMIC_RADIO_BUTTON_BY_TEXT="xpath=//label[text()='%s']/input";
    public static final String DISPLAY_NAME="xpath=//p[@class='oxd-userdropdown-name']";
    public static final String DYNAMIC_CALENDER_BUTTON="xpath=//div[text()='%s']";
    public static final String DYNAMIC_TODAY="xpath=//div[@class='oxd-calendar-date --selected --today' and text()='%s']";
    public static final String DYNAMIC_DAY_IN_CALENDER_AFTER_SELECTED="xpath=//div[@class='oxd-calendar-date --selected' and text()='%s']";

    public static final String TOGGLE ="xpath=//input[@type='checkbox']";
    public static final String GENDER_RADIO="xpath=//label[text()='%s']/input[@type='radio']";
    public static final String DYNAMIC_ADD_BUTTON_PIM_BY_LABEL = "xpath=//h6[text()='%s']/following-sibling::button";
    public static final String DYNAMIC_ADD_BUTTON_ADMIN_BY_LABEL = "xpath=//h6[text()='%s']/following-sibling::div/button";
    public static final String DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DYNAMIC_ICON_CANLENDER_TEXTBOX_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String DYNAMIC_DROPDOWN_PARENT_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='listbox']/div[@role='option']";
    public static final String DYNAMIC_VALUE_ITEM_DROPDOWN_BY_NAME="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@role='listbox']/div/span[text()='%s']";
    public static final String DYNAMIC_VALUE_DROPDOWN_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']";
    public static final String SPINNER_ICON ="xpath=//div[@class='oxd-loading-spinner']";
    public static final String DYNAMIC_SAVE_BUTTON_BY_LABEL ="xpath=//h6[text()='%s']/parent::div//button[contains(string(),'Save')]";
    public static final String SAVE_BUTTON="xpath=//button[@type='submit']";
    public static final String UPLOAD_FILE_TYPE="xpath=//input[@type='file']";
    public static final String DYNAMIC_SUCCESS_MESSAGE="xpath=//p[contains(@class,'oxd-text--toast-message') and text()='%s']";
    public static final String TEXTAREA="xpath=//textarea";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME="xpath=//div[text()='%s']/preceding-sibling::div";
    public static final String DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX="xpath=//div[@class='oxd-table-card']/div[@role='row'][%s]/div[%s]/div[text()='%s']";
    public static final String DYNAMIC_EDIT_ICON_BY_COLUMN_INDEX_ROW_INDEX ="xpath=//div[@class='oxd-table-card'][%s]//div[%s]//button[2]";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE ="xpath=//th[contains(string(),'%s')]//preceding-sibling::th";
    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX_IN_TABLE ="xpath=//tr[%s]/td[%s]//input";
    public static final String DYNAMIC_DROPDOWN_PARENT_BY_LABEL_IN_TABLE="xpath=//tr[%s]/td[%s]//i";
    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL_IN_TABlE="xpath=//tr[%s]/td[%s]//preceding-sibling::div//div//span";
    public static final String DYNAMIC_COMBOBOX_CHILDREN_ITEM_BY_LABEL_IN_TABlE="xpath=//tr[%s]/td[%s]//preceding-sibling::div//div//span[text()='%s']";
    public static final String DYNAMIC_OPTION_COBOBOX="xpath=//div[@role='option']//span[text()='%s']";
    public static final String CELL_VALUE_IN_TABLE ="xpath=//tr[%s]/td[%s]";
    public static final String MESSAGE_ERROR="css=.oxd-input-field-error-message";
    public static final String DYNAMIC_ERROR_MESSAGE_BY_LABEL ="xpath=//label[contains(text(),'%s')]/parent::div//following-sibling::span[contains(.,'%s')]";
    public static final String DYNAMIC_ERROR_MESSAGE_BY_NAME ="xpath=//input[@name='%s']/parent::div/following-sibling::span[contains(.,'%s')]";
    public static final String DYNAMIC_BUTTON="xpath=//button[contains(string(),'%s')]";

    public static final String DEFAULT_AVATA="xpath=//img[@src='/orangehrm5/orangehrm5/web/images/default-photo.png']";
    public static final String AVATA_AFTER_UPLOAD="xpath=//img[@src='data:image/jpeg;base64,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']";

    public static final String CANCEL_BUTTON="xpath=//button[contains(.,'Cancel')]";
    public static final String BUTTON_BY_NAME="xpath=//button[text()='%s']";
    public static final String ADD_ROW_BUTTON="xpath=//i[@class='oxd-icon bi-plus']";
    // Dùng %d làm placeholder để format chỉ số
    public static final String EDIT_ICON_BY_INDEX = "xpath=(//i[@class='oxd-icon bi-pencil-fill'])[1]";
    public static final String DELETE_ICON_BY_INDEX="xpath=(//i[@class='oxd-icon bi-trash'])[1]";



}


