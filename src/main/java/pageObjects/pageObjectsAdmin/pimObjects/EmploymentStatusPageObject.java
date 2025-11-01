package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;

public class EmploymentStatusPageObject extends SubMenuAdminPageObject {
    WebDriver driver;
    public EmploymentStatusPageObject (WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
