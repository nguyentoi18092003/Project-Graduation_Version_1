package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuPIMPageObject;
import pageUIs.pageUIsAdmin.pimUIs.EmployeeListPageUI;

public class EmployeeListPageObject extends SubMenuPIMPageObject {
    WebDriver driver;

    public EmployeeListPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public void clickToAddButtton() {
        waitForElementClickable(driver, EmployeeListPageUI.ADD_BUTTON);
        clickToElement(driver,EmployeeListPageUI.ADD_BUTTON);
    }


}
