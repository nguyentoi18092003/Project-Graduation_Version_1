package pageUIs.pageUIsAdmin.recruitmentUIs;

public class AddCandidatePageUI {
    public static final String APPLICATION_STATUS = "xpath=//p[contains(.,'Status:')]";
    public static final String DYNAMIC_DROPDOWN_PARENT_BY_LABEL="xpath=//label[text()='%s']/parent::div/following-sibling::div//i";
    public static final String DYNAMIC_DROPDOWN_CHILDREN_ITEM ="xpath=//div[@role='option']/span";
    public static final String HEADER= "xpath=//h6[text()='Add Candidate']";
    public static final String DYNAMIC_LABEL="xpath=//label[text()='Date of Application']";
    public static final String DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL = "xpath=//label[text()='Date of Application']/parent::div/following-sibling::div//i";

}
