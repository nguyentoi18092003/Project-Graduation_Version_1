package pageObjects.pageObjectsAdmin.leaveObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuLeavePageObject;
import pageUIs.pageUIsAdmin.leaveUIs.LeaveListPageUI;
import pageUIs.pageUIsClient.MyLeaveListPageUIC;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class LeaveListPageObject extends SubMenuLeavePageObject {
    WebDriver driver;

    public LeaveListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }
    public void clickButtonByName(String nameButton){
        waitForElementClickable(driver, LeaveListPageUI.BUTTON_BY_NAME,nameButton);
        clickToElement(driver, LeaveListPageUI.BUTTON_BY_NAME,nameButton);
    }
    public void clickButtonByJSAndName(String nameButton){
        waitForElementClickable(driver, LeaveListPageUI.BUTTON_BY_NAME,nameButton);
        clickToElementByJS(driver, LeaveListPageUI.BUTTON_BY_NAME,nameButton);
    }
    public void clickIconByName(String nameButton){
        waitForElementClickable(driver, LeaveListPageUI.ICON_BY_NAME,nameButton);
        clickToElement(driver, LeaveListPageUI.ICON_BY_NAME,nameButton);
    }
    public String getValueInTextboxInTable(String columnName, String rowIndex){
        int columnIndex = getListElementSize(driver, LeaveListPageUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName)+1;
        return getElementText(driver,LeaveListPageUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
    }
    public void clickLabel(String label) {
        waitForElementClickable(driver, LeaveListPageUI.DYNAMIC_LABEL, label);
        clickToElement(driver, LeaveListPageUI.DYNAMIC_LABEL, label);
    }
    public void selectDayInCalender(String day){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
        clickToElement(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
    }
    public void searchAndSelectInComboboxByLabel(String searchValue, String itemToSelect, String label) {
        enterToTextboxByLabel(searchValue,label);
        waitForElementVisible(driver,LeaveListPageUI.DROPDOWN_ITEM ,itemToSelect);
        clickToElement(driver, LeaveListPageUI.DROPDOWN_ITEM ,itemToSelect );
    }
    public void disableAutoFocus() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "HTMLElement.prototype.scrollIntoView = function() {};"
        );
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
    public List<String> getListValueShowLeaveWithStatusFromDB() {
        List<String> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql = """
                SELECT ls.name 
                FROM ohrm_leave_status ls
                ORDER BY ls.id;
                """;

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
    public void clickToDropList(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        clickToElement(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
    }
    public void clearAllSelectedOptionsInCombobox(String label) {
        waitForElementVisible(driver, MyLeaveListPageUIC.DYNAMIC_COMBOBOX_CONTAINER_BY_LABEL, label);

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
                    System.out.println("Lỗi khi click icon thứ " + i + " trong combobox " + label + ": " + e.getMessage());
                }
            }
        }
    }
    public void selectToDropdownByLabel(String label, String itemTextExpected ) {
        waitForElementClickable(driver, LeaveListPageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        selectItemDropdown(driver, LeaveListPageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, LeaveListPageUI.DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL, itemTextExpected, label);
    }

    public void selectMultiDropdownByLabel(String label, String... values) {
        clearAllSelectedOptionsInCombobox(label);
        for (String value : values) {
            waitForElementClickable(driver, LeaveListPageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
            selectItemDropdown(driver, LeaveListPageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, LeaveListPageUI.DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL, value, label);
        }
    }

    public String getDisplayedTagsInCombobox(String labelName) {
        waitForElementVisible(driver, LeaveListPageUI.DYNAMIC_SELECTED_TAG_TEXT_BY_LABEL, labelName);

        List<WebElement> tagElements = getListWebElement(driver, LeaveListPageUI.DYNAMIC_SELECTED_TAG_TEXT_BY_LABEL, labelName);
        List<String> tagTexts = new ArrayList<>();

        for (WebElement tag : tagElements) {
            String text = tag.getText().replace("✖", "").trim();
            if (!text.isEmpty()) {
                tagTexts.add(text);
            }
        }

        return String.join(",", tagTexts);
    }

    public List<String> getEmployeeNamesFromDB(String keyword) {
        List<String> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT TRIM(REPLACE(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname), '  ', ' ')) AS emp_full_name " +
                            "FROM hs_hr_employee e " +
                            "HAVING emp_full_name LIKE CONCAT('%', TRIM(?), '%') " +
                            "ORDER BY emp_full_name " +
                            "LIMIT 5;";

            pstm = conn.prepareStatement(sql);

            // Trim keyword BEFORE binding → xử lý khoảng cách đầu/cuối
            pstm.setString(1, keyword.trim());

            rs = pstm.executeQuery();

            while (rs.next()) {
                String fullName = rs.getString("emp_full_name");
                if (fullName != null && !fullName.isEmpty()) {
                    records.add(fullName.trim());
                }
            }

        } catch (Exception e) {
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

        System.out.println("DB Employee list  = " + records);
        return records;
    }

    public List<String> getSuggestionValuesInCombobox(String label) {
        List<WebElement> options = getListWebElement(driver, LeaveListPageUI.AUTOCOMPLETE_SUGGESTION_OPTIONS);

        List<String> uiValues = new ArrayList<>();
        for (WebElement option : options) {
            String text = option.getText().trim();
            if (!text.isEmpty() && !text.equalsIgnoreCase("No Records Found")) {
                uiValues.add(text);
            }
        }
        System.out.println("UI Suggestion List: " + uiValues);
        return uiValues;
    }

//    public boolean isCheckboxChecked(WebElement element) {
//        return isElementSelected(driver,LeaveListPageUI.ROW_CHECKBOXES);
//    }
//
//    public boolean areAllRowCheckboxesChecked() {
//        List<WebElement> checkboxes = getListWebElement(driver, LeaveListPageUI.ROW_CHECKBOXES);
//
//        for (WebElement checkbox : checkboxes) {
//            if (!isCheckboxChecked(checkbox)) {
//                return false;
//            }
//        }
//        return true;
//    }

    public void clickCheckAll(){
        WebElement cb = getWebElement(driver,LeaveListPageUI.CHECKBOX_ALL);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb);
    }

    private List<WebElement> getRowCheckboxes() {
        List<WebElement> allCbs = getListWebElement(driver, LeaveListPageUI.CHECKBOX);
        if (allCbs.size() <= 1) {
            return new ArrayList<>();
        }
        return allCbs.subList(2, allCbs.size());
    }

    public boolean areAllRowCheckboxesChecked() {
        List<WebElement> rowCheckboxes = getRowCheckboxes();
        for (int i = 0; i < rowCheckboxes.size(); i++) {
            Boolean checked = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checked;", rowCheckboxes.get(i));
            if (!checked) {
                return false;
            }
        }
        return true;
    }


    public void checkAllRowCheckboxesIndividually() {
        List<WebElement> allCheckboxes = getRowCheckboxes();

        for (int i = 0;i < allCheckboxes.size(); i++) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allCheckboxes.get(i));
            sleepInSecond(10);
        }
    }

    public boolean isCheckboxCheckedByIndex(int index) {
        WebElement cb = getWebElement(driver,LeaveListPageUI.DYNAMIC_CHECKBOX_BY_INDEX, String.valueOf(index));
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checked;", cb);
    }

    public boolean isCheckAllChecked() {
        WebElement icon = getWebElement(driver, LeaveListPageUI.CHECKBOX_ALL);
        String iconClass = icon.getAttribute("class");
        return iconClass.contains("bi-check");
    }

    public void clickRowCheckboxByIndex(int index) {
        WebElement cb = getWebElement(driver,LeaveListPageUI.DYNAMIC_CHECKBOX_BY_INDEX, String.valueOf(index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb);
    }

    public int getTotalRecordOnUI() {
        String text = getElementText(driver, LeaveListPageUI.TOTAL_RECORD_FOUND_TEXT);
        if (text.equalsIgnoreCase("No Records Found")) {
            return 0;
        }
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }

    public List<Map<String, String>> getLeaveListByDateRange(String fromDate, String toDate) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql = "SELECT lr.date_applied, " +
                    "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS fullname, " +
                    "lt.name AS leave_type, l.length_days, ls.name AS status_name " +
                    "FROM ohrm_leave_request lr " +
                    "JOIN ohrm_leave l ON l.leave_request_id = lr.id " +
                    "JOIN hs_hr_employee e ON e.emp_number = lr.emp_number " +
                    "JOIN ohrm_leave_type lt ON lt.id = lr.leave_type_id " +
                    "JOIN ohrm_leave_status ls ON ls.status = l.status " +
                    "WHERE lr.date_applied BETWEEN ? AND ? AND l.status = 1 " +
                    "ORDER BY lr.date_applied DESC";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, fromDate);
            pstm.setString(2, toDate);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("date_applied", rs.getString("date_applied"));
                row.put("fullname", rs.getString("fullname"));
                row.put("leave_type", rs.getString("leave_type"));
                row.put("length_days", rs.getString("length_days"));
                row.put("status_name", rs.getString("status_name"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getLeaveListByEmployeeName(String employeeName) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT lr.date_applied, " +
                            "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS fullname, " +
                            "lt.name AS leave_type, l.length_days, ls.name AS status_name " +
                            "FROM ohrm_leave_request lr " +
                            "JOIN ohrm_leave l ON l.leave_request_id = lr.id " +
                            "JOIN hs_hr_employee e ON e.emp_number = lr.emp_number " +
                            "JOIN ohrm_leave_type lt ON lt.id = lr.leave_type_id " +
                            "JOIN ohrm_leave_status ls ON ls.status = l.status " +
                            "WHERE lr.date_applied BETWEEN '2025-01-01' AND '2025-12-31' " +
                            "AND l.status = 1 " +
                            "HAVING fullname LIKE ? " +
                            "ORDER BY lr.date_applied DESC";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + employeeName + "%");

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("date_applied", rs.getString("date_applied"));
                row.put("fullname", rs.getString("fullname"));
                row.put("leave_type", rs.getString("leave_type"));
                row.put("length_days", rs.getString("length_days"));
                row.put("status_name", rs.getString("status_name"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getLeaveListByLeaveType(String leaveType) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT lr.date_applied, " +
                            "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS fullname, " +
                            "lt.name AS leave_type, l.length_days, ls.name AS status_name " +
                            "FROM ohrm_leave_request lr " +
                            "JOIN ohrm_leave l ON l.leave_request_id = lr.id " +
                            "JOIN hs_hr_employee e ON e.emp_number = lr.emp_number " +
                            "JOIN ohrm_leave_type lt ON lt.id = lr.leave_type_id " +
                            "JOIN ohrm_leave_status ls ON ls.status = l.status " +
                            "WHERE l.status = 1 ";

            if (!leaveType.equalsIgnoreCase("-- Select --")) {
                sql += " HAVING leave_type LIKE ? ";
            }

            sql += " ORDER BY lr.date_applied DESC";
            pstm = conn.prepareStatement(sql);

            if (!leaveType.equalsIgnoreCase("-- Select --")) {
                pstm.setString(1, "%" + leaveType + "%");
            }

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("date_applied", rs.getString("date_applied"));
                row.put("fullname", rs.getString("fullname"));
                row.put("leave_type", rs.getString("leave_type"));
                row.put("length_days", rs.getString("length_days"));
                row.put("status_name", rs.getString("status_name"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getLeaveListByEmployeeAndLeaveType(String employeeName, String leaveType) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT lr.date_applied, " +
                            "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS fullname, " +
                            "lt.name AS leave_type, l.length_days, ls.name AS status_name " +
                            "FROM ohrm_leave_request lr " +
                            "JOIN ohrm_leave l ON l.leave_request_id = lr.id " +
                            "JOIN hs_hr_employee e ON e.emp_number = lr.emp_number " +
                            "JOIN ohrm_leave_type lt ON lt.id = lr.leave_type_id " +
                            "JOIN ohrm_leave_status ls ON ls.status = l.status " +
                            "WHERE l.status = 1 " +
                            "  AND lr.date_applied BETWEEN '2025-01-01' AND '2025-12-31' " +   // ngày mặc định
                            "  AND lt.name LIKE ? " +
                            "HAVING fullname LIKE ? " +
                            "ORDER BY lr.date_applied DESC";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + leaveType + "%");
            pstm.setString(2, "%" + employeeName + "%");

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("date_applied", rs.getString("date_applied"));
                row.put("fullname", rs.getString("fullname"));
                row.put("leave_type", rs.getString("leave_type"));
                row.put("length_days", rs.getString("length_days"));
                row.put("status_name", rs.getString("status_name"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getLeaveListByStatuses(String... statuses) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            // Tạo placeholders ?,?,?
            StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < statuses.length; i++) {
                placeholders.append("?,");
            }
            placeholders.deleteCharAt(placeholders.length() - 1);
            String sql =
                    "SELECT lr.date_applied, " +
                            "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS fullname, " +
                            "lt.name AS leave_type, " +
                            "l.length_days, " +
                            "ls.name AS status_name " +
                            "FROM ohrm_leave_request lr " +
                            "JOIN ohrm_leave l ON l.leave_request_id = lr.id " +
                            "JOIN hs_hr_employee e ON e.emp_number = lr.emp_number " +
                            "JOIN ohrm_leave_type lt ON lt.id = lr.leave_type_id " +
                            "JOIN ohrm_leave_status ls ON ls.status = l.status " +
                            "WHERE lr.date_applied BETWEEN '2025-01-01' AND '2025-12-31' " +
                            "  AND ls.name IN (" + placeholders + ") " +
                            "ORDER BY lr.date_applied DESC";

            pstm = conn.prepareStatement(sql);

            for (int i = 0; i < statuses.length; i++) {
                pstm.setString(i + 1, statuses[i]);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("date_applied", rs.getString("date_applied"));
                row.put("fullname", rs.getString("fullname"));
                row.put("leave_type", rs.getString("leave_type"));
                row.put("length_days", rs.getString("length_days"));
                row.put("status_name", rs.getString("status_name"));
                records.add(row);
            }

        } catch (Exception e) {
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


}
