package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;

public class JobTitlesPageObject extends SubMenuAdminPageObject {
    WebDriver driver;
    public JobTitlesPageObject (WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
