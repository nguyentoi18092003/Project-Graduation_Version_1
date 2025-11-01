package pageObjects.pageObjectsAdmin.adminObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;

public class AdminPageObject extends SubMenuAdminPageObject {
    WebDriver driver;
    public AdminPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
