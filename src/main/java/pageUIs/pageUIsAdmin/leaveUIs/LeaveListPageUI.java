package pageUIs.pageUIsAdmin.leaveUIs;

public class LeaveListPageUI {
    public static final String ICON_BY_NAME = "xpath=//div[contains(@class,'oxd-table-row')][1]//div[contains(@class,'oxd-table-cell')]//button[text()='%s']";
    public static final String BUTTON_BY_NAME = "xpath=//button[contains(.,'%s')]";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME ="xpath=//div[contains(@class,'oxd-table-header-cell') and text()='%s']//preceding-sibling::div";
    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX = "xpath=//div[@class='oxd-table-body']/div[@class='oxd-table-card'][%s]//div[%s]/div";
    public static final String DYNAMIC_LABEL="xpath=//label[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_PARENT_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text--after']";
//    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL="xpath=//div[@role='option']/span";
    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL="xpath=//div[@role='option']";
    public static final String DYNAMIC_SELECTED_TAG_TEXT_BY_LABEL = "xpath=//label[normalize-space(text())='%s']/parent::div/following-sibling::div//span[contains(@class,'oxd-chip')]";
    public static final String AUTOCOMPLETE_SUGGESTION_OPTIONS ="xpath=//div[contains(@class,'oxd-autocomplete-dropdown')]//div[@role='option']";
    public static final String DYNAMIC_CHECKBOX_BY_INDEX = "xpath=(//input[@type='checkbox'])[%s]";
    public static final String CHECKBOX="css=input[type='checkbox']";
    public static final String CHECKBOX_ALL="xpath=//div[@role='columnheader']//i[contains(@class,'oxd-checkbox-input-icon')]";
    public static final String TOTAL_RECORD_FOUND_TEXT = "xpath=//span[contains(string(),'Record')]";
    public static final String COMBOBOX_SEARCH_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//input";
    public static final String DROPDOWN_ITEM = "xpath=//div[@role='listbox']//span[text()='%s']";


}

