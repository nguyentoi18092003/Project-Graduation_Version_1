package pageObjects.pageObjectCommon.loginObjects;

import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import pageUIs.pageUIsCommon.loginUIs.LoginPageUI;

import java.util.Set;


public class LoginPageObject extends BaseElement {
    WebDriver driver;

    public LoginPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
    }
    public void login(String userName, String password){
        enterToTextboxByName(userName,"username");
        enterToTextboxByName(password,"password");
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
    }
    public String colorOfForgotPasswordLink(){
        return getElementCssValue(driver, LoginPageUI.FORGOT_PASSWORD_LINK,"color");
    }
    public String colorOfIconSocialPage(String socialPage){
        return getElementCssValue(driver, LoginPageUI.ICON_SOCIAL_PAGE,"color", socialPage);
    }
    public String colorOfTitlePage(){
        return getElementCssValue(driver, LoginPageUI.TITLE_LOGIN,"color");
    }
    public void clickToForgotPasswordLink(){
        waitForElementClickable(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
        clickToElement(driver, LoginPageUI.FORGOT_PASSWORD_LINK);
    }
    public Boolean resetPasswordDisplay(){
        return isElementDisplayed(driver,LoginPageUI.RESET_PASSWORD);
    }
    public void clickToOrangeSocialPage(String namePage){
        waitForElementClickable(driver,LoginPageUI.ICON_SOCIAL_PAGE, namePage);
        clickToElement(driver,LoginPageUI.ICON_SOCIAL_PAGE,namePage);
    }
    public boolean isLinkedPageDisplay(){
        return isElementDisplayed(driver, LoginPageUI.LOGO_LINKED_PAGE);
    }
    public void closePopupInFacebookPage(){
        waitForElementClickable(driver,LoginPageUI.POPUP);
        clickToElement(driver,LoginPageUI.POPUP);
    }
    public boolean isFacebookPageDisplay(){
        waitForElementVisible(driver, LoginPageUI.TITLE_FACEBOOK_PAGE);
        return isElementDisplayed(driver, LoginPageUI.TITLE_FACEBOOK_PAGE);
    }
    public void closePopupInLinkedinPage(){
        waitForElementClickable(driver, LoginPageUI.PopUpInLinkedinPage);
        clickToElement(driver, LoginPageUI.PopUpInLinkedinPage);
    }
    public boolean isTiwsterPageDisplay(){
        return isElementDisplayed(driver, LoginPageUI.TITLE_TWISTER_PAGE);
    }
    public boolean isYoutubePageDisplay(){
        return isElementDisplayed(driver, LoginPageUI.TITLE_YOUTUBE_PAGE);
    }
    public void swithTab(String titlePage){
        switchToWindowByTilte(driver,titlePage);
    }

    public String getOrangeIDPage(){
        Set<String> ids = driver.getWindowHandles();
        String firstId = ids.iterator().next();  // Lấy phần tử đầu tiên
        return firstId;
    }
    public void closeTabExOrangePage(String parentID){
        closeAllWindowWithoutParent(driver,parentID);
    }
    public boolean isDisplayLoginFail(){
        return isElementDisplayed(driver,LoginPageUI.LOGIN_FAIL_MESSAGAE);
    }
    public boolean isDisplayLoginFailTerminated(){
        return isElementDisplayed(driver,LoginPageUI.LOGIN_TERMINATED_MESSAGAE);
    }


}
