package pageObjects.pageObjectsClient.timeObject;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;
import pageUIs.pageUIsClient.PunchInPageUIC;

public class PunchInPageObjectC extends SubMenuTimePageObject {
    WebDriver driver;
    public PunchInPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public String getValueInCanlenderTextBoxByJS(){
        waitForElementVisible(driver, PunchInPageUIC.DATE_CALENDAR);
        return getValueEventElementByJS(driver,PunchInPageUIC.DATE_CALENDAR);
    }
    public String getValueTimeTextBoxByJS(){
        waitForElementVisible(driver, PunchInPageUIC.TIME);
        return getValueEventElementByJS(driver,PunchInPageUIC.TIME);
    }
}
