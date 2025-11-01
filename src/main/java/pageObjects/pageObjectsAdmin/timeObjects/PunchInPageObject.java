package pageObjects.pageObjectsAdmin.timeObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;

public class PunchInPageObject extends SubMenuTimePageObject {
    WebDriver driver;
    public PunchInPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
