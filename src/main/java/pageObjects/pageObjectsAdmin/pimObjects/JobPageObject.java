package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.pageUIsAdmin.pimUIs.JobPageUI;

public class JobPageObject extends EmployeeListMenuPageObject {
    WebDriver driver;

    public JobPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }

    public void clickToContractDetailsButton() {
        waitForElementClickable(driver, JobPageUI.CONTRACT_DETAIL_BUTTON);
        clickToElement(driver,JobPageUI.CONTRACT_DETAIL_BUTTON);
    }
    public void clickToSaveButtonInPopup() {
        waitForElementClickable(driver, JobPageUI.SAVE_BUTTON_IN_POPUP);
        clickToElement(driver,JobPageUI.SAVE_BUTTON_IN_POPUP);
    }
}
