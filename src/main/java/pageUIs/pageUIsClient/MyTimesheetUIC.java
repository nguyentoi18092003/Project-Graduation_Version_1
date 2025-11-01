package pageUIs.pageUIsClient;

public class MyTimesheetUIC {
    public static final String STATUS="xpath=//p[text()='%s']";
    public static final String DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE="xpath=//div[@role='columnheader' and text()='%s']/preceding-sibling::div";
    public static final String CELL_VALUE_IN_TABLE="xpath=(//div[@role='row'])[%s]/div[%s]";
    public static final String ROW="xpath=//div[@role='row']";
}
