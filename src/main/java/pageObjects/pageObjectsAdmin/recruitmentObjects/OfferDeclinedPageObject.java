package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.OfferDeclinedPageUI;

public class OfferDeclinedPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public OfferDeclinedPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String getApplicationStatus() {
        waitForElementVisible(driver, OfferDeclinedPageUI.APPLICATION_STATUS);
        String text = getElementText(driver, OfferDeclinedPageUI.APPLICATION_STATUS);
        return text.replace("Status:", "").trim();
    }
}