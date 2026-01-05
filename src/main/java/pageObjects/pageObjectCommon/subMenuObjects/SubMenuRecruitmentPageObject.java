package pageObjects.pageObjectCommon.subMenuObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsCommon.subMenuUIs.subMenuRecruitmentPageUI;

public class SubMenuRecruitmentPageObject extends SidebarPageObject {
    WebDriver driver;

    public SubMenuRecruitmentPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToItemSubMenuByName(String nameItemOfSubmenu) {
        waitForElementClickable(driver, subMenuRecruitmentPageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME, nameItemOfSubmenu);
        clickToElement(driver, subMenuRecruitmentPageUI.DYNAMIC_ITEM_SUBMENU_BY_NAME, nameItemOfSubmenu);
    }

    public void clickToSubMenuByName(String nameSubMenu) {
        waitForElementClickable(driver, subMenuRecruitmentPageUI.DYNAMIC_SUBMENU_BY_NAME, nameSubMenu);
        clickToElement(driver, subMenuRecruitmentPageUI.DYNAMIC_SUBMENU_BY_NAME, nameSubMenu);
    }
}
