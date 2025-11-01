package pageObjects.pageObjectsAdmin.timeObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;
import pageUIs.pageUIsCommon.subMenuUIs.subMenuPIMPageUI;

public class PunchOutPageObject extends SubMenuTimePageObject {
    WebDriver driver;
    public PunchOutPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToItemSubMenuByName(String nameSubMenu,String nameItemOfSubmenu){
        waitForElementClickable(driver, subMenuPIMPageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameSubMenu,nameItemOfSubmenu);
        clickToElement(driver, subMenuPIMPageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME,nameSubMenu,nameItemOfSubmenu);
    }
    public void clickToSubMenuByName(String nameSubMenu){
        waitForElementClickable(driver, subMenuPIMPageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
        clickToElement(driver, subMenuPIMPageUI.DYNAMIC_SUBMENU_BY_NAME,nameSubMenu);
    }
}
