package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;

public class JobCategoriesPageObject extends SubMenuAdminPageObject {
    WebDriver driver;
    public JobCategoriesPageObject (WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}

