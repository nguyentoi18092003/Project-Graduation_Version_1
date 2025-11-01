package pageObjects.pageObjectsClient.timeObject;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;
import pageUIs.pageUIsClient.PunchInPageUIC;
import pageUIs.pageUIsClient.PunchOutPageUIC;

public class PunchOutPageObjectC extends SubMenuTimePageObject {
    WebDriver driver;

    public PunchOutPageObjectC(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String getValueInCanlenderTextBoxByJS(){
        waitForElementVisible(driver, PunchOutPageUIC.DATE_CALENDAR);
        return getValueEventElementByJS(driver,PunchOutPageUIC.DATE_CALENDAR);
    }
    public String getValueTimeTextBoxByJS(){
        waitForElementVisible(driver, PunchOutPageUIC.TIME);
        return getValueEventElementByJS(driver,PunchOutPageUIC.TIME);
    }
}
