package pageObjects.pageObjectCommon.subMenuObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsCommon.subMenuUIs.SubMenuTimePageUI;
import pageUIs.pageUIsCommon.subMenuUIs.subMenuPIMPageUI;

public class SubMenuTimePageObject extends SidebarPageObject {
    WebDriver driver;
    public SubMenuTimePageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToItemSubMenuByName(String nameItemOfSubmenu){
        waitForElementClickable(driver, SubMenuTimePageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameItemOfSubmenu);
        clickToElement(driver, SubMenuTimePageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameItemOfSubmenu);
    }
    public void clickToSubMenuByName(String nameSubMenu){
        waitForElementClickable(driver, SubMenuTimePageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
        clickToElement(driver, SubMenuTimePageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
    }
}
