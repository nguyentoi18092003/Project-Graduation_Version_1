package pageObjects.pageObjectsClient.LeaveObjectC;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuLeavePageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.AddCandidatePageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.ScheduleInterviewPageUI;
import pageUIs.pageUIsClient.ApplyLeavePageUIC;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ApplyLeavePageObjectC extends SubMenuLeavePageObject {
    WebDriver driver;

    public ApplyLeavePageObjectC(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public void clickButtonByJSAndName(String nameButton){
        waitForElementClickable(driver, ApplyLeavePageUIC.BUTTON_BY_NAME,nameButton);
        clickToElementByJS(driver, ApplyLeavePageUIC.BUTTON_BY_NAME,nameButton);
    }

    public Map<String, String> leaveFromDB(String employeeName, String fromDate, String toDate) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql = """
                    SELECT
                        lr.date_applied,
                        rc.comments AS leave_comments,
                        lt.name AS leave_type_name,
                        ls.name AS leave_status_name,
                        TRIM(CONCAT(e.emp_firstname, ' ', IFNULL(e.emp_middle_name, ''), ' ', e.emp_lastname)) AS emp_full_name
                    FROM ohrm_leave_request lr
                    JOIN ohrm_leave l ON lr.id = l.leave_request_id
                    JOIN ohrm_leave_type lt ON l.leave_type_id = lt.id
                    JOIN ohrm_leave_status ls ON l.status = ls.status
                    JOIN hs_hr_employee e ON lr.emp_number = e.emp_number
                    LEFT JOIN ohrm_leave_request_comment rc ON lr.id = rc.leave_request_id
                    WHERE TRIM(CONCAT(e.emp_firstname,' ',IFNULL(e.emp_middle_name,''),' ',e.emp_lastname)) = ?
                      AND DATE(l.date) BETWEEN ? AND ?
                    ORDER BY lr.id DESC
                    LIMIT 1;   
                """;

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, employeeName);
            pstm.setString(2, fromDate);
            pstm.setString(3, toDate);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
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
    public void clickLabel(String label) {
        waitForElementClickable(driver, ApplyLeavePageUIC.DYNAMIC_LABEL, label);
        clickToElement(driver, ApplyLeavePageUIC.DYNAMIC_LABEL, label);
    }
    public void rollbackApplyLeave(String email, String dateApplied) {
        try (Connection conn = MySQLConnUtils.getMySQLConnection()) {

            Integer requestId = null;
            String findRequestId = """
                    SELECT lr.id
                    FROM ohrm_leave_request lr
                    JOIN hs_hr_employee e ON lr.emp_number = e.emp_number
                    WHERE LOWER(TRIM(e.emp_work_email)) = LOWER(TRIM(?))
                      AND DATE(lr.date_applied) = DATE(?)
                    ORDER BY lr.id DESC
                    LIMIT 1;
                """;
            try (PreparedStatement ps = conn.prepareStatement(findRequestId)) {
                ps.setString(1, email);
                ps.setString(2, dateApplied);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    requestId = rs.getInt("id");
                }
            }

            if (requestId == null) {
                System.out.println("No leave request found for " + email + " on " + dateApplied);
                return;
            }

            String deleteComments = """
            DELETE FROM ohrm_leave_request_comment
            WHERE leave_request_id = ?;
        """;
            try (PreparedStatement ps = conn.prepareStatement(deleteComments)) {
                ps.setInt(1, requestId);
                ps.executeUpdate();
            }

            String deleteLeaveDetails = """
            DELETE FROM ohrm_leave
            WHERE leave_request_id = ?;
        """;
            try (PreparedStatement ps = conn.prepareStatement(deleteLeaveDetails)) {
                ps.setInt(1, requestId);
                ps.executeUpdate();
            }

            String deleteLeaveRequest = """
            DELETE FROM ohrm_leave_request
            WHERE id = ?;
        """;
            try (PreparedStatement ps = conn.prepareStatement(deleteLeaveRequest)) {
                ps.setInt(1, requestId);
                ps.executeUpdate();
            }

            System.out.println("Rollback Apply Leave done for " + email + " (" + dateApplied + ")");

        } catch (SQLException e) {
            System.err.println("Rollback Apply Leave failed: " + e.getMessage());
        }
    }
    public void clickCanlanderIconByLabel(String label){
        waitForElementClickable(driver,ApplyLeavePageUIC.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
        clickToElement(driver,ApplyLeavePageUIC.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
    }
    public void selectDayInCalender(String day){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
        clickToElement(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
    }
    public List<String> getListValueLeaveTypeFromDB() {
        List<String> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            // Lấy tất cả loại nghỉ đang active
            String sql = "SELECT lt.name FROM ohrm_leave_type lt WHERE lt.is_deleted = 0 ORDER BY lt.name;";
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
    public double getLeaveBalanceFromDB(String email, String leaveType) {
        double balance = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String sql = """
                    SELECT 
                        ROUND(SUM(le.no_of_days) - IFNULL(SUM(l.length_days), 0), 2) AS remaining_days
                    FROM ohrm_leave_entitlement le
                    JOIN ohrm_leave_type lt ON lt.id = le.leave_type_id
                    LEFT JOIN hs_hr_employee e ON e.emp_number = le.emp_number
                    LEFT JOIN ohrm_leave l 
                        ON l.leave_type_id = lt.id
                        AND l.emp_number = le.emp_number
                        AND l.status IN (1, 2, 3)  -- Pending Approval, Scheduled, Taken
                    WHERE LOWER(TRIM(e.emp_work_email)) = LOWER(TRIM(?))
                      AND lt.name = ?
                      AND lt.is_deleted = 0
                    GROUP BY lt.name;
                """;

            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, leaveType);

            rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("remaining_days");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return balance;
    }
    public double getDisplayedLeaveBalanceValue() {
        waitForElementVisible(driver, ApplyLeavePageUIC.LEAVE_BALANCE_TEXT);
        String balanceText = getElementText(driver, ApplyLeavePageUIC.LEAVE_BALANCE_TEXT).trim();
        String numericPart = balanceText.split(" ")[0];
        return Double.parseDouble(numericPart);
    }
    public void disableAutoFocus() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "HTMLElement.prototype.scrollIntoView = function() {};"
        );
    }
    public void clickTimeButton(String label, String type) {
        String locator;
        switch (type.toLowerCase()) {
            case "hour-up":
                locator = ApplyLeavePageUIC.HOUR_UP_BUTTON_BY_LABEL;
                break;
            case "hour-down":
                locator = ApplyLeavePageUIC.HOUR_DOWN_BUTTON_BY_LABEL;
                break;
            case "minute-up":
                locator = ApplyLeavePageUIC.MINUTE_UP_BUTTON_BY_LABEL;
                break;
            case "minute-down":
                locator = ApplyLeavePageUIC.MINUTE_DOWN_BUTTON_BY_LABEL;
                break;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
        waitForElementClickable(driver, locator, label);
        clickToElementByJS(driver, locator, label);
    }
    public void openTimePickerByLabel(String label) {
        waitForElementClickable(driver, ApplyLeavePageUIC.TIME_ICON_BY_LABEL, label);
        clickToElementByJS(driver, ApplyLeavePageUIC.TIME_ICON_BY_LABEL, label);
        sleepInSeconds(1);
    }


    public String getTimeValueByLabel(String label) {
        waitForElementVisible(driver, ApplyLeavePageUIC.TIME_INPUT_BY_LABEL, label);
        return getElementAttribute(driver, ApplyLeavePageUIC.TIME_INPUT_BY_LABEL, "value", label);
    }
    public void enterTimeByLabel(String label, String timeValue, boolean triggerReact) {
        waitForElementVisible(driver, ApplyLeavePageUIC.TIME_INPUT_BY_LABEL, label);

        WebElement timeInput = getWebElement(driver, getDynamicLocator(ApplyLeavePageUIC.TIME_INPUT_BY_LABEL, label));
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
    public String getValueInTextBoxByName(String name){
        waitForElementVisible(driver, ApplyLeavePageUIC.DYNAMIC_TEXTAREA,name);
        return getElementAttribute(driver, ApplyLeavePageUIC.DYNAMIC_TEXTAREA, "value", name);
    }
    public boolean isOverlapPopupDisplayed() {
        return isElementDisplayed(driver, ApplyLeavePageUIC.OVERLAP_TITLE);
    }
}
