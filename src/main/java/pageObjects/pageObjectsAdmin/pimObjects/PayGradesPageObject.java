package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;

public class PayGradesPageObject extends SubMenuAdminPageObject {
    WebDriver driver;
    public PayGradesPageObject (WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
