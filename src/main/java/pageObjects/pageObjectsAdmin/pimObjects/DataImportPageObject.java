package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.pageUIsAdmin.pimUIs.DataImportPageUI;

public class DataImportPageObject extends EmployeeListMenuPageObject{
    WebDriver driver;

    public DataImportPageObject (WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToUploadButton(){
        waitForElementClickable(driver,DataImportPageUI.UPLOAD_BUTTON);
        clickToElement(driver,DataImportPageUI.UPLOAD_BUTTON);
    }
    public void isPopUpSucessDisplay(){
        waitForElementInvisible(driver,DataImportPageUI.POP_UP_SUCCESS);
        isElementDisplayed(driver,DataImportPageUI.POP_UP_SUCCESS);
    }
}
