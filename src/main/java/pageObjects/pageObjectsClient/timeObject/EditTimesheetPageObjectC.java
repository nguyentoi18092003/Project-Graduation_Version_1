package pageObjects.pageObjectsClient.timeObject;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;

public class EditTimesheetPageObjectC extends SubMenuAdminPageObject {
    WebDriver driver;

    public EditTimesheetPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
}
