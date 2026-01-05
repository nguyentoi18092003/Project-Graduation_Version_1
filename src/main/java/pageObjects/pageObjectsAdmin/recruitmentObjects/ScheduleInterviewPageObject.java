package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.AddCandidatePageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.ScheduleInterviewPageUI;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.Date;

public class ScheduleInterviewPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public ScheduleInterviewPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
//    public String getApplicationStatus() {
//        waitForElementVisible(driver, ScheduleInterviewPageUI.APPLICATION_STATUS);
//        String text = getElementText(driver,  ScheduleInterviewPageUI.APPLICATION_STATUS);
//        return text.replace("Status:", "").trim();
//    }
    public Map<String, String> InterviewFromDB(String email) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String sql = """
                    SELECT 
                        i.interview_name,
                        i.interview_date,
                        i.interview_time,
                        CONCAT(e.emp_firstname, ' ', IFNULL(e.emp_middle_name,''), ' ', e.emp_lastname) AS interviewer_name
                    FROM ohrm_job_interview i
                    JOIN ohrm_job_candidate c ON i.candidate_id = c.id
                    LEFT JOIN ohrm_job_interview_interviewer ii ON i.id = ii.interview_id
                    LEFT JOIN hs_hr_employee e ON ii.interviewer_id = e.emp_number
                    WHERE c.email = ?
                    ORDER BY i.id DESC
                    LIMIT 1;
                """;

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    records.put(metaData.getColumnLabel(i), rs.getString(i));
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

//    public void enterTimeByLabel(String label, String timeValue) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement timeInput = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(getByLocator(String.format(ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, label)))
//        );
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", timeInput);
//        timeInput.sendKeys(Keys.CONTROL + "a");
//        timeInput.sendKeys(Keys.BACK_SPACE);
//        timeInput.sendKeys(timeValue);
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
//                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
//                timeInput
//        );
//        timeInput.sendKeys(Keys.TAB);
//    }
    public void rollbackScheduleInterview(String email) {
        try (Connection conn = MySQLConnUtils.getMySQLConnection()) {

            //Xóa người phỏng vấn (bảng con)
            String deleteInterviewers = """
            DELETE FROM ohrm_job_interview_interviewer
            WHERE interview_id IN (
                SELECT id FROM ohrm_job_interview
                WHERE candidate_id = (
                    SELECT id FROM ohrm_job_candidate WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))
                )
            );
        """;
            try (PreparedStatement ps = conn.prepareStatement(deleteInterviewers)) {
                ps.setString(1, email);
                ps.executeUpdate();
            }

            // Xóa lịch phỏng vấn
            String deleteInterview = """
            DELETE FROM ohrm_job_interview
            WHERE candidate_id = (
                SELECT id FROM ohrm_job_candidate WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))
            );
        """;
            try (PreparedStatement ps = conn.prepareStatement(deleteInterview)) {
                ps.setString(1, email);
                ps.executeUpdate();
            }

            // Cập nhật lại trạng thái về SHORTLISTED
            String updateStatus = """
            UPDATE ohrm_job_candidate_vacancy
            SET status = 'SHORTLISTED'
            WHERE candidate_id = (
                SELECT id FROM ohrm_job_candidate WHERE LOWER(TRIM(email)) = LOWER(TRIM(?))
            );
        """;
            try (PreparedStatement ps = conn.prepareStatement(updateStatus)) {
                ps.setString(1, email);
                ps.executeUpdate();
            }

            System.out.println("Rollback Schedule Interview done for: " + email);

        } catch (SQLException e) {
            System.err.println("Rollback Schedule Interview failed: " + e.getMessage());
        }
    }

    public Map<String, String> CandidateFromDB(String email) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql ="""
                    SELECT 
                        CONCAT(TRIM(c.first_name), ' ', TRIM(c.middle_name), ' ', TRIM(c.last_name)) AS full_name,
                        cv.status, v.name AS vacancy_name
                    FROM ohrm_job_candidate c
                    JOIN ohrm_job_candidate_vacancy cv ON c.id = cv.candidate_id
                    JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id
                    WHERE LOWER(TRIM(c.email)) = LOWER(TRIM(?))
                    ORDER BY c.id DESC LIMIT 1
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

    public void clickCanlanderIconByLabel(String label){
        waitForElementClickable(driver, ScheduleInterviewPageUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
        clickToElement(driver,ScheduleInterviewPageUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
    }
    public void clickLabel(String label) {
        waitForElementClickable(driver, ScheduleInterviewPageUI.DYNAMIC_LABEL, label);
        clickToElement(driver, ScheduleInterviewPageUI.DYNAMIC_LABEL, label);
    }
    public void selectDayInCalender(String day){
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
        clickToElement(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
    }
    public void openTimePickerByLabel(String label) {
        waitForElementClickable(driver, ScheduleInterviewPageUI.TIME_ICON_BY_LABEL, label);
        clickToElementByJS(driver, ScheduleInterviewPageUI.TIME_ICON_BY_LABEL, label);
        sleepInSeconds(1);
    }

//    public void enterTimeByLabel(String timeValue) {
//        waitForElementVisible(driver, ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL);
//        sendkeyToElement(driver, ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, timeValue);
//        sendKeyBoardToElement(driver, ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, Keys.TAB);
//    }

    public void clickTimeButton(String label, String type) {
        String locator;
        switch (type.toLowerCase()) {
            case "hour-up":
                locator = ScheduleInterviewPageUI.HOUR_UP_BUTTON_BY_LABEL;
                break;
            case "hour-down":
                locator = ScheduleInterviewPageUI.HOUR_DOWN_BUTTON_BY_LABEL;
                break;
            case "minute-up":
                locator = ScheduleInterviewPageUI.MINUTE_UP_BUTTON_BY_LABEL;
                break;
            case "minute-down":
                locator = ScheduleInterviewPageUI.MINUTE_DOWN_BUTTON_BY_LABEL;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        waitForElementClickable(driver, locator, label);
        clickToElementByJS(driver, locator, label);
    }

    public String getTimeValueByLabel(String label) {
        waitForElementVisible(driver, ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, label);
        return getElementAttribute(driver, ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, "value", label);
    }
    public void enterTimeByLabel(String label, String timeValue, boolean triggerReact) {
        waitForElementVisible(driver, ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, label);

        WebElement timeInput = getWebElement(driver, getDynamicLocator(ScheduleInterviewPageUI.TIME_INPUT_BY_LABEL, label));
        timeInput.sendKeys(Keys.CONTROL + "a");
        timeInput.sendKeys(Keys.BACK_SPACE);
        timeInput.sendKeys(timeValue);

        if (triggerReact) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                    timeInput
            );
        } else {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                    timeInput
            );
        }

        timeInput.sendKeys(Keys.TAB);
    }
    public String convertTo24HourFormat(String time12h) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = inputFormat.parse(time12h.trim());
            return outputFormat.format(date);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid time format: " + time12h);
        }
    }

}

