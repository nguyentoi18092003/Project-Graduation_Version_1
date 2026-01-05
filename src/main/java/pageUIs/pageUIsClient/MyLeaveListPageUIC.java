package pageUIs.pageUIsClient;

public class MyLeaveListPageUIC {
    public static final String ICON_BY_NAME = "xpath=//div[contains(@class,'oxd-table-row')][1]//div[contains(@class,'oxd-table-cell')]//button[text()='%s']";
    public static final String DYNAMIC_COMBOBOX_CONTAINER_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div";
    public static final String DYNAMIC_SELECTED_OPTION_CLOSE_ICON_BY_LABEL = "xpath=//label[text()='%s']/parent::div/following-sibling::div//i[contains(@class,'oxd-icon bi-x')]";
    public static final String DYNAMIC_DROPDOWN_PARENT_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//div[@class='oxd-select-text--after']";
    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL="xpath=//div[@role='option']/span";
//    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME ="xpath=//div[contains(@class,'oxd-table-header-cell') and text()='%s']//preceding-sibling::div";
//    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX = "xpath=//div[contains(@class,'oxd-table-card')]['%s']//div['%s']/div";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME ="xpath=//div[contains(@class,'oxd-table-header-cell') and text()='%s']//preceding-sibling::div";
    public static final String DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX = "xpath=//div[@class='oxd-table-body']/div[@class='oxd-table-card'][%s]//div[%s]/div";
}
