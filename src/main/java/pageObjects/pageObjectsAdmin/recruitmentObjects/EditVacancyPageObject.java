package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.EditVacancyPageUI;

public class EditVacancyPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public EditVacancyPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void searchAndSelectInComboboxByLabel( String valueToSearch, String valueToSelect, String label){
        enterToTextboxByLabel(valueToSearch,label);
        selectItemInCombobox(driver, EditVacancyPageUI.DYNAMIC_OPTION_COBOBOX,valueToSelect);
        enterToTextboxByLabel(valueToSearch,label);
        selectItemInCombobox(driver, EditVacancyPageUI.DYNAMIC_OPTION_COBOBOX,valueToSelect);
    }
}
