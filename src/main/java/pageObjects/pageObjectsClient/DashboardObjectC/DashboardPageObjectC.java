package pageObjects.pageObjectsClient.DashboardObjectC;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;

public class DashboardPageObjectC extends SidebarPageObject {
    WebDriver driver;
    public DashboardPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
}
