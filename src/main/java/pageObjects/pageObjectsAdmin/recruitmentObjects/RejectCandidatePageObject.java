package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.AddCandidatePageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.RejectCandidatePageUI;

public class RejectCandidatePageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public RejectCandidatePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String getApplicationStatus() {
        waitForElementVisible(driver, RejectCandidatePageUI.APPLICATION_STATUS);
        String text = getElementText(driver, RejectCandidatePageUI.APPLICATION_STATUS);
        return text.replace("Status:", "").trim();
    }
}
