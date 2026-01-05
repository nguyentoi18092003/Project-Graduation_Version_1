package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.AddCandidatePageUI;
import pageUIs.pageUIsClient.ApplyLeavePageUIC;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class AddCandidatePageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public AddCandidatePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public Map<String, String> CandidateFromDB(String email) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql = """
                    SELECT 
                        c.first_name,
                        c.middle_name,
                        c.last_name,
                        c.email,
                        c.contact_number,
                        c.date_of_application,
                        a.file_name AS resume_file,
                        v.name AS vacancy_name,
                        cv.status AS application_status
                    FROM ohrm_job_candidate c
                    JOIN ohrm_job_candidate_vacancy cv ON c.id = cv.candidate_id
                    JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id
                    LEFT JOIN ohrm_job_candidate_attachment a ON a.candidate_id = c.id
                    WHERE LOWER(TRIM(c.email)) = LOWER(TRIM(?))
                    ORDER BY c.id DESC
                    LIMIT 1;
                """;

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String columnValue = rs.getString(i);
                    records.put(columnName, columnValue);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return records;
    }
    public String getApplicationStatus() {
        waitForElementVisible(driver, AddCandidatePageUI.APPLICATION_STATUS);
        String text = getElementText(driver, AddCandidatePageUI.APPLICATION_STATUS);
        return text.replace("Status:", "").trim();
    }

    public void selectToDropdownByLabel(String expectedItemText,String label) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement parent = getWebElement(driver, AddCandidatePageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", parent);

        new Actions(driver)
                .moveToElement(parent)
                .pause(Duration.ofMillis(100))
                .click()
                .build()
                .perform();

        By childBy = getByLocator(String.format(AddCandidatePageUI.DYNAMIC_DROPDOWN_CHILDREN_ITEM, label));
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

        boolean found = false;
        for (WebElement item : items) {
            if (item.isDisplayed() && item.getText().trim().equalsIgnoreCase(expectedItemText.trim())) {
                new Actions(driver)
                        .moveToElement(item)
                        .pause(Duration.ofMillis(100))
                        .click()
                        .build()
                        .perform();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Không tìm thấy item '" + expectedItemText + "' trong dropdown '" + label + "'");
        }

    }
    public String getEmailRadom() {
        Random rand = new Random();
        return "auto" + rand.nextInt(99999);
    }
    public void clickLabel(String label) {
        waitForElementClickable(driver, AddCandidatePageUI.DYNAMIC_LABEL, label);
        clickToElement(driver, AddCandidatePageUI.DYNAMIC_LABEL, label);
    }
    public void selectDayInCalender(String day){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
        clickToElement(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
    }

    public void clickCanlanderIconByLabel(String label){
        waitForElementClickable(driver, ApplyLeavePageUIC.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
        clickToElement(driver,ApplyLeavePageUIC.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
    }
    public List<String> getListValueVacancyFromDB() {
        List<String> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String sql = "SELECT v.name FROM ohrm_vacancy v WHERE v.is_active = 1 ORDER BY v.name;";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    if (columnValue != null && columnValue.length() > 0) {
                        records.add(columnValue.trim());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return records;
    }
    public List<String> getListActualValueDropList() {
        List<String> valueInDropList;
        valueInDropList = getListElementText(driver, BaseElementUI.DYNAMIC_VALUE_IN_DROPLIST);
        return valueInDropList;
    }
    public void clickToVacancyDropList(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        clickToElement(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
    }
}
