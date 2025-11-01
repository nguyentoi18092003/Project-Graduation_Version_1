package pageObjects.pageObjectsAdmin.timeObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;

public class EditTimesheetPageObject extends SubMenuTimePageObject {
    WebDriver driver;

    public EditTimesheetPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
}
