package pageObjects.pageObjectsClient.MyInfoObjectsC;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsAdmin.pimUIs.EmployeeListMenuPageUI;

public class ImmigrationPageObjectC extends SidebarPageObject {
    WebDriver driver;
    public ImmigrationPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToLinkByName(String linkName){
        waitForElementClickable(driver, EmployeeListMenuPageUI.DYNAAMIC_LINK_TEXT_BY_NAME,linkName);
        clickToElement(driver,EmployeeListMenuPageUI.DYNAAMIC_LINK_TEXT_BY_NAME,linkName);
    }
}
