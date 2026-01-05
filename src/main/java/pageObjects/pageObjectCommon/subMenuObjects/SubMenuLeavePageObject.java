package pageObjects.pageObjectCommon.subMenuObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsCommon.subMenuUIs.SubMenuLeavePageUI;

public class SubMenuLeavePageObject extends SidebarPageObject {
    WebDriver driver;
    public SubMenuLeavePageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToItemSubMenuByName(String nameItemOfSubmenu){
        waitForElementClickable(driver, SubMenuLeavePageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameItemOfSubmenu);
        clickToElement(driver, SubMenuLeavePageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameItemOfSubmenu);
    }
    public void clickToSubMenuByName(String nameSubMenu){
        waitForElementClickable(driver, SubMenuLeavePageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
        clickToElement(driver, SubMenuLeavePageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
    }
}