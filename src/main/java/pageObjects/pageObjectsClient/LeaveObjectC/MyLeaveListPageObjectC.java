package pageObjects.pageObjectsClient.LeaveObjectC;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuLeavePageObject;
import pageUIs.pageUIsAdmin.leaveUIs.LeaveListPageUI;
import pageUIs.pageUIsClient.MyLeaveListPageUIC;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;

import java.util.List;

public class MyLeaveListPageObjectC  extends SubMenuLeavePageObject {
    WebDriver driver;

    public MyLeaveListPageObjectC(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public void clearAllSelectedOptionsInCombobox(String label) {
        waitForElementVisible(driver, MyLeaveListPageUIC.DYNAMIC_COMBOBOX_CONTAINER_BY_LABEL, label);
        waitForSpinnerIconInvisible();

        List<WebElement> removeIcons = getListWebElement(driver,
                MyLeaveListPageUIC.DYNAMIC_SELECTED_OPTION_CLOSE_ICON_BY_LABEL, label);

        if (removeIcons.isEmpty()) {
            return;
        }

        // Click từng icon, re-query lại list mỗi lần để tránh stale element
        for (int i = 0; i < removeIcons.size(); i++) {
            List<WebElement> currentIcons = getListWebElement(driver,
                    MyLeaveListPageUIC.DYNAMIC_SELECTED_OPTION_CLOSE_ICON_BY_LABEL, label);

            if (currentIcons.size() > 0) {
                try {
                    clickToElementByJS(driver, currentIcons.get(0)); // click icon đầu tiên
                    sleepInSecond(5);
                } catch (Exception e) {
                    System.out.println("Loi khi click phan tu thu " + i + " trong combobox " + label + ": " + e.getMessage());
                }
            }
        }
        waitForSpinnerIconInvisible();
    }

    public void disableAutoFocus() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "HTMLElement.prototype.scrollIntoView = function() {};"
        );
    }

    public void selectToDropdownByLabel(String label, String itemTextExpected ) {
        waitForElementClickable(driver, MyLeaveListPageUIC.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        selectItemDropdown(driver, MyLeaveListPageUIC.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, MyLeaveListPageUIC.DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL, itemTextExpected, label);
    }
    public String getValueInTextboxInTable(String columnName, String rowIndex){
        int columnIndex = getListElementSize(driver, MyLeaveListPageUIC.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName)+1;
        return getElementText(driver,MyLeaveListPageUIC.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
    }
    public void clickIconByName(String nameButton){
        waitForElementClickable(driver, MyLeaveListPageUIC.ICON_BY_NAME,nameButton);
        clickToElement(driver, MyLeaveListPageUIC.ICON_BY_NAME,nameButton);
    }

}