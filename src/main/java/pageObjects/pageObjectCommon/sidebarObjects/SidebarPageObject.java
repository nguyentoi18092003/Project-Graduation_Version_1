package pageObjects.pageObjectCommon.sidebarObjects;



import commons.BaseElement;
import org.openqa.selenium.WebDriver;
import pageUIs.pageUIsCommon.sideBarUIs.SidebarPageUI;


public class SidebarPageObject extends BaseElement {
    WebDriver driver;
    public SidebarPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToSidebarLinkByText(String name){
        waitForElementClickable(driver, SidebarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME,name);
        clickToElementByJS(driver,SidebarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME,name);

    }
    public void clickToSidebarLinkByTextAndJS(String name){
        waitForElementClickable(driver, SidebarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME,name);
        clickToElementByJS(driver,SidebarPageUI.DYNAMIC_SIDEBAR_LINK_BY_NAME,name);

    }

}
