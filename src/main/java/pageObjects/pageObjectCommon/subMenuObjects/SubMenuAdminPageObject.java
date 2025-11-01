package pageObjects.pageObjectCommon.subMenuObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsCommon.subMenuUIs.SubMenuAdminPageUI;

public class SubMenuAdminPageObject extends SidebarPageObject {
    WebDriver driver;
    public SubMenuAdminPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToItemSubMenuByName(String nameSubMenu,String nameItemOfSubmenu){
        waitForElementClickable(driver, SubMenuAdminPageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameSubMenu,nameItemOfSubmenu);
        clickToElement(driver, SubMenuAdminPageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameSubMenu,nameItemOfSubmenu);
    }
    public void clickToSubMenuByName(String nameSubMenu){
        waitForElementClickable(driver, SubMenuAdminPageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
        clickToElement(driver, SubMenuAdminPageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
    }
}
