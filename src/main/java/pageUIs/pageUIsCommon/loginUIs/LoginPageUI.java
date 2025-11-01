package pageUIs.pageUIsCommon.loginUIs;

public class LoginPageUI {

    public static final String LOGIN_BUTTON="css=button[type='submit']";
    public static final String FORGOT_PASSWORD_LINK="xpath=//p[contains(.,'Forgot your password? ')]";
    public static final String ICON_SOCIAL_PAGE="xpath=//a[contains(@href,'%s')]//*[local-name()='svg']";
    public static final String TITLE_LOGIN="xpath=//h5[contains(@class,'orangehrm-login-title')]";
    public static final String RESET_PASSWORD="xpath=//h6[contains(@class,'orangehrm-forgot-password-title')]";
    public static final String LOGO_LINKED_PAGE="xpath=//icon[@data-test-id='nav-logo']";
    public static final String POPUP="xpath=//div[@aria-label='Close']";
    public static final String TITLE_FACEBOOK_PAGE="xpath=//*[@aria-label='Facebook']/*[name()='svg']/*[name()='path']";
    public static final String TITLE_TWISTER_PAGE="xpath=(//span[contains(.,'OrangeHRM')])[2]";
    public static final String TITLE_YOUTUBE_PAGE="xpath= (//a[@title='YouTube Home'])[1]";
    public static final String PopUpInLinkedinPage="xpath=//img/parent::div/parent::div/preceding-sibling::button";
    public static final String LOGIN_FAIL_MESSAGAE="xpath=//p[text()='Invalid credentials']";
    public static final String LOGIN_TERMINATED_MESSAGAE="xpath=//p[text()='Employee is terminated']";

}
