package pageObjects.pageObjectsAdmin.timeObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;

public class EditPunchInOutPageObject extends SubMenuTimePageObject {
    WebDriver driver;
    public EditPunchInOutPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
}
