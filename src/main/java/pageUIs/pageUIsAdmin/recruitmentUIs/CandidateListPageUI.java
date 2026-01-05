package pageUIs.pageUIsAdmin.recruitmentUIs;

public class CandidateListPageUI {
    public static final String ADD_BUTTON = "xpath=//button[@type='button' and text()=' Add '] ";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME ="xpath=//div[contains(@class,'oxd-table-header-cell') and text()='%s']//preceding-sibling::div";
    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX = "xpath=//div[@class='oxd-table-body']/div[@class='oxd-table-card'][%s]//div[%s]/div";
    public static final String DYNAMIC_CANLENDER_TEXTBOX_BY_PLACEHOLDER = "xpath=//input[@placeholder='%s']";
    public static final String TOTAL_RECORD_FOUND_TEXT = "xpath=//span[contains(string(),'Record')]";
    public static final String DROPDOWN_ITEM = "xpath=//div[contains(@class,'oxd-autocomplete-option')]//span[text()='%s']";
    public static final String DYNAMIC_DROPDOWN_PARENT_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text--after']";
    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL="xpath=//div[@role='option']";


}
