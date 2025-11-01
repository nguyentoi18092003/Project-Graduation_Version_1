package pageObjects.pageObjectCommon.subMenuObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsCommon.subMenuUIs.subMenuPIMPageUI;

public class SubMenuPIMPageObject extends SidebarPageObject {
    WebDriver driver;
    public SubMenuPIMPageObject(WebDriver driver){
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
    public void clickToConfigurationSubmenu(){
        waitForElementClickable(driver, subMenuPIMPageUI.CONFIGURATION_SUBMENU);
        clickToElement(driver, subMenuPIMPageUI.CONFIGURATION_SUBMENU);
    }
    public void clickToItemConfigurationSubmenu(String name){
        waitForElementClickable(driver, subMenuPIMPageUI.ITEM_CONFIGURATION_SUBMENU_BY_NAME, name);
        clickToElement(driver, subMenuPIMPageUI.ITEM_CONFIGURATION_SUBMENU_BY_NAME,name);
    }
}
